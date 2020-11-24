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
    const nome = usuario.nome;
    const cpf = usuario.cpf;
    const dtNasc = usuario.dtNasc;
    const endereco= usuario.endereco;
    const nacionalidade = usuario.nacionalidade;
    const categoria = usuario.categorias;
    const nivel = usuario.nivels;
    const uf = usuario.ufs;
    const cep = usuario.cep;
    const telefone = usuario.telefone;
    const email = usuario.email;
    const genero = usuario.genero;
    return this.http.post(`${this.urlFuncionario}/salvaFuncionario/${nome}/${cpf}/${dtNasc}/${endereco}/${uf}/${cep}/${nacionalidade}/${genero}/${telefone}/${categoria}/${email}/${nivel}`,{headers: this.headers});
  }

  salvarDisponibilidadeDoFuncionario(agenda: any){
    return this.http.post(this.urlFuncionario + '/salvarDisponibilidade' + agenda,{headers: this.headers});
  }

  getTodosFuncionarios(){      
    return this.http.get(this.urlFuncionario + '/funcionarios',{headers: this.headers});
  }

  salvarDisponibilidade(disponiblidade: any){
    return this.http.post(this.urlFuncionario + '/disponiblidade' + disponiblidade,{headers: this.headers});

  }




}