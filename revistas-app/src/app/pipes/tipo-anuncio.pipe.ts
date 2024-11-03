import { Pipe, PipeTransform } from '@angular/core';
import { TipoAnuncio } from '../interfaces/anunciocreate';

@Pipe({
  name: 'tipoAnuncio',
  standalone: true
})
export class TipoAnuncioPipe implements PipeTransform {

  transform(value: TipoAnuncio): string {
    switch (value) {
      case TipoAnuncio.TEXTO_E_IMAGEN:
        return 'anuncio de texto e imagen';
      case TipoAnuncio.VIDEO:
        return 'anuncio de video';
      case TipoAnuncio.TEXTO:
        return 'anuncio de texto';
    }
  }

}
