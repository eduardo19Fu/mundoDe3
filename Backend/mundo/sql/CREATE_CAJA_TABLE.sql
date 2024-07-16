CREATE TABLE cajas(
    id_caja BIGINT AUTO_INCREMENT PRIMARY KEY,
    monto_apertura DECIMAL(10,2) NOT NULL,
    monto_actual DECIMAL(10,2) NOT NULL,
    fecha_apertura DATETIME,
    estado ENUM('APERTURADA','CERRADA','SUSPENDIDA','ACTIVA'),
    id_usuario INT,
    CONSTRAINT fk_cajas_id_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);