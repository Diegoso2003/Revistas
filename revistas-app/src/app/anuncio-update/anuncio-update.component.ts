import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AnuncioServiciosService } from '../servicios/anuncio-servicios.service';
import { Anuncio } from '../interfaces/anuncio';
import { AnuncioVistaComponent } from "../anuncio-vista/anuncio-vista.component";
import { TextoUpdateComponent } from '../texto-update/texto-update.component';
import { VideoUpdateComponent } from '../video-update/video-update.component';
import { ImagenUpdateComponent } from '../imagen-update/imagen-update.component';

@Component({
  selector: 'app-anuncio-update',
  standalone: true,
  imports: [AnuncioVistaComponent, TextoUpdateComponent, VideoUpdateComponent, ImagenUpdateComponent],
  templateUrl: './anuncio-update.component.html',
  styleUrl: './anuncio-update.component.css',
})
export class AnuncioUpdateComponent implements OnInit {
  private ruta = inject(ActivatedRoute);
  id!: number;
  private router = inject(Router);
  private _anuncioService = inject(AnuncioServiciosService);
  anuncio!: Anuncio;

  ngOnInit(): void {
    this.id = this.ruta.snapshot.params['id'] ?? null;
    if (!this.id) {
      this.router.navigate(['/anunciador/anunciosVigentes']);
    }

    this._anuncioService.obtenerAnuncio(this.id).subscribe({
      next: (anuncio) => {
        this.anuncio = anuncio;
      },
      error: (error) => {
        console.error(error);
        this.router.navigate(['/anunciador/anunciosVigentes']);
      },
    });
  }
}
