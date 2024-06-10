package com.aglayatech.mundo3.repository;

import com.aglayatech.mundo3.model.Caja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ICajaRepository extends JpaRepository<Caja, Long> {

    List<Caja> findByFechaAperturaBetween(LocalDate fecha1, LocalDate fecha2);
}
