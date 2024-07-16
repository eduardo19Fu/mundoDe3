import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MovimientoCajaService } from '../../../services/movimientos-caja/movimiento-caja.service';

@Component({
  selector: 'app-modal-crear-movimiento-caja',
  templateUrl: './modal-crear-movimiento-caja.component.html',
  styleUrls: ['./modal-crear-movimiento-caja.component.css']
})
export class ModalCrearMovimientoCajaComponent implements OnInit {

  @Input() tipoMovimiento: string;

  title: string;

  constructor(
    private movimientoCajaService: MovimientoCajaService,
    private router: Router
  ) {
    this.title = 'Crear movimiento de caja';
  }

  ngOnInit(): void {
  }

}
