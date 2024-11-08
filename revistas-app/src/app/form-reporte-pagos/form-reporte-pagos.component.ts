import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ReportePago } from '../interfaces/reportePago';
import { Anuncio } from '../interfaces/anuncio';
import { Router } from '@angular/router';
import { AnuncioServiciosService } from '../servicios/anuncio-servicios.service';
import { AnuncioDerechaComponent } from '../anuncio-derecha/anuncio-derecha.component';
import { AnuncioIzquierdaComponent } from '../anuncio-izquierda/anuncio-izquierda.component';
import { ReporteservicioService } from '../servicios/reporteservicio.service';

@Component({
  selector: 'app-form-reporte-pagos',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule,
    AnuncioDerechaComponent,
    AnuncioDerechaComponent,
    AnuncioIzquierdaComponent,
  ],
  templateUrl: './form-reporte-pagos.component.html',
  styleUrl: './form-reporte-pagos.component.css',
})
export class FormReportePagosComponent implements OnInit {
  formReportePago: FormGroup;
  anuncios: Anuncio[] = [];
  mostrarAnuncios: boolean = false;
  anuncioDerecha!: Anuncio;
  anuncioIzquierda!: Anuncio;
  url!: string;

  private _anunciosservice = inject(AnuncioServiciosService);
  private router = inject(Router);
  private _reporteservice = inject(ReporteservicioService);

  reportePago!: ReportePago;
  constructor(private formbuilder: FormBuilder) {
    this.formReportePago = this.formbuilder.group({
      fechaInicio: [''],
      fechaFin: [''],
      id: [''],
    });
  }

  enviar(event: Event) {
    this.reportePago = this.formReportePago.value as ReportePago;

    this._reporteservice.filtrarReportePago(this.reportePago).subscribe({
      next: (blob: Blob) => {
        const url = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = 'ReporteAnuncios.pdf';
        link.click();
        window.URL.revokeObjectURL(url);
      },
      error: (error: any) => {
        console.error('Error al generar el reporte');
        console.error(error);
      },
    });
  }

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
          } else {
            this.anuncioIzquierda = this.anuncios[0];
          }
        }
      },
      error: (error) => {
        console.error('Error al cargar los anuncios');
        console.error(error);
      },
    });
  }
}
