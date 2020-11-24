package br.dh.barbearia.java.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "funcionarios")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_func")
	private Integer idFuncionario;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "nome")
	private String nome;


	@Column(name = "data_nasc")
	private LocalDate dtNascimento;

	@Column(name = "genero")
	private String genero;

	@Column(name = "email")
	private String email;

	@Column(name = "telefone")
	private String telefone;

	@Column(name = "nacionalidade")
	private String nacionalidade;

	@Column(name = "endereco")
	private String endereco;

	@Column(name = "uf")
	private Integer uf;

	@Column(name = "cep")
	private String cep;

	@Column(name = "categoria")
	private Integer categoria;

	@Column(name = "nivel_func")
	private Integer nivelFunc;

	@Column(name = "matricula")
	private String matricula;

	@Column(name = "senha")
	private String senha;

	public Funcionario() {

	}

	public Funcionario(Integer idFuncionario, String nome) {
		this.idFuncionario = idFuncionario;
		this.nome = nome;
	}
}
