import { HttpClient } from '@angular/common/http';
import { analyzeAndValidateNgModules } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AppService } from '../app.service';
import { AgendamentoService } from '../services/agendamento.service';


@Component({
  selector: 'app-agendamento',
  templateUrl: './agendamento.component.html',
  styleUrls: ['./agendamento.component.css']
})
export class AgendamentoComponent implements OnInit {


 
  constructor(private service: AgendamentoService) { }

  teste:any;
  ngOnInit(): void {
    
    this.service.getTodasCategorias().subscribe(result => result = this.teste);
  }

  agendamentoForm = new FormGroup({
    cpf: new FormControl('', Validators.nullValidator && Validators.required),
    nome: new FormControl('', Validators.nullValidator && Validators.required),
    email: new FormControl('', Validators.nullValidator && Validators.required),
    telefone: new FormControl('', Validators.nullValidator && Validators.required),
    sexo: new FormControl('', Validators.nullValidator && Validators.required),
    servicos: new FormControl('', Validators.nullValidator && Validators.required),
    categoria: new FormControl('', Validators.nullValidator && Validators.required)
  });

 
} 


