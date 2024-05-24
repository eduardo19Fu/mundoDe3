package com.aglayatech.mundo3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "tipos_producto")
public class TipoProducto implements Serializable {

	private static final long serialVersionUID = 7717156391525891191L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoProducto;
	private String tipoProducto;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	@JsonIgnoreProperties({ "password", "roles", "hibernateLazyInitializer", "handler" })
	private Usuario usuario;

	@PrePersist
	public void configFechaRegistro() {
		this.fechaRegistro = new Date();
	}
}
