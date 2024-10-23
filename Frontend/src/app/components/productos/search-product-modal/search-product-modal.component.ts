import { Component, OnInit } from '@angular/core';
import { Producto } from 'src/app/models/producto';
import { ProductoService } from '../../../services/producto.service';
import { JqueryConfigs } from '../../../utils/jquery/jquery-utils';
import { ProductoDTO } from '../../../dto/producto-dto';

@Component({
  selector: 'app-search-product-modal',
  templateUrl: './search-product-modal.component.html',
  styleUrls: ['./search-product-modal.component.css']
})
export class SearchProductModalComponent implements OnInit {

  title: string;
  productos: Producto[];
  productosDto: ProductoDTO[];


  jqueryConfig: JqueryConfigs;

  constructor(
    private productoService: ProductoService
  ) {
    this.title = 'Listado de Productos';
    this.jqueryConfig = new JqueryConfigs();
  }

  ngOnInit(): void {
    this.loadProductos();
  }

  loadProductos(): void{
    this.productoService.getProductos().subscribe(
      productos => {
        this.productos = productos;
        this.jqueryConfig.configDataTableModal('productos');
      }
    );
  }

  loadProductosDto(): void {
    this.productoService.getProductosDto().subscribe(
      productosDto => {
        this.productosDto = productosDto;
        this.jqueryConfig.configDataTableModal('productos');
      }
    );
  }

}
