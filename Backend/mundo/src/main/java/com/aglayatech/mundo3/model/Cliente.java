package com.aglayatech.mundo3.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = -6380220471531863389L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCliente;
	private String nombre;
	private String nit;
	private String direccion;
	private String telefono;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro;

	@PrePersist
	public void configFechaRegistro() {
		fechaRegistro = new Date();
	}

}
