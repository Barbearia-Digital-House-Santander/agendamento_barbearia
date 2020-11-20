import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { AgendamentoService } from '../services/agendamento.service';
import { FuncionarioService } from '../services/funcionario.service';

@Component({
  selector: 'app-agenda-func',
  templateUrl: './agenda-func.component.html',
  styleUrls: ['./agenda-func.component.css']
})
export class AgendaFuncComponent implements OnInit {

  constructor(private service: FuncionarioService) { }

  users: any[] = [];
  horaList: any[];

  timeCount = 0;

  title = 'app-funcionario';

  userForm = new FormGroup({
    data: new FormControl('', Validators.nullValidator && Validators.required),
    hora: new FormControl('', Validators.nullValidator && Validators.required)

  });

  
  userCount = 0;

  destroy$: Subject<boolean> = new Subject<boolean>();

  onSubmit() {
      this.service.salvarDisponibilidade(this.userForm.value).pipe(takeUntil(this.destroy$)).subscribe(data => {
      this.userCount = this.userCount + 1;
      this.userForm.reset();
    });
  }

  getAllDisponibilidade() {
    this.service.getTodosFuncionarios().pipe(takeUntil(this.destroy$)).subscribe((horarios: any[]) => {
      this.userCount = horarios.length;
      this.users = horarios;
      return this.users;
    });
  }

  ngOnDestroy() {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }

  ngOnInit() {
    this.userForm.get('hora').setValue(this.buscarHoras());

	this.getAllDisponibilidade();
  }

/*   selecionaHora(horas:any){
    const id = horas.idCategoria;
    this.userForm.get("hora").setValue(id);
      this.service.getHora(id).pipe(takeUntil(this.destroy$)).subscribe((time: any[]) => {
        this.timeCount = time.length;
        this.horaList = time;
        return this.horaList;
      });
  } */

  buscarHoras() {
    this.service.getHora().pipe(takeUntil(this.destroy$)).subscribe((time: any[]) => {
      this.timeCount = time.length;
      this.horaList = time;
      return this.horaList;
    });
  }
}
