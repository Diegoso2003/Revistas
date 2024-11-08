import { Component, inject } from '@angular/core';
import { Router, RouterLink, RouterLinkActive, RouterModule, RouterOutlet } from '@angular/router';
import { UsuarioServiciosService } from '../servicios/usuario-servicios.service';

@Component({
  selector: 'app-nav-admin',
  standalone: true,
  imports: [RouterLinkActive, RouterOutlet, RouterModule, RouterLink],
  templateUrl: './nav-admin.component.html',
  styleUrl: './nav-admin.component.css'
})
export class NavAdminComponent {
  private _usuarioservice = inject(UsuarioServiciosService);
  private router = inject(Router);

  cerrarSesion() {
    this._usuarioservice.logout();
    this.router.navigate(['/inicio']);
  }
}
