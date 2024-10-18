CREATE PROCEDURE `PR_CONSULTAR_VENTAS_POR_FECHA`(IN pFechaIni DATE, IN pFechaFin DATE)
BEGIN
    DECLARE vFechaIni DATE;
    DECLARE vFechaFin DATE;

    BEGIN
        SET vFechaIni = pFechaIni;
        SET vFechaFin = pFechaFin;

        IF vFechaIni IS NULL OR vFechaIni = '' THEN
            SELECT *
            FROM facturas;
        ELSE
            SELECT *
            FROM facturas facs
            WHERE facs.fecha BETWEEN vFechaIni AND vFechaFin;
        END IF;
    END;
END