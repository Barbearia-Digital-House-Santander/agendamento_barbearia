import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import {MatSelectModule} from '@angular/material/select'; 
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { InMemoryWebApiModule } from 'angular-in-memory-web-api';

//Templates
import { AppComponent } from './app.component';
import { GaleriaComponent } from './galeria/galeria.component';
import { HomeComponent } from './home/home.component';
import { PreCarregamentoComponent } from './pre-carregamento/pre-carregamento.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { MenuAgendaComponent } from './menu-agenda/menu-agenda.component';
import { AgendamentoComponent } from './agendamento/agendamento.component';
import { LoginComponent } from './login/login.component';
import { AgendamentoService } from './services/agendamento.service';
import { DisplayBoardComponent } from './display-board/display-board.component';
import { FuncionariosComponent } from './funcionarios/funcionarios.component';
import { DialogConfirmService } from './services/dialogconfirm.service';
import { ClientesListaComponent } from './clientes-lista/clientes-lista.component';


@NgModule({
  declarations: [
    AppComponent,
    GaleriaComponent,
    HomeComponent,
    PreCarregamentoComponent,
    FooterComponent,
    HeaderComponent,
    MenuAgendaComponent,
    AgendamentoComponent,
    LoginComponent,
    DisplayBoardComponent,
    FuncionariosComponent,
    ClientesListaComponent
    ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    MatSelectModule,
    BrowserAnimationsModule,
  ],
  providers: [AgendamentoService, DialogConfirmService],
  bootstrap: [AppComponent]
})
export class AppModule { }
