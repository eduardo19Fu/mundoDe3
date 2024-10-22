import { Producto } from './producto';
import { UsuarioAuxiliar } from './auxiliar/usuario-auxiliar';

export class MovimientoProducto {
    idMovimiento: number;
    cantidad: number;
    stockInicial: number;
    fechaMovimiento: Date;
    tipoMovimiento: string;
    
    producto: Producto;
    usuario: UsuarioAuxiliar;
}
