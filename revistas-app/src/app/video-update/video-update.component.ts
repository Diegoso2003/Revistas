import { CommonModule } from '@angular/common';
import { Component, inject, Input } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Anuncio } from '../interfaces/anuncio';
import { AnuncioServiciosService } from '../servicios/anuncio-servicios.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-video-update',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './video-update.component.html',
  styleUrl: './video-update.component.css'
})
export class VideoUpdateComponent {
  @Input({required: true})
  id!: number;

  videoForm: FormGroup;
  error: boolean = false;
  exito: boolean = false;
  mensajeExito: string = '';
  mensaje: string = '';
  anuncio!: Anuncio;
  private _anuncioservice = inject(AnuncioServiciosService);
  private router = inject(Router);

  constructor(private formbuilder: FormBuilder) {
    this.videoForm = formbuilder.group({
      urlVideo: ['', Validators.required]
    });
  }

  hasErrors(field: string, typeError: string) {
    return (
      this.videoForm.get(field)?.hasError(typeError) &&
      this.videoForm.get(field)?.touched
    );
  }

  enviar(event: Event) {
    this.anuncio = this.videoForm.value as Anuncio;
    this.anuncio.id = this.id;

    this._anuncioservice.actualizarVideo(this.anuncio).subscribe({
      next: () => {
        this.exito = true;
        this.error = false;
        this.mensaje = 'Video actualizado con éxito';
        this.router.navigate(['/anunciador/anunciosVigentes']);
      },
      error: (error) => {
        this.error = true;
        this.exito = false;
        this.mensaje = error.error.mensaje || 'Error al actualizar el video';
      }
    });
  }
}
