package br.dh.barbearia.java.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Categoria {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_categoria")
	private Long idCategoria;
	
    @Column(name="nome", nullable=false)
	private String nome;
    
}
