import { UsuarioAuxiliar } from "./auxiliar/usuario-auxiliar";

export class Caja {
    idCaja: number;
    montoApertura: number;
    montoActual: number;
    fechaApertura: Date;
    estado: string;

    usuario: UsuarioAuxiliar;
}
