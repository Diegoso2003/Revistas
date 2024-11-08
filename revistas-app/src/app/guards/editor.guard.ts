import { CanActivateChildFn, Router } from '@angular/router';
import { UsuarioServiciosService } from '../servicios/usuario-servicios.service';
import { TokenjwtService } from '../servicios/tokenjwt.service';
import { inject } from '@angular/core';
import { TipoRol } from '../interfaces/usuario';

export const editorGuard: CanActivateChildFn = (childRoute, state) => {
  const _usuarioservice = inject(UsuarioServiciosService);
  const _token = inject(TokenjwtService);
  const router = inject(Router);
  const token2 = _usuarioservice.getToken();
  if (!token2) {
    return router.navigate(['/inicio']);
  }
  const datosToken = _token.decodificarToken(token2!);
  const expirationTime = datosToken.exp;
  const currentTime = Math.floor(Date.now() / 1000);
  if (expirationTime < currentTime) {
    return router.navigate(['/inicio']);
  }
  const rol: TipoRol = datosToken.rol as TipoRol;
  if (rol !== TipoRol.EDITOR) {
    return router.navigate(['/inicio']);
  }
  return true;
};
