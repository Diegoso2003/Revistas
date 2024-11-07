import { Component, inject, Input, OnInit } from '@angular/core';
import { Revista } from '../interfaces/revista';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { bloqueosRevista } from '../interfaces/bloqueosRevista';
import { CommonModule } from '@angular/common';
import { RevistasService } from '../servicios/revistas.service';

@Component({
  selector: 'app-bloqueo-revista-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './bloqueo-revista-form.component.html',
  styleUrl: './bloqueo-revista-form.component.css'
})
export class BloqueoRevistaFormComponent implements OnInit {
  @Input({required: true})
  revista! : Revista;

  bloqueos!: bloqueosRevista;
  revistaForm!: FormGroup;
  error: boolean = false;
  mensajeError: string = '';
  exito: boolean = false;
  mensajeExito: string = '';

  private _revistaService = inject(RevistasService);
  private _formbuilder = inject(FormBuilder);


  ngOnInit(): void {
    this.revistaForm = this._formbuilder.group({
      bloqueoComentarios: [this.revista?.bloqueoComentario],
      bloqueoSuscripcion: [this.revista?.bloqueoSuscripcion]
    });
  }

  enviar(event: Event) {
    this.bloqueos = {
      bloqueoComentarios: this.revistaForm.get('bloqueoComentarios')?.value,
      bloqueoSuscripcion: this.revistaForm.get('bloqueoSuscripcion')?.value,
      idRevista: this.revista.id
    }

    this._revistaService.actualizarBloqueos(this.bloqueos).subscribe({
      next: () => {
        this.exito = true;
        this.mensajeExito = 'Bloqueos actualizados correctamente';
      },
      error: (error) => {
        this.error = true;
        this.mensajeError = 'Error al actualizar los bloqueos';
      }
    });
  }
}
