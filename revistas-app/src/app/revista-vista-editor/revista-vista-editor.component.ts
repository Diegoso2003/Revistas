import { Component, inject, OnInit } from '@angular/core';
import { Revista } from '../interfaces/revista';
import { RevistasService } from '../servicios/revistas.service';
import { Anuncio } from '../interfaces/anuncio';
import { ActivatedRoute, Router } from '@angular/router';
import { AnuncioServiciosService } from '../servicios/anuncio-servicios.service';
import { AnuncioDerechaComponent } from '../anuncio-derecha/anuncio-derecha.component';
import { AnuncioIzquierdaComponent } from '../anuncio-izquierda/anuncio-izquierda.component';
import { RevistaVistaComponent } from '../revista-vista/revista-vista.component';

@Component({
  selector: 'app-revista-vista-editor',
  standalone: true,
  imports: [AnuncioDerechaComponent, AnuncioIzquierdaComponent,RevistaVistaComponent],
  templateUrl: './revista-vista-editor.component.html',
  styleUrl: './revista-vista-editor.component.css'
})
export class RevistaVistaEditorComponent implements OnInit{
  revista!: Revista;
  bloqueo: boolean = false;
  anuncios: Anuncio[] = [];
  mostrarAnuncios: boolean = false;
  anuncioDerecha!: Anuncio;
  anuncioIzquierda!: Anuncio;
  url!: string;

  private _revistaservice = inject(RevistasService);
  private _anunciosservice = inject(AnuncioServiciosService);
  private router = inject(Router);
  private router2 = inject(ActivatedRoute);

  ngOnInit(): void {
    const id = this.router2.snapshot.params['id'] ?? null;
    if (!id) {
      this.router.navigate(['/editor/revistasSubidas']);
    }

    this._revistaservice.conseguirRevista(id).subscribe({
      next: (revista: Revista) => {
        this.revista = revista;
        console.log('Revista obtenida correctamente');
        if(revista){
          console.log(revista);
          this.bloqueo = revista.bloqueoAnuncios;
        } else{
          this.router.navigate(['/editor/revistasSubidas']);
        }

        if(!this.bloqueo){
          console.log('anuncios activos')
          console.log(this.bloqueo);
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
      },
      error: (error) => {
        console.error(error);
        this.router.navigate(['/editor/revistasSubidas']);
      },
    });

    

  }
}
