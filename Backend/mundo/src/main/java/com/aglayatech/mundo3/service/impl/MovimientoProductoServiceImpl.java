package com.aglayatech.mundo3.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import javax.sql.DataSource;

import com.aglayatech.mundo3.model.Estado;
import com.aglayatech.mundo3.model.TipoMovimiento;
import com.aglayatech.mundo3.service.IEstadoService;
import com.aglayatech.mundo3.service.IProductoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aglayatech.mundo3.model.MovimientoProducto;
import com.aglayatech.mundo3.model.Producto;
import com.aglayatech.mundo3.repository.IMovimientoProductoRepository;
import com.aglayatech.mundo3.service.IMovimientoProductoService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovimientoProductoServiceImpl implements IMovimientoProductoService {

	private final IMovimientoProductoRepository repoMovimiento;
	private final IProductoService productoService;
	private final IEstadoService estadoService;
	private final DataSource localDateSource;

	@Override
	public List<MovimientoProducto> findAll() {
		return repoMovimiento.findAll();
	}

	@Override
	public Page<MovimientoProducto> findAll(Pageable pageble) {
		return repoMovimiento.findAll(pageble);
	}

	@Override
	public Page<MovimientoProducto> findProductoMoves(Producto producto, Pageable pageable) {
		return repoMovimiento.findByProducto(producto, pageable);
	}

	@Override
	public MovimientoProducto save(MovimientoProducto movimientoProducto) {
		String __method = new Object() {}.getClass().getEnclosingClass().getSimpleName() + "::" + new Object() {}.getClass().getEnclosingMethod().getName();
		log.debug("Enter {}", __method);

		MovimientoProducto movimientoProductoSaved = null;

		try {
			if(calcularStock(movimientoProducto)) {
				log.info("Registrando Movimiento de Producto: {}", movimientoProducto.getProducto().getCodProducto());
				movimientoProductoSaved = repoMovimiento.save(movimientoProducto);
				return movimientoProductoSaved;
			} else {
				log.warn("No se ha podido llevar a cabo el registro del movimiento");
				throw new RuntimeException("No se ha podido llevar a cabo el registro");
			}
		} catch (DataAccessException e) {
			log.error("Ha ocurrido un error a nivel de base de datos: {}", e.toString());
			throw new com.aglayatech.mundo3.error.exceptions.DataAccessException(e.getMessage(), e.getCause());
		} finally {
			log.debug("{} Exit", __method);
		}
	}

	@Override
	public List<TipoMovimiento> getTiposMovimiento() {
		return this.repoMovimiento.findTiposMovimiento();
	}

	@Override
	public List<MovimientoProducto> findByFecha(Date fechaIni, Date fechaFin) {
		return repoMovimiento.findByFechaMovimientoBetween(fechaIni, fechaFin);
	}

	/**
	 * Método encargado de llevar a cabo la suma o resta de existencias dependiendo del tipo de movimiento que se
	 * esté llevando a cabo.
	 * @param movimientoProducto Objeto de tipo MovimientoProducto que guarda los datos a operar
	 * @return boolean Devuelve un valor verdadero si el procedimiento de guardado de las nuevas existencias del producto se ha
	 *         llevado a cabo con éxito.
	 *
	 * */
	public boolean calcularStock(MovimientoProducto movimientoProducto) {
		int tmpStock = 0;
		Producto producto = null;
		Producto productoSaved = null;
		Estado estado = null;

		try {
			tmpStock = movimientoProducto.getProducto().getStock();
			producto = movimientoProducto.getProducto();
			movimientoProducto.setStockInicial(tmpStock);

			switch (movimientoProducto.getTipoMovimiento().getTipoMovimiento()) {
				case "VENTA":
				case "SALIDA":
				case "ELIMINAR_COMPRA":
					log.debug("Operando salidas al stock por operaciones de tipo VENTA, SALIDA");
					producto.setStock(tmpStock - movimientoProducto.getCantidad());

					if(producto.getStock() <= 12 && producto.getStock() > 0)
						estado = estadoService.findByEstado("POR AGOTARSE");
					else if(producto.getStock() == 0)
						estado = estadoService.findByEstado("AGOTADO");
					else
						estado = estadoService.findByEstado("ACTIVO");

					producto.setEstado(estado);
					break;
				case "COMPRA":
				case "ENTRADA":
				case "ANULACION FACTURA":
					log.debug("Operando suma al stock por operaciones de tipo COMPRA, ENTRADA");
					producto.setStock(tmpStock + movimientoProducto.getCantidad());

					if(producto.getStock() > 12)
						estado = estadoService.findByEstado("ACTIVO");
					else if(producto.getStock() > 0)
						estado = estadoService.findByEstado("POR AGOTARSE");

					producto.setEstado(estado);
					break;
				default:
					log.debug("No existe la operación deseada");
					break;
			}

			productoSaved = productoService.save(producto);
		} catch (Exception ex) {
			log.error("Error: {}", ex.getMessage());
		}

		return (productoSaved != null);
	}

	/********* PDF REPORTS SERVICES ***********/
	
	@Override
	public byte[] inventory(Date fechaIni, Date fechaFin) 
			throws JRException, FileNotFoundException, SQLException { // REPORTE DE INVENTARIO
		
		Connection con = localDateSource.getConnection();
		Map<String, Object> params = new HashMap<>();
		InputStream file = getClass().getResourceAsStream("/reports/rpt_inventario.jrxml");

		params.put("fechaIni", fechaIni);
		params.put("fechaFin", fechaFin);
		
		JasperReport jasperReport = JasperCompileManager.compileReport(file);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, con);
		
		ByteArrayOutputStream byteArrayOutputStream = getByteArrayOutputStream(jasperPrint);
		
		con.close();
		return byteArrayOutputStream.toByteArray();
	}

	@Override
	public TipoMovimiento findTipoMovimiento(String tipoMovimiento) {
		Optional<TipoMovimiento> optional = this.repoMovimiento.findTipoMovimientoByNombre(tipoMovimiento);
		return (optional.isPresent() ? optional.get() : null);
	}

	protected ByteArrayOutputStream getByteArrayOutputStream(JasperPrint jasperPrint) throws JRException {
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);
	    return byteArrayOutputStream;
	}

}
