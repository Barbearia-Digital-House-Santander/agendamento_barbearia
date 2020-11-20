import { Component, OnInit } from '@angular/core';
import { Agendamento } from '../models/agendamento';
import { AgendamentoService } from '../services/agendamento.service';
import { ClientesService } from '../services/clientes.service';
import { DialogConfirmService } from '../services/dialogconfirm.service';

@Component({
  selector: 'app-clientes-lista',
  templateUrl: './clientes-lista.component.html',
  styleUrls: ['./clientes-lista.component.css']
})
export class ClientesListaComponent implements OnInit {
  clientes : Agendamento[] ;

    constructor(
        private agendaService : AgendamentoService,
        private dialogconfirmService :  DialogConfirmService
        ){}

    ngOnInit() : void {
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
}