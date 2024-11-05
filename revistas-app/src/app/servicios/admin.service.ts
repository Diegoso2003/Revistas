import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Precios } from '../interfaces/precios';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private _http = inject(HttpClient);
  private url: string = 'http://localhost:8080/Revistas-Rest/api/v1/admin';
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

  actualizarPrecios(precios: Precios): Observable<Precios> {
    return this._http.put<Precios>(`${this.url}/anuncios`, precios, { headers: this.getAuthHeaders() });
  }
}
