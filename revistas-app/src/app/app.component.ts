import { Component } from '@angular/core';
import { InicioComponent } from "./inicio/inicio.component";
import { RouterLink, RouterOutlet } from '@angular/router';
import { RegistroComponent } from "./registro/registro.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [InicioComponent, RouterLink, RouterOutlet, RegistroComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'revistas-app';
}
