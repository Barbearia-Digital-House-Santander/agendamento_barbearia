import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { stringify } from 'querystring';
import { map } from 'rxjs/operators';
import { Agendamento } from '../models/agendamento';
import { Categoria } from '../models/categoria';

@Injectable({
  providedIn: 'root'
})
export class AgendamentoService {

  private headers = new HttpHeaders().set('Accept', 'application/json').set('Content-Type', 'application/json');
  private urlAgenda:string;
  private baseUrlService:string = '';

  constructor(private http: HttpClient) {
    this.urlAgenda = 'http://localhost:8080/agenda';
    this.headers.append('Content-Type', 'application/json');
   }
    

    getClientes(){
      return this.http.get(this.urlAgenda + '/agendamentos',{headers: this.headers});
    }

    update(cliente: Agendamento): Promise<Agendamento> {
      const url = `${this.urlAgenda}/${cliente.idAgendamento}`; 
      return this.http
      .put(url, JSON.stringify(cliente), {headers:this.headers})
      .toPromise()
      .then(() => cliente as Agendamento)  
      .catch(this.trataErro);
  }

  private trataErro(err : any) : Promise<any> {
    return Promise.reject(err.message || err );
  }

  salvarAgendaCliente(agenda: any){
    return this.http.post(this.urlAgenda + '/salvarAgenda', agenda,{headers: this.headers});
  }

  podeSalvar(agenda:any){
    return this.http.post(this.urlAgenda + '/podeSalvar', agenda,{headers: this.headers});
  }

} 
    