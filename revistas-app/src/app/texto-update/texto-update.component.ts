import { Component, inject, Input } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AnuncioServiciosService } from '../servicios/anuncio-servicios.service';
import { CommonModule } from '@angular/common';
import { Anuncio } from '../interfaces/anuncio';
import { Router } from '@angular/router';
import { TipoAnuncio, Vigencia } from '../interfaces/anunciocreate';

@Component({
  selector: 'app-texto-update',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './texto-update.component.html',
  styleUrl: './texto-update.component.css'
})
export class TextoUpdateComponent {

  @Input({required: true})
  ID!: number ;

  textoForm: FormGroup;
  private _anuncioservice = inject(AnuncioServiciosService);
  router = inject(Router);
  error: boolean = false;
  exito: boolean = false;
  mensajeExito: string = '';
  mensaje: string = '';
  anuncio!: Anuncio;

  constructor(private formbuilder: FormBuilder) {
    this.textoForm = formbuilder.group({
      textoAnuncio: ['', Validators.required]
    });
  }

  hasErrors(field: string, typeError: string) {
    return (
      this.textoForm.get(field)?.hasError(typeError) &&
      this.textoForm.get(field)?.touched
    );
  }

  enviar(event: Event) {

    this.anuncio = this.textoForm.value as Anuncio;
    this.anuncio.id = this.ID;
    console.log(this.anuncio);
    this._anuncioservice.actualizarTexto(this.anuncio).subscribe({
      next: () => {
        this.exito = true;
        this.error = false;
        this.mensaje = 'Texto actualizado con éxito';
        this.router.navigate(['/anunciador/anunciosVigentes']);
      },
      error: (error) => {
        this.error = true;
        this.exito = false;
        this.mensaje = error.error.mensaje || 'Error al actualizar el texto';
      }
    });
  }
}
