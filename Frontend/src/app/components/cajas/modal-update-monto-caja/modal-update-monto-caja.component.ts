import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Caja } from '../../../models/caja';
import { AuthService } from '../../../services/auth.service';
import { CajaService } from '../../../services/cajas/caja.service';

import Swal from 'sweetalert2';

@Component({
  selector: 'app-modal-update-monto-caja',
  templateUrl: './modal-update-monto-caja.component.html',
  styleUrls: ['./modal-update-monto-caja.component.css']
})
export class ModalUpdateMontoCajaComponent implements OnInit {

  title: string;

  @Input() caja: Caja;

  constructor(
    private cajaService: CajaService,
    private authService: AuthService,
    private router: Router
  ) {
    this.title = 'Actualizar Monto Caja';
  }

  ngOnInit(): void {
  }

  update(): void {
    this.cajaService.update(this.caja).subscribe();
  }

}
