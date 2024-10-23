import { Component, OnInit, Output, EventEmitter } from '@angular/core';

import { ProductoService } from '../../../../services/producto.service';
import { Producto } from '../../../../models/producto';

import { JqueryConfigs } from 'src/app/utils/jquery/jquery-utils';
import { ProductoDTO } from '../../../../dto/producto-dto';

@Component({
  selector: 'app-modal-buscar-producto-movimiento',
  templateUrl: './modal-buscar-producto-movimiento.component.html',
  styles: [
  ]
})
export class ModalBuscarProductoMovimientoComponent implements OnInit {

  @Output() producto = new EventEmitter<Producto>();
  
  title: string;
  productos: Producto[];
  productosDto: ProductoDTO[];

  jqueryConfigs: JqueryConfigs = new JqueryConfigs();

  constructor(
    private productoService: ProductoService
  ) {
    this.title = 'Búscar Producto';
  }

  ngOnInit(): void {
    // this.loadProductos();
    this.loadProductosDto();
  }

  loadProductos(): void {
    this.productoService.getProductosActivosSP().subscribe(
      productos => {
        this.productos = productos;
        this.jqueryConfigs.configDataTableModal("productos");
      }
    );
  }

  loadProductosDto(): void {
    this.productoService.getProductosDto().subscribe(
      productosDto => {
        this.productosDto = productosDto;
        this.jqueryConfigs.configDataTableModal("productos");
      }
    );
  }
  
  chooseProducto(producto: Producto): void {
    this.producto.emit(producto);
  }

}
