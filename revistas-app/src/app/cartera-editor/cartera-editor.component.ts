import { Component, inject, OnInit } from '@angular/core';
import { CarteraComponent } from '../cartera/cartera.component';
import { AnuncioDerechaComponent } from '../anuncio-derecha/anuncio-derecha.component';
import { AnuncioIzquierdaComponent } from '../anuncio-izquierda/anuncio-izquierda.component';
import { Anuncio } from '../interfaces/anuncio';
import { AnuncioServiciosService } from '../servicios/anuncio-servicios.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cartera-editor',
  standalone: true,
  imports: [CarteraComponent, AnuncioDerechaComponent, AnuncioIzquierdaComponent],
  templateUrl: './cartera-editor.component.html',
  styleUrl: './cartera-editor.component.css'
})
export class CarteraEditorComponent implements OnInit {
  anuncios: Anuncio[] = [];
  mostrarAnuncios: boolean = false;
  anuncioDerecha!: Anuncio;
  anuncioIzquierda!: Anuncio;
  url!: string;

  private _anunciosservice = inject(AnuncioServiciosService);
  private router = inject(Router);

  ngOnInit(): void {
    this.url = this.router.url;
    this._anunciosservice.desplegarAnuncios().subscribe({
      next: (anuncios: Anuncio[]) => {
        
        this.anuncios = anuncios;
        const longitud = this.anuncios.length;
        if (longitud > 0) {
          this.mostrarAnuncios = true;
          this.anuncioDerecha = this.anuncios[0];
          if (longitud === 2) {
            this.anuncioIzquierda = this.anuncios[1];
          }else {
            this.anuncioIzquierda = this.anuncios[0];
          }
        }
      },
      error: (error) => {
        console.error('Error al cargar los anuncios');
        console.error(error);
      }
    });
  }
}
