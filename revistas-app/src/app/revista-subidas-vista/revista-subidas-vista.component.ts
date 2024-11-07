import { Component, inject, OnInit } from '@angular/core';
import { RevistasService } from '../servicios/revistas.service';
import { Revista } from '../interfaces/revista';
import { RevistaVistaComponent } from '../revista-vista/revista-vista.component';
import { AnuncioDerechaComponent } from '../anuncio-derecha/anuncio-derecha.component';
import { AnuncioIzquierdaComponent } from '../anuncio-izquierda/anuncio-izquierda.component';
import { Anuncio } from '../interfaces/anuncio';
import { Router } from '@angular/router';
import { AnuncioServiciosService } from '../servicios/anuncio-servicios.service';

@Component({
  selector: 'app-revista-subidas-vista',
  standalone: true,
  imports: [RevistaVistaComponent, AnuncioDerechaComponent, AnuncioIzquierdaComponent],
  templateUrl: './revista-subidas-vista.component.html',
  styleUrl: './revista-subidas-vista.component.css'
})
export class RevistaSubidasVistaComponent implements OnInit {
  private _revistaService = inject(RevistasService);

  revistas: Revista[] = [];
  anuncios: Anuncio[] = [];
  mostrarAnuncios: boolean = false;
  anuncioDerecha!: Anuncio;
  anuncioIzquierda!: Anuncio;
  url!: string;

  private _anunciosservice = inject(AnuncioServiciosService);
  private router = inject(Router);

  ngOnInit(): void {
    this._revistaService.conseguirRevistas().subscribe({
      next: (revistas: Revista[]) => {
        this.revistas = revistas;
      },
      error: (error) => {
        console.error('Error al cargar las revistas');
        console.error(error);
      }
    });

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
