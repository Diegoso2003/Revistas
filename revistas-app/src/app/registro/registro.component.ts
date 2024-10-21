import { CommonModule, NgClass } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { Usuario } from '../interfaces/usuario';
import { UsuarioServiciosService } from '../servicios/usuario-servicios.service';

@Component({
  selector: 'app-registro',
  standalone: true,
  imports: [RouterLink, RouterOutlet, ReactiveFormsModule, NgClass, CommonModule],
  templateUrl: './registro.component.html',
  styleUrl: './registro.component.css'
})

export class RegistroComponent {

  error: boolean = false;
  mensajeError: string = '';
  usuarioForm!: FormGroup;
  usuario?: Usuario;
  private _usuarioServicios = inject(UsuarioServiciosService);
  private _router = inject(Router);

  constructor(private formBuilder: FormBuilder) {
    this.usuarioForm = this.formBuilder.group({
      nombre: ['', [Validators.required]],
      contraseña: ['', [Validators.required, Validators.minLength(8)]],
      confirmacionContraseña: ['', [Validators.required,  Validators.minLength(8)]],
      rol: ['', [Validators.required]]
    });
  }

  sonDiferentes() {
    const confirmacionContraseña = this.usuarioForm.get('confirmacionContraseña');
    const contraseña = this.usuarioForm.get('contraseña');
    
    if (confirmacionContraseña?.touched && contraseña?.touched) {
        return confirmacionContraseña.value !== contraseña?.value;
    }
    
    return false;
  }

  hasErrors(field: string, typeError: string) {
    return this.usuarioForm.get(field)?.hasError(typeError) && this.usuarioForm.get(field)?.touched;
  }

  enviar(event: Event) {
    event.preventDefault();
    console.log("enviar");
    if (this.usuarioForm.valid){
      console.log("valido");
      const datos = this.usuarioForm.value;
      this.usuario = {
        nombre: datos.nombre,
        contraseña: datos.contraseña,
        confirmacionContraseña: datos.confirmacionContraseña,
        rol: datos.rol
      };
      this._usuarioServicios.crearUsuario(this.usuario).subscribe({
        next: () => {
          this.usuarioForm.reset();
          this._router.navigate(['/inicio']);
        },
        error: (error: any) => {
          this.error = true;
          this.mensajeError = error.error.mensaje || 'Ocurrió un error al crear el usuario.';
        }
      });       
    }else {
      this.error = true;
      this.mensajeError = 'Por favor, llena todos los campos correctamente.';
    }
  }
  
}
