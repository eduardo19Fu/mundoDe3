import { Component, OnInit } from '@angular/core';
import { CajaService } from '../../services/cajas/caja.service';
import { AuthService } from '../../services/auth.service';
import { Caja } from '../../models/caja';
import { JqueryConfigs } from '../../utils/jquery/jquery-utils';

@Component({
  selector: 'app-cajas',
  templateUrl: './cajas.component.html',
  styles: [
  ]
})
export class CajasComponent implements OnInit {

  title: String;

  cajas: Caja[];
  jQueryConfigs: JqueryConfigs;

  constructor
  (
    private cajaService: CajaService,
    public auth: AuthService
  ) {
    this.title = 'Listado de Cajas';
    this.jQueryConfigs = new JqueryConfigs();
  }

  ngOnInit(): void {
    this.loadCajas();
  }

  loadCajas(): void {
    this.cajaService.getCajas().subscribe(
      response => {
        this.cajas = response;
        this.jQueryConfigs.configDataTable("cajas");
      }
    );
  }

  delete(caja: Caja): void {}

}
