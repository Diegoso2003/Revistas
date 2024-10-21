import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { Usuario } from '../interfaces/usuario';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UsuarioServiciosService {

  private _http = inject(HttpClient);
  private url: string = 'http://localhost:8080/Revistas-Rest/api/v1/usuarios';
  private tokenKey: string = 'auth-token';
  private router = inject(Router);

  crearUsuario(usuario: Usuario): Observable<any> {
    return this._http.post(`${this.url}/registro`, usuario);
  }

  login(usuario: Usuario): Observable<any> {
    return this._http.post(`${this.url}/login`, usuario).pipe(
      tap((response: any) => {
        console.log(response);
        if (response.token) {
          this.setToken(response.token);
        }
      })
    );
  }

  private setToken(token: string) {
    localStorage.setItem(this.tokenKey, token);
  }

  private getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  isLogged(): boolean {
    const token = this.getToken();
    if (!token) {
      return false;
    }
    const payload = JSON.parse(atob(token.split('.')[1]));
    const expirado = payload.exp * 1000;
    return Date.now() < expirado;
  }

  logout() {
    localStorage.removeItem(this.tokenKey);
    this.router.navigate(['/inicio']);
  }
}
