import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

//importando nossas rotas
import { GaleriaComponent } from '../galeria/galeria.component';
import { HomeComponent } from '../home/home.component';
import { MenuAgendaComponent } from '../menu-agenda/menu-agenda.component';
import { AgendamentoComponent } from '../agendamento/agendamento.component';
import { LoginComponent } from '../login/login.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'galeria', component: GaleriaComponent },
  { path: 'agendaCentral', component: MenuAgendaComponent },
  { path: 'agendamento', component: AgendamentoComponent },
  { path: 'login', component: LoginComponent } 
  
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
