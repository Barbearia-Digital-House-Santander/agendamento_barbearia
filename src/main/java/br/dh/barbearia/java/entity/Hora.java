package br.dh.barbearia.java.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="hora")
@Data
public class Hora {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_hora")
	private Integer idHora;
	
	@Column(name="hora")
	private String hora;
	
}
