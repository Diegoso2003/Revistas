import { Component, Input } from '@angular/core';
import { Pdf } from '../interfaces/pdf';

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
}
