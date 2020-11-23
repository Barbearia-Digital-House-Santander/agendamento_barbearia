import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { HomeFuncComponent } from '../home-func/home-func.component';
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
    this.service.autenticarFuncionario(this.loginForm.value).pipe(takeUntil(this.destroy$)).subscribe(func => {
    
    this.service.setUsuarioLogado(func);
    this.loginForm.reset();
    this.router.navigate(['/', 'home']);
    
  });
}

}
