import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import Swal from 'sweetalert2';
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
  adicionaFunc = true;
  
  destroy$: Subject<boolean> = new Subject<boolean>();
  router: Router;

  constructor(private service: AutentificacaoService, router: Router){
    this.router = router; }


  ngOnInit(): void {
    this.logado = this.service.usuario ;
    if(this.service.usuario== undefined || this.service.usuario == null){
      this.router.navigate(['/', 'inicio']);
      this.naoExisteUsuarioLogado();
    }else{
      if(this.logado[0].nivels == 1){
        this.adicionaFunc = false;
      }
    this.usuario = this.logado;
    this.subject.subscribe(a => a = this.logado);
    this.usuario;
    this.msgBemVindo();
  }
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

  pagMeusDados(){
    this.router.navigate(['/', 'meusDados']);
    this.service.setUsuarioLogado(this.logado);
  }

  pagSenha(){
    this.router.navigate(['/', 'senha']);
    this.service.setUsuarioLogado(this.logado);
  }

  naoExisteUsuarioLogado(){  
    Swal.fire({  
      
      title: 'Ops...',  
      text: 'Parece que você perdeu a conexão faça seu login novamente para reiniciar a sessão.',  
      icon: 'error' ,
     footer: '<a href="login"> Sim, fazer login </a>',  
      
    });  
  }  

 msgBemVindo(){  
    Swal.fire({  
      
      title: 'Seja Bem-Vindo',  
      text: 'Ao nosso portal, você pode adicionar seus horários, visualizar seus clientes entre outros.',  
      icon: 'success' ,
      footer: 'Se é a sua primeira vez, altere sua senha acessando a página Meus Dados.',  
      
    });  
  }  
}
