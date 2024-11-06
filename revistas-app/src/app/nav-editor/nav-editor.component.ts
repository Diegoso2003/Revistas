import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink, RouterModule, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-nav-editor',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterModule, CommonModule],
  templateUrl: './nav-editor.component.html',
  styleUrl: './nav-editor.component.css'
})
export class NavEditorComponent {

}
