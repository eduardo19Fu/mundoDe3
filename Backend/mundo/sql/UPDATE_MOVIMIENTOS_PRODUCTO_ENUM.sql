UPDATE movimientos_producto
SET tipo_movimiento =
        CASE
            WHEN id_tipo_movimiento = 1 THEN 'VENTA'
            WHEN id_tipo_movimiento = 2 THEN 'INGRESO'
            WHEN id_tipo_movimiento = 3 THEN 'ANULACION_FACTURA'
            WHEN id_tipo_movimiento = 4 THEN 'ENTRADA'
            WHEN id_tipo_movimiento = 5 THEN 'COMPRA'
            WHEN id_tipo_movimiento = 6 THEN 'SALIDA'
            WHEN id_tipo_movimiento = 7 THEN 'ELIMINAR_COMPRA'
            WHEN id_tipo_movimiento = 8 THEN 'DEVOLUCION'
            WHEN id_tipo_movimiento = 9 THEN 'REFACTURACION'
        END;