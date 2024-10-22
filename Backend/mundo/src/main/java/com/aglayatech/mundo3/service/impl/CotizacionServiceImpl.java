package com.aglayatech.mundo3.service.impl;

import com.aglayatech.mundo3.error.exceptions.ReportGenerationException;
import com.aglayatech.mundo3.model.Cotizacion;
import com.aglayatech.mundo3.repository.ICotizacionRepository;
import com.aglayatech.mundo3.service.ICotizacionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.sql.Connection;
import java.sql.SQLException;

import java.io.InputStream;

import javax.sql.DataSource;

@Service
@RequiredArgsConstructor
@Slf4j
public class CotizacionServiceImpl implements ICotizacionService {

    private final ICotizacionRepository proformaRepository;

    protected final DataSource localDataSource;

    @Override
    public List<Cotizacion> findAll() {
        return this.proformaRepository.findAll(Sort.by(Direction.DESC, "fechaEmision"));
    }

    @Override
    public Page<Cotizacion> findAll(Pageable pageable) {
        return this.proformaRepository.findAll(pageable);
    }

    @Override
    public Cotizacion findProforma(Long idproforma) {
        return this.proformaRepository.findById(idproforma).orElse(null);
    }

    @Override
    public Cotizacion save(Cotizacion proforma) {
        return this.proformaRepository.save(proforma);
    }

    @Override
    public void delete(Long id) {
        proformaRepository.deleteById(id);
    }

    /********* PDF REPORTS SERVICES
     * @param idcotizacion***********/
    @Override
    public byte[] showCotizacion(Long idcotizacion) {
        try (Connection con = localDataSource.getConnection()) {

            Map<String, Object> params = new HashMap<>();
            params.put("ID", idcotizacion);
            InputStream file = getClass().getResourceAsStream("/reports/cotizacion.jrxml");
            if(file == null) {
                log.error("El archivo no se encunetra en la ruta especificada");
                throw new ReportGenerationException("El archivo no se encuentra en la ruta especificada", null);
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(file);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, con);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            log.error("Ha ocurrido un error durante la generación de la proforma: {}", e.getMessage());
            throw new ReportGenerationException(e.getMessage(), e.getCause());
        } catch (SQLException e) {
            log.error("Ha ocurrido un error al intentar ejecutar una instucción SQL: {}", e.getMessage());
            throw new com.aglayatech.mundo3.error.exceptions.SQLException(e.getMessage(), e.getCause());
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado: {}", e.getMessage());
            throw new RuntimeException("Ha ocurrido un error inesperado: {}", e);
        }
    }

}
