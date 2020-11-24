import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Funcionario } from '../models/funcionario';
import { AutentificacaoService } from '../services/autentificacao.service';

@Component({
  selector: 'app-home-func',
  templateUrl: './home-func.component.html',
  styleUrls: ['./home-func.component.css']
})
export class HomeFuncComponent implements OnInit {

  usuario: Funcionario;

  logado:any;
  subject = new BehaviorSubject([]);

  destroy$: Subject<boolean> = new Subject<boolean>();
  router: Router;

  constructor(private service: AutentificacaoService, router: Router){
    this.router = router; }


  ngOnInit(): void {
    this.logado = this.service.usuario ;
    this.usuario = this.logado;
    this.subject.subscribe(a => a = this.logado);
    this.usuario;
  }

  logout(){
    this.service.lougoutUsuario(this.usuario).pipe(takeUntil(this.destroy$)).subscribe(mens => {
    
      this.router.navigate(['/', '']);
    });
  }

  pagMinhaAgenda(){
    this.router.navigate(['/', 'minhaAgenda']);
    this.service.setUsuarioLogado(this.logado);
  }

  pagMarcacoes(){
    this.router.navigate(['/', 'marcacoes']);
    this.service.setUsuarioLogado(this.logado);
  }

  pagHome(){
    this.router.navigate(['/', 'home']);
    this.service.setUsuarioLogado(this.logado);
  }
  pagFuncionarios(){
    this.router.navigate(['/', 'funcionarios']);
    this.service.setUsuarioLogado(this.logado);
  }
}
