import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import {MatSelectModule} from '@angular/material/select'; 
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { IConfig, NgxMaskModule } from 'ngx-mask';

//Templates
import { AppComponent } from './app.component';
import { GaleriaComponent } from './galeria/galeria.component';
import { HomeComponent } from './home/home.component';
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
import { ContatoComponent } from './contato/contato.component';
import { DisponiblidadeFuncionarioComponent } from './disponiblidade-funcionario/disponiblidade-funcionario.component';
import { HomeFuncComponent } from './home-func/home-func.component';
import { PreLoaderComponent } from './pre-loader/pre-loader.component';
import { ServicosComponent } from './servicos/servicos.component';
import { SobreComponent } from './sobre/sobre.component';
import { AdmComponent } from './adm/adm.component';
import { AjudaComponent } from './ajuda/ajuda.component';

const maskConfig: Partial<IConfig> = {
  validation: false,
};

@NgModule({
  declarations: [
    AppComponent,
    GaleriaComponent,
    HomeComponent,
    FooterComponent,
    HeaderComponent,
    MenuAgendaComponent,
    AgendamentoComponent,
    LoginComponent,
    DisplayBoardComponent,
    FuncionariosComponent,
    ClientesListaComponent,
    ContatoComponent,
    DisponiblidadeFuncionarioComponent,
    HomeFuncComponent,
    PreLoaderComponent,
    ServicosComponent,
    SobreComponent,
    AdmComponent,
    AjudaComponent,
    ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    MatSelectModule,
    BrowserAnimationsModule,
    NgxMaskModule.forRoot(maskConfig),
  ],
  providers: [AgendamentoService, DialogConfirmService],
  bootstrap: [AppComponent]
})
export class AppModule { }
