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
@Table(name = "facturas_detalle")
public class DetalleFactura implements Serializable {

	private static final long serialVersionUID = -1329506259104351935L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDetalle;
	private Integer cantidad;
	private BigDecimal subTotal;
	private BigDecimal descuento;
	private BigDecimal subTotalDescuento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_producto")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Producto producto;

}
