import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AgendamentoService {

  private headers: HttpHeaders;
  private urlBarbearia:string;
  private baseUrlService:string = '';

  constructor(private http: HttpClient) {
    this.urlBarbearia = 'http://localhost:8080/barbearia';
    //this.headers.append('Content-Type', 'application/json');
   }
    

/**CONSULTA TODAS AS CATEGORIAS CADASTRADAS */
    getTodasCategorias(){        
        return this.http.get(this.urlBarbearia + '/categorias', { headers: this.headers });
    }
  }
