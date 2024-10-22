import { Producto } from "./producto";

export class DetalleNotaCredito {
    idDetalleNotaCredito: number;
    cantidadDevuelta: number;
    subTotal: number;

    producto: Producto;
    
    public calcularImporte(): number{
        return this.producto.precioVenta * this.cantidadDevuelta;
    }

    /** Metodo que calcula el nuevo precio venta con o sin descuento aplicado
     * @returns Valor del precioVenta por item
     */
    public calcularNuevoPrecioVenta(): number {
        return parseFloat(this.producto.precioVenta.toFixed(2));
        
    }

    /**
     * Método que calcula el subTotal del item en caso de poseer o no descuento.
     * @returns subTotal con descuento aplicado
     */
    public calcularImporteDescuento(): number {
        const importeSinDescuento = this.producto.precioVenta * this.cantidadDevuelta;
        return parseFloat(importeSinDescuento.toFixed(2));
        
    }
}
