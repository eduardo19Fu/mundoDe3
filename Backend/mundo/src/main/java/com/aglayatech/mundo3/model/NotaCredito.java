package com.aglayatech.mundo3.model;

import com.aglayatech.mundo3.model.enums.TipoDevolucionEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "notas_credito")
public class NotaCredito implements Serializable {

    private static final long serialVersionUID = -6921001179988501610L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotaCredito;
    private LocalDateTime fechaNotaCredito;
    private BigDecimal montoNota;
    private String serieComprobante;
    private Long noComprobante;
    private boolean refacturado;

    @Enumerated(EnumType.STRING)
    private TipoDevolucionEnum tipoDevolucion;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_devolucion")
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private List<DetalleNotaCredito> items;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonIgnoreProperties(value = {"password", "roles", "enabled"})
    private Usuario usuario;

    @PrePersist
    public void prepersist() {
        fechaNotaCredito = LocalDateTime.now();
    }
}
