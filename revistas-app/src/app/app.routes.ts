import { Routes } from '@angular/router';
import { RegistroComponent } from './registro/registro.component';
import { InicioComponent } from './inicio/inicio.component';
import { PruebaComponent } from './prueba/prueba.component';

export const routes: Routes = [
    {
    path: 'registro',
    title: 'registro',
    component: RegistroComponent
    },
    {
    path: 'inicio',
    title: 'Inicio',
    component: InicioComponent
    },
    {
    path: '',
    redirectTo: 'inicio',
    pathMatch: 'full'    
    },
    { path: '**', redirectTo: 'inicio' }
];
