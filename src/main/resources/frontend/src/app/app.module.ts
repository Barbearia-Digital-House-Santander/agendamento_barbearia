import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing/app-routing.module';

import { AppComponent } from './app.component';
import { GaleriaComponent } from './galeria/galeria.component';
import { HomeComponent } from './home/home.component';
import { PreCarregamentoComponent } from './pre-carregamento/pre-carregamento.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { MenuAgendaComponent } from './menu-agenda/menu-agenda.component';
import { AgendamentoComponent } from './agendamento/agendamento.component';

@NgModule({
  declarations: [
    AppComponent,
    GaleriaComponent,
    HomeComponent,
    PreCarregamentoComponent,
    FooterComponent,
    HeaderComponent,
    MenuAgendaComponent,
    AgendamentoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
