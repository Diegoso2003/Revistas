import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Precios } from '../interfaces/precios';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Anuncio } from '../interfaces/anuncio';

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

  uploadFile(dataToSend: Anuncio): Observable<any> {
    const headers = this.getAuthHeaders();
    let formData: FormData = new FormData();
    formData.append('fileObject', dataToSend.imagen);
    if (dataToSend.imagen) {
      formData.append('fileObject', dataToSend.imagen.name);
    }
    formData.append('video', dataToSend.video);
    formData.append('fecha', dataToSend.fecha);
    formData.append('texto', dataToSend.texto);
    formData.append('tipo', dataToSend.tipo);
    formData.append('vigencia', dataToSend.vigencia);
    return this._http.post(`${this.url}/subirAnuncio`, formData, { headers });
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
}
