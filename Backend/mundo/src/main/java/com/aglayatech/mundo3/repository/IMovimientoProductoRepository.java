package com.aglayatech.mundo3.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aglayatech.mundo3.model.MovimientoProducto;
import com.aglayatech.mundo3.model.Producto;
import org.springframework.data.jpa.repository.Query;

public interface IMovimientoProductoRepository extends JpaRepository<MovimientoProducto, Long> {
	
	Page<MovimientoProducto> findByProducto(Producto producto, Pageable pageable);
	
	// Movimientos listados por rango de fechas
	List<MovimientoProducto> findByFechaMovimientoBetween(Date fechaIni, Date fechaFin);

	@Query(value = "Select * from movimientos_producto mp order by mp.fecha_movimiento desc Limit 1000", nativeQuery = true)
	List<MovimientoProducto> findAllMovimientosLimit();
}
