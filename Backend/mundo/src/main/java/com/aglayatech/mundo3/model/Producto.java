package com.aglayatech.mundo3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "productos")
public class Producto implements Serializable {

	private static final long serialVersionUID = 629262714163378345L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProducto;
	private String codProducto;
	private String serie;
	private String nombre;
	private Double precioCompra;
	private Double precioVenta;
	private Double precioSugerido;
	private float porcentajeGanancia;
	private String imagen;
	private String descripcion;
	private String link;

	@Temporal(TemporalType.DATE)
	private Date fechaVencimiento;

	@Temporal(TemporalType.DATE)
	private Date fechaIngreso;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro;

	private int stock;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_marca_producto")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private MarcaProducto marcaProducto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_producto")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private TipoProducto tipoProducto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_estado")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Estado estado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_proveedor")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Proveedor proveedor;

	@PrePersist
	public void configFechaRegistro() {
		this.fechaRegistro = new Date();
	}

}
