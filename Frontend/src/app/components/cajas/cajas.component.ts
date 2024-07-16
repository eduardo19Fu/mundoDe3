import { Component, OnInit } from '@angular/core';

import { Caja } from '../../models/caja';
import { CajaService } from '../../services/cajas/caja.service';
import { AuthService } from '../../services/auth.service';

import { JqueryConfigs } from '../../utils/jquery/jquery-utils';

@Component({
  selector: 'app-cajas',
  templateUrl: './cajas.component.html',
  styleUrls: ['./cajas.component.css']
})
export class CajasComponent implements OnInit {

  title: String;

  cajaUsuario: Caja;
  cajas: Caja[];
  jQueryConfigs: JqueryConfigs;

  constructor
  (
    private cajaService: CajaService,
    public auth: AuthService
  ) {
    this.title = 'Listado de Cajas';
    this.cajaUsuario = new Caja();
    this.cajas = [];
    this.jQueryConfigs = new JqueryConfigs();
  }

  ngOnInit(): void {
    // this.loadCajas();
    this.loadCaja();
  }

  loadCajas(): void {
    this.cajaService.getCajas().subscribe(
      response => {
        this.cajas = response;
        this.jQueryConfigs.configDataTable("cajas");
      }
    );
  }

  loadCaja(): void {
    this.cajaService.getCajaPorUsuario(this.auth.usuario.idUsuario).subscribe(
      response => {
        this.cajaUsuario = response;
        console.log(this.cajaUsuario);
      }
    );
  }

  delete(caja: Caja): void {}

}
