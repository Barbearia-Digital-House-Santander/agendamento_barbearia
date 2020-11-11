import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { stringify } from 'querystring';
import { map } from 'rxjs/operators';
import { Categoria } from '../models/categoria';

@Injectable({
  providedIn: 'root'
})
export class AgendamentoService {

  private headers = new HttpHeaders().set('Accept', 'application/json').set('Content-Type', 'application/json');
  private urlBarbearia:string;
  private baseUrlService:string = '';
  private x:any;  
  constructor(private http: HttpClient) {
    this.urlBarbearia = 'http://localhost:8080/barbearia';
    this.headers.append('Content-Type', 'application/json');
   }
    

/**CONSULTA TODAS AS CATEGORIAS CADASTRADAS */
    getTodasCategorias(){  
         
      return this.http.get(this.urlBarbearia + '/categorias',{headers: this.headers});
    }

    getServicosDaCategoria(categoria:number){
      return this.http.get(this.urlBarbearia + '/servicosDaCategoria/'+ categoria,{headers: this.headers});
    }

  } 
    