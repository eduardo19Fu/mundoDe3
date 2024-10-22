import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { NotaCredito } from '../../models/nota-credito';
import { global } from '../global';

@Injectable({
  providedIn: 'root'
})
export class NotaCreditoService {

  url: string;

  constructor(private httpClient: HttpClient) {
    this.url = global.url;
  }

  getNotasCredito(): Observable<NotaCredito[]> {
    return this.httpClient.get<NotaCredito[]>(`${this.url}/notas-credito`).pipe(
      catchError(e => {
        return throwError(e);
      })
    );
  }

  getNotaCredito(id: number): Observable<any> {
    return this.httpClient.get<any>(`${this.url}/notas-credito/${id}`).pipe(
      catchError(e => {
        return throwError(e);
      })
    );
  }

  create(notaCredito: NotaCredito): Observable<any> {
    return this.httpClient.post<any>(`${this.url}/notas-credito`, notaCredito).pipe(
      catchError(e => {
        return throwError(e);
      })
    );
  }
}
