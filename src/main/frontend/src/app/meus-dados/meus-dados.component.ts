import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { Funcionario } from '../models/funcionario';
import { AutentificacaoService } from '../services/autentificacao.service';
import { FuncionarioService } from '../services/funcionario.service';

@Component({
  selector: 'app-meus-dados',
  templateUrl: './meus-dados.component.html',
  styleUrls: ['./meus-dados.component.css']
})
export class MeusDadosComponent implements OnInit {
  usuario: Funcionario;
  router: Router;
  logado:any;
  adicionaFunc = true;

  destroy$: Subject<boolean> = new Subject<boolean>();
  
  constructor(private service: FuncionarioService, private serviceAut: AutentificacaoService, router: Router){
    this.router = router; }

  ngOnInit(): void {
    this.logado = this.serviceAut.usuario;
    if(this.serviceAut.usuario== undefined || this.serviceAut.usuario == null){
      this.router.navigate(['/', 'inicio']);
      this.naoExisteUsuarioLogado();
    }else{
    this.usuario = this.logado[0];
    if(this.logado[0].nivels == 1){
      this.adicionaFunc = false;
    }
    this.meusDadosForm.get('nome').setValue(this.usuario.nome);
    this.meusDadosForm.get('cpf').setValue(this.usuario.cpf);
    this.meusDadosForm.get('categoria').setValue(this.usuario.categorias);
    this.meusDadosForm.get('dtNasc').setValue(this.usuario.dtNasc);
    this.meusDadosForm.get('matricula').setValue(this.usuario.matricula)
    this.meusDadosForm.get('categoria').setValue(this.usuario.categorias);
    this.meusDadosForm.get('dtNasc').setValue(this.usuario.dtNasc);
    this.meusDadosForm.get('cep').setValue(this.usuario.cep)
    this.meusDadosForm.get('email').setValue(this.usuario.email);
    this.meusDadosForm.get('endereco').setValue(this.usuario.endereco);
    this.meusDadosForm.get('telefone').setValue(this.usuario.telefone);
    this.meusDadosForm.get('nivel').setValue(this.usuario.nivels);
    this.meusDadosForm.get('nacionalidade').setValue(this.usuario.nacionalidade);
   }
  }

  meusDadosForm = new FormGroup({
    nome: new FormControl('', Validators.nullValidator && Validators.required),
    cpf: new FormControl('', Validators.nullValidator && Validators.required),
    email: new FormControl('', Validators.nullValidator && Validators.required),
    telefone: new FormControl('', Validators.nullValidator && Validators.required),
    endereco: new FormControl('', Validators.nullValidator && Validators.required),
    categoria: new FormControl('', Validators.nullValidator && Validators.required),
    cep: new FormControl('', Validators.nullValidator && Validators.required),
    genero: new FormControl('', Validators.nullValidator && Validators.required),
    nivel: new FormControl(''),
    dtNasc: new FormControl('', Validators.nullValidator && Validators.required),
    nacionalidade: new FormControl(''),
    matricula:new FormControl(''),
    senha:new FormControl('')
  });

  onSubmit() {
     this.service.atualizarDados(this.meusDadosForm.value).pipe(takeUntil(this.destroy$)).subscribe((func: Funcionario) => {
      if(func.msg == "ok"){
        this.meusDadosForm.reset();
        this.msgSalvoComSucesso();
      }else{
        this.msgErroAoAtualizarDados();
      }
    
  }); 
}

logout(){
  this.serviceAut.lougoutUsuario(this.usuario).pipe(takeUntil(this.destroy$)).subscribe(mens => {
  
    this.router.navigate(['/', '']);
  });
}

pagMinhaAgenda(){
  this.router.navigate(['/', 'minhaAgenda']);
  this.serviceAut.setUsuarioLogado(this.logado);
}

pagMarcacoes(){
  this.router.navigate(['/', 'marcacoes']);
  this.serviceAut.setUsuarioLogado(this.logado);
}

pagHome(){
  this.router.navigate(['/', 'home']);
  this.serviceAut.setUsuarioLogado(this.logado);
}
pagFuncionarios(){
  this.router.navigate(['/', 'funcionarios']);
  this.serviceAut.setUsuarioLogado(this.logado);
}

pagMeusDados(){
  this.router.navigate(['/', 'meusDados']);
  this.serviceAut.setUsuarioLogado(this.logado);
}

pagSenha(){
  this.router.navigate(['/', 'senha']);
  this.serviceAut.setUsuarioLogado(this.logado);
}

naoExisteUsuarioLogado(){  
  Swal.fire({  
    
    title: 'Ops...',  
    text: 'Parece que você perdeu a conexão faça seu login novamente para reiniciar a sessão.',  
    icon: 'error' ,
    footer: '<a href="login"> Sim, fazer login </a>',  
    
  });  
}  

msgErroAoAtualizarDados(){  
  Swal.fire({  
    
    title: 'Ops...',  
    text: 'Ocorreu um erro ao tentar atualizar seus dados, por favor tente mais tarde.',  
    icon: 'error' 
    
  });  
}  

msgSalvoComSucesso(){  
  Swal.fire({  
    
    title: 'Sucesso',  
    text: 'Dados atualizado com sucesso!',  
    icon: 'success'       
  });  
} 
}
