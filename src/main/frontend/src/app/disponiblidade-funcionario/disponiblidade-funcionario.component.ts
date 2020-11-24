import { Component, OnDestroy, OnInit } from '@angular/core';
import { FuncionarioService } from '../services/funcionario.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { Funcionario } from '../models/funcionario';
import { AutentificacaoService } from '../services/autentificacao.service';
import { LoginComponent } from '../login/login.component';
import { Router } from '@angular/router';
import { SelectService } from '../services/selects.service';

@Component({
  selector: 'app-disponiblidade-funcionario',
  templateUrl: './disponiblidade-funcionario.component.html',
  styleUrls: ['./disponiblidade-funcionario.component.css']
})
export class DisponiblidadeFuncionarioComponent implements OnDestroy {

  horaList: any[];
  timeCount = 0;
  disponiblidadeCount = 0;

  usuario: Funcionario;
  router: Router;
  logado:any;
  

  constructor(private service: FuncionarioService, private serviceAut: AutentificacaoService, 
    private selects: SelectService, router: Router){
    this.router = router; }


  ngOnInit(): void {
    this.logado = this.serviceAut.usuario;

    this.usuario = this.logado[0] ;
    this.disponivelForm.get('funcionario').setValue(this.usuario.nome);
    this.disponivelForm.get('hora').setValue(this.buscarHoras());
    this.usuario = this.logado;
  }
  

  disponivelForm = new FormGroup({
    funcionario: new FormControl('', Validators.nullValidator && Validators.required), 
    data: new FormControl('', Validators.nullValidator && Validators.required),
    hora: new FormControl('', Validators.nullValidator && Validators.required)

  });

  destroy$: Subject<boolean> = new Subject<boolean>();

  ngOnDestroy() {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }

  
  buscarHoras() {
    this.selects.getHora().pipe(takeUntil(this.destroy$)).subscribe((time: any[]) => {
      this.timeCount = time.length;
      this.horaList = time;
      return this.horaList;
    });
  }

  salvarDisponibilidade(){
    this.service.salvarDisponibilidadeDoFuncionario(this.disponivelForm.value).pipe(takeUntil(this.destroy$)).subscribe(data => {
      this.disponiblidadeCount = this.disponiblidadeCount + 1;
      this.disponivelForm.reset();
  });}
  
  onSubmit() {
    this.disponivelForm.value;
    this.salvarDisponibilidade();
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

}
