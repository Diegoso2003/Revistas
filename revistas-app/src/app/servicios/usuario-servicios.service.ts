import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from '../interfaces/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioServiciosService {

  private _http = inject(HttpClient);
  private url: string = 'http://localhost:8080/Revistas-Rest/api/v1/usuario/registro';
  constructor() { }

  crearUsuario(usuario: Usuario): Observable<any> {
    return this._http.post(`${this.url}/`, usuario);
  }
}
