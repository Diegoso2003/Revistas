import { Component } from '@angular/core';
import { AppComponent } from "../app.component";
import { CommonModule } from '@angular/common';
import { RouterLink, RouterModule, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-nav-anunciador',
  standalone: true,
  imports: [AppComponent, CommonModule, RouterLink, RouterOutlet, RouterModule],
  templateUrl: './nav-anunciador.component.html',
  styleUrl: './nav-anunciador.component.css'
})
export class NavAnunciadorComponent {

}
