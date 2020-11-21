import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { stringify } from 'querystring';
import { map } from 'rxjs/operators';
import { Categoria } from '../models/categoria';

@Injectable({
  providedIn: 'root'
})
export class AutentificacaoService {

    
  private headers = new HttpHeaders().set('Accept', 'application/json').set('Content-Type', 'application/json');
  private urlAutentica:string;

  constructor(private http: HttpClient) {
    this.urlAutentica = 'http://localhost:8080/autentificar';
    this.headers.append('Content-Type', 'application/json');
   }

   autenticarFuncionario(usuario:any){
    return this.http.get(this.urlAutentica + '/login' + usuario,{headers: this.headers});

  }
}