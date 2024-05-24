package com.aglayatech.mundo3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "compras")
public class Compra implements Serializable {

    private static final long serialVersionUID = -7478350419283560351L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompra;
    private String noComprobante;
    private Float iva;
    private BigDecimal totalFlete;
    private BigDecimal totalDescuento;
    private BigDecimal totalCompra;
    private LocalDate fechaCompra;
    private LocalDateTime fechaRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedor")
    @JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
    private Proveedor proveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado")
    @JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
    private Estado estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_comprobante")
    @JsonIgnoreProperties(value ={ "hibernateLazyInitializer", "handler" })
    private TipoComprobante tipoComprobante;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_compra")
    @JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
    private List<DetalleCompra> items;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    @PrePersist
    public void prepersist() {
        this.fechaRegistro = LocalDateTime.now();
    }
}
