import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { ReportePago } from '../interfaces/reportePago';

@Injectable({
  providedIn: 'root'
})
export class ReporteservicioService {

  private url: string = 'http://localhost:8080/Revistas-Rest/api/v1/reportes';
  private _http = inject(HttpClient);
  private tokenKey: string = 'auth-token';
  constructor() { }

  private getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  private getAuthHeaders(): HttpHeaders {
    const token = this.getToken();
    let headers = new HttpHeaders();
    if (token) {
      headers = headers.set('Authorization', `Bearer ${token}`);
    }
    return headers;
  }

  filtrarReportePago(reporte: ReportePago): any {
    return this._http.post(`${this.url}/reportePago`, reporte, { 
      headers: this.getAuthHeaders(),
      responseType: 'blob' as 'json'
    });
  }
}
