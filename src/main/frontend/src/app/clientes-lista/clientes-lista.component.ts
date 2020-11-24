import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Agendamento } from '../models/agendamento';
import { Funcionario } from '../models/funcionario';
import { AgendamentoService } from '../services/agendamento.service';
import { AutentificacaoService } from '../services/autentificacao.service';
import { ClientesService } from '../services/clientes.service';
import { DialogConfirmService } from '../services/dialogconfirm.service';

@Component({
  selector: 'app-clientes-lista',
  templateUrl: './clientes-lista.component.html',
  styleUrls: ['./clientes-lista.component.css']
})
export class ClientesListaComponent implements OnInit {
  clientes : Agendamento[] ;
  usuario: Funcionario;
  router: Router;
  logado:any;

  destroy$: Subject<boolean> = new Subject<boolean>();

    constructor(
        private agendaService : AgendamentoService,
        private dialogconfirmService :  DialogConfirmService,
        private serviceAut: AutentificacaoService, 
        router: Router){
            this.router = router; }

    ngOnInit() : void {
        this.logado = this.serviceAut.usuario;
        this.usuario = this.logado; 

        this.agendaService.getClientes()
        .subscribe((clientes : Agendamento[]) => {
            this.clientes = clientes;
        })
     }
    

    onDelete(cliente : Agendamento) : void {
        this.dialogconfirmService.confirm('Deseja remarcar o cliente ' + cliente.nome + ' ?')
        .then((podeAlterar : boolean) => {

            if(podeAlterar) {
                this.agendaService
                .update(cliente)
                .then(()=> {
                     this.clientes = this.clientes.filter((c:Agendamento) => c.idAgendamento != cliente.idAgendamento);
                }).catch(err => {
                   console.log(err);
                });
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
}