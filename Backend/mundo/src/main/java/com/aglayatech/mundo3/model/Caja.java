package com.aglayatech.mundo3.model;

import com.aglayatech.mundo3.model.enums.EstadoCajaEnum;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "cajas")
public class Caja implements Serializable {

    private static final long serialVersionUID = -4667099713462376428L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCaja;
    private BigDecimal montoApertura;
    private BigDecimal montoActual;
    private LocalDateTime fechaApertura;

    @Enumerated(EnumType.STRING)
    private EstadoCajaEnum estado;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonIgnoreProperties(value = {"roles", "password"})
    private Usuario usuario;
}
