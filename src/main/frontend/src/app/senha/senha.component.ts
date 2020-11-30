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
  selector: 'app-senha',
  templateUrl: './senha.component.html',
  styleUrls: ['./senha.component.css']
})
export class SenhaComponent implements OnInit {
  usuario: Funcionario;
  router: Router;
  logado:any;

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
         this.senhaForm.get('matricula').setValue(this.usuario.matricula);
        
         this.senhaForm.get('nomeFuncLogado').setValue(this.usuario.nome)
        
        }
       }
     
       senhaForm = new FormGroup({
         senha: new FormControl('', Validators.nullValidator && Validators.required),
         novaSenha: new FormControl('', Validators.nullValidator && Validators.required),
         nomeFuncLogado: new FormControl(''),
         matricula: new FormControl('')
       });
     
       onSubmit() {
        this.service.atualizarSenha(this.senhaForm.value).pipe(takeUntil(this.destroy$)).subscribe((func: Funcionario) => {
           if(func.msg == "ok"){    
             this.senhaForm.reset();
             this.msgSalvoComSucesso();
           }else{
             this.msgErroAoSalvarSenha;
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
  
  naoExisteUsuarioLogado(){  
    Swal.fire({  
      
      title: 'Ops...',  
      text: 'Parece que você perdeu a conexão faça seu login novamente para reiniciar a sessão.',  
      icon: 'error' ,
      footer: '<a href="login"> Sim, fazer login </a>',  
      
    });  
  }  

  pagSenha(){
    this.router.navigate(['/', 'senha']);
    this.serviceAut.setUsuarioLogado(this.logado);
  }

  msgErroAoSalvarSenha(){  
    Swal.fire({  
      
      title: 'Ops...',  
      text: 'Ocorreu um erro ao tentar registrar a nova senha, por favor tente mais tarde.',  
      icon: 'error' 
      
    });  
  }  

  msgSalvoComSucesso(){  
    Swal.fire({  
      
      title: 'Sucesso',  
      text: 'A nova senha foi registrada com sucesso!',  
      icon: 'success'       
    });  
  } 
}
