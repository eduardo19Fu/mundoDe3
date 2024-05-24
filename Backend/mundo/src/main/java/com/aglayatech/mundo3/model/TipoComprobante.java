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
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "tipos_comprobante")
public class TipoComprobante implements Serializable {

    private static final long serialVersionUID = -6602155287631059914L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoComprobante;
    @NotNull(message = "Tipo de Comprobante no puede estar vacío.")
    private String tipoComprobante;
}
