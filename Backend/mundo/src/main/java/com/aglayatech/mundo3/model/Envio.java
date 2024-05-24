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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "envios")
public class Envio implements Serializable {

    private static final long serialVersionUID = 7553891315091377057L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEnvio;
    private LocalDate fechaPedido;
    private String telefonoReferencia;
    private Double totalEnvio;
    private Double abono;
    private Double saldoPendiente;
    private LocalDateTime fechaRegistro;
    @NotNull(message = "La referencia no puede estar vacía.")
    private String referencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_factura")
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private Factura factura;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_envio")
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private List<DetalleEnvio> itemsEnvio;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "estados_envio", joinColumns = @JoinColumn(name = "id_envio"), inverseJoinColumns = @JoinColumn(name = "id_estado"))
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private List<Estado> estados;

    @PrePersist
    private void prepersist() {
        this.fechaRegistro = LocalDateTime.now();
    }


    /**
     * Función que determina el tipo de estados a asignar cuando se crear un nuevo envío o se genera un movimiento
     * sobre el mismo.
     * @param estados Listado de estados disponibles en la base de datos para poder asignar
     * @return Devuelve el listado de estados para asignar al envío analizado.
     * */
    public List<Estado> determinarEstadosEnvio(List<Estado> estados) {
        List<Estado> newEstados = new ArrayList<>();

        if (abono <= 0 && totalEnvio > 0.0) {
            for(Estado estado : estados) {
                if (estado.getEstado().equals("pendiente".toUpperCase()) || estado.getEstado().equals("pagado".toUpperCase())) {
                    newEstados.add(estado);
                }
            }
        } else if (abono > 0.0) {
            for (Estado estado : estados) {
                if (estado.getEstado().equals("pendiente".toUpperCase()) || estado.getEstado().equals("no pagado".toUpperCase())) {
                    newEstados.add(estado);
                }
            }
        }

        return newEstados;
    }

    public static List<Estado> determinarEstadosEnvio(int movimiento, List<Estado> estados) {
        // movimiento 1 = DESPACHAR SOLO SI YA ESTA PAGADO O ABONO ES MAYOR QUE CERO
        // movimiento 2 = COMPLETAR PAGO SI ABONO ES MAYOR A CERO Y DESPACHAR

        List<Estado> newEstados = new ArrayList<>();

        switch (movimiento) {
            case 1:
                newEstados = estados.stream()
                                .filter((estado -> estado.getEstado().equals("despachado".toUpperCase()) || estado.getEstado().equals("no pagado".toUpperCase())))
                                .map(estado -> estado)
                                .collect(Collectors.toList());
                break;
            case 2:
                newEstados = estados
                                .stream()
                                .filter((estado -> estado.getEstado().equals("despachado".toUpperCase()) || estado.getEstado().equals("pagado".toUpperCase())))
                                .map(estado -> estado)
                                .collect(Collectors.toList());
                break;
            case 3:
                newEstados = estados
                        .stream()
                        .filter((estado -> estado.getEstado().equals("cancelado".toUpperCase()) || estado.getEstado().equals("no pagado".toUpperCase())))
                        .map(estado -> estado)
                        .collect(Collectors.toList());
                break;
            default:
                break;
        }

        return newEstados;
    }

}
