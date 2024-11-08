import { Component, inject } from '@angular/core';
import { AppComponent } from "../app.component";
import { CommonModule } from '@angular/common';
import { Router, RouterLink, RouterModule, RouterOutlet } from '@angular/router';
import { UsuarioServiciosService } from '../servicios/usuario-servicios.service';

@Component({
  selector: 'app-nav-anunciador',
  standalone: true,
  imports: [AppComponent, CommonModule, RouterLink, RouterOutlet, RouterModule],
  templateUrl: './nav-anunciador.component.html',
  styleUrl: './nav-anunciador.component.css'
})
export class NavAnunciadorComponent {
  private _usuarioservice = inject(UsuarioServiciosService);
  private router = inject(Router);

  cerrarSesion() {
    this._usuarioservice.logout();
    this.router.navigate(['/inicio']);
  }
}
