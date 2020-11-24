package br.dh.barbearia.java.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="uf")
@Data
public class UF {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_uf")
	private Integer idUF;
	
	@Column(name="nome")
	private String nome;
}
