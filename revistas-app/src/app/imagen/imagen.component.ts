import { Component, inject, Input, OnInit } from '@angular/core';
import { SafeUrl } from '@angular/platform-browser';
import { AnuncioServiciosService } from '../servicios/anuncio-servicios.service';

@Component({
  selector: 'app-imagen',
  standalone: true,
  imports: [],
  templateUrl: './imagen.component.html',
  styleUrl: './imagen.component.css',
})
export class ImagenComponent implements OnInit {
  @Input({ required: true })
  id!: number;
  anuncioservice = inject(AnuncioServiciosService);
  imagenUrl: SafeUrl | undefined;

  ngOnInit(): void {
    this.anuncioservice.obtenerImagen(this.id).subscribe({
      next: (imagen) => {
        const url = URL.createObjectURL(imagen);
        this.imagenUrl = url; // Aquí guar
      },
      error: (error) => {
        console.error('Error al obtener la imagen', error);
      },
    });
  }
}
