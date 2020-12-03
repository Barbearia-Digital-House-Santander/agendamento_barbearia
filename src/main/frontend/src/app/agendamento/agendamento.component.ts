import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { map, takeUntil } from 'rxjs/operators';
import { AppService } from '../app.service';
import { Categoria } from '../models/categoria';
import { Disponibilidades } from '../models/disponibilidades';
import { Servicos } from '../models/servicos';
import { AgendamentoService } from '../services/agendamento.service';
import { SelectService } from '../services/selects.service';
import Swal from 'sweetalert2/dist/sweetalert2.js';  
import { Agendamento } from '../models/agendamento';
import { analyzeAndValidateNgModules } from '@angular/compiler';
import { Funcionario } from '../models/funcionario';

@Component({
  selector: 'app-agendamento',
  templateUrl: './agendamento.component.html',
  styleUrls: ['./agendamento.component.css']
})
export class AgendamentoComponent implements OnInit {
  d: Disponibilidades[];
  funcis: Funcionario;
  categoriaList: any[];
  servicoList: any[];
  horaList: any[];
  valorList: Servicos[];
  hrList:any[];
  funcionarioList: any[];
  dataList: any[];
  dispEspList: any[];
  dataCount = 0;
  timeCount = 0;
  catCount = 0;
  servCount = 0;
  selecionar: any;
  isHidden = true;
  isHidden2 = true;
  isHiddenSelect1 = true;
  isHiddenSelect2 = true;
  isHiddenHora1 = true;
  isHiddenHora2 = true;
  isHiddenFunc = true;
  isHiddenTotal  = true;
  disponiblidadeFunc: any;
  val:any;
  agendas:  Array<Agendamento> = [];
  mask:string;
 

  constructor(private service: AgendamentoService, private selects: SelectService) {
  }

  ngOnInit(): void {
    this.controlePrecisaDeProfissionalEspecifico();
    this.desabilitarFormsControllers();
   
    this.controleServicosDiponiveis();
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
    hora: new FormControl(),
    precisaProfissional: new FormControl('',Validators.nullValidator && Validators.required),
    funcionario: new FormControl(),
    valor:new FormControl(''),
    msg:new FormControl('')
  });

  destroy$: Subject<boolean> = new Subject<boolean>();

  onSubmit() {
    this.podeSalvarAgendamento();
}

  ngOnDestroy() {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }

  salvarAgendamento(){
    this.agendamentoForm.updateValueAndValidity();
    this.service.salvarAgendaCliente(this.agendamentoForm.value).pipe(takeUntil(this.destroy$)).subscribe(agenda => {
      this.agendamentoForm.reset();
    });
  }

  podeSalvarAgendamento(){
    
    this.service.podeSalvar(this.agendamentoForm.value).pipe(takeUntil(this.destroy$)).subscribe((agenda: Agendamento)=> {
      if( agenda.msg == "OK"){
          this.val = this.agendamentoForm.get('valor');
          this.confirmaAgendamentoCliente(this.val.value);
     }
     else{
          this.erroalert();
     }
    });
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

  buscarServicosValor(id:number){
    this.selects.getValorServicos(id).pipe(takeUntil(this.destroy$)).subscribe((val: any[]) => {
      this.valorList = val;
      this.isHiddenTotal = false;
      this.agendamentoForm.get('valor').setValue(this.valorList[0].valor);
    });
  }

  buscarHoras(data:any){
    this.selects.getHoraDisponivelData(data).pipe(takeUntil(this.destroy$)).subscribe((horas: any[]) => {
      this.horaList = horas;
      return this.horaList;
    });
  }

  buscarDisponibilidadeFuncionarioEspecifico(){
    this.selects.getDisponibilidades(this.agendamentoForm.get("funcionario").value).subscribe((disp : Disponibilidades[]) => {
      this.dispEspList = disp;
    });
  }

  buscarFuncionarios(){
    this.selects.getFuncionarios().pipe(takeUntil(this.destroy$)).subscribe((funcs: any[]) => {
      this.funcionarioList = funcs;
      return this.funcionarioList;
    });
  }

  buscarDatas() {
    this.selects.getDataHoraDisponiveis().pipe(takeUntil(this.destroy$)).subscribe((time: any[]) => {
      this.dataCount = time.length;
      this.dataList = time;
      return this.dataList;
    });
  }
  
  controlePrecisaDeProfissionalEspecifico(){
    this.agendamentoForm.get('precisaProfissional').valueChanges.subscribe(selectedValue => {
      
      this.limparFormsControllers();
     
      this.agendamentoForm.updateValueAndValidity();
      
      if(selectedValue == "Nao"){ 
       
          this.isHidden = false;
          this.isHidden2 = true;
          this.isHiddenFunc = true;
          
          this.agendamentoForm.get('valor').setValue(null);
          this.agendamentoForm.get('data').setValue(this.buscarDatas()); 
          this.agendamentoForm.get('categorias').setValue(this.buscarCategorias());
        
         
          this.agendamentoForm.get('categorias').enable();
          this.agendamentoForm.get('data').enable();
          this.controleDatasDiponiveisSemProfissional();
          this.controleCategoriasDiponiveisSemProfissional();
      }
     else if(selectedValue == "Sim"){
      this.agendamentoForm.updateValueAndValidity();
       this.isHidden = true;
       this.isHidden2 = true;
       this.isHiddenFunc = false;
     
        this.agendamentoForm.get('funcionario').setValue(this.buscarFuncionarios()); 
        this.controleFuncionarios();
        
      }
    });

  }

  desabilitarFormsControllers(){
    this.agendamentoForm.get('hora').disable();
    this.agendamentoForm.get('servicos').disable();
    this.agendamentoForm.get('categorias').disable();
    this.agendamentoForm.get('data').disable();
    
  }

  limparFormsControllers(){
    this.agendamentoForm.get('categorias').setValue(undefined);
    this.agendamentoForm.get('data').setValue(undefined);
    this.agendamentoForm.get('servicos').setValue(undefined);
    this.agendamentoForm.get('hora').setValue(undefined);
    this.agendamentoForm.get('funcionario').setValue(undefined);
    this.agendamentoForm.get('valor').setValue(null);

  }

  controleDatasDiponiveisSemProfissional(){
    this.agendamentoForm.get('data').valueChanges.subscribe(selectedValue => {
      this.isHiddenHora1 = false;
      this.agendamentoForm.get('hora').enable();
      this.agendamentoForm.get('hora').setValue(this.buscarHoras(this.agendamentoForm.value.data));
    });
  }

  controleCategoriasDiponiveisSemProfissional(){
    this.agendamentoForm.get('categorias').valueChanges.subscribe(selectedValue => {
      this.isHiddenSelect1 = false;
      this.isHiddenTotal = true;
      this.agendamentoForm.get('servicos').enable();
      this.agendamentoForm.get('servicos').setValue(this.buscarServicosDaCategoria(this.agendamentoForm.value.categorias));
      this.controleServicosDiponiveis();
    });
  }

  controleFuncionarios(){
    this.agendamentoForm.get('funcionario').valueChanges.subscribe((selectedValue: Funcionario) => {
      this.isHidden2 = false;
      this.agendamentoForm.get('categorias').enable();
      this.agendamentoForm.get('data').enable();
      this.buscarDisponibilidadeFuncionarioEspecifico();
      this.controleDatasDiponiveisComProfissional();
      this.controleCategoriasDiponiveisComProfissional();
    });
  }
  controleDatasDiponiveisComProfissional(){
    this.agendamentoForm.get('data').valueChanges.subscribe(selectedValue => {
      this.isHiddenHora2 = false;
      this.agendamentoForm.get('hora').enable();
      this.agendamentoForm.get('hora').setValue(this.buscarHorasFuncEsp(this.agendamentoForm.value.funcionario,this.agendamentoForm.value.data));

    });
  }
  buscarHorasFuncEsp(funcionario: any, data: any): any {
    this.selects.getHoraFunc(funcionario,data).pipe(takeUntil(this.destroy$)).subscribe((horas: any[]) => {
      this.hrList = horas;
      return this.hrList;
    });
  }

  controleCategoriasDiponiveisComProfissional(){
    
    this.agendamentoForm.get('categorias').valueChanges.subscribe(selectedValue => {
      this.isHiddenSelect2 = false;
      
      this.agendamentoForm.get('servicos').enable();
      this.agendamentoForm.get('servicos').setValue(this.buscarServicosDaCategoria(this.agendamentoForm.value.categorias));
      this.controleServicosDiponiveis();
    });
  }

  controleServicosDiponiveis(){
    
    this.agendamentoForm.get('servicos').valueChanges.subscribe(selectedValue => {
      this.buscarServicosValor(selectedValue);
        });
  }

////////alerts

confirmaAgendamentoCliente(valor: string){  
  Swal.fire({  
    title: 'Deseja realizar o agendamento?',  
    text: 'O valor do serviço a ser realidado será de:' + valor +', podendo ser pago em dinheiro ou cartão no dia da atividade.',  
    icon: 'warning',  
    showCancelButton: true,  
    confirmButtonText: 'Sim, me agende',  
    cancelButtonText: 'Não, deixe para a próxima'  
  }).then((result) => {  
    if (result.value) {  
      this.salvarAgendamento();
      Swal.fire(  
        'PARABÉNS!',  
        'seu agendamento foi realizado, vejo você em breve :) !!! \n\n ****RECIBO: PROCURE EM SEU COMPUTADOR O ARQUIVO: PDF_BarbeariaAgendamento.PDF',  
        'success'  
      )  
    } else if (result.dismiss === Swal.DismissReason.cancel) {  
      Swal.fire(  
        'AGENDAMENTO CANCELADO',  
        'Que pena, esperamos que você mude de ideia e marqur uma hora conosco, adorariamos ter você como cliente.',  
        'error'  
      )  
    }  
  })  
}  

erroalert()  
{  
  Swal.fire({  
    icon: 'error',  
    title: 'Oops... Parece que alguém passou na sua frente...',  
    text: 'Não foi possível fazer o agendamento, este horário está ocupado.',  
    footer: 'Tente com outro horário,data ou funcionário.'  
  })  
}  

}

