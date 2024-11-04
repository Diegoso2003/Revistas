import { Component, inject, Input } from '@angular/core';
import { AnuncioServiciosService } from '../servicios/anuncio-servicios.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-imagen-update',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './imagen-update.component.html',
  styleUrl: './imagen-update.component.css'
})
export class ImagenUpdateComponent {
  @Input({required: true})
  ID!: number ;
  ImagenForm: FormGroup;
  private _anuncioservice = inject(AnuncioServiciosService);
  private router = inject(Router);
  error: boolean = false;
  exito: boolean = false;
  mensajeExito: string = '';
  mensaje: string = '';

  constructor(formbuilder: FormBuilder) {
    this.ImagenForm = formbuilder.group({
      imagen: ['', Validators.required]
    });
  }

  enviar(event: Event) {
    const formData = new FormData();
    formData.append('fileObject', this.ImagenForm.get('imagen')?.value);
    formData.append('id', this.ID.toString());

    this._anuncioservice.actualizarImagen(formData).subscribe({
      next: () => {
        this.exito = true;
        this.error = false;
        this.mensaje = 'Imagen actualizada con éxito';
        this.router.navigate(['/anunciador/anunciosVigentes']);
      },
      error: (error) => {
        this.error = true;
        this.exito = false;
        this.mensaje = error.error.mensaje || 'Error al actualizar la imagen';
      }
    });
  }

  hasErrors(field: string, typeError: string) {
    return (
      this.ImagenForm.get(field)?.hasError(typeError) &&
      this.ImagenForm.get(field)?.touched
    );
  }

  onFileSelected(event: Event) {
    const fileInput = event.target as HTMLInputElement;
  if (fileInput.files && fileInput.files.length > 0) {
    const file = fileInput.files[0];
    this.ImagenForm.patchValue({
      imagen: file, // Aquí asegúrate de que 'imagen' almacene el archivo en sí
    });
    this.ImagenForm.get('imagen')?.updateValueAndValidity();
  }
  }
}
