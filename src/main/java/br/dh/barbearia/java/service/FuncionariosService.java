package br.dh.barbearia.java.service;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Service;

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
	


	public void salvarNovoFunc(String cpf, String nome, LocalDate dataNascimento, String nacionalidade,
	  		  String genero, String email,String telefone, String endereco, Integer uf, String cep, Integer nivel,
	  		  Integer categoria, String matricula, String senha) {

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
			  func.setSenha(senha);
			  func.setNivels(nivel);
			  
			  funcionarioRepository.save(func);
		
	          //return "redirect:/barbearia/notificacaoAgendamentoOK";
		}
	
	@SuppressWarnings("unlikely-arg-type")
	public String salvarNovaDisponibilidade(String funcionario, String data, Integer hora) {
		
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
}
