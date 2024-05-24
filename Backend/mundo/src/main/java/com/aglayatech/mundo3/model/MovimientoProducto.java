package com.aglayatech.mundo3.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "movimientos_producto")
public class MovimientoProducto implements Serializable {

	private static final long serialVersionUID = -5640566713525007234L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMovimiento;
	private Integer cantidad;
	private Integer stockInicial;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaMovimiento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_producto")
	@JsonIgnoreProperties({ "movimientos", "hibernateLazyInitializer", "handler" })
	private Producto producto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_movimiento")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private TipoMovimiento tipoMovimiento;
	
	@PrePersist
	public void configFecha() {
		this.fechaMovimiento = new Date();
	}
	
	public void calcularStock() {
		if(this.getTipoMovimiento().getTipoMovimiento().equals("ENTRADA") || this.getTipoMovimiento().getTipoMovimiento().equals("ANULACION FACTURA")
				|| this.getTipoMovimiento().getTipoMovimiento().equals("COMPRA")) {

			int tempStock = this.producto.getStock();
			this.setStockInicial(tempStock);
			this.producto.setStock((tempStock + this.getCantidad()));

		} else if(this.getTipoMovimiento().getTipoMovimiento().equals("SALIDA") || this.getTipoMovimiento().getTipoMovimiento().equals("VENTA")
				|| this.getTipoMovimiento().getTipoMovimiento().equals("ELIMINAR_COMPRA")) {

			int tempStock = this.producto.getStock();
			this.setStockInicial(tempStock);
			this.producto.setStock((tempStock - this.getCantidad()));
		}
	}

}
