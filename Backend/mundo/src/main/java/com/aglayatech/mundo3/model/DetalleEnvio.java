package com.aglayatech.mundo3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ToString
@Entity
@Table(name = "detalle_envio")
public class DetalleEnvio implements Serializable {

    private static final long serialVersionUID = 684391554563623232L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalleEnvio;
    private double subTotal;
    @NotNull(message = "Cantidad no puede ser 0.")
    private int cantidad;
    private float descuento;
    private double subTotalDescuento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    @JsonIgnoreProperties(value = {"movimientos", "hibernateLazyInitializer", "handler"})
    private Producto producto;

}
