package br.dh.barbearia.java.service;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.dh.barbearia.java.commun.Constantes;
import br.dh.barbearia.java.commun.RandomCommun;
import br.dh.barbearia.java.entity.Funcionario;
import br.dh.barbearia.java.entity.Hora;
import br.dh.barbearia.java.entity.NivelHierarquico;
import br.dh.barbearia.java.entity.UF;
import br.dh.barbearia.java.repository.FuncionarioRepository;
import br.dh.barbearia.java.repository.HoraRepository;
import br.dh.barbearia.java.repository.NivelRepository;
import br.dh.barbearia.java.repository.UFRepository;

@Service
public class FuncionariosService {
	
	@Resource
	private FuncionarioRepository funcionarioRepository;
	
	@Resource
	private HoraRepository horaRepository;
	
	@Resource
	private UFRepository ufRepository;
	
	@Resource
	private NivelRepository nivelRepository;
	
	private RandomCommun randomCommun;

	public void salvarNovoFunc(String cpf, String nome, LocalDate dataNascimento, String nacionalidade,
	  		  String genero, String email,String telefone, String endereco, Integer uf, String cep, Integer nivel, Integer categoria) {

			  Funcionario func = new Funcionario();
			  func.setCpf(cpf);
			  func.setNome(nome);
			  func.setCategoria(categoria);
			  func.setDtNascimento(dataNascimento);
			  func.setNacionalidade(nacionalidade);
			  func.setEndereco(endereco);;
			  func.setGenero(genero);
			  func.setEmail(email);
			  func.setTelefone(telefone);
			  func.setCep(cep);
			  func.setUf(uf);
			  func.setMatricula(randomCommun.geradorLetrasNumerosAleatorios(Constantes.CARACTERES, Constantes.TAMANHO_RANDOM));
			  func.setSenha(randomCommun.geradorLetrasNumerosAleatorios(Constantes.CARACTERES, Constantes.TAMANHO_RANDOM));
			  funcionarioRepository.save(func);
		
	          //return "redirect:/barbearia/notificacaoAgendamentoOK";
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
