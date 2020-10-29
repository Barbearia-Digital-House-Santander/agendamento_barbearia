package br.dh.barbearia.java.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="funcionarios")
public class Funcionario {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_func")
	private Long idFuncionario;
	
	@Column(name="cpf")
	private String cpf;
	
    @Column(name="nome")
	private String nome;
    
    @Column(name="servico")
	private String servico;
    
    @Column(name="data_nasc")
    private int dtNascimento;
    
    @Column(name="genero")
   	private String genero;
    
    @Column(name="email")
   	private String email;
    
    @Column(name="telefone")
   	private String telefone;
    
    @Column(name="nacionalidade")
   	private String nacionalidade;
    
    @Column(name="endereco")
   	private String endereco;
    
    @Column(name="uf")
   	private String uf;
    
    @Column(name="cep")
   	private String cep;
    
    @Column(name="cargo")
   	private String cargo;
    
    @Column(name="nivel_func")
   	private String nivelFunc;
    
    @Column(name="matricula")
   	private String matricula;
    
    @Column(name="senha")
   	private String senha;
}
