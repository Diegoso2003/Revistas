import { Component, inject, OnInit } from '@angular/core';
import { Anuncio } from '../interfaces/anuncio';
import { AnuncioServiciosService } from '../servicios/anuncio-servicios.service';
import { AnuncioVistaComponent } from '../anuncio-vista/anuncio-vista.component';

@Component({
  selector: 'app-anuncios-vigentes-vista',
  standalone: true,
  imports: [AnuncioVistaComponent],
  templateUrl: './anuncios-vigentes-vista.component.html',
  styleUrl: './anuncios-vigentes-vista.component.css',
})
export class AnunciosVigentesVistaComponent implements OnInit {
  anunciosVigentes!: Anuncio[];

  private _anuncioService = inject(AnuncioServiciosService);

  constructor() {}

  ngOnInit(): void {
    this._anuncioService.obtenerAnuncios().subscribe({
      next: (anuncios) => {
        this.anunciosVigentes = anuncios;
      },
      error: (error) => {
        console.log(error);
      },
    });
  }
}
