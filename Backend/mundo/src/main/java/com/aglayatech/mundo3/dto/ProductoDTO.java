package com.aglayatech.mundo3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDTO {
    private Integer idProducto;
    private String codProducto;
    private String serie;
    private String nombre;
    private BigDecimal precioVenta;
    private BigDecimal precioSugerido;
    private BigDecimal precioCompra;
    private float porcentajeGanancia;
    private String imagen;
    private String descripcion;
    private String link;
    private LocalDate fechaVencimiento;
    private LocalDate fechaIngreso;
    private LocalDate fechaRegistro;
    private int stock;
    private String marcaProducto;
    private String tipoProducto;
    private String estado;
    private int idEstado;
    private String proveedor;
}
