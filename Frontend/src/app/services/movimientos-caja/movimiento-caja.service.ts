import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';

import { MovimientoCaja } from 'src/app/models/movimiento-caja';
import { global } from '../global';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MovimientoCajaService {

  url: string;

  constructor(private httpClient: HttpClient) {
    this.url = global.url;
  }

  getMovimientosCaja(): Observable<MovimientoCaja[]> {
    return this.httpClient.get<MovimientoCaja[]>(`${this.url}/movimientos-caja/get`).pipe(
      catchError(e => {
        return throwError(e);
      })
    );
  }

  getMovimientosCajaPorCaja(idCaja: number): Observable<MovimientoCaja[]> {
    return this.httpClient.get<MovimientoCaja[]>(`${this.url}/movimientos-caja/movimientos/${idCaja}/get`).pipe(
      catchError(e => {
        return throwError(e);
      })
    );
  }

  getMovimientoCaja(id: number): Observable<MovimientoCaja> {
    return this.httpClient.get<MovimientoCaja>(`${this.url}/movimientos-caja/movimiento/${id}/get`).pipe(
      catchError(e => {
        return throwError(e);
      })
    );
  }

  
  create(movimientoCaja: MovimientoCaja): Observable<MovimientoCaja> {
    return this.httpClient.post<MovimientoCaja>(`${this.url}/movimientos-caja/post`, movimientoCaja).pipe(
      catchError(e => {
        return throwError(e);
      })
    );
  }
}
