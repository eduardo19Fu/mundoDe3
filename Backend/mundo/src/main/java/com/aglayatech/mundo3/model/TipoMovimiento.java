package com.aglayatech.mundo3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "tipos_movimiento")
public class TipoMovimiento implements Serializable {

    private static final long serialVersionUID = 730158680729298687L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoMovimiento;
    private String tipoMovimiento;
}
