import { Component, OnInit , OnDestroy} from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { AgendamentoService } from '../services/agendamento.service';
import { FuncionarioService } from '../services/funcionario.service';

@Component({
  selector: 'app-funcionarios',
  templateUrl: './funcionarios.component.html',
  styleUrls: ['./funcionarios.component.css']
})
export class FuncionariosComponent implements OnDestroy {


  constructor(private service: FuncionarioService) { }


  title = 'app-funcionario';

  userForm = new FormGroup({
    nome: new FormControl('', Validators.nullValidator && Validators.required),
    cpf: new FormControl('', Validators.nullValidator && Validators.required),
    email: new FormControl('', Validators.nullValidator && Validators.required),
    telefone: new FormControl('', Validators.nullValidator && Validators.required),
    endereco: new FormControl('', Validators.nullValidator && Validators.required),
    uf: new FormControl('', Validators.nullValidator && Validators.required),
    categoria: new FormControl('', Validators.nullValidator && Validators.required),
    cep: new FormControl('', Validators.nullValidator && Validators.required),
    genero: new FormControl('', Validators.nullValidator && Validators.required),
    nivel: new FormControl('', Validators.nullValidator && Validators.required),

  });

  users: any[] = [];
  userCount = 0;

  destroy$: Subject<boolean> = new Subject<boolean>();

  onSubmit() {
      this.service.salvarFuncionario(this.userForm.value).pipe(takeUntil(this.destroy$)).subscribe(data => {
      this.userCount = this.userCount + 1;
      this.userForm.reset();
    });
  }

  getAllUsers() {
    this.service.getTodosFuncionarios().pipe(takeUntil(this.destroy$)).subscribe((funcionario: any[]) => {
      this.userCount = funcionario.length;
      this.users = funcionario;
      return this.users;
    });
  }

  ngOnDestroy() {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }

  ngOnInit() {
	this.getAllUsers();
  }
}