package com.aglayatech.mundo3.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import java.sql.SQLException;
import java.sql.Connection;
import javax.sql.DataSource;

import com.aglayatech.mundo3.configurations.ReportProperties;
import com.aglayatech.mundo3.error.exceptions.NoContentException;
import com.aglayatech.mundo3.error.exceptions.NotFoundException;
import com.aglayatech.mundo3.model.TipoFactura;
import com.aglayatech.mundo3.repository.ITipoFacturaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.aglayatech.mundo3.model.Factura;
import com.aglayatech.mundo3.repository.IFacturaRepository;
import com.aglayatech.mundo3.service.IFacturaService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
@RequiredArgsConstructor
@Slf4j
public class FacturaServiceImpl implements IFacturaService {

	private final IFacturaRepository repoFactura;
	private final ITipoFacturaRepository tipoFacturaRepository;
	protected final DataSource localDataSource;

	private final ReportProperties reportProperties;
	private final Environment environment;

	@Override
	public List<Factura> findAll() {
		return repoFactura.findAll(Sort.by(Direction.DESC, "fecha"));
	}

	@Override
	public Page<Factura> findAll(Pageable pageable) {
		return repoFactura.findAll(pageable);
	}

	@Override
	public Factura findFactura(Long idfactura) {
		return repoFactura.findById(idfactura).orElse(null);
	}

	@Override
	public Factura findFacturaBySerieAndComprobante(String serie, Long noComprobante) {
		String __method = new Object() {}.getClass().getEnclosingClass().getSimpleName() + "::" + new Object() {}.getClass().getEnclosingMethod().getName();
		log.debug("Enter {}", __method);

		try {
			Optional<Factura> factura = repoFactura.getFacturaBySerieAndNoFactura(serie, noComprobante);
			if(factura.isPresent()) {
				log.info("Retornando factura: {}", factura.get().getIdFactura());
				return factura.get();
			} else {
				log.warn("La factura con serie {} y número de factura {} no existe", serie, noComprobante);
				throw new NotFoundException("La factura no se encuentra registrada en la base de datos");
			}
		} catch (DataAccessException e) {
			log.error("Ha ocurrido un error a nivel de base de datos: {}", e.getMessage());
			throw new com.aglayatech.mundo3.error.exceptions.DataAccessException("Ha ocurrido un error a nivel de base de datos", e.getCause());
		} finally {
			log.debug("{} Exit", __method);
		}
	}

	@Override
	public Factura save(Factura factura) {
		String __method = new Object() {}.getClass().getEnclosingClass().getSimpleName() + "::" + new Object() {}.getClass().getEnclosingMethod().getName();
		log.debug("Enter {}", __method);

		try {
			return repoFactura.save(factura);
		} catch (DataAccessException e) {
			log.error("Ha ocurrido un error a nivel de base de datos: {}", e.getMessage());
			throw new com.aglayatech.mundo3.error.exceptions.DataAccessException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public TipoFactura findTipoFactura(Integer idTipoFactura) {
		return this.tipoFacturaRepository.findById(idTipoFactura).orElse(null);
	}

	@Override
	public Integer getMaxVentas() {
		return this.repoFactura.getMaxVentas();
	}

	@Override
	public List<Factura> facturasPorFecha(String iniDate, String endDate) {
		String __method = new Object() {}.getClass().getEnclosingClass().getSimpleName() + "::" + new Object() {}.getClass().getEnclosingMethod().getName();
		log.debug("Enter {}", __method);

		try {

			Date date1;
			Date date2;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			date1 = format.parse(iniDate);
			date2 = format.parse(endDate);
			List<Factura> facturas = repoFactura.findAllFacturas(date1, date2);

			if(!facturas.isEmpty()) {
				log.info("Devolviendo listado de Facturas en el rango de fechas: {} y {}", iniDate, endDate);
				return facturas;
			} else {
				log.warn("No existen facturas registradas en el rango de fechas comprendidas entre: {} y {}", iniDate, endDate);
				throw new NoContentException("No existen facturas registradas en el rango de fechas comprendidas entre: " + iniDate + " y " + endDate);
			}
		} catch (DataAccessException e) {
			log.error("Ha ocurrido un error a nivel de Base de datos: {}", e.getMessage());
			throw new com.aglayatech.mundo3.error.exceptions.DataAccessException("Ha ocurrido un error a nivel de Base de Datos", e.getCause());
		} catch (ParseException e) {
			log.error("No se puede llevar a cabo la conversión de fechas");
			throw new com.aglayatech.mundo3.error.exceptions.ParseException("No se puede llevar a cabo la conversión de fechas", e.getCause());
		} finally {
			log.debug("{} Exit", __method);
		}
	}

	/****************** PDF REPORT SERVICES *******************/

	// REPORTE DE VENTAS DIARIAS
	@Override
	public byte[] resportDailySales(Integer usuario, Date fecha)
			throws JRException, FileNotFoundException, SQLException {

		Connection con = localDataSource.getConnection(); // Obtiene la conexión actual a la base de datos
		Map<String, Object> params = new HashMap<>();
		InputStream file = getClass().getResourceAsStream("/reports/poliza.jrxml");
		params.put("usuario", usuario);
		params.put("fechaIni", fecha);
		log.info("Fecha para poliza => " + params.get("fechaIni"));

		JasperReport jasperReport = JasperCompileManager.compileReport(file);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, con);

		ByteArrayOutputStream byteArrayOutputStream = getByteArrayOutputStream(jasperPrint);

		con.close();
		return byteArrayOutputStream.toByteArray();
	}

	// GENERADOR DE REPORTE DE FACTURA
	@Override
	public byte[] showBill(Long idfactura)
            throws JRException, IOException, SQLException {

		Connection con = localDataSource.getConnection();
		Map<String, Object> params = new HashMap<>();
		params.put("idfactura", idfactura);
		String reportPath = reportProperties.getReportPath();
		InputStream file = null;

		file = getClass().getResourceAsStream("/reports/comprobante-venta.jrxml");
//		if(reportPath == null || reportPath.isEmpty()) {
//			file = getClass().getResourceAsStream("/reports/comprobante-venta.jrxml");
//		} else {
//			file = Files.newInputStream(Paths.get(reportPath));
//		}

		JasperReport jasperReport = JasperCompileManager.compileReport(file);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, con);

		ByteArrayOutputStream byteArrayOutputStream = getByteArrayOutputStream(jasperPrint);

		con.close();
		return byteArrayOutputStream.toByteArray();
	}

	@Override
	public byte[] showBill2(Long idfactura)
			throws JRException, FileNotFoundException, SQLException {

		Connection con = localDataSource.getConnection();
		Map<String, Object> params = new HashMap<>();
		params.put("idfactura", idfactura);
		InputStream file = getClass().getResourceAsStream( "/reports/factura_2.jrxml");

		JasperReport jasperReport = JasperCompileManager.compileReport(file);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, con);

		ByteArrayOutputStream byteArrayOutputStream = getByteArrayOutputStream(jasperPrint);

		con.close();
		return byteArrayOutputStream.toByteArray();
	}

	@Override
	public byte[] reportMonthlySales(Integer year) throws JRException, FileNotFoundException, SQLException {
		Connection con = localDataSource.getConnection(); // Obtiene la conexión actual a la base de datos
		Map<String, Object> params = new HashMap<>();
		InputStream file = getClass().getResourceAsStream("/reports/rpt_ventas_mensuales.jrxml");
		params.put("pYear", year);

		JasperReport jasperReport = JasperCompileManager.compileReport(file);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, con);

		ByteArrayOutputStream byteArrayOutputStream = getByteArrayOutputStream(jasperPrint);

		con.close();
		return byteArrayOutputStream.toByteArray();
	}

	@Override
	public byte[] reportAllDailySales(String fecha) throws JRException, FileNotFoundException, SQLException, ParseException {
		Connection con = localDataSource.getConnection();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaFiltro = format.parse(fecha);
		Map params = new HashMap();
		InputStream file = getClass().getResourceAsStream("/reports/rpt_resumen_ventas_diarias.jrxml");
		params.put("pFecha", fechaFiltro);

		JasperReport jasperReport = JasperCompileManager.compileReport(file);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, con);

		ByteArrayOutputStream byteArrayOutputStream = getByteArrayOutputStream(jasperPrint);

		con.close();
		return byteArrayOutputStream.toByteArray();
	}

	protected ByteArrayOutputStream getByteArrayOutputStream(JasperPrint jasperPrint) throws JRException {
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);
	    return byteArrayOutputStream;
	}

}
