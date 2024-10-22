import { DetalleNotaCredito } from "./detalle-nota-credito";
import { Usuario } from "./usuario";

export class NotaCredito {
    idNotaCredito: number;
    fechaNotaCredito: Date;
    montoNota: number;
    serieComprobante: string;
    noComprobante: number;
    refacturado: boolean;
    tipoDevolucion: string;

    usuario: Usuario;
    items: DetalleNotaCredito[] = [];

    calcularTotal(): number{
        this.montoNota = 0;
        this.items.forEach((item: DetalleNotaCredito) => {
            this.montoNota += item.calcularImporteDescuento();
        });

        return this.montoNota;
    }
}
