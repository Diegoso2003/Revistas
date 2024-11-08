import { Component, inject, Input } from '@angular/core';
import { Pdf } from '../interfaces/pdf';
import { RevistasService } from '../servicios/revistas.service';

@Component({
  selector: 'app-pdf-button',
  standalone: true,
  imports: [],
  templateUrl: './pdf-button.component.html',
  styleUrl: './pdf-button.component.css'
})
export class PdfButtonComponent {

  @Input({required: true})
  pdf!: Pdf;

  private pdfservice = inject(RevistasService);

  descargarPdf() {
    this.pdfservice.obtenerPDF(this.pdf.idPdf).subscribe({
      next: (blob: Blob) => {
        const url = window.URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = this.pdf.nombre + '.pdf';
        link.click();
        window.URL.revokeObjectURL(url);
      },
      error: (error: any) => {
        console.error('Error al descargar el pdf');
        console.error(error);
      }
    });
  }
}
