import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { HomeFuncComponent } from '../home-func/home-func.component';
import { Funcionario } from '../models/funcionario';
import { AutentificacaoService } from '../services/autentificacao.service';
import { FuncionarioService } from '../services/funcionario.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  users: any[] = [];
  userCount = 0;

  destroy$: Subject<boolean> = new Subject<boolean>();
  router: Router;

  constructor(private service: AutentificacaoService, router: Router){
    this.router = router; }

  ngOnInit(): void {
  }

  loginForm = new FormGroup({ 
    matricula: new FormControl('', Validators.nullValidator && Validators.required),
    senha: new FormControl('', Validators.nullValidator && Validators.required)
  });

  onSubmit() {
    this.service.autenticarFuncionario(this.loginForm.value).pipe(takeUntil(this.destroy$)).subscribe((func: Funcionario) => {
      if(func[0].msg == "ok"){
          this.service.pessoaLogada.push(func);
          this.service.setUsuarioLogado(func);
          this.loginForm.reset();
          this.router.navigate(['/', 'home']);
      }
      else{
        this.loginForm.reset();
        this.loginErrado();
      }
    
  });
}

loginErrado(){  
  Swal.fire({  
    
    title: 'Ops...',  
    text: 'Parece que você digitou a senha ou matrícula errada, tente novamente.',  
    icon: 'error' ,   
  });  
}  
}
