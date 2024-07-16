import { Component, OnInit } from '@angular/core';
import { JqueryConfigs } from '../../utils/jquery/jquery-utils';
import { MovimientoCajaService } from 'src/app/services/movimientos-caja/movimiento-caja.service';

@Component({
  selector: 'app-movimientos-caja',
  templateUrl: './movimientos-caja.component.html',
  styles: [
  ]
})
export class MovimientosCajaComponent implements OnInit {

  title: string;

  jqueryConfigs: JqueryConfigs;

  constructor(
    private movimientoCajaService: MovimientoCajaService
  ) {
    this.title = 'Movimientos de Caja';
    this.jqueryConfigs = new JqueryConfigs();
  }

  ngOnInit(): void {
  }

}
