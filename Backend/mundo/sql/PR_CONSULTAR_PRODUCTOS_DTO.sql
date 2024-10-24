CREATE PROCEDURE `PR_CONSULTAR_PRODUCTOS_DTO`()
BEGIN
    SELECT prod.id_producto,
           prod.cod_producto,
           prod.nombre,
           prod.precio_compra,
           prod.precio_venta,
           prod.precio_sugerido,
           prod.stock,
           prod.porcentaje_ganancia,
           prod.id_estado,
           e.estado,
           m.marca,
           tp.tipo_producto,
           pr.nombre AS proveedor
    FROM productos as prod
    INNER JOIN estados AS e ON e.id_estado = prod.id_estado
    INNER JOIN marcas_producto AS m ON m.id_marca_producto = prod.id_marca_producto
    INNER JOIN tipos_producto AS tp ON tp.id_tipo_producto = prod.id_tipo_producto
    LEFT JOIN proveedores AS pr ON pr.id_proveedor = prod.id_proveedor;
END