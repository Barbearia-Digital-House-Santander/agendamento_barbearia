import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { stringify } from 'querystring';
import { map } from 'rxjs/operators';
import { Categoria } from '../models/categoria';

@Injectable({
  providedIn: 'root'
})
export class SelectService {

  private headers = new HttpHeaders().set('Accept', 'application/json').set('Content-Type', 'application/json');
  private urlFuncionario:string;
  private urlAgenda:string;

  constructor(private http: HttpClient) {
    this.urlAgenda = 'http://localhost:8080/agenda';
    this.urlFuncionario = 'http://localhost:8080/funcionario';
    this.headers.append('Content-Type', 'application/json');
   }

   getHora(){
    return this.http.get(this.urlFuncionario + '/hora',{headers: this.headers});
  }

  getFuncionarios() {
    return this.http.get(this.urlFuncionario + '/funcionarios',{headers: this.headers});
  }

  getUF(){
    return this.http.get(this.urlFuncionario + '/ufs',{headers: this.headers});
  }

 getNivel(){
    return this.http.get(this.urlFuncionario + '/nivel',{headers: this.headers});
  }

  getHoraDisponiveis(){
    return this.http.get(this.urlAgenda + '/horaDisponiveis',{headers: this.headers});
  }
  getDataHoraDisponiveis(){
    return this.http.get(this.urlAgenda + '/datahoraDisponiveis',{headers: this.headers});
  }
  
/**CONSULTA TODAS AS CATEGORIAS CADASTRADAS */
  getTodasCategorias(){      
    return this.http.get(this.urlAgenda + '/categorias',{headers: this.headers});
  }

  getServicosDaCategoria(categoria:number){
    return this.http.get(this.urlAgenda + '/servicosDaCategoria/'+ categoria,{headers: this.headers});
  }

  getHoraDisponivelData(data:any){
    return this.http.get(this.urlAgenda + '/horaDisponivel/'+ data,{headers: this.headers});
  }

  getDisponibilidades(funcionario:string) {
    return this.http.get(this.urlFuncionario + '/getDisponibilidadeDoFuncionario/' + funcionario,{headers: this.headers});
    }

    getHoraFunc(funcionario: any, data: any) {
      return this.http.get(`${this.urlFuncionario}/getHoraFuncionario/${funcionario}/${data}`,{headers: this.headers});
    }
}