package com.aglayatech.mundo3.repository;

import com.aglayatech.mundo3.model.Caja;
import com.aglayatech.mundo3.model.MovimientoCaja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IMovimientoCajaRepository extends JpaRepository<MovimientoCaja, Long> {

    @Query("Select mc From MovimientoCaja mc Where mc.caja = :caja")
    List<MovimientoCaja> findByCaja(@Param("caja") Caja caja);

    @Query("Select mc From MovimientoCaja mc Where Date(mc.fechaMovimiento) between :fecha1 and :fecha2")
    List<MovimientoCaja> findByFechaMovimientoBetween(@Param("fecha1") LocalDate fecha1, @Param("fecha2") LocalDate fecha2);
}
