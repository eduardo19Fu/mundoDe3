package com.aglayatech.mundo3.model;

import com.aglayatech.mundo3.model.enums.TipoMovimientoCajaEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
@ToString
@Entity
@Table(name = "movimientos_caja")
public class MovimientoCaja implements Serializable {

    private static final long serialVersionUID = -7293434535883025126L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovimientoCaja;
    private LocalDateTime fechaMovimiento;
    private String observaciones;
    private BigDecimal montoMovimiento;

    @Enumerated(EnumType.STRING)
    private TipoMovimientoCajaEnum tipoMovimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_caja")
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private Caja caja;
}
