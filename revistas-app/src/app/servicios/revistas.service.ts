import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Revista } from '../interfaces/revista';
import { Observable } from 'rxjs';
import { bloqueosRevista } from '../interfaces/bloqueosRevista';

@Injectable({
  providedIn: 'root'
})
export class RevistasService {

  private _http = inject(HttpClient);
  private url: string = 'http://localhost:8080/Revistas-Rest/api/v1/revistas';
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

  subirRevista(revista: Revista): Observable<Revista> {
    return this._http.post<Revista>(`${this.url}/datosRevista`, revista, { headers: this.getAuthHeaders() });
  }

  subirPDF(dataToSend: FormData): Observable<any> {
    const headers = this.getAuthHeaders();
    return this._http.post(`${this.url}/pdfrevista`, dataToSend, { headers });
  }

  conseguirCategoria(): Observable<string[]> {
    return this._http.get<string[]>(`${this.url}/categorias`);
  }

  conseguirRevistas(): Observable<Revista[]> {
    return this._http.get<Revista[]>(`${this.url}/conseguirRevistas`, { headers: this.getAuthHeaders() });
  }

  conseguirRevista(id: number): Observable<Revista> {
    return this._http.get<Revista>(`${this.url}/conseguir/${id}`, { headers: this.getAuthHeaders() });
  }

  actualizarBloqueos(bloqueos: bloqueosRevista): Observable<any> {
    return this._http.put(`${this.url}/bloqueos`, bloqueos, { headers: this.getAuthHeaders() });
  }
}
