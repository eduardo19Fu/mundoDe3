import { Caja } from "./caja";

export class MovimientoCaja {
    idMovimientoCaja: number;
    fechaMovimiento: Date;
    observaciones: string;
    montoMovimiento: number;
    tipoMovimiento: string;

    caja: Caja;
}
