import { CommonModule, NgClass } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { UsuarioServiciosService } from '../servicios/usuario-servicios.service';
import { TipoRol, Usuario } from '../interfaces/usuario';
import { TokenjwtService } from '../servicios/tokenjwt.service';

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [RouterOutlet, RouterLink, ReactiveFormsModule, CommonModule, NgClass],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.css'
})
export class InicioComponent {
  usuario?: Usuario;
  usuarioForm: FormGroup;
  error: boolean = false;
  mensajeError: string = '';
  private _usuarioServicios = inject(UsuarioServiciosService);
  private jwt = inject(TokenjwtService);
  private _router = inject(Router);

  constructor(private formBuilder: FormBuilder) {
    this.usuarioForm = this.formBuilder.group({
      nombre: ['', Validators.required],
      contraseña: ['', Validators.required]
    });
  }

  hasErrors(field: string, typeError: string) {
    return this.usuarioForm.get(field)?.hasError(typeError) && this.usuarioForm.get(field)?.touched;
  }

  enviar(event: Event) {
    event.preventDefault();
    if (this.usuarioForm.valid){
      const datos = this.usuarioForm.value;
      this.usuario = {
        nombre: datos.nombre,
        contraseña: datos.contraseña,
        confirmacionContraseña: null,
        rol: null
      };
      this._usuarioServicios.login(this.usuario).subscribe({
        next: (response: any) => {
          this.usuarioForm.reset();
          const datosToken = this.jwt.decodificarToken(response.token);
          this.evaluarRol(datosToken);
        },
        error: (error: any) => {
          this.error = true;
          this.mensajeError = error.error.mensaje || 'Ocurrió un error al iniciar sesion, intenta mas tarde.';
        }
      });       
    }else {
      this.error = true;
      this.mensajeError = 'Por favor, llena todos los campos correctamente.';
    }
  }

  private evaluarRol(datosToken: any) {
    const rol: TipoRol = datosToken.rol as TipoRol;
    console.log(rol);
    switch (rol) {
      case TipoRol.ADMINISTRADOR:
        this._router.navigate(['/administrador']);
        break;
      case TipoRol.EDITOR:
        console.log("editor");
        this._router.navigate(['/editor']);
        break;
      case TipoRol.LECTOR:
        this._router.navigate(['/lector']);
        break;
      case TipoRol.ANUNCIADOR:
        this._router.navigate(['/anunciador']);
        break;
    }
  }
}
