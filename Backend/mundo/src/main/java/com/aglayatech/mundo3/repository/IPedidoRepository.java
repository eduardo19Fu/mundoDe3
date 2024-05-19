package com.aglayatech.mundo3.repository;

import com.aglayatech.mundo3.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPedidoRepository extends JpaRepository<Pedido, Long> {
}
