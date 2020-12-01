package br.dh.barbearia.java.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.dh.barbearia.java.commun.Constantes;
import br.dh.barbearia.java.config.Password;
import br.dh.barbearia.java.entity.DisponibilidadeFuncionario;
import br.dh.barbearia.java.entity.Funcionario;
import br.dh.barbearia.java.entity.Hora;
import br.dh.barbearia.java.entity.NivelHierarquico;
import br.dh.barbearia.java.entity.UF;
import br.dh.barbearia.java.repository.DisponibilidadeFuncionarioRepository;
import br.dh.barbearia.java.repository.FuncionarioRepository;
import br.dh.barbearia.java.repository.HoraRepository;
import br.dh.barbearia.java.repository.NivelRepository;
import br.dh.barbearia.java.repository.UFRepository;

@Service
public class FuncionariosService {
	
	@Resource
	private FuncionarioRepository funcionarioRepository;
	
	@Resource
	private DisponibilidadeFuncionarioRepository disponibilidadeFuncionarioRepository;
	
	@Resource
	private HoraRepository horaRepository;
	
	@Resource
	private UFRepository ufRepository;
	
	@Resource
	private NivelRepository nivelRepository;
	
	public Funcionario salvarNovoFunc(String cpf, String nome, LocalDate dataNascimento, String nacionalidade,
	  		  String genero, String email,String telefone, String endereco, Integer uf, String cep, Integer nivel,
	  		  Integer categoria, String matricula) {

			  Funcionario func = new Funcionario();
			  func.setCpf(cpf);
			  func.setNome(nome);
			  func.setCategorias(categoria);
			  func.setDtNasc(dataNascimento);
			  func.setNacionalidade(nacionalidade);
			  func.setEndereco(endereco);;
			  func.setGenero(genero);
			  func.setEmail(email);
			  func.setTelefone(telefone);
			  func.setCep(cep);
			  func.setUfs(uf);
			  func.setMatricula(matricula);
			  func.setSenha(Constantes.SENHA_PADRAO);
			  func.setNivels(nivel);
			  func.setMsg("ok");
			  funcionarioRepository.save(func);
			  return func;
		}
	
	public String salvarNovaDisponibilidade(String funcionario, LocalDate data, Integer hora) {
		
		DisponibilidadeFuncionario trabalhador = new DisponibilidadeFuncionario();
		if(funcionario != null || !funcionario.isEmpty()) {
			List <DisponibilidadeFuncionario> func = disponibilidadeFuncionarioRepository.findByFuncionarioAndDataAndHora(funcionario, data, hora);
				if(Boolean.TRUE.equals(func == null || func.isEmpty())){
					
					  trabalhador.setFuncionario(funcionario);;
					  trabalhador.setData(data);
					  trabalhador.setHora(hora);
					  
					  disponibilidadeFuncionarioRepository.save(trabalhador);
					}
					else {
					
					        return "Marcação já existe";
				}
		}else {
		  return "Funcionário não encontrado, faça o login novamente" ;}
		return "Salvo com Sucesso";
	}
	
	public Funcionario atualizarSenhaFunc(String nome, String senhaAtual, String novaSenha, String matricula) {

	  List<Funcionario> funcionario = funcionarioRepository.findByMatricula(matricula);
	  Funcionario func = new Funcionario();	  
	  Boolean senha = this.verificarSenha(senhaAtual, funcionario.get(0).getSenha());
			  
	  if(senha.equals(true)) {
		  func.setIdFuncionario(funcionario.get(0).getIdFuncionario());
		  func.setMatricula(funcionario.get(0).getMatricula());
		  func.setNome(funcionario.get(0).getNome());
		  func.setCpf(funcionario.get(0).getCpf());
		  func.setEmail(funcionario.get(0).getEmail());
		  func.setTelefone(funcionario.get(0).getTelefone());
		  func.setDtNasc(funcionario.get(0).getDtNasc());
		  func.setCep(funcionario.get(0).getCep());
		  func.setEndereco(funcionario.get(0).getEndereco());
		  func.setCategorias(funcionario.get(0).getCategorias());
		  func.setNivels(funcionario.get(0).getNivels());
		  func.setUfs(funcionario.get(0).getUfs());
		  func.setGenero(funcionario.get(0).getGenero());
		  func.setNacionalidade(funcionario.get(0).getNacionalidade());
		  func.setSenha(Password.encryptPassword(novaSenha));
		  funcionarioRepository.save(func);
		  func.setMsg("ok");
	  }else {	 
	  func.setMsg("erro");
	  }
	  return func;
		}
	
	public List<Funcionario> buscarTodosFuncionarios() {
		return funcionarioRepository.findAll();
	}
	
	public List<Hora> buscarTodasHoras() {
		return horaRepository.findAll();
	}
	
	public List<Funcionario> buscaFuncionarioEspecifico(String matricula, String senha){
		 return funcionarioRepository.findByMatriculaAndSenha(matricula, senha);	
	}
	
	public List<UF> buscarTodosUFs(){
		return ufRepository.findAll();
	}
	
	public List<NivelHierarquico> buscarTodosOsNiveis(){
		return nivelRepository.findAll();
	}
	
	public List<DisponibilidadeFuncionario>  buscarTodasDisponibilidadeDoFuncionario(String funcionario){
		return  disponibilidadeFuncionarioRepository.findByFuncionario(funcionario);
	}
	
	public List<DisponibilidadeFuncionario> datasNaoRepetidasFuncEsp(List<DisponibilidadeFuncionario> dados){
		List<DisponibilidadeFuncionario> dts = new ArrayList<DisponibilidadeFuncionario>();
		
		if(dts.isEmpty() || dts == null) {
			dts.add(dados.get(0));
		}

		for(int i = 0; i < dts.size(); i++) {
			if(dts.get(i) != null && !dts.isEmpty()) {
				LocalDate dt = dts.get(i).getData();
				for(DisponibilidadeFuncionario d : dados) {
					if(!d.getData().equals(dt)) {
						dts.add(d);
					}
				}
			}
		}
			
		return dts;
		}

	public List<DisponibilidadeFuncionario> buscarHoraDoFuncionarioNaData(String funcionario, String data) {
		List<DisponibilidadeFuncionario> disp =  disponibilidadeFuncionarioRepository.findByFuncionarioAndData(funcionario, data);		
		List<Hora> horas = horaRepository.findAll();
		for(DisponibilidadeFuncionario d : disp) {
			List<Hora> hrs = horas.stream().filter(y -> y.getIdHora().equals(d.getHora())).collect(Collectors.toList());
			for(Hora h : hrs) {
				d.setHoraS(h.getHora());
			}
			
	}
	return disp;
	}
	
	public Boolean isDataPassada(Date data){
		LocalDate dataInserida = data.toInstant().atZone(ZoneId.systemDefault())
	      .toLocalDate();
	
		LocalDate dataAtual = LocalDate.now();
		if(dataInserida.isBefore(dataAtual)) {
			return true;
		}
		return false;
	}
	
	public Boolean verificarSenha(String senhaAtual, String senhaRegistradaBD) {
		if(Password.checkPassword(senhaAtual, senhaRegistradaBD)) {
			return true;
		}
		return false;
	}

	public Funcionario atualizarDadosFunc( String nome, String matricula, String endereco, String cep, String email, String telefone) {
		List<Funcionario> funcionario = funcionarioRepository.findByMatricula(matricula);
		  Funcionario func = new Funcionario();	  
				  
		  if(!funcionario.isEmpty()) {
			  func.setIdFuncionario(funcionario.get(0).getIdFuncionario());
			  func.setMatricula(funcionario.get(0).getMatricula());
			  func.setNome(nome);
			  func.setCpf(funcionario.get(0).getCpf());
			  func.setEmail(email);
			  func.setTelefone(telefone);
			  func.setDtNasc(funcionario.get(0).getDtNasc());
			  func.setCep(cep);
			  func.setEndereco(endereco);
			  func.setCategorias(funcionario.get(0).getCategorias());
			  func.setNivels(funcionario.get(0).getNivels());
			  func.setGenero(funcionario.get(0).getGenero());
			  func.setNacionalidade(funcionario.get(0).getNacionalidade());
			  
			  funcionarioRepository.save(func);
			  func.setMsg("ok");
		  }else {	 
		  func.setMsg("erro");
		  }
		  return func;
	}
}
