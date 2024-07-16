import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Caja } from '../../models/caja';
import { catchError } from 'rxjs/operators';

import { global } from '../global';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class CajaService {

  url: string;

  constructor(private httpClient: HttpClient) {
    this.url = global.url;
  }

  getCajas(): Observable<Caja[]> {
    return this.httpClient.get<Caja[]>(`${this.url}/cajas/get`).pipe(
      catchError(e => {
        Swal.fire(e.error.code, e.error.message, 'error');
        return throwError(e);
      })
    );
  }

  getCajasPorFecha(fecha1: Date, fecha2: Date): Observable<Caja[]> {
    return this.httpClient.get<Caja[]>(`${this.url}/cajas/filtro-fecha/get`).pipe(
      catchError(e => {
        return throwError(e);
      })
    );
  }

  getCaja(id: number): Observable<Caja> {
    return this.httpClient.get<Caja>(`${this.url}/cajas/${id}/get`).pipe(
      catchError(e => {
        return throwError(e);
      })
    );
  }

  getCajaPorUsuario(idusuario: number): Observable<Caja> {
    return this.httpClient.get<Caja>(`${this.url}/cajas/caja-usuario/${idusuario}/get`).pipe(
      catchError(e => {
        return throwError(e);
      })
    );
  }

  create(caja: Caja): Observable<any> {
    return this.httpClient.post<any>(`${this.url}/cajas/post`, caja).pipe(
      catchError(e => {
        return throwError(e);
      })
    );
  }

  createV2(caja: Caja): Observable<any> {
    return this.httpClient.post<any>(`${this.url}/cajas/v2/post`, caja).pipe(
      catchError(e => {
        return throwError(e);
      })
    );
  }

  update(caja: Caja): Observable<any> {
    return this.httpClient.put<any>(`${this.url}/cajas/put`, caja).pipe(
      catchError(e => {
        return throwError(e);
      })
    );
  }

  delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(`${this.url}/cajas/${id}/delete`).pipe(
      catchError(e => {
        return throwError(e);
      })
    );
  }
}
