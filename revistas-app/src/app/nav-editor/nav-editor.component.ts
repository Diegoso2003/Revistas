import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { Router, RouterLink, RouterModule, RouterOutlet } from '@angular/router';
import { UsuarioServiciosService } from '../servicios/usuario-servicios.service';

@Component({
  selector: 'app-nav-editor',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterModule, CommonModule],
  templateUrl: './nav-editor.component.html',
  styleUrl: './nav-editor.component.css'
})
export class NavEditorComponent {
  private _usuarioservice = inject(UsuarioServiciosService);
  private router = inject(Router);

  cerrarSesion() {
    this._usuarioservice.logout();
    this.router.navigate(['/inicio']);
  }
}
