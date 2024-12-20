import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Precios } from '../interfaces/precios';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AnuncioCreate } from '../interfaces/anunciocreate';
import { Anuncio } from '../interfaces/anuncio';
import { registro } from '../interfaces/registro';

@Injectable({
  providedIn: 'root'
})
export class AnuncioServiciosService {

  private url: string = 'http://localhost:8080/Revistas-Rest/api/v1/anuncios';
  private _http = inject(HttpClient);
  private tokenKey: string = 'auth-token';
  constructor() { }

  obtenerPrecios(): Observable<Precios> {
    return this._http.get<Precios>(`${this.url}/precios`);
  }

  uploadFile(dataToSend: FormData): Observable<any> {
    const headers = this.getAuthHeaders();
    return this._http.post(`${this.url}/subirAnuncio`, dataToSend, { headers });
  }

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

  obtenerAnuncios(): Observable<Anuncio[]> {
    return this._http.get<Anuncio[]>(`${this.url}`, { headers: this.getAuthHeaders() });
  }

  obtenerImagen(id: number): Observable<any> {
    return this._http.get(`${this.url}/imagen/${id}`, { responseType: 'blob' });
  }

  cancelarAnuncio(id: number): Observable<any> {
    return this._http.delete(`${this.url}/${id}`, { headers: this.getAuthHeaders() });
  }

  obtenerAnuncio(id: number): Observable<Anuncio> {
    return this._http.get<Anuncio>(`${this.url}/obtenerAnuncio/${id}`, { headers: this.getAuthHeaders() });
  }

  actualizarImagen(dataToSend: FormData): Observable<any> {
    const headers = this.getAuthHeaders();
    return this._http.put(`${this.url}/imagen`, dataToSend, { headers });
  }

  actualizarTexto(anuncio: Anuncio): Observable<any> {
    const headers = this.getAuthHeaders();
    return this._http.put(`${this.url}/texto`, anuncio ,{ headers });
  }

  actualizarVideo(anuncio: Anuncio): Observable<any> {
    const headers = this.getAuthHeaders();
    return this._http.put(`${this.url}/video`, anuncio, { headers });
  }

  desplegarAnuncios(): Observable<Anuncio[]> {
    return this._http.get<Anuncio[]>(`${this.url}/desplegarAnuncios`);
  }

  subirRegistro(registro: registro): Observable<any> {
    return this._http.post(`${this.url}/subirRegistro`, registro);
  }
}
