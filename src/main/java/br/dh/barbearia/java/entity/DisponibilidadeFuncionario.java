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
@Table(name = "disponibilidade")
public class DisponibilidadeFuncionario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_disp")
	private Integer idDisp;

	@Column(name = "funcionario")
	private String funcionario;

	@Column(name = "data")
	private LocalDate data;
	
	@Column(name = "hora")
	private Integer hora;
	
	@Column(name = "categoria")
	private Integer categoria;
	
	private String horaS;
	
}
