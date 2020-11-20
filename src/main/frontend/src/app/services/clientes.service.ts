import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/toPromise';
import { Agendamento } from '../models/agendamento';

/* import { Cliente } from './cliente.model';
import { CLIENTES } from './clientes-mock'; */

@Injectable()
export class ClientesService{

   // app é a pasta de onde fizermos a chamada
   // clientes é o nome da variável na classe InMemoryDataService
 /*  private urlCliente:string;
   private headers = new HttpHeaders().set('Accept', 'application/json').set('Content-Type', 'application/json');

    constructor(private http: HttpClient    ) {
      this.urlCliente = 'http://localhost:8080/clientes';
    }

    getClientes() : Promise<Agendamento[]> {

       return this.http.get(this.urlCliente) 
         .toPromise()
         .then(response => response.json().data as Agendamento[])
         .catch(this.trataErro);
      }

    private trataErro(err : any) : Promise<any> {
        return Promise.reject(err.message || err );
      }

    getCliente(id:number): Promise<Agendamento> {
           return this.getClientes()
           .then((clientes: Agendamento[]) => clientes.find(cliente => cliente.idAgendamento === id)); 
          }

    create(cliente: Agendamento): Promise<Agendamento> {
        return this.http
        .post(this.clientesUrl, JSON.stringify(cliente), {headers:this.headers})
        .toPromise()
        .then((response : Response) => {
            console.log(response.json().data);
            return response.json().data as Agendamento;  
        })
        .catch(this.trataErro);
    }

     update(cliente: Agendamento): Promise<Agendamento> {
        const url = `${this.clientesUrl}/${cliente.idAgendamento}`; //app/cliente/:id
        return this.http
        .put(url, JSON.stringify(cliente), {headers:this.headers})
        .toPromise()
        .then(() => cliente as Agendamento)  
        .catch(this.trataErro);
    }

     delete(cliente: Agendamento): Promise<Agendamento> {
        const url = `${this.clientesUrl}/${cliente.idAgendamento}`; //app/cliente/:id
        return this.http
        .delete(url, {headers:this.headers})
        .toPromise()
        .then(() => cliente as Agendamento)  
        .catch(this.trataErro);
    }
    */
}