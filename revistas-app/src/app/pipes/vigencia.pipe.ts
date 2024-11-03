import { Pipe, PipeTransform } from '@angular/core';
import { Vigencia } from '../interfaces/anunciocreate';

@Pipe({
  name: 'vigencia',
  standalone: true
})
export class VigenciaPipe implements PipeTransform {

  transform(value: Vigencia): string {
    switch (value) {
      case Vigencia.DIA_1:
        return '1 día';
      case Vigencia.DIA_3:
        return '3 días';
      case Vigencia.SEMANA_1:
        return '1 semana';
      case Vigencia.SEMANA_2:
        return '2 semanas';
    }
  }
}
