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
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "certificadores")
public class Certificador implements Serializable {

    private static final long serialVersionUID = 1836583438171174700L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCertificador;
    private String aliasWs;
    private String llaveWs;
    private String tokenSigner;

    private String prefijo;

    private String correoCopia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado")
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private Estado estado;
}
