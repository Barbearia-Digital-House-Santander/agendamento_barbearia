package br.dh.barbearia.java.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="nivel")
@Data
public class NivelHierarquico {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_nivel")
	private Integer idNivel;
	
	@Column(name="descricao")
	private String descricao;
}
