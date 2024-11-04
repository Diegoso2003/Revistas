import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AnuncioServiciosService } from '../servicios/anuncio-servicios.service';

@Component({
  selector: 'app-anuncio-update',
  standalone: true,
  imports: [],
  templateUrl: './anuncio-update.component.html',
  styleUrl: './anuncio-update.component.css'
})
export class AnuncioUpdateComponent implements OnInit {
  private ruta = inject(ActivatedRoute);
  id!: number;
  private router = inject(Router);
  private _anuncioService = inject(AnuncioServiciosService);

  ngOnInit(): void {
     this.id = this.ruta.snapshot.params['codigo'];
     if (this.id) {
      
    } else {
      this.router.navigate(['/anunciador/anunciosVigentes']);
    }
}
}
