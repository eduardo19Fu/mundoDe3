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
@Table(name = "paises")
public class Pais implements Serializable {

    private static final long serialVersionUID = -1965186139096423445L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPais;
    private String nombre;
}
