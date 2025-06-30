import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component'; 
import { CadastroComponent } from './pages/cadastro/cadastro.component'; 
import {NavbarComponent } from './pages/navbar/navbar.component'; 
import {RoomComponent } from './pages/room/room.component'; 
export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'cadastro', component: CadastroComponent },
    { path: 'navbar', component: NavbarComponent },
     { path: 'room', component: RoomComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' } 
];
