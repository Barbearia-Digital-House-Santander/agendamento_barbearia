import { HttpClient } from '@angular/common/http';
import { analyzeAndValidateNgModules } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';


@Component({
  selector: 'app-agendamento',
  templateUrl: './agendamento.component.html',
  styleUrls: ['./agendamento.component.css']
})
export class AgendamentoComponent implements OnInit {


 
  constructor(private service: AppService) { }

  ngOnInit(): void {
    
    this.service.listar()._subscribe(result => this.)
  }


 
} 
}

onSubmit() {
this.userService.save(this.user).subscribe(result => this.gotoUserList());
}

