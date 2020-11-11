import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { map, takeUntil } from 'rxjs/operators';
import { AppService } from '../app.service';
import { Categoria } from '../models/categoria';
import { AgendamentoService } from '../services/agendamento.service';


@Component({
  selector: 'app-agendamento',
  templateUrl: './agendamento.component.html',
  styleUrls: ['./agendamento.component.css']
})
export class AgendamentoComponent implements OnInit {
  categoriaList: any[];
  servicoList: any[];
  catCount = 0;

  constructor(private service: AgendamentoService) {
  }

  ngOnInit(): void {
    this.agendamentoForm.get('categorias').setValue(this.buscarCategorias());

  }

  agendamentoForm = new FormGroup({
    cpf: new FormControl('', Validators.nullValidator && Validators.required),
    nome: new FormControl('', Validators.nullValidator && Validators.required),
    email: new FormControl('', Validators.nullValidator && Validators.required),
    telefone: new FormControl('', Validators.nullValidator && Validators.required),
    sexo: new FormControl('', Validators.nullValidator && Validators.required),
    servicos: new FormControl({value:'', disabled: true}, Validators.nullValidator && Validators.required),
    categorias: new FormControl()
  });

  
  


  destroy$: Subject<boolean> = new Subject<boolean>();

  buscarCategorias() {
    this.service.getTodasCategorias().pipe(takeUntil(this.destroy$)).subscribe((categoria: any[]) => {
      this.catCount = categoria.length;
      this.categoriaList = categoria;
      return this.categoriaList;
    });
  }

  ngOnDestroy() {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }

  selecionaCategoria(categoria:any){
    const id = categoria.idCategoria;
    this.agendamentoForm.get("categorias").setValue(id);
    this.agendamentoForm.get("servicos").enable();
      this.service.getServicosDaCategoria(id).pipe(takeUntil(this.destroy$)).subscribe((servs: any[]) => {
        this.catCount = servs.length;
        this.servicoList = servs;
        return this.servicoList;
      });
  }

  selecionaServico(serv:any){
    const id = serv.idServico;
    this.agendamentoForm.get("servicos").setValue(id);
    //this.agendamentoForm.get("servicos").enable();
      
  }
}


