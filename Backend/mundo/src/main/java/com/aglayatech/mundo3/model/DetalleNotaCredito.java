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

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
@Entity
@Table(name = "detalle_notas_credito")
public class DetalleNotaCredito implements Serializable {

    private static final long serialVersionUID = 379217396480999650L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleNotaCredito;
    private int cantidadDevuelta;
    private BigDecimal subTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private Producto producto;
}
