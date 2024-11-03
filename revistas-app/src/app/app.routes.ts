import { Routes } from '@angular/router';
import { RegistroComponent } from './registro/registro.component';
import { InicioComponent } from './inicio/inicio.component';
import { NavAnunciadorComponent } from './nav-anunciador/nav-anunciador.component';
import { NavEditorComponent } from './nav-editor/nav-editor.component';
import { NavLectorComponent } from './nav-lector/nav-lector.component';
import { NavAdminComponent } from './nav-admin/nav-admin.component';
import { FormCompraAnuncioComponent } from './form-compra-anuncio/form-compra-anuncio.component';
import { CarteraComponent } from './cartera/cartera.component';
import { AnunciosVigentesVistaComponent } from './anuncios-vigentes-vista/anuncios-vigentes-vista.component';

export const routes: Routes = [
    //canActivate: [authGuard]
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
        path: 'anunciador',
        title: 'Anunciador',
        component: NavAnunciadorComponent,
        children: [
            {
                path: 'subirAnuncio',
                title: 'Subir Anuncio',
                component: FormCompraAnuncioComponent
            },
            {
                path: 'recargarSaldo',
                title: 'Recargar Saldo',
                component: CarteraComponent
            },
            {
                path: 'anunciosVigentes',
                title: 'Anuncios Vigentes',
                component: AnunciosVigentesVistaComponent
            },
            {
                path: '',
                redirectTo: 'subirAnuncio',
                pathMatch: 'full'    
                },
                { path: '**', redirectTo: 'subirAnuncio' }
        ]
    },
    {
        path: 'editor',
        title: 'Editor',
        component: NavEditorComponent
    },
    {
        path: 'lector',
        title: 'Lector',
        component: NavLectorComponent
    },
    {
        path: 'administrador',
        title: 'Administrador',
        component: NavAdminComponent
    },
    {
    path: '',
    redirectTo: 'inicio',
    pathMatch: 'full'    
    },
    { path: '**', redirectTo: 'inicio' }
];
