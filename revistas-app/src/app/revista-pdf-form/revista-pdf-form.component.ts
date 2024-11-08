import { Component, inject, Input } from '@angular/core';
import { RevistasService } from '../servicios/revistas.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-revista-pdf-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './revista-pdf-form.component.html',
  styleUrl: './revista-pdf-form.component.css'
})
export class RevistaPdfFormComponent {
  @Input({required: true})
  id!: number;

  private _revistaService = inject(RevistasService);
  private router = inject(Router);
  pdfForm: FormGroup;
  error: boolean = false;
  mensajeError: string = '';

  constructor(formBuilder: FormBuilder) {
    this.pdfForm = formBuilder.group({
      pdf: ['', Validators.required]
    });
  }

  onFileSelected(event: Event) {
    const fileInput = event.target as HTMLInputElement;
  if (fileInput.files && fileInput.files.length > 0) {
    const file = fileInput.files[0];
    this.pdfForm.patchValue({
      pdf: file, 
    });
    this.pdfForm.get('pdf')?.updateValueAndValidity();
  }
  }

  enviar(event: Event) {
    const formData = new FormData();
    const fileObject = this.pdfForm.get('pdf')?.value;
      if (fileObject) {
        formData.append('fileObject', fileObject, fileObject.name);
      }
        formData.append('idRevista', this.id.toString());

    this._revistaService.subirNuevoNumero(formData).subscribe({
      next: () => {
        console.log('Pdf actualizado correctamente');
        const currentUrl = this.router.url;
        this.router
          .navigateByUrl('/', { skipLocationChange: true })
          .then(() => {
            this.router.navigate([currentUrl]);
          });
      },
      error: (error: any) => {
        console.error(error);
        this.error = true;
        this.mensajeError = error.error.mensaje || 'Error al actualizar el pdf';
      }
    });
  }
}
