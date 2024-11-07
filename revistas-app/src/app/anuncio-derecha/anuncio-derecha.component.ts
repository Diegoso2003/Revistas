import { Component, inject, Input, OnInit } from '@angular/core';
import { Anuncio } from '../interfaces/anuncio';
import { ImagenComponent } from '../imagen/imagen.component';
import { VideoComponent } from '../video/video.component';
import { registro } from '../interfaces/registro';
import { AnuncioServiciosService } from '../servicios/anuncio-servicios.service';

@Component({
  selector: 'app-anuncio-derecha',
  standalone: true,
  imports: [ImagenComponent, VideoComponent],
  templateUrl: './anuncio-derecha.component.html',
  styleUrl: './anuncio-derecha.component.css'
})
export class AnuncioDerechaComponent implements OnInit {
  @Input({required: true})
  anuncio!: Anuncio;

  @Input({required: true})
  url!: string;

  registro!: registro;
  private _anuncioservice = inject(AnuncioServiciosService);

  ngOnInit(): void {
    this.registro = {
      id: 1,
      idAnuncio: this.anuncio.id,
      url: this.url
    };
    this._anuncioservice.subirRegistro(this.registro).subscribe({
      next: () => {
        console.log('Registro subido correctamente');
        console.log(this.registro);
      },
      error: (error) => {
        console.error('Error al subir el registro');
        console.error(error);
      }
    });
  }
}
