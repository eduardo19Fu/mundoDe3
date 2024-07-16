package com.aglayatech.mundo3.repository;

import com.aglayatech.mundo3.model.Caja;
import com.aglayatech.mundo3.model.Usuario;
import com.aglayatech.mundo3.model.enums.EstadoCajaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ICajaRepository extends JpaRepository<Caja, Long> {

    List<Caja> findByFechaAperturaBetween(LocalDate fecha1, LocalDate fecha2);

    @Query("Select c From Caja c where c.usuario = :usuario and c.estado = :estado")
    Optional<Caja> findByUsuario(@Param("usuario") Usuario usuario, @Param("estado") EstadoCajaEnum estado);
}
