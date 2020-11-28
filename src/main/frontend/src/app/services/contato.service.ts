import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

export interface Contato {
  nome: string;
  email: string;
  assunto: string;
  mensagem: string;
}

@Injectable({
  providedIn: 'root'
})
export class ContatoService{
  private headers = new HttpHeaders().set('Accept', 'application/json').set('Content-Type', 'application/json');
  private urlContato:string;

  constructor(private http: HttpClient) {
    this.urlContato = 'http://localhost:8080/contato';
    this.headers.append('Content-Type', 'application/json');
  }

  salvarContato(contato: Contato){
    return this.http.post(this.urlContato + '/adicionar', contato,{headers: this.headers});
  }
}