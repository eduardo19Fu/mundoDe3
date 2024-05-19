package com.aglayatech.mundo3.repository;

import com.aglayatech.mundo3.model.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICotizacionRepository extends JpaRepository<Cotizacion, Long> {
}
