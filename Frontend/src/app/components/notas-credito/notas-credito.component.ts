import { Component, OnInit } from '@angular/core';

import { NotaCreditoService } from '../../services/notas-credito/nota-credito.service';
import { NotaCredito } from '../../models/nota-credito';
import { JqueryConfigs } from '../../utils/jquery/jquery-utils';

@Component({
  selector: 'app-notas-credito',
  templateUrl: './notas-credito.component.html',
  styleUrls: ['./notas-credito.component.css']
})
export class NotasCreditoComponent implements OnInit {

  title: string;
  jQueryConfigs: JqueryConfigs;

  notaSeleccionada: NotaCredito;
  notasCredito: NotaCredito[] = [];

  constructor(
    private notaCreditoService: NotaCreditoService,
  ) {
    this.title = 'Notas de Credito';
    this.jQueryConfigs = new JqueryConfigs();
  }

  ngOnInit(): void {
  }

}
