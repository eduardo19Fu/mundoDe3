import { Component, OnInit, Output, EventEmitter, ElementRef, AfterViewInit, ChangeDetectorRef, OnDestroy } from '@angular/core';
import { ProductoService } from '../../../../services/producto.service';
import { Producto } from '../../../../models/producto';
import { JqueryConfigs } from '../../../../utils/jquery/jquery-utils';
import { ProductoDTO } from '../../../../dto/producto-dto';

declare var $: any;

@Component({
  selector: 'app-modal-buscar-producto',
  templateUrl: './modal-buscar-producto.component.html',
  styles: [
  ]
})
export class ModalBuscarProductoComponent implements OnInit {

  @Output() producto = new EventEmitter<Producto>();

  title: string;
  loading: boolean = false;
  productos: Producto[] = [];
  productosDto: ProductoDTO[] = [];

  jqueryConfigs: JqueryConfigs;

  constructor(
    private productoService: ProductoService,
    private elementRef: ElementRef,
    private cdr: ChangeDetectorRef
  ) {
    this.title = 'Búsqueda de Productos';
    this.jqueryConfigs = new JqueryConfigs();  
  }

  ngOnInit(): void {
    // this.loadProductos();
    this.loadProductosDto();
  }

  loadProductos(): void{
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

  chooseProducto(producto: Producto): void{
    this.producto.emit(producto);
  }
}
