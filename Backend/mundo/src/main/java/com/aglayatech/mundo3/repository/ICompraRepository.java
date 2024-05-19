package com.aglayatech.mundo3.repository;

import com.aglayatech.mundo3.model.Compra;
import com.aglayatech.mundo3.model.TipoComprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICompraRepository extends JpaRepository<Compra, Long> {

    @Query("Select t from TipoComprobante t")
    List<TipoComprobante> findtipos();
}
