import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ObjetosComponent } from './objetos/objetos.component';
import { ContactoComponent } from './contacto/contacto.component';
import { DetallesObjetoComponent } from './detalles-objeto/detalles-objeto.component';

export const routes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'objetos', component: ObjetosComponent },
    { path: 'objetos/:objetosId', component: DetallesObjetoComponent },
    { path: 'contacto', component: ContactoComponent },
    { path: '', redirectTo: '/home', pathMatch: 'full' }
];