import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { stringify } from 'querystring';
import { map } from 'rxjs/operators';
import { Categoria } from '../models/categoria';

@Injectable({
  providedIn: 'root'
})
export class FuncionarioService {

  private headers = new HttpHeaders().set('Accept', 'application/json').set('Content-Type', 'application/json');
  private urlFuncionario:string;

  constructor(private http: HttpClient) {
    this.urlFuncionario = 'http://localhost:8080/funcionario';
    this.headers.append('Content-Type', 'application/json');
   }
    
   salvarFuncionario(usuario: any){
    return this.http.post(this.urlFuncionario +'/salvaFuncionario',usuario,{headers: this.headers});
  }

  salvarDisponibilidadeDoFuncionario(agenda: any){
    return this.http.post(this.urlFuncionario + '/salvarDisponibilidade', agenda,{headers: this.headers});
  }

  getTodosFuncionarios(){      
    return this.http.get(this.urlFuncionario + '/funcionarios',{headers: this.headers});
  }

  salvarDisponibilidade(disponiblidade: any){
    return this.http.post(this.urlFuncionario + '/disponiblidade' + disponiblidade,{headers: this.headers});

  }




}