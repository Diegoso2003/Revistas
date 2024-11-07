import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Revista } from '../interfaces/revista';
import { RevistasService } from '../servicios/revistas.service';
import { AnuncioIzquierdaComponent } from '../anuncio-izquierda/anuncio-izquierda.component';
import { AnuncioDerechaComponent } from '../anuncio-derecha/anuncio-derecha.component';
import { AnuncioServiciosService } from '../servicios/anuncio-servicios.service';
import { Anuncio } from '../interfaces/anuncio';
import { Router } from '@angular/router';

@Component({
  selector: 'app-revista-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, AnuncioIzquierdaComponent, AnuncioDerechaComponent],
  templateUrl: './revista-form.component.html',
  styleUrl: './revista-form.component.css'
})
export class RevistaFormComponent implements OnInit {
  error: boolean = false;
  mensajeError: string = '';
  revistaForm!: FormGroup;
  categorias!: string[];
  revista!: Revista;
  anuncios: Anuncio[] = [];
  mostrarAnuncios: boolean = false;
  anuncioDerecha!: Anuncio;
  anuncioIzquierda!: Anuncio;
  url!: string;

  private _revistaService = inject(RevistasService);
  private _anunciosservice = inject(AnuncioServiciosService);
  private router = inject(Router);

  constructor(formbuilder: FormBuilder) {
    this.revistaForm = formbuilder.group({
      nombre: ['', [Validators.required, Validators.minLength(5)]],
      fecha: ['', [Validators.required]],
      nombreCategoria: ['', [Validators.required]],
      descripcion: ['', [Validators.required, Validators.minLength(10)]],
      etiquetasUnidas: ['', [Validators.required]],
      archivo: [null, [Validators.required]]
    });
  }

  enviar(event: Event) {
    event.preventDefault();
    this.revista = Object.assign({}, this.revistaForm.value, { archivo: undefined }) as Revista;
    console.log(this.revista);
    const formData = new FormData();

    this._revistaService.subirRevista(this.revista).subscribe({
      next: (revista : Revista) => {
        this.revista = revista;
        console.log('Revista subida correctamente');
        console.log(revista);
        const fileObject = this.revistaForm.get('archivo')?.value;
      if (fileObject) {
        formData.append('fileObject', fileObject, fileObject.name);
      }
        formData.append('idRevista', revista.id.toString());
        console.log('datos enviados');
        this._revistaService.subirPDF(formData).subscribe({
          next: () => {
            console.log('PDF subido correctamente');
            this.revistaForm.reset();
          },
          error: (error) => {
            this.error = true;
            this.mensajeError = error.error.mensaje || 'Error al subir el PDF';
          }
        });
      },
      error: (error) => {
        this.error = true;
        this.mensajeError =  error.error.mensaje || 'Error al subir la revista';
      }
    });
  }

  hasErrors(field: string, typeError: string) {
    return this.revistaForm.get(field)?.hasError(typeError) && this.revistaForm.get(field)?.touched;
  }

  onFileSelected(event: Event) {
    const fileInput = event.target as HTMLInputElement;
  if (fileInput.files && fileInput.files.length > 0) {
    const file = fileInput.files[0];
    this.revistaForm.patchValue({
      archivo: file, // Aquí asegúrate de que 'imagen' almacene el archivo en sí
    });
    this.revistaForm.get('archivo')?.updateValueAndValidity();
  }
  }

  ngOnInit(): void {
    this.url = this.router.url;
      this._revistaService.conseguirCategoria().subscribe({
        next: (categorias) => {
          this.categorias = categorias;
        },
        error: (error) => {
          this.error = true;
          this.mensajeError = 'Error al cargar las categorías';
        }
      });

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
