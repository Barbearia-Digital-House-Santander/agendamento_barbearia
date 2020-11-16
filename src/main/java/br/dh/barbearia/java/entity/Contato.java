package br.dh.barbearia.java.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name="contato")
@Data
@Entity
public class Contato {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_contato")
	private Integer idContato;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="email")
	private String email;
	
	@Column(name="assunto")
	private String assunto;
	
	@Column(name="mensagem")
	private String mensagem;
}
