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
import { Disponibilidades } from '../models/disponibilidades';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-disponiblidade-funcionario',
  templateUrl: './disponiblidade-funcionario.component.html',
  styleUrls: ['./disponiblidade-funcionario.component.css']
})
export class DisponiblidadeFuncionarioComponent implements OnDestroy {

  horaList: any[];
  timeCount = 0;
  disponiblidadeCount = 0;
  marcacoes = 0;
  disponibilidade : Disponibilidades[] ;
  usuario: Funcionario;
  router: Router;
  logado:any;
  dataPassada = false;

  constructor(private service: FuncionarioService, private serviceAut: AutentificacaoService, 
    private selects: SelectService, router: Router){
    this.router = router; }


  ngOnInit(): void {
     this.logado = this.serviceAut.usuario;

   
    if(this.serviceAut.usuario== undefined || this.serviceAut.usuario == null){
      this.router.navigate(['/', 'inicio']);
      this.naoExisteUsuarioLogado();
    }else{
      this.usuario = this.logado[0] ;
    this.disponivelForm.get('funcionario').setValue(this.usuario.nome);
    this.disponivelForm.get('hora').setValue(this.buscarHoras());
    

    this.selects.getDisponibilidades(this.usuario.nome)
        .subscribe((disp : Disponibilidades[]) => {
            this.disponibilidade = disp;
        });
        this.usuario = this.logado;

        this.disponivelForm.get('data').valueChanges.subscribe(selectedValue => {
          if(selectedValue != ''){
              this.verificaSeDataPassada(selectedValue);
           
          } });
        }
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
      this.selects.getDisponibilidades(this.disponivelForm.get("funcionario").value).subscribe((disp : Disponibilidades[]) => {
        this.disponibilidade = disp;
    });
  });}
  
  onSubmit() {
    this.disponivelForm.value;
    if(this.dataPassada == false){
      this.salvarDisponibilidade();
    }else{
      Swal.fire('Atenção', 'A data deve ser posterior ao dia de hoje.', 'error')
    }
}

logout(){
  this.serviceAut.lougoutUsuario(this.usuario).pipe(takeUntil(this.destroy$)).subscribe(mens => {
  
    this.router.navigate(['/', '']);
  });
}

pagMeusDados(){
  this.router.navigate(['/', 'meusDados']);
  this.serviceAut.setUsuarioLogado(this.logado);
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

verificaSeDataPassada(dt: any){
  this.service.isDataPassada(dt).pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
    this.dataPassada = data;
    if( this.dataPassada == true){
      Swal.fire('Atenção', 'A data deve ser posterior ao dia de hoje.', 'error')
    }
  });
}

naoExisteUsuarioLogado(){  
  Swal.fire({  
    
    title: 'Ops...',  
    text: 'Parece que você perdeu a conexão faça seu login novamente para reiniciar a sessão.',  
    icon: 'error' ,
   footer: '<a href="login"> Sim, fazer login </a>',  
    
  });  
}  

}
