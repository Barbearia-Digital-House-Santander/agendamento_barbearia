import { Component, OnInit , OnDestroy} from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { AgendamentoService } from '../services/agendamento.service';
import { FuncionarioService } from '../services/funcionario.service';
import { Router } from '@angular/router';
import { AutentificacaoService } from '../services/autentificacao.service';
import { Funcionario } from '../models/funcionario';
import { SelectService } from '../services/selects.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-funcionarios',
  templateUrl: './funcionarios.component.html',
  styleUrls: ['./funcionarios.component.css']
})
export class FuncionariosComponent implements OnDestroy {

  usuario: Funcionario;
  router: Router;
  logado:any;
selecionar: any;
  ufList: any[];
  ufCount = 0;
  categoriaList: any[];
  nivelList: any[];
  catCount = 0;
  nivelCount = 0;
  adicionaFunc = true;
  

  constructor(private service: FuncionarioService,private serviceAut: AutentificacaoService,
    private selects: SelectService, router: Router){
    this.router = router; }


  title = 'app-funcionario';

  userForm = new FormGroup({
    nome: new FormControl('', Validators.nullValidator && Validators.required),
    cpf: new FormControl('', Validators.nullValidator && Validators.required),
    email: new FormControl('', Validators.nullValidator && Validators.required),
    telefone: new FormControl('', Validators.nullValidator && Validators.required),
    endereco: new FormControl('', Validators.nullValidator && Validators.required),
    ufs: new FormControl('', Validators.nullValidator && Validators.required),
    categorias: new FormControl('', Validators.nullValidator && Validators.required),
    cep: new FormControl('', Validators.nullValidator && Validators.required),
    genero: new FormControl('', Validators.nullValidator && Validators.required),
    nivels: new FormControl(''),
    dtNasc: new FormControl('', Validators.nullValidator ),
    nacionalidade: new FormControl('Brasileira(o)'),
    nomeFuncLogado: new FormControl(''),
    nivelFuncLogado: new FormControl('')
  });

  users: any[] = [];
  userCount = 0;

  destroy$: Subject<boolean> = new Subject<boolean>();

  onSubmit() {
      this.service.salvarFuncionario(this.userForm.value).pipe(takeUntil(this.destroy$)).subscribe((func: Funcionario) => {
        if(func.msg == "ok"){
          this.userCount = this.userCount + 1;
          this.userForm.reset();
          this.msgSalvoComSucesso(func.matricula, func.senha);
        }else{
          this.msgErroAoSalvar();
        }
      
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
    this.logado = this.serviceAut.usuario;
    if(this.serviceAut.usuario== undefined || this.serviceAut.usuario == null){
      this.router.navigate(['/', 'inicio']);
      this.naoExisteUsuarioLogado();
    }else{
      if(this.usuario.nivels == 1){
        this.adicionaFunc = false;
      }
    this.usuario = this.logado[0];
    this.userForm.get('categorias').setValue(this.buscarCategorias());
    this.userForm.get('ufs').setValue(this.buscarUF());
    this.userForm.get('nivels').setValue(this.buscarNivel());
    this.userForm.get('nivelFuncLogado').setValue(this.usuario.nivels);
    this.userForm.get('nomeFuncLogado').setValue(this.usuario.nome)
  	this.getAllUsers();
   }
  }
  pagMinhaAgenda(){
    this.router.navigate(['/', 'minhaAgenda']);
    this.serviceAut.setUsuarioLogado(this.logado);
  }

  pagMarcacoes(){
    this.router.navigate(['/', 'marcacoes']);
    this.serviceAut.setUsuarioLogado(this.logado);
  }

  pagHome(){
    this.router.navigate(['/', 'home']);
    this.serviceAut.setUsuarioLogado(this.logado);
  }
  pagFuncionarios(){
    this.router.navigate(['/', 'funcionarios']);
    this.serviceAut.setUsuarioLogado(this.logado);
  }

  pagMeusDados(){
    this.router.navigate(['/', 'meusDados']);
    this.serviceAut.setUsuarioLogado(this.logado);
  }

  pagSenha(){
    this.router.navigate(['/', 'senha']);
    this.serviceAut.setUsuarioLogado(this.logado);
  }

  logout(){
    this.serviceAut.lougoutUsuario(this.usuario).pipe(takeUntil(this.destroy$)).subscribe(mens => {
    
      this.router.navigate(['/', '']);
    });
  }

  
  buscarCategorias() {
    this.selects.getTodasCategorias().pipe(takeUntil(this.destroy$)).subscribe((categoria: any[]) => {
      this.catCount = categoria.length;
      this.categoriaList = categoria;
      return this.categoriaList;
    });
  }

  buscarNivel() {
    this.selects.getNivel().pipe(takeUntil(this.destroy$)).subscribe((nivel: any[]) => {
      this.nivelCount = nivel.length;
      this.nivelList = nivel;
      return this.nivelList;
    });
  }

  buscarUF() {
    this.selects.getUF().pipe(takeUntil(this.destroy$)).subscribe((uf: any[]) => {
      this.ufCount = uf.length;
      this.ufList = uf;
      return this.ufList;
    });
  }

  naoExisteUsuarioLogado(){  
    Swal.fire({  
      
      title: 'Ops...',  
      text: 'Parece que você perdeu a conexão faça seu login novamente para reiniciar a sessão.',  
      icon: 'error' ,
      footer: '<a href="login"> Sim, fazer login </a>',  
      
    });  
  }  

  msgSalvoComSucesso( matricula: String, senha: String ){  
    Swal.fire({  
      
      title: 'Salvo',  
      text: 'O Funcionário foi registrado \n \n <strong> MATRICULA: </strong>' + matricula +'\n \n \n <strong>SENHA:</strong>' + senha,  
      icon: 'success'       
    });  
  } 

  msgErroAoSalvar(){  
    Swal.fire({  
      
      title: 'Ops...',  
      text: 'Ocorreu um erro ao tentar registrar, tente novamente ou procure a assistência técnica',  
      icon: 'error' 
      
    });  
  }  

}