import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';


import { NotaCreditoService } from '../../../services/notas-credito/nota-credito.service';
import { UsuarioService } from '../../../services/usuarios/usuario.service';
import { ClienteService } from '../../../services/cliente.service';
import { ClienteCreateService } from '../../../services/facturas/cliente-create.service';
import { ProductoService } from '../../../services/producto.service';
import { AuthService } from '../../../services/auth.service';

import { NotaCredito } from '../../../models/nota-credito';
import { DetalleNotaCredito } from '../../../models/detalle-nota-credito';
import { UsuarioAuxiliar } from '../../../models/auxiliar/usuario-auxiliar';
import { Cliente } from '../../../models/cliente';
import { Producto } from '../../../models/producto';

import Swal from 'sweetalert2';

@Component({
  selector: 'app-create-nota-credito',
  templateUrl: './create-nota-credito.component.html',
  styles: [
  ]
})
export class CreateNotaCreditoComponent implements OnInit {

  @ViewChild('mybuscar') myBuscarTexto: ElementRef;

  title: string;
  nitIngresado: string;

  notaCredito: NotaCredito;
  usuario: UsuarioAuxiliar;
  cliente: Cliente;
  producto: Producto;

  constructor(
    private notaCreditoService: NotaCreditoService,
    private productoService: ProductoService,
    private usuarioService: UsuarioService,
    private clienteService: ClienteService,
    private clienteCreateService: ClienteCreateService,
    private authService: AuthService,
    private router: Router
  ) {
    this.title = 'Crear Nota de Crédito';
    this.notaCredito = new NotaCredito();
    this.usuario = new UsuarioAuxiliar();
    this.cliente = new Cliente();
    this.producto = new Producto();
  }

  ngOnInit(): void {
    this.loadUsuario();
    this.loadClientePorDefecto();
  }

  loadUsuario(): void {
    this.usuarioService.getUsuario(this.authService.usuario.idUsuario).subscribe(
      usuario => {
        this.usuario = usuario;
      }
    );
  }

  loadCliente(event: any): void {
    (document.getElementById('buscar') as HTMLInputElement).value = event.nit;
    (document.getElementById('button-2x')).click();
    this.buscarCliente();
  }

  buscarCliente(): void {
    const nit = this.myBuscarTexto.nativeElement.value;

    if (nit) {
      this.clienteService.getClienteByNit(nit).subscribe(
        cliente => {
          this.cliente = cliente;
          (document.getElementById('serie')).focus();
        },
        error => {
          if (error.status === 400) {
            Swal.fire(`Error: ${error.status}`, 'Petición Equivocada', 'error');
          }
          if (error.status === 404) {
            this.nitIngresado = nit;
            this.clienteCreateService.abrirModal();
          }
        }
      );
    } else {
      Swal.fire('NIT Vacío', 'Ingrese un valor valido para realizar la búsqueda.', 'warning');
    }
  }

  buscarProducto(): void {
    const codigo = ((document.getElementById('codigo') as HTMLInputElement)).value;

    if (codigo) {
      this.productoService.getProductoByCode(codigo).subscribe(
        producto => {
          this.producto = producto;
          // (document.getElementById('cantidad') as HTMLInputElement).focus();
          // (document.getElementById('cantidad') as HTMLInputElement).value = '';
          this.agregarLinea();
        },
        error => {
          if (error.status === 400) {
            Swal.fire(`Error: ${error.status}`, 'Petición no se puede llevar a cabo.', 'error');
          }

          if (error.status === 404) {
            Swal.fire(`Error: ${error.status}`, error.error.mensaje, 'error');
          }
        }
      );
    } else {
      Swal.fire('Código Inválido', 'Ingrese un código de producto válido para realizar la búsqueda.', 'warning');
    }
  }

  buscarProductoPorId(id: number): void {
    this.productoService.getProducto(id).subscribe(
      producto => {
        this.producto = producto;
        // (document.getElementById('cantidad') as HTMLInputElement).focus();
        this.agregarLinea();
      },
      error => {
        if (error.status === 400) {
          Swal.fire(`Error: ${error.status}`, 'Petición no se puede llevar a cabo.', 'error');
        }

        if (error.status === 404) {
          Swal.fire(`Error: ${error.status}`, error.error.mensaje, 'error');
        }
      }
    );
  }

  loadProducto(event): void {
    (document.getElementById('codigo') as HTMLInputElement).value = event.codigo;
    this.buscarProductoPorId(event.idProducto);
    (document.getElementById('button-x')).click();
    // (document.getElementById('cantidad') as HTMLInputElement).focus();
    // (document.getElementById('cantidad') as HTMLInputElement).value = '';
  }

  agregarLinea(): void {
    if (!this.cliente) { // Comprueba que el cliente exista
      Swal.fire('Ha ocurrido un Problema', 'Por favor, elija un cliente antes de llevar a cabo la venta.', 'error');
    } else {
      if (this.producto) { // comprueba que el producto exista
        const item = new DetalleNotaCredito();

        // item.cantidad = +((document.getElementById('cantidad') as HTMLInputElement)).value; // valor obtenido del formulario de cantidad
        item.cantidadDevuelta = 1;

        
          if (item.cantidadDevuelta && item.cantidadDevuelta !== 0) {
            if (this.existeItem(this.producto.idProducto)) {
              this.incrementaCantidad(this.producto.idProducto, item.cantidadDevuelta);
              this.producto = new Producto();
              // (document.getElementById('cantidad') as HTMLInputElement).value = '';
              (document.getElementById('codigo') as HTMLInputElement).focus();
              (document.getElementById('codigo') as HTMLInputElement).value = '';
            } else {
              item.producto = this.producto;
              item.subTotal = item.calcularImporte();
              this.notaCredito.items.push(item);
              this.producto = new Producto();

              // (document.getElementById('cantidad') as HTMLInputElement).value = '';
              (document.getElementById('codigo') as HTMLInputElement).focus();
              (document.getElementById('codigo') as HTMLInputElement).value = '';
            }

          } else if (item.cantidadDevuelta === 0) {
            Swal.fire('Cantidad Erronéa', 'La cantidad a agregar debe ser mayor a 0.', 'warning');
          } else if (!item.cantidadDevuelta) {
            Swal.fire('Valor Inválido', 'La cantidad no puede estar vacía.  Ingrese un valor válido.', 'warning');
          }
        
      }
    }
  }

  createNotaCredito(): void {}

  actualizarCantidad(idProducto: number, event: any): void {
    const cantidad = event.target.value as number;

    this.notaCredito.items = this.notaCredito.items.map((item: DetalleNotaCredito) => {
      if (idProducto === item.producto.idProducto) {
          item.cantidadDevuelta = cantidad;
          item.subTotal = item.calcularImporte();
      }

      return item;
    });
  }

  existeItem(id: number): boolean {
    let existe = false;
    this.notaCredito.items.forEach((item: DetalleNotaCredito) => {
      if (id === item.producto.idProducto) {
        existe = true;
      }
    });
    return existe;
  }

  incrementaCantidad(idProducto: number, cantidad: number): void {
    this.notaCredito.items = this.notaCredito.items.map((item: DetalleNotaCredito) => {
      if (idProducto === item.producto.idProducto) {
        item.cantidadDevuelta = item.cantidadDevuelta + cantidad;
        item.subTotal = item.calcularImporte();
      }

      return item;
    });
  }

  loadClientePorDefecto(): void {
    this.clienteService.getClienteByNit('CF').subscribe(
      response => {
        this.cliente = response;
      }
    );
  }

}
