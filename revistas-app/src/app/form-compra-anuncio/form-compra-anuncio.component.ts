import { CommonModule } from '@angular/common';
import { Component, computed, inject, input, OnInit, ɵINPUT_SIGNAL_BRAND_WRITE_TYPE } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Precios } from '../interfaces/precios';
import { AnuncioServiciosService } from '../servicios/anuncio-servicios.service';
import { Anuncio } from '../interfaces/anuncio';

@Component({
  selector: 'app-form-compra-anuncio',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './form-compra-anuncio.component.html',
  styleUrl: './form-compra-anuncio.component.css',
})
export class FormCompraAnuncioComponent implements OnInit {
  error: boolean = false;
  exito: boolean = false;
  mensajeExito: string = '';
  mensajeError: string = '';
  anuncioForm: FormGroup;
  precios!: Precios;
  tipoPrecio: number = 0;
  vigenciaPrecio: number = 0;
  anuncio!: Anuncio;
  precioTotal = this.tipoPrecio + this.vigenciaPrecio;
  private _anuncioServices = inject(AnuncioServiciosService);

  constructor(private formBuilder: FormBuilder) {
    this.anuncioForm = this.formBuilder.group({
      fecha: ['', Validators.required],
      texto: [{ value: '', disabled: true }],
      imagen: [{ value: null, disabled: true }],
      video: [{ value: '', disabled: true }],
      tipo: ['', Validators.required],
      vigencia: ['', Validators.required],
    });
  }

  hasErrors(field: string, typeError: string) {
    return (
      this.anuncioForm.get(field)?.hasError(typeError) &&
      this.anuncioForm.get(field)?.touched
    );
  }

  ngOnInit(): void {
    this.anuncioForm.get('tipo')?.valueChanges.subscribe((value) => {
      this.onTipoChange(value);
    });

    this.anuncioForm.get('vigencia')?.valueChanges.subscribe((value) => {
      this.onVigenciaChange(value);
    });

    this._anuncioServices.obtenerPrecios().subscribe({
      next: (precios: Precios) => {
        this.precios = precios;
      },
      error: (error: any) => {
        console.error(error);
      },
    });
  }

  onTipoChange(value: string): void {
    const textoControl = this.anuncioForm.get('texto');
    const imagenControl = this.anuncioForm.get('imagen');
    const videoControl = this.anuncioForm.get('video');

    if (value === 'TEXTO') {
      this.tipoPrecio = this.precios.texto;
      textoControl?.setValidators([Validators.required]);
      imagenControl?.clearValidators();
      videoControl?.clearValidators();
      textoControl?.enable();
      imagenControl?.disable();
      videoControl?.disable();
    } else if (value === 'IMAGEN') {
      this.tipoPrecio = this.precios.imagen;
      textoControl?.setValidators([Validators.required]);
      imagenControl?.setValidators([Validators.required]);
      videoControl?.clearValidators();
      textoControl?.enable();
      imagenControl?.enable();
      videoControl?.disable();
    } else if (value === 'VIDEO') {
      this.tipoPrecio = this.precios.video;
      videoControl?.setValidators([Validators.required]);
      textoControl?.clearValidators();
      imagenControl?.clearValidators();
      textoControl?.disable();
      imagenControl?.disable();
      videoControl?.enable();
    }
    this.precioTotal = this.tipoPrecio + this.vigenciaPrecio;
    textoControl?.updateValueAndValidity();
    imagenControl?.updateValueAndValidity();
    videoControl?.updateValueAndValidity();
  }

  onVigenciaChange(value: string): void {
    if (value === 'DIA_1') {
      this.vigenciaPrecio = this.precios.dia1;
    } else if (value === 'DIA_3') {
      this.vigenciaPrecio = this.precios.dia3;
    } else if (value === 'SEMANA_1') {
      this.vigenciaPrecio = this.precios.semana1;
    } else if (value === 'SEMANA_2') {
      this.vigenciaPrecio = this.precios.semana2;
    }
    this.precioTotal = this.tipoPrecio + this.vigenciaPrecio;
  }

  enviar(event: Event) {
    event.preventDefault();
    if (this.anuncioForm.valid) {
      this.anuncio = this.anuncioForm.value as Anuncio;
      console.log('valido');
      this._anuncioServices.uploadFile(this.anuncio).subscribe({
        next: () => {
          this.anuncioForm.reset();
          this.exito = true;
          this.mensajeExito = 'Anuncio subido con éxito.';
        },
        error: (error: any) => {
          this.error = true;
          console.error(error);
          this.mensajeError =
            error.error.mensaje || 'Ocurrió un error al subir el anuncio.';
        },
      });
    } else {
      this.error = true;
      this.mensajeError = 'Por favor, llena todos los campos correctamente.';
    }
  }
}
