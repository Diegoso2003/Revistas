import { Component, Input } from '@angular/core';
import { Anuncio } from '../interfaces/anuncio';
import { CurrencyPipe } from '@angular/common';
import { TipoAnuncioPipe } from '../pipes/tipo-anuncio.pipe';
import { VigenciaPipe } from '../pipes/vigencia.pipe';
import { RouterLink } from '@angular/router';
import { VideoComponent } from '../video/video.component';
import { ImagenComponent } from "../imagen/imagen.component";

@Component({
  selector: 'app-anuncio-vista',
  standalone: true,
  imports: [CurrencyPipe, TipoAnuncioPipe, VigenciaPipe, RouterLink, VideoComponent, ImagenComponent],
  templateUrl: './anuncio-vista.component.html',
  styleUrl: './anuncio-vista.component.css'
})
export class AnuncioVistaComponent {

  @Input({required: true}) 
  anuncio!: Anuncio;

  @Input()
  admin: boolean = false;

  estado: boolean = true;

  cancelar() {
    this.estado = false;
  }
}
