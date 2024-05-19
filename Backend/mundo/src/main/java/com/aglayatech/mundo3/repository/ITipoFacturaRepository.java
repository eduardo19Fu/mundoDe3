package com.aglayatech.mundo3.repository;

import com.aglayatech.mundo3.model.TipoFactura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITipoFacturaRepository extends JpaRepository<TipoFactura, Integer> {
}
