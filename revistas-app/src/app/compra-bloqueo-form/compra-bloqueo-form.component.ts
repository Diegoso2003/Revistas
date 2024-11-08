import { CommonModule } from '@angular/common';
import { Component, inject, Input } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Revista } from '../interfaces/revista';
import { RevistasService } from '../servicios/revistas.service';
import { CompraBloqueo } from '../interfaces/compraBloqueo';
import { Router } from '@angular/router';

@Component({
  selector: 'app-compra-bloqueo-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './compra-bloqueo-form.component.html',
  styleUrl: './compra-bloqueo-form.component.css'
})
export class CompraBloqueoFormComponent {
  @Input({required: true})
  revista!: Revista;
  
  bloqueoForm: FormGroup;
  private _revistaService = inject(RevistasService);
  bloqueo!: CompraBloqueo;
  private router = inject(Router);
  error: boolean = false;
  mensajeError: string = '';

  constructor(formBuilder: FormBuilder) {
    this.bloqueoForm = formBuilder.group({
      fecha: ['', Validators.required],
      dias: ['', Validators.required]
    });
  }

  enviar(event: Event) {
    event.preventDefault();
    this.bloqueo = {
      fecha: this.bloqueoForm.get('fecha')?.value,
      dias: this.bloqueoForm.get('dias')?.value,
      idRevista: this.revista.id
    };

    this._revistaService.comprarBloqueo(this.bloqueo).subscribe({
      next: () => {
        console.log('Bloqueo comprado correctamente');
        const currentUrl = this.router.url;
        this.router
          .navigateByUrl('/', { skipLocationChange: true })
          .then(() => {
            this.router.navigate([currentUrl]);
          });
      },
      error: (error: any) => {
        console.log('Error al comprar bloqueo');
        console.error(error);
        error = true;
        this.mensajeError = error.error.mensaje || 'Error al comprar bloqueo';
      }
  });
}
}
