import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Headers} from '@angular/http';
import { RequestOptions } from '@angular/http';

@Injectable({
  providedIn: 'root'
})
export class AgendamentoService {

  private headers:Headers;
  private options:RequestOptions;
  private urlBarbearia:string;
  private baseUrlService:string = '';

  constructor(private http: Http) {
    this.urlBarbearia = 'http://localhost:8090/barbearia/agendamento';
   }

   getUrlBarbearia(): string {
 
    return this.urlBarbearia;
}

    /**SETANDO A URL DO SERVIÇO REST QUE VAI SER ACESSADO */
    this.baseUrlService = getUrlBarbearia() + '/pessoa/';

    /*ADICIONANDO O JSON NO HEADER */
    this.headers = new Headers({ 'Content-Type': 'application/json;charset=UTF-8' });                
    this.options = new RequestOptions({ headers: this.headers });
}

/**CONSULTA TODAS AS PESSOAS CADASTRADAS */
getPessoas(){        
    return this.http.get(this.baseUrlService).map(res => res.json());
}

