-- Eliminar las tablas si existen
DROP TABLE IF EXISTS detalle_notas_credito;
DROP TABLE IF EXISTS notas_credito;

-- Crear la tabla notas_credito
CREATE TABLE notas_credito (
    id_nota_credito BIGINT PRIMARY KEY AUTO_INCREMENT,
    fecha_nota_credito DATETIME NOT NULL,
    monto_nota DECIMAL(8,2) NOT NULL,
    serie_comprobante TEXT,
    no_comprobante BIGINT,
    refacturado TINYINT,
    tipo_devolucion ENUM('EXISTENCIAS','EFECTIVO','CREDITO'),
    id_usuario INT,
    CONSTRAINT fk_notas_credito_id_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Crear la tabla detalle_notas_credito
CREATE TABLE detalle_notas_credito (
    id_detalle_nota_credito BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_nota_credito BIGINT,
    id_producto INT,
    cantidad INT NOT NULL,
    sub_total DECIMAL(6,2) NOT NULL,
    CONSTRAINT fk_detalle_notas_cred_id_nota_credito FOREIGN KEY (id_nota_credito) REFERENCES notas_credito(id_nota_credito) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_detalle_notas_cred_id_producto FOREIGN KEY (id_producto) REFERENCES productos(id_producto) ON DELETE SET NULL ON UPDATE CASCADE
);
