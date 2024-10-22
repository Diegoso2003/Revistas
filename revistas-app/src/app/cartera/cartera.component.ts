import { CommonModule, NgClass } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UsuarioServiciosService } from '../servicios/usuario-servicios.service';

@Component({
  selector: 'app-cartera',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, NgClass],
  templateUrl: './cartera.component.html',
  styleUrl: './cartera.component.css'
})
export class CarteraComponent implements OnInit {

  private _usuarioServicios = inject(UsuarioServiciosService);
  carteraForm: FormGroup;
  error: boolean = false;
  mensajeError: string = '';
  cartera: number = 0;

  constructor(formBuilder: FormBuilder) {
    this.carteraForm = formBuilder.group({
      cartera: ['', [Validators.required, Validators.min(0.01)]]
    });
  }

  hasErrors(field: string, typeError: string) {
    return this.carteraForm.get(field)?.hasError(typeError) && this.carteraForm.get(field)?.touched;
  }

  enviar(event: Event) {
    if (this.carteraForm.valid) {
      const cartera = this.carteraForm.value.cartera;
      this._usuarioServicios.actualizarCartera(cartera).subscribe({
        next: (cartera: number) => {
          this.cartera = cartera;
          console.log('Cartera actualizada');
          console.log(cartera);
        },
        error: (error: any) => {
          this.error = true;
          this.mensajeError = error.error.mensaje || 'Ocurrió un error al actualizar la cartera, intenta mas tarde.';
        }
      });
    }
  }

  ngOnInit(): void {
    this._usuarioServicios.obtenerCartera().subscribe({
      next: (cartera: number) => {
        this.cartera = cartera;
      },
      error: (error: any) => {
        this.error = true;
        this.mensajeError = error.error.mensaje || 'Ocurrió un error al obtener la cartera, intenta mas tarde.';
      }
    });
  }
}
