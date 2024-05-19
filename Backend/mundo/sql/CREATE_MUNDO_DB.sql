-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: techos_db
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
                              `id_categoria` int NOT NULL AUTO_INCREMENT,
                              `nombre` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                              `id_tipo_categoria` int DEFAULT NULL,
                              PRIMARY KEY (`id_categoria`),
                              KEY `fk_categorias_id_tipo_categoria` (`id_tipo_categoria`),
                              CONSTRAINT `fk_categorias_id_tipo_categoria` FOREIGN KEY (`id_tipo_categoria`) REFERENCES `tipos_categoria` (`id_tipo_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `certificadores`
--

DROP TABLE IF EXISTS `certificadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `certificadores` (
                                  `id_certificador` int NOT NULL AUTO_INCREMENT,
                                  `alias_ws` varchar(20) NOT NULL,
                                  `llave_ws` varchar(50) NOT NULL,
                                  `token_signer` varchar(50) NOT NULL,
                                  `correo_copia` varchar(80) DEFAULT NULL,
                                  `id_estado` int DEFAULT NULL,
                                  PRIMARY KEY (`id_certificador`),
                                  KEY `fk_certificadores_idestado` (`id_estado`),
                                  CONSTRAINT `fk_certificadores_idestado` FOREIGN KEY (`id_estado`) REFERENCES `estados` (`id_estado`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
                            `id_cliente` int NOT NULL AUTO_INCREMENT,
                            `nombre` varchar(300) NOT NULL,
                            `nit` varchar(45) DEFAULT NULL,
                            `direccion` varchar(500) NOT NULL,
                            `fecha_registro` timestamp NULL DEFAULT NULL,
                            `telefono` varchar(45) DEFAULT NULL,
                            PRIMARY KEY (`id_cliente`),
                            UNIQUE KEY `nit_UNIQUE` (`nit`)
) ENGINE=InnoDB AUTO_INCREMENT=340 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `compras`
--

DROP TABLE IF EXISTS `compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compras` (
                           `id_compra` bigint NOT NULL AUTO_INCREMENT,
                           `id_proveedor` int DEFAULT NULL,
                           `fecha_compra` date NOT NULL,
                           `id_estado` int DEFAULT NULL,
                           `id_tipo_comprobante` int DEFAULT NULL,
                           `no_comprobante` varchar(25) COLLATE utf8mb4_general_ci DEFAULT NULL,
                           `iva` float DEFAULT NULL,
                           `total_flete` decimal(8,2) DEFAULT NULL,
                           `total_descuento` decimal(10,2) DEFAULT NULL,
                           `total_compra` decimal(10,2) DEFAULT NULL,
                           `fecha_registro` timestamp NULL DEFAULT NULL,
                           `id_usuario` int DEFAULT NULL,
                           PRIMARY KEY (`id_compra`),
                           KEY `fk_compras_id_proveedor` (`id_proveedor`),
                           KEY `fk_compras_id_estado` (`id_estado`),
                           KEY `fk_compras_id_tipo_comprobante` (`id_tipo_comprobante`),
                           KEY `fk_compras_id_usuario` (`id_usuario`),
                           CONSTRAINT `fk_compras_id_estado` FOREIGN KEY (`id_estado`) REFERENCES `estados` (`id_estado`),
                           CONSTRAINT `fk_compras_id_proveedor` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedores` (`id_proveedor`),
                           CONSTRAINT `fk_compras_id_tipo_comprobante` FOREIGN KEY (`id_tipo_comprobante`) REFERENCES `tipos_comprobante` (`id_tipo_comprobante`),
                           CONSTRAINT `fk_compras_id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `correlativos`
--

DROP TABLE IF EXISTS `correlativos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `correlativos` (
                                `id_correlativo` bigint NOT NULL AUTO_INCREMENT,
                                `correlativo_inicial` bigint DEFAULT NULL,
                                `correlativo_final` bigint DEFAULT NULL,
                                `correlativo_actual` bigint DEFAULT NULL,
                                `id_estado` int DEFAULT NULL,
                                `id_usuario` int DEFAULT NULL,
                                `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                `serie` varchar(10) DEFAULT NULL,
                                PRIMARY KEY (`id_correlativo`),
                                KEY `fk_correlativos_idestado` (`id_estado`),
                                KEY `fk_correlativos_idusuario` (`id_usuario`),
                                CONSTRAINT `correlativos_ibfk_1` FOREIGN KEY (`id_estado`) REFERENCES `estados` (`id_estado`),
                                CONSTRAINT `correlativos_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cotizaciones`
--

DROP TABLE IF EXISTS `cotizaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cotizaciones` (
                                `id_cotizacion` bigint NOT NULL AUTO_INCREMENT,
                                `total` decimal(10,2) NOT NULL,
                                `fecha_emision` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                `id_usuario` int DEFAULT NULL,
                                `id_estado` int DEFAULT NULL,
                                `id_cliente` int DEFAULT NULL,
                                PRIMARY KEY (`id_cotizacion`),
                                KEY `fk_proformas_id_usuario` (`id_usuario`),
                                KEY `fk_proformas_id_estado` (`id_estado`),
                                KEY `fk_proformas_id_cliente` (`id_cliente`),
                                CONSTRAINT `fk_proformas_id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`) ON DELETE SET NULL ON UPDATE CASCADE,
                                CONSTRAINT `fk_proformas_id_estado` FOREIGN KEY (`id_estado`) REFERENCES `estados` (`id_estado`) ON DELETE SET NULL ON UPDATE CASCADE,
                                CONSTRAINT `fk_proformas_id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cotizaciones_detalle`
--

DROP TABLE IF EXISTS `cotizaciones_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cotizaciones_detalle` (
                                        `id_detalle` bigint NOT NULL AUTO_INCREMENT,
                                        `id_producto` int DEFAULT NULL,
                                        `id_cotizacion` bigint DEFAULT NULL,
                                        `cantidad` int DEFAULT NULL,
                                        `descuento` float DEFAULT NULL,
                                        `sub_total` decimal(10,2) DEFAULT NULL,
                                        `sub_total_descuento` decimal(10,2) DEFAULT NULL,
                                        PRIMARY KEY (`id_detalle`),
                                        KEY `fk_proformas_detalle_id_producto` (`id_producto`),
                                        KEY `fk_proformas_detalle_id_proforma` (`id_cotizacion`),
                                        CONSTRAINT `fk_proformas_detalle_id_producto` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE SET NULL ON UPDATE CASCADE,
                                        CONSTRAINT `fk_proformas_detalle_id_proforma` FOREIGN KEY (`id_cotizacion`) REFERENCES `cotizaciones` (`id_cotizacion`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detalle_compra`
--

DROP TABLE IF EXISTS `detalle_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_compra` (
                                  `id_detalle_compra` bigint NOT NULL AUTO_INCREMENT,
                                  `id_producto` int DEFAULT NULL,
                                  `id_compra` bigint DEFAULT NULL,
                                  `cantidad` int NOT NULL,
                                  `precio_unitario` decimal(10,2) NOT NULL,
                                  `sub_total` decimal(10,2) DEFAULT NULL,
                                  PRIMARY KEY (`id_detalle_compra`),
                                  KEY `fk_detalle_compra_id_producto` (`id_producto`),
                                  KEY `fk_detalle_compra_id_compra` (`id_compra`),
                                  CONSTRAINT `fk_detalle_compra_id_compra` FOREIGN KEY (`id_compra`) REFERENCES `compras` (`id_compra`) ON DELETE CASCADE ON UPDATE CASCADE,
                                  CONSTRAINT `fk_detalle_compra_id_producto` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=615 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detalle_envio`
--

DROP TABLE IF EXISTS `detalle_envio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_envio` (
                                 `id_detalle_envio` int NOT NULL AUTO_INCREMENT,
                                 `id_envio` int DEFAULT NULL,
                                 `id_producto` int DEFAULT NULL,
                                 `sub_total` decimal(10,2) NOT NULL DEFAULT '0.00',
                                 `cantidad` int DEFAULT NULL,
                                 `descuento` float NOT NULL DEFAULT '0',
                                 `sub_total_descuento` decimal(10,2) NOT NULL DEFAULT '0.00',
                                 PRIMARY KEY (`id_detalle_envio`),
                                 KEY `fk_detalle_envio_id_envio` (`id_envio`),
                                 KEY `fk_detalle_envio_id_producto` (`id_producto`),
                                 CONSTRAINT `fk_detalle_envio_id_envio` FOREIGN KEY (`id_envio`) REFERENCES `envios` (`id_envio`) ON DELETE CASCADE ON UPDATE CASCADE,
                                 CONSTRAINT `fk_detalle_envio_id_producto` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detalle_pedidos`
--

DROP TABLE IF EXISTS `detalle_pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_pedidos` (
                                   `id_detalle_pedido` bigint NOT NULL AUTO_INCREMENT,
                                   `id_producto` int DEFAULT NULL,
                                   `id_pedido` bigint DEFAULT NULL,
                                   `cantidad` int DEFAULT NULL,
                                   `precio_unitario` decimal(10,2) DEFAULT NULL,
                                   `descuento` float DEFAULT NULL,
                                   `precio_descuento_aplicado` decimal(10,2) DEFAULT NULL,
                                   `sub_total` decimal(10,2) DEFAULT NULL,
                                   PRIMARY KEY (`id_detalle_pedido`),
                                   KEY `fk_detalle_pedidos_id_producto` (`id_producto`),
                                   KEY `fk_detalle_pedidos_id_pedido` (`id_pedido`),
                                   CONSTRAINT `fk_detalle_pedidos_id_pedido` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`) ON DELETE CASCADE ON UPDATE CASCADE,
                                   CONSTRAINT `fk_detalle_pedidos_id_producto` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `emisores`
--

DROP TABLE IF EXISTS `emisores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emisores` (
                            `id_emisor` int NOT NULL AUTO_INCREMENT,
                            `codigo_postal` varchar(10) DEFAULT NULL,
                            `correo_emisor` varchar(100) DEFAULT NULL,
                            `departamento` varchar(45) DEFAULT NULL,
                            `municipio` varchar(45) DEFAULT NULL,
                            `direccion` varchar(150) DEFAULT NULL,
                            `nit` varchar(20) NOT NULL,
                            `nombre_comercial` varchar(200) NOT NULL,
                            `nombre_emisor` varchar(200) NOT NULL,
                            `id_estado` int DEFAULT NULL,
                            PRIMARY KEY (`id_emisor`),
                            KEY `fk_emisores_idestado` (`id_estado`),
                            CONSTRAINT `fk_emisores_idestado` FOREIGN KEY (`id_estado`) REFERENCES `estados` (`id_estado`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `envios`
--

DROP TABLE IF EXISTS `envios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `envios` (
                          `id_envio` int NOT NULL AUTO_INCREMENT,
                          `fecha_pedido` date NOT NULL,
                          `id_cliente` int DEFAULT NULL,
                          `telefono_referencia` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
                          `total_envio` decimal(10,2) DEFAULT NULL,
                          `abono` decimal(10,2) NOT NULL DEFAULT '0.00',
                          `saldo_pendiente` decimal(10,2) NOT NULL DEFAULT '0.00',
                          `fecha_registro` timestamp NULL DEFAULT NULL,
                          `referencia` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL,
                          `id_usuario` int DEFAULT NULL,
                          `id_factura` bigint DEFAULT NULL,
                          PRIMARY KEY (`id_envio`),
                          KEY `fk_envios_id_cliente` (`id_cliente`),
                          KEY `fk_envios_id_usuario` (`id_usuario`),
                          KEY `fk_envios_id_factura_idx` (`id_factura`),
                          CONSTRAINT `fk_envios_id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`) ON DELETE SET NULL ON UPDATE CASCADE,
                          CONSTRAINT `fk_envios_id_factura` FOREIGN KEY (`id_factura`) REFERENCES `facturas` (`id_factura`) ON DELETE CASCADE ON UPDATE CASCADE,
                          CONSTRAINT `fk_envios_id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `estados`
--

DROP TABLE IF EXISTS `estados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estados` (
                           `id_estado` int NOT NULL AUTO_INCREMENT,
                           `estado` varchar(75) DEFAULT NULL,
                           PRIMARY KEY (`id_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `estados_envio`
--

DROP TABLE IF EXISTS `estados_envio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estados_envio` (
                                 `id_envio` int NOT NULL,
                                 `id_estado` int NOT NULL,
                                 PRIMARY KEY (`id_envio`,`id_estado`),
                                 KEY `fk_estados_envio_id_estado` (`id_estado`),
                                 CONSTRAINT `fk_estados_envio_id_envio` FOREIGN KEY (`id_envio`) REFERENCES `envios` (`id_envio`) ON DELETE CASCADE ON UPDATE CASCADE,
                                 CONSTRAINT `fk_estados_envio_id_estado` FOREIGN KEY (`id_estado`) REFERENCES `estados` (`id_estado`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `facturas`
--

DROP TABLE IF EXISTS `facturas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facturas` (
                            `id_factura` bigint NOT NULL AUTO_INCREMENT,
                            `no_factura` bigint DEFAULT NULL,
                            `serie` text,
                            `total` decimal(10,2) DEFAULT NULL,
                            `fecha` timestamp NULL DEFAULT NULL,
                            `id_usuario` int DEFAULT NULL,
                            `id_estado` int DEFAULT NULL,
                            `id_cliente` int DEFAULT NULL,
                            `id_tipo_factura` int DEFAULT NULL,
                            `iva` double DEFAULT NULL,
                            `correlativo_sat` varchar(25) DEFAULT NULL,
                            `certificacion_sat` varchar(200) DEFAULT NULL,
                            `serie_sat` varchar(200) DEFAULT NULL,
                            `mensaje_sat` varchar(250) DEFAULT NULL,
                            `fecha_certificacion_sat` varchar(50) DEFAULT NULL,
                            PRIMARY KEY (`id_factura`),
                            UNIQUE KEY `fk_ventas_no_factura` (`no_factura`,`id_usuario`),
                            KEY `fk_ventas_idestado_idx` (`id_estado`),
                            KEY `fk_ventas_idusuario_idx` (`id_usuario`),
                            KEY `fk_ventas_idcliente_idx` (`id_cliente`),
                            KEY `fk_ventas_idtipofactura_idx` (`id_tipo_factura`),
                            CONSTRAINT `fk_ventas_idcliente` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`) ON DELETE SET NULL ON UPDATE CASCADE,
                            CONSTRAINT `fk_ventas_idestado` FOREIGN KEY (`id_estado`) REFERENCES `estados` (`id_estado`) ON DELETE SET NULL ON UPDATE CASCADE,
                            CONSTRAINT `fk_ventas_idtipofactura` FOREIGN KEY (`id_tipo_factura`) REFERENCES `tipos_factura` (`id_tipo_factura`) ON DELETE SET NULL ON UPDATE CASCADE,
                            CONSTRAINT `fk_ventas_idusuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=296 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `facturas_detalle`
--

DROP TABLE IF EXISTS `facturas_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facturas_detalle` (
                                    `id_detalle` bigint NOT NULL AUTO_INCREMENT,
                                    `id_producto` int DEFAULT NULL,
                                    `id_factura` bigint DEFAULT NULL,
                                    `cantidad` int DEFAULT NULL,
                                    `descuento` float NOT NULL DEFAULT '0',
                                    `sub_total` decimal(10,2) NOT NULL DEFAULT '0.00',
                                    `sub_total_descuento` decimal(10,2) NOT NULL DEFAULT '0.00',
                                    PRIMARY KEY (`id_detalle`),
                                    UNIQUE KEY `idx_idproducto_idfactura_uq` (`id_producto`,`id_factura`),
                                    KEY `fk_detalles_idproducto_idx` (`id_producto`),
                                    KEY `fk_detalles_idfactura_idx` (`id_factura`),
                                    CONSTRAINT `fk_detalles_fac_idfactura` FOREIGN KEY (`id_factura`) REFERENCES `facturas` (`id_factura`) ON DELETE CASCADE ON UPDATE CASCADE,
                                    CONSTRAINT `fk_detalles_fac_idproducto` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1657 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `historico`
--

DROP TABLE IF EXISTS `historico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historico` (
                             `id_registro` int NOT NULL AUTO_INCREMENT,
                             `no_factura` int DEFAULT NULL,
                             `cliente` varchar(100) DEFAULT NULL,
                             `nit` varchar(45) DEFAULT NULL,
                             `fecha_eliminacion` timestamp NULL DEFAULT NULL,
                             `fecha_venta` timestamp NULL DEFAULT NULL,
                             `usuario` varchar(200) DEFAULT NULL,
                             PRIMARY KEY (`id_registro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `marcas_producto`
--

DROP TABLE IF EXISTS `marcas_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marcas_producto` (
                                   `id_marca_producto` int NOT NULL AUTO_INCREMENT,
                                   `marca` varchar(100) NOT NULL,
                                   `fecha_registro` timestamp NULL DEFAULT NULL,
                                   `id_usuario` int DEFAULT NULL,
                                   PRIMARY KEY (`id_marca_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=309 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `movimientos_producto`
--

DROP TABLE IF EXISTS `movimientos_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimientos_producto` (
                                        `id_movimiento` bigint NOT NULL AUTO_INCREMENT,
                                        `fecha_movimiento` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                        `stock_inicial` int DEFAULT NULL,
                                        `cantidad` int NOT NULL DEFAULT '0',
                                        `id_producto` int DEFAULT NULL,
                                        `id_usuario` int DEFAULT NULL,
                                        `id_tipo_movimiento` int DEFAULT NULL,
                                        PRIMARY KEY (`id_movimiento`),
                                        KEY `fk_mov_producto_idproducto` (`id_producto`),
                                        KEY `fk_mov_producto_idusuario` (`id_usuario`),
                                        KEY `idx_mov_producto_fecha` (`fecha_movimiento`),
                                        KEY `fk_movimientos_id_tipo_movimiento` (`id_tipo_movimiento`),
                                        CONSTRAINT `fk_mov_producto_idproducto` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE,
                                        CONSTRAINT `fk_mov_producto_idusuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
                                        CONSTRAINT `fk_movimientos_id_tipo_movimiento` FOREIGN KEY (`id_tipo_movimiento`) REFERENCES `tipos_movimiento` (`id_tipo_movimiento`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2395 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `paises`
--

DROP TABLE IF EXISTS `paises`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paises` (
                          `id_pais` int NOT NULL AUTO_INCREMENT,
                          `nombre` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
                          PRIMARY KEY (`id_pais`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
                           `id_pedido` bigint NOT NULL AUTO_INCREMENT,
                           `id_proveedor` int DEFAULT NULL,
                           `id_estado` int DEFAULT NULL,
                           `fecha_pedido` date DEFAULT NULL,
                           `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           `total_pedido` decimal(10,2) DEFAULT NULL,
                           `id_usuario` int DEFAULT NULL,
                           PRIMARY KEY (`id_pedido`),
                           KEY `fk_pedidos_id_proveedor` (`id_proveedor`),
                           KEY `fk_pedidos_id_estado` (`id_estado`),
                           KEY `fk_pedidos_id_usuario` (`id_usuario`),
                           CONSTRAINT `fk_pedidos_id_estado` FOREIGN KEY (`id_estado`) REFERENCES `estados` (`id_estado`) ON DELETE SET NULL ON UPDATE CASCADE,
                           CONSTRAINT `fk_pedidos_id_proveedor` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedores` (`id_proveedor`) ON DELETE SET NULL ON UPDATE CASCADE,
                           CONSTRAINT `fk_pedidos_id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
                             `id_producto` int NOT NULL AUTO_INCREMENT,
                             `serie` varchar(100) DEFAULT NULL,
                             `cod_producto` varchar(100) DEFAULT NULL,
                             `nombre` varchar(100) DEFAULT NULL,
                             `id_tipo_producto` int DEFAULT NULL,
                             `id_marca_producto` int DEFAULT NULL,
                             `precio_compra` decimal(10,2) DEFAULT NULL,
                             `precio_venta` decimal(10,2) DEFAULT NULL,
                             `precio_sugerido` decimal(10,2) DEFAULT NULL,
                             `porcentaje_ganancia` float DEFAULT NULL,
                             `fecha_ingreso` date DEFAULT NULL,
                             `fecha_vencimiento` date DEFAULT NULL,
                             `id_estado` int DEFAULT NULL,
                             `stock` int DEFAULT NULL,
                             `imagen` varchar(500) DEFAULT NULL,
                             `fecha_registro` timestamp NULL DEFAULT NULL,
                             `descripcion` varchar(9000) DEFAULT NULL,
                             `link` varchar(1000) DEFAULT NULL,
                             `id_proveedor` int DEFAULT NULL,
                             PRIMARY KEY (`id_producto`),
                             KEY `fk_producto_idtipo_idx` (`id_tipo_producto`),
                             KEY `fk_producto_idmarca_idx` (`id_marca_producto`),
                             KEY `fk_producto_idestado_idx` (`id_estado`),
                             KEY `fk_productos_id_proveedor` (`id_proveedor`),
                             CONSTRAINT `fk_productos_id_estado` FOREIGN KEY (`id_estado`) REFERENCES `estados` (`id_estado`),
                             CONSTRAINT `fk_productos_id_marca_producto` FOREIGN KEY (`id_marca_producto`) REFERENCES `marcas_producto` (`id_marca_producto`),
                             CONSTRAINT `fk_productos_id_proveedor` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedores` (`id_proveedor`),
                             CONSTRAINT `fk_productos_id_tipo_producto` FOREIGN KEY (`id_tipo_producto`) REFERENCES `tipos_producto` (`id_tipo_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=8486 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `before_insert_productos` BEFORE INSERT ON `productos` FOR EACH ROW BEGIN
	IF NEW.serie IS NULL OR NEW.serie = '' THEN
        SET NEW.serie = CONCAT('GENERADO-SERIE-', generar_codigo_aleatorio());
    END IF;

    IF NEW.cod_producto IS NULL OR NEW.cod_producto = '' THEN
        SET NEW.cod_producto = CONCAT('GENERADO-', generar_codigo_aleatorio());
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `proveedores`
--

DROP TABLE IF EXISTS `proveedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedores` (
                               `id_proveedor` int NOT NULL AUTO_INCREMENT,
                               `nombre` varchar(75) COLLATE utf8mb4_general_ci NOT NULL,
                               `contacto` varchar(150) COLLATE utf8mb4_general_ci DEFAULT NULL,
                               `id_pais` int DEFAULT NULL,
                               `web` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL,
                               `id_estado` int DEFAULT NULL,
                               `email` varchar(150) COLLATE utf8mb4_general_ci DEFAULT NULL,
                               `telefono` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
                               `fecha_registro` timestamp NULL DEFAULT NULL,
                               `direccion` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
                               PRIMARY KEY (`id_proveedor`),
                               KEY `fk_proveedores_id_estado` (`id_estado`),
                               KEY `fk_proveedores_id_pais` (`id_pais`),
                               CONSTRAINT `fk_proveedores_id_estado` FOREIGN KEY (`id_estado`) REFERENCES `estados` (`id_estado`),
                               CONSTRAINT `fk_proveedores_id_pais` FOREIGN KEY (`id_pais`) REFERENCES `paises` (`id_pais`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
                         `id_role` int NOT NULL,
                         `role` varchar(100) NOT NULL,
                         PRIMARY KEY (`id_role`),
                         UNIQUE KEY `role_UNIQUE` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipos_categoria`
--

DROP TABLE IF EXISTS `tipos_categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos_categoria` (
                                   `id_tipo_categoria` int NOT NULL AUTO_INCREMENT,
                                   `tipo_categoria` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
                                   PRIMARY KEY (`id_tipo_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipos_comprobante`
--

DROP TABLE IF EXISTS `tipos_comprobante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos_comprobante` (
                                     `id_tipo_comprobante` int NOT NULL AUTO_INCREMENT,
                                     `tipo_comprobante` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
                                     PRIMARY KEY (`id_tipo_comprobante`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipos_factura`
--

DROP TABLE IF EXISTS `tipos_factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos_factura` (
                                 `id_tipo_factura` int NOT NULL AUTO_INCREMENT,
                                 `tipo_factura` varchar(45) DEFAULT NULL,
                                 PRIMARY KEY (`id_tipo_factura`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipos_movimiento`
--

DROP TABLE IF EXISTS `tipos_movimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos_movimiento` (
                                    `id_tipo_movimiento` int NOT NULL AUTO_INCREMENT,
                                    `tipo_movimiento` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
                                    PRIMARY KEY (`id_tipo_movimiento`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipos_producto`
--

DROP TABLE IF EXISTS `tipos_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos_producto` (
                                  `id_tipo_producto` int NOT NULL AUTO_INCREMENT,
                                  `tipo_producto` varchar(100) DEFAULT NULL,
                                  `fecha_registro` timestamp NULL DEFAULT NULL,
                                  `id_usuario` int DEFAULT NULL,
                                  PRIMARY KEY (`id_tipo_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
                            `id_usuario` int NOT NULL AUTO_INCREMENT,
                            `usuario` varchar(50) DEFAULT NULL,
                            `password` varchar(500) DEFAULT NULL,
                            `primer_nombre` varchar(50) DEFAULT NULL,
                            `segundo_nombre` varchar(50) DEFAULT NULL,
                            `apellido` varchar(100) DEFAULT NULL,
                            `fecha_registro` timestamp NULL DEFAULT NULL,
                            `enabled` tinyint DEFAULT NULL,
                            PRIMARY KEY (`id_usuario`),
                            UNIQUE KEY `usuario_UNIQUE` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuarios_roles`
--

DROP TABLE IF EXISTS `usuarios_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_roles` (
                                  `usuario_id` int NOT NULL,
                                  `role_id` int NOT NULL,
                                  PRIMARY KEY (`usuario_id`,`role_id`),
                                  KEY `fk_usuarios_role_idrole_idx` (`role_id`),
                                  CONSTRAINT `fk_usuarios_roles_idrole` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id_role`) ON UPDATE CASCADE,
                                  CONSTRAINT `fk_usuarios_roles_idusuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'techos_db'
--
/*!50003 DROP FUNCTION IF EXISTS `generar_codigo_aleatorio` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `generar_codigo_aleatorio`() RETURNS varchar(255) CHARSET utf8mb4 COLLATE utf8mb4_general_ci
    DETERMINISTIC
BEGIN
    DECLARE numeroAleatorio INT;
    DECLARE resultadoTexto VARCHAR(255);

    -- Generar un número aleatorio en el rango de 0 a 10,000
    SET numeroAleatorio = FLOOR(RAND() * 10000001);

    -- Convertir el número a texto y asignarlo al resultado
    SET resultadoTexto = CAST(numeroAleatorio AS CHAR);

    -- Devolver el resultado
RETURN resultadoTexto;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `get_cant_clientes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `get_cant_clientes`() RETURNS int
    DETERMINISTIC
BEGIN
	DECLARE cant_clientes INT;
SELECT COUNT(*) INTO cant_clientes FROM clientes;
return cant_clientes;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `get_cant_productos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `get_cant_productos`() RETURNS int
    DETERMINISTIC
BEGIN
	DECLARE cant_productos INT;
SELECT
    COUNT(*) INTO cant_productos
FROM
    productos
WHERE
    id_estado <> 5;
return cant_productos;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `get_cant_usuarios` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `get_cant_usuarios`() RETURNS int
    DETERMINISTIC
BEGIN
	DECLARE users INT;
SELECT
    COUNT(*) INTO users
FROM
    usuarios
WHERE
    enabled <> 0;
return users;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `get_cant_ventas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `get_cant_ventas`() RETURNS int
    DETERMINISTIC
BEGIN
	DECLARE MAXSALES INT;
	DECLARE CANTSALES INT;
SELECT
    COUNT(1) INTO MAXSALES
FROM
    facturas
WHERE
    id_estado <> 4;
SET CANTSALES = MAXSALES;
RETURN CANTSALES;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `get_mes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `get_mes`(mes int) RETURNS text CHARSET utf8mb4 COLLATE utf8mb4_general_ci
    DETERMINISTIC
BEGIN
	DECLARE NOMBRE_MES VARCHAR(100);

SELECT CASE
           WHEN mes = 1 THEN 'ENERO'
           WHEN mes = 2 THEN 'FEBRERO'
           WHEN mes = 3 THEN 'MARZO'
           WHEN mes = 4 THEN 'ABRIL'
           WHEN mes = 5 THEN 'MAYO'
           WHEN mes = 6 THEN 'JUNIO'
           WHEN mes = 7 THEN 'JULIO'
           WHEN mes = 8 THEN 'AGOSTO'
           WHEN mes = 9 THEN 'SEPTIEMBRE'
           WHEN mes = 10 THEN 'OCTUBRE'
           WHEN mes = 11 THEN 'NOVIEMBRE'
           WHEN mes = 12 THEN 'DICIEMBRE'
           ELSE ''
           END
INTO NOMBRE_MES;
RETURN NOMBRE_MES;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `get_numero_letras` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `get_numero_letras`(XNumero NUMERIC(20,2)) RETURNS varchar(512) CHARSET utf8mb3
    DETERMINISTIC
BEGIN
DECLARE XlnEntero INT;
DECLARE XlcRetorno VARCHAR(512);
DECLARE XlnTerna INT;
DECLARE XlcMiles VARCHAR(512);
DECLARE XlcCadena VARCHAR(512);
DECLARE XlnUnidades INT;
DECLARE XlnDecenas INT;
DECLARE XlnCentenas INT;
DECLARE XlnFraccion INT;
DECLARE Xresultado varchar(512);

SET XlnEntero = FLOOR(XNumero);
SET XlnFraccion = (XNumero - XlnEntero) * 100;
SET XlcRetorno = '';
SET XlnTerna = 1 ;
    WHILE( XlnEntero > 0) DO

        #Recorro terna por terna
        SET XlcCadena = '';
        SET XlnUnidades = XlnEntero MOD 10;
        SET XlnEntero = FLOOR(XlnEntero/10);
        SET XlnDecenas = XlnEntero MOD 10;
        SET XlnEntero = FLOOR(XlnEntero/10);
        SET XlnCentenas = XlnEntero MOD 10;
        SET XlnEntero = FLOOR(XlnEntero/10);

        #Analizo las unidades
        SET XlcCadena =
            CASE # UNIDADES
                WHEN XlnUnidades = 1 AND XlnTerna = 1 THEN CONCAT('UNO ', XlcCadena)
                -- WHEN XlnUnidades = 1 AND XlnTerna <> 1 THEN CONCAT('UN ', XlcCadena)
                WHEN XlnUnidades = 2 THEN CONCAT('DOS ', XlcCadena)
                WHEN XlnUnidades = 3 THEN CONCAT('TRES ', XlcCadena)
                WHEN XlnUnidades = 4 THEN CONCAT('CUATRO ', XlcCadena)
                WHEN XlnUnidades = 5 THEN CONCAT('CINCO ', XlcCadena)
                WHEN XlnUnidades = 6 THEN CONCAT('SEIS ', XlcCadena)
                WHEN XlnUnidades = 7 THEN CONCAT('SIETE ', XlcCadena)
                WHEN XlnUnidades = 8 THEN CONCAT('OCHO ', XlcCadena)
                WHEN XlnUnidades = 9 THEN CONCAT('NUEVE ', XlcCadena)
                ELSE XlcCadena
END; #UNIDADES

#Analizo las decenas
        SET XlcCadena =
            CASE #DECENAS
                WHEN XlnDecenas = 1 THEN
                    CASE XlnUnidades
                        WHEN 0 THEN 'DIEZ '
                        WHEN 1 THEN 'ONCE '
                        WHEN 2 THEN 'DOCE '
                        WHEN 3 THEN 'TRECE '
                        WHEN 4 THEN 'CATORCE '
                        WHEN 5 THEN 'QUINCE'
                        ELSE CONCAT('DIECI', XlcCadena)
END
WHEN XlnDecenas = 2 AND XlnUnidades = 0 THEN CONCAT('VEINTE ', XlcCadena)
                WHEN XlnDecenas = 2 AND XlnUnidades <> 0 THEN CONCAT('VEINTI', XlcCadena)
                WHEN XlnDecenas = 3 AND XlnUnidades = 0 THEN CONCAT('TREINTA ', XlcCadena)
                WHEN XlnDecenas = 3 AND XlnUnidades <> 0 THEN CONCAT('TREINTA Y ', XlcCadena)
                WHEN XlnDecenas = 4 AND XlnUnidades = 0 THEN CONCAT('CUARENTA ', XlcCadena)
                WHEN XlnDecenas = 4 AND XlnUnidades <> 0 THEN CONCAT('CUARENTA Y ', XlcCadena)
                WHEN XlnDecenas = 5 AND XlnUnidades = 0 THEN CONCAT('CINCUENTA ', XlcCadena)
                WHEN XlnDecenas = 5 AND XlnUnidades <> 0 THEN CONCAT('CINCUENTA Y ', XlcCadena)
                WHEN XlnDecenas = 6 AND XlnUnidades = 0 THEN CONCAT('SESENTA ', XlcCadena)
                WHEN XlnDecenas = 6 AND XlnUnidades <> 0 THEN CONCAT('SESENTA Y ', XlcCadena)
                WHEN XlnDecenas = 7 AND XlnUnidades = 0 THEN CONCAT('SETENTA ', XlcCadena)
                WHEN XlnDecenas = 7 AND XlnUnidades <> 0 THEN CONCAT('SETENTA Y ', XlcCadena)
                WHEN XlnDecenas = 8 AND XlnUnidades = 0 THEN CONCAT('OCHENTA ', XlcCadena)
                WHEN XlnDecenas = 8 AND XlnUnidades <> 0 THEN CONCAT('OCHENTA Y ', XlcCadena)
                WHEN XlnDecenas = 9 AND XlnUnidades = 0 THEN CONCAT('NOVENTA ', XlcCadena)
                WHEN XlnDecenas = 9 AND XlnUnidades <> 0 THEN CONCAT('NOVENTA Y ', XlcCadena)
                ELSE XlcCadena
END; #DECENAS

# Analizo las centenas
        SET XlcCadena =
            CASE # CENTENAS
                WHEN XlnCentenas = 1 AND XlnUnidades = 0 AND XlnDecenas = 0 THEN CONCAT('CIEN ', XlcCadena)
                WHEN XlnCentenas = 1 AND NOT(XlnUnidades = 0 AND XlnDecenas = 0) THEN CONCAT('CIENTO ', XlcCadena)
                WHEN XlnCentenas = 2 THEN CONCAT('DOSCIENTOS ', XlcCadena)
                WHEN XlnCentenas = 3 THEN CONCAT('TRESCIENTOS ', XlcCadena)
                WHEN XlnCentenas = 4 THEN CONCAT('CUATROCIENTOS ', XlcCadena)
                WHEN XlnCentenas = 5 THEN CONCAT('QUINIENTOS ', XlcCadena)
                WHEN XlnCentenas = 6 THEN CONCAT('SEISCIENTOS ', XlcCadena)
                WHEN XlnCentenas = 7 THEN CONCAT('SETECIENTOS ', XlcCadena)
                WHEN XlnCentenas = 8 THEN CONCAT('OCHOCIENTOS ', XlcCadena)
                WHEN XlnCentenas = 9 THEN CONCAT('NOVECIENTOS ', XlcCadena)
                ELSE XlcCadena
END; #CENTENAS

# Analizo la terna
        SET XlcCadena =
            CASE # TERNA
                WHEN XlnTerna = 1 THEN XlcCadena
                WHEN XlnTerna = 2 AND (XlnUnidades + XlnDecenas + XlnCentenas <> 0) THEN CONCAT(XlcCadena,  'MIL ')
                WHEN XlnTerna = 3 AND (XlnUnidades + XlnDecenas + XlnCentenas <> 0) AND XlnUnidades = 1 AND XlnDecenas = 0 AND XlnCentenas = 0 THEN CONCAT(XlcCadena, 'MILLON ')
                WHEN XlnTerna = 3 AND (XlnUnidades + XlnDecenas + XlnCentenas <> 0) AND NOT (XlnUnidades = 1 AND XlnDecenas = 0 AND XlnCentenas = 0) THEN CONCAT(XlcCadena, 'MILLONES ')
                WHEN XlnTerna = 4 AND (XlnUnidades + XlnDecenas + XlnCentenas <> 0) THEN CONCAT(XlcCadena, 'MIL MILLONES ')
                ELSE ''
END; #TERNA

#Armo el retorno terna a terna
        SET XlcRetorno = CONCAT(XlcCadena, XlcRetorno);
        SET XlnTerna = XlnTerna + 1;
END WHILE; # WHILE

    IF XlnTerna = 1 THEN SET XlcRetorno = 'CERO'; END IF;

SET Xresultado = CONCAT('', RTRIM(XlcRetorno), ' QUETZALES CON ', LTRIM(XlnFraccion), '/100 ');

RETURN Xresultado;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PR_CONSULTAR_PRODUCTOS` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PR_CONSULTAR_PRODUCTOS`()
BEGIN
SELECT *
FROM productos
WHERE id_estado = 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PR_VENTAS_MENSUALES` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `PR_VENTAS_MENSUALES`(V_YEAR INT)
    DETERMINISTIC
BEGIN
SELECT get_mes(MONTH(fecha)) AS MES, SUM(total) AS TOTAL
FROM facturas
WHERE YEAR(fecha) = V_YEAR
GROUP BY MONTH(fecha);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-18 11:51:45
