import { Producto } from './producto';

export class DetalleFactura {
    idDetalle: number;
    cantidad = 1;
    subTotal: number;
    descuento: number;
    subTotalDescuento: number;

    producto: Producto;

    public calcularImporte(): number{
        return this.producto.precioVenta * this.cantidad;
    }

    public calcularPrecioDescuento(): number{
        return this.descuento <= 0 ? this.producto.precioVenta 
        : Math.round((this.producto.precioVenta - this.descuento) * 100) / 100;
    }

    public calcularImporteDescuento(): number{
        return this.descuento <= 0 ? this.producto.precioVenta * this.cantidad
        : Math.round((this.producto.precioVenta - this.descuento) * this.cantidad * 100) / 100;
    }

    public calcularImporteDescuentoSinPorcentaje(): number {
        return this.descuento;
    }
}
