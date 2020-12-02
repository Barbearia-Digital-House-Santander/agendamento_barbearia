import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
  })
  export class CancelamentoService {
      
  private headers = new HttpHeaders().set('Accept', 'application/json').set('Content-Type', 'application/json');
  private urlCancelamento:string;
  private baseUrlService:string = '';

  constructor(private http: HttpClient) {
    this.urlCancelamento = 'http://localhost:8080/cancelar';
    this.headers.append('Content-Type', 'application/json');
   }

   fazerCancelamento(chave:any){
    return this.http.get(this.urlCancelamento + '/cancelaAgendamento/' + chave,{headers: this.headers});
  }

  }