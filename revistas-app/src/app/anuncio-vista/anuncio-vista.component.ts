import { Component, inject, Input } from '@angular/core';
import { Anuncio } from '../interfaces/anuncio';
import { CurrencyPipe, NgClass } from '@angular/common';
import { TipoAnuncioPipe } from '../pipes/tipo-anuncio.pipe';
import { VigenciaPipe } from '../pipes/vigencia.pipe';
import { Router, RouterLink } from '@angular/router';
import { VideoComponent } from '../video/video.component';
import { ImagenComponent } from "../imagen/imagen.component";
import { AnuncioServiciosService } from '../servicios/anuncio-servicios.service';

@Component({
  selector: 'app-anuncio-vista',
  standalone: true,
  imports: [CurrencyPipe, TipoAnuncioPipe, VigenciaPipe, RouterLink, VideoComponent, ImagenComponent, NgClass],
  templateUrl: './anuncio-vista.component.html',
  styleUrl: './anuncio-vista.component.css'
})
export class AnuncioVistaComponent {

  @Input({required: true}) 
  anuncio!: Anuncio;

  @Input()
  admin: boolean = false;

  @Input()
  editar: boolean = false;

  estado: boolean = true;
  error: boolean = false;
  mensaje: string = '';
  anuncioservice = inject(AnuncioServiciosService);
  router: Router = inject(Router);

  cancelar() {
    this.anuncioservice.cancelarAnuncio(this.anuncio.id).subscribe(
      {
        next: () => {
          this.router.navigate(['/anunciador/anunciosVigentes']);
        },
        error: (error) => {
          console.error(error);
          this.error = true;
          this.mensaje = 'Error al cancelar el anuncio intente mas tarde';
        }
      });
  }
}
