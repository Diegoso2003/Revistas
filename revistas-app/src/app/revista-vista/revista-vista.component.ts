import { Component, inject, Input } from '@angular/core';
import { Revista } from '../interfaces/revista';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { Router } from '@angular/router';
import { BloqueoRevistaFormComponent } from '../bloqueo-revista-form/bloqueo-revista-form.component';
import { CompraBloqueoFormComponent } from '../compra-bloqueo-form/compra-bloqueo-form.component';
import { RevistaPdfFormComponent } from '../revista-pdf-form/revista-pdf-form.component';

@Component({
  selector: 'app-revista-vista',
  standalone: true,
  imports: [CommonModule, CurrencyPipe, BloqueoRevistaFormComponent, CompraBloqueoFormComponent, RevistaPdfFormComponent],
  templateUrl: './revista-vista.component.html',
  styleUrl: './revista-vista.component.css'
})
export class RevistaVistaComponent {
  @Input({required: true})
  admin! : boolean;

  @Input({required: true})
  editor! : boolean;

  @Input({required: true})
  lector! : boolean;

  @Input({required: true})
  preview! : boolean;

  @Input({required: true})
  revista! : Revista;

  router = inject(Router);

  editarRevista() {
    this.router.navigate(['/editor/administrarRevista', this.revista.id]);
  }
}
