package br.dh.barbearia.java.service;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.dh.barbearia.java.commun.Constantes;
import br.dh.barbearia.java.commun.RandomCommun;
import br.dh.barbearia.java.entity.Categoria;
import br.dh.barbearia.java.entity.Funcionario;
import br.dh.barbearia.java.repository.FuncionarioRepository;

@Service
public class FuncionariosService {
	
	@Resource
	private FuncionarioRepository funcionarioRepository;
	
	private RandomCommun randomCommun;

	public void salvarNovoFunc(String cpf, String nome, LocalDate dataNascimento, String nacionalidade,
	  		  String genero, String email,String telefone, String endereco, String uf, String cep, String nivel, String categoria) {

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
			  funcionarioRepository.save(func);
		
	          //return "redirect:/barbearia/notificacaoAgendamentoOK";
		}
	
	public List<Funcionario> buscarTodosFuncionarios() {
		return funcionarioRepository.findAll();
	}
}
