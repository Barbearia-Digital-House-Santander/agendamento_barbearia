import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { map, takeUntil } from 'rxjs/operators';
import { AppService } from '../app.service';
import { Categoria } from '../models/categoria';
import { AgendamentoService } from '../services/agendamento.service';
import { SelectService } from '../services/selects.service';


@Component({
  selector: 'app-agendamento',
  templateUrl: './agendamento.component.html',
  styleUrls: ['./agendamento.component.css']
})
export class AgendamentoComponent implements OnInit {
  categoriaList: any[];
  servicoList: any[];
  horaList: any[];
  dataList: any[];
  dataCount = 0;
  timeCount = 0;
  catCount = 0;
  servCount = 0;
  selecionar: any;

  constructor(private service: AgendamentoService, private selects: SelectService) {
  }

  ngOnInit(): void {
     this.agendamentoForm.get('categorias').setValue(this.buscarCategorias());
     this.agendamentoForm.get('hora').disable();
     this.agendamentoForm.get('data').setValue(this.buscarDatas()); 
    
     this.agendamentoForm.get('categorias').valueChanges.subscribe(selectedValue => {
      this.agendamentoForm.get('servicos').enable();
      this.agendamentoForm.get('servicos').setValue(this.buscarServicosDaCategoria(this.agendamentoForm.value.categorias));
    });

    this.agendamentoForm.get('data').valueChanges.subscribe(selectedValue => {
      this.agendamentoForm.get('hora').enable();
      this.agendamentoForm.get('hora').setValue(this.buscarHoras(this.agendamentoForm.value.data));
    });
  }

  agendamentoForm = new FormGroup({
    cpf: new FormControl('', Validators.nullValidator && Validators.required),
    nome: new FormControl('', Validators.nullValidator && Validators.required),
    email: new FormControl('', Validators.nullValidator && Validators.required),
    telefone: new FormControl('', Validators.nullValidator && Validators.required),
    sexo: new FormControl('', Validators.nullValidator && Validators.required),
    servicos: new FormControl({value:'', disabled: true}, Validators.nullValidator && Validators.required),
    categorias: new FormControl(),
    data: new FormControl(),
    hora: new FormControl()
  });

  destroy$: Subject<boolean> = new Subject<boolean>();

  onSubmit() {
    this.agendamentoForm.value;
    this.salvarAgendamento();
}


  ngOnDestroy() {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }

  salvarAgendamento(){
  }

  
  buscarCategorias() {
    this.selects.getTodasCategorias().pipe(takeUntil(this.destroy$)).subscribe((categoria: any[]) => {
      this.catCount = categoria.length;
      this.categoriaList = categoria;
      return this.categoriaList;
    });
  }

  buscarServicosDaCategoria(id:number){
    this.selects.getServicosDaCategoria(id).pipe(takeUntil(this.destroy$)).subscribe((servs: any[]) => {
      this.servCount = servs.length;
      this.servicoList = servs;
      return this.servicoList;
    });
  }

  buscarHoras(data:any){
    this.selects.getHoraDisponivelData(data).pipe(takeUntil(this.destroy$)).subscribe((horas: any[]) => {
      this.horaList = horas;
      return this.horaList;
    });
  }

  buscarDatas() {
    this.selects.getDataHoraDisponiveis().pipe(takeUntil(this.destroy$)).subscribe((time: any[]) => {
      this.dataCount = time.length;
      this.dataList = time;
      return this.dataList;
    });
  }
  

}

