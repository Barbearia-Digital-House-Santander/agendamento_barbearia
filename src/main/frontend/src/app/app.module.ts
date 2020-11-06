import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { HttpClientModule } from '@angular/common/http';

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
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [AgendamentoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
