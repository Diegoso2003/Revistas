import { Component, inject, Input, OnInit } from '@angular/core';
import { Anuncio } from '../interfaces/anuncio';
import { VideoComponent } from '../video/video.component';
import { ImagenComponent } from '../imagen/imagen.component';
import { registro } from '../interfaces/registro';
import { AnuncioServiciosService } from '../servicios/anuncio-servicios.service';

@Component({
  selector: 'app-anuncio-izquierda',
  standalone: true,
  imports: [VideoComponent, ImagenComponent],
  templateUrl: './anuncio-izquierda.component.html',
  styleUrl: './anuncio-izquierda.component.css'
})
export class AnuncioIzquierdaComponent implements OnInit {
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
