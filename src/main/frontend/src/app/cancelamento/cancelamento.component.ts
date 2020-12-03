import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import Swal from 'sweetalert2';

import { CancelamentoService } from '../services/cancelamento.service';

@Component({
  selector: 'app-cancelamento',
  templateUrl: './cancelamento.component.html',
  styleUrls: ['./cancelamento.component.css']
})
export class CancelamentoComponent implements OnInit {

  constructor(private service: CancelamentoService) {
  }

  ngOnInit(): void {
  }

  cancelamentoForm = new FormGroup({
    chaveDeCancelamento: new FormControl('', Validators.nullValidator && Validators.required)
  });

  destroy$: Subject<boolean> = new Subject<boolean>();

  onSubmit() {
    this.cancelarAgendamento();
}

cancelarAgendamento(){
  this.service.fazerCancelamento(this.cancelamentoForm.value.chaveDeCancelamento).pipe(takeUntil(this.destroy$)).subscribe(cancela => {
    if(cancela == "ok"){
      this.cancelamentoForm.reset();
      this.mensagemDeSucesso();
    }
   else{
     this.mensagemDeErro();
   }
 });
}

mensagemDeErro()  {  
  Swal.fire({  
    icon: 'error',  
    title: 'ATENÇÃO',  
    text: 'Não foi possível fazer o cancelamentp, verifique se o código está correto.',  
    
  })  
} 

mensagemDeSucesso()  {  
  Swal.fire({  
    icon: 'success',  
    title: 'CANCELAMENTO REALIZADO COM SUCESSO',  
    
  })  
} 
}
