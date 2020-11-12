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
    
   salvarFuncionario(user: any){
    return this.http.post(this.urlFuncionario + '/salvaFuncionario' + user,{headers: this.headers});
  }

  getTodosFuncionarios(){      
    return this.http.get(this.urlFuncionario + '/funcionarios',{headers: this.headers});
  }
}