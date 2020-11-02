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
@Table(name = "comentarios")

public class Comentarios {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_comentario")
	private Integer idComentario;

	@Column(name = "nome")
	private String nome;

	@Column(name = "comentario")
	private String comentario;
	
	public Comentarios() {

	}

	public Comentarios(Integer idComentario, String nome, String comentario) {
		this.idComentario = idComentario;
		this.nome = nome;
		this.comentario = comentario;
	}

}
