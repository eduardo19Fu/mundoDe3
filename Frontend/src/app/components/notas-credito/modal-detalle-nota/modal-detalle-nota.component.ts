import { Component, Input, OnInit } from '@angular/core';
import { NotaCredito } from '../../../models/nota-credito';

@Component({
  selector: 'app-modal-detalle-nota',
  templateUrl: './modal-detalle-nota.component.html',
  styleUrls: ['./modal-detalle-nota.component.css']
})
export class ModalDetalleNotaComponent implements OnInit {

  @Input() notaSeleccionada: NotaCredito;

  constructor() { }

  ngOnInit(): void {
  }

}
