import { Component, Input } from '@angular/core';
import { Anuncio } from '../interfaces/anuncio';

@Component({
  selector: 'app-anuncio-vista',
  standalone: true,
  imports: [],
  templateUrl: './anuncio-vista.component.html',
  styleUrl: './anuncio-vista.component.css'
})
export class AnuncioVistaComponent {

  @Input({required: true}) 
  anuncio!: Anuncio;
}
