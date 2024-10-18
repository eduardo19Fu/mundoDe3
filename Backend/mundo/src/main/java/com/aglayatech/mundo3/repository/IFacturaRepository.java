package com.aglayatech.mundo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aglayatech.mundo3.model.Factura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IFacturaRepository extends JpaRepository<Factura, Long> {

    @Query(value = "Select get_cant_ventas()", nativeQuery = true)
    Integer getMaxVentas();

    Optional<Factura> getFacturaBySerieAndNoFactura(String serie, Long noFactura);

    List<Factura> findByFechaBetween(Date iniDate, Date endDate);

    @Query(value = "{call PR_CONSULTAR_VENTAS_POR_FECHA(:date1, :date2)}", nativeQuery = true)
    List<Factura> findAllFacturas(@Param("date1") Date date1, @Param("date2") Date date2);
}
