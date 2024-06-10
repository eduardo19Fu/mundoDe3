CREATE TABLE movimientos_caja (
    id_movimiento_caja INT AUTO_INCREMENT PRIMARY KEY,
    fecha_movimiento DATETIME,
    observaciones VARCHAR(150),
    monto_movimiento DECIMAL(10,2),
    tipo_movimiento ENUM('INGRESO','DEVOLUCION','PRESTAMO','GASTO', 'PAGO'),
    id_caja BIGINT,
    CONSTRAINT fk_movimientos_caja_id_caja FOREIGN KEY (id_caja) REFERENCES cajas(id_caja) ON DELETE CASCADE ON UPDATE CASCADE
);