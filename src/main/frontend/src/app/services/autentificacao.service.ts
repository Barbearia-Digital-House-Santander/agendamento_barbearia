import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { stringify } from 'querystring';
import { map } from 'rxjs/operators';
import { Categoria } from '../models/categoria';

@Injectable({
  providedIn: 'root'
})
export class AutentificacaoService {

    
  private headers = new HttpHeaders()
  .set('Accept', 'application/json')
  .set('Content-Type', 'application/json')
  .set('Access-Control-Allow-Origin','*');


  private urlAutentica:string;
  usuario: any;
  pessoaLogada: any = [];

  constructor(private http: HttpClient) {
    this.urlAutentica = 'http://localhost:8080/autentificar';
    this.headers.append('Content-Type', 'application/json');
    this.headers.append('Access-Control-Allow-Origin','*');
   }

   autenticarFuncionario(usuario:any){
    const matricula = usuario.matricula;
    const senha = usuario.senha;
    return this.http.get(`${this.urlAutentica}/login/${matricula}/${senha}`,{headers: this.headers});
  }

  setUsuarioLogado(logado:any){
    this.usuario = logado;
    this.pessoaLogada.push(this.usuario);
  }

  lougoutUsuario(usuario:any){
    return this.http.get(`${this.urlAutentica}/logout/${usuario}`,{headers: this.headers});
  }
}