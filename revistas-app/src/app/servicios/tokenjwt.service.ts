import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class TokenjwtService {

  constructor() { }

  decodificarToken(token: string): any {
    try {
      return jwtDecode(token);
    } catch (Error) {
      return null;
    }
  }


  getUserRole(token: string): string | null {
    const decodedToken = this.decodificarToken(token);
    return decodedToken ? decodedToken.rol : null;
  }
}
