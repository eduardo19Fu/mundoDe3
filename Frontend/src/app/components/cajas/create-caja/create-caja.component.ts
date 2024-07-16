import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Caja } from '../../../models/caja';
import { Usuario } from '../../../models/usuario';

import { CajaService } from '../../../services/cajas/caja.service';
import { UsuarioService } from '../../../services/usuarios/usuario.service';

import Swal from 'sweetalert2';

@Component({
  selector: 'app-create-caja',
  templateUrl: './create-caja.component.html',
  styles: [
  ]
})
export class CreateCajaComponent implements OnInit {

  title: string;
  
  caja: Caja;
  usuarios: Usuario[];

  constructor(
    private cajaService: CajaService,
    private usuarioService: UsuarioService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.title = 'Apertura de Caja';
    this.caja = new Caja();
    this. usuarios = [];
  }

  ngOnInit(): void {
    this.loadUsuarios();
  }

  create(): void {
    this.cajaService.create(this.caja).subscribe(
      response => {
        this.router.navigate(['/cajas/index']);
        Swal.fire('Caja Aperturada', `La caja ${response.idCaja} ha sido aperturada con éxito`, 'success');
      }, error => {
        console.log(error);
      }
    );
  }

  createV2(): void {
    this.cajaService.createV2(this.caja).subscribe(
      response => {
        if(response) {
          if(response.code === '30001') {
            this.router.navigate(['/cajas/index']);
            Swal.fire('Caja Aperturada', `${response.mensaje}: ${response.caja.idCaja}`, 'success');
          } else if(response.code === '30002') {
            Swal.fire('¡Advertencia!',`Usuario ${this.caja.usuario.usuario} ya posee una caja aperturada`, 'warning');
          }
        }
      }
    );
  }

  update(): void {
    // TODO: Queda pendiente la implementación de la actualización ya que no es necesario para esta primera version
  }

  loadUsuarios(): void {
    this.usuarioService.getCajeros().subscribe(response => this.usuarios = response);
  }

}
