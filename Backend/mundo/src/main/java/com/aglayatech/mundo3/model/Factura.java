package com.aglayatech.mundo3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "facturas")
public class Factura implements Serializable {

	private static final long serialVersionUID = 8846940318850867537L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFactura;
	private Long noFactura;
	private String serie;
	private Double total;
	private Double iva;
	private String correlativoSat;
	private String certificacionSat;
	private String serieSat;
	private String mensajeSat;
	private String fechaCertificacionSat;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_estado")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Estado estado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	@JsonIgnoreProperties({ "password", "hibernateLazyInitializer", "handler" })
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Cliente cliente;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_factura")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private List<DetalleFactura> itemsFactura;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_factura")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private TipoFactura tipoFactura;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "factura")
	@JsonIgnoreProperties(value = {"factura", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private Envio envio;

	@PrePersist
	public void initFecha() {
		this.fecha = new Date();
	}
}
