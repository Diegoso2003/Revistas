import { CanActivateFn, Router } from '@angular/router';
import { UsuarioServiciosService } from '../servicios/usuario-servicios.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const usuarioServiciosService = inject(UsuarioServiciosService);
  const router = inject(Router);
  if (usuarioServiciosService.isLogged()) {
    return true;
  } else {
    return router.navigate(['/inicio']);
  }
};
