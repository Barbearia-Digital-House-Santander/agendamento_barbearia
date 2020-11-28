import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { ContatoService } from '../services/contato.service';

@Component({
  selector: 'app-contato',
  templateUrl: './contato.component.html',
  styleUrls: ['./contato.component.css']
})
export class ContatoComponent implements OnInit {

  constructor(private service: ContatoService) { }

  ngOnInit(): void {
  }

  contatoForm = new FormGroup({
    nome: new FormControl('', Validators.nullValidator && Validators.required),
    email: new FormControl('', Validators.nullValidator && Validators.required),
    assunto: new FormControl('', Validators.nullValidator && Validators.required),
    mensagem: new FormControl('', Validators.nullValidator && Validators.required)
  })

  destroy$: Subject<boolean> = new Subject<boolean>();

  onSubmit() {
    this.service.salvarContato(this.contatoForm.value).pipe(takeUntil(this.destroy$)).subscribe(data => {
    this.contatoForm.reset();
  });
}

}
