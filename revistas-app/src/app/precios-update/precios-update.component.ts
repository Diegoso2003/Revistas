import { Component, inject, OnInit } from '@angular/core';
import { AnuncioServiciosService } from '../servicios/anuncio-servicios.service';
import { Precios } from '../interfaces/precios';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { AdminService } from '../servicios/admin.service';

@Component({
  selector: 'app-precios-update',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, CurrencyPipe],
  templateUrl: './precios-update.component.html',
  styleUrl: './precios-update.component.css'
})
export class PreciosUpdateComponent implements OnInit {

  precios!: Precios;
  private _anuncioServices = inject(AnuncioServiciosService);
  private _adminServices = inject(AdminService);
  preciosForm: FormGroup;
  error: boolean = false;
  mensajeError: string = '';

  constructor(private formBuilder: FormBuilder) {
    this.preciosForm = this.formBuilder.group({
      texto: [''],
      imagen: [''],
      video: [''],
      dia1: [''],
      dia3: [''],
      semana1: [''],
      semana2: ['']
    });
  }

  ngOnInit(): void {
    this._anuncioServices.obtenerPrecios().subscribe({
      next: (precios: Precios) => {
        this.precios = precios;
      },
      error: (error: any) => {
        console.error(error);
      },
    });
  }

  enviar(event: Event) {
  
    const precios2: Precios = this.preciosForm.value as Precios;
    this._adminServices.actualizarPrecios(precios2).subscribe({
      next: (precios3 : Precios) => {
        this.error = false;
        this.precios = precios3;
      },
      error: (error) => {
        this.error = true;
        this.mensajeError = error.error.mensaje || 'Error al actualizar los precios';
      }
    });
  }
}
