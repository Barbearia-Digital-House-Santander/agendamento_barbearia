package br.dh.barbearia.java.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Agenda {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idAgendamento;
	
    @Column(name="nome", nullable=false)
	private String nome;

	@Column(name="servico", nullable=false)
	private String servico;
	
    @Column(name="dataAgendamento", nullable=false)
	private Date dataAgendamento;
	
    @Column(name="horaAgendamento", nullable=false)
	private String horaAgendamento;
	
    @Column(name="genero", nullable=false)
	private String genero;

    @Column(name="email", nullable=false)
	private String email;
	
    @Column(name="telefone", nullable=false)
	private String telefone;
	
    @Column(name="numProc", nullable=false)
	private String numProcCancelamento;
    
    @Column(name="temCancelamento", nullable=true)
  	private  String temCancelamento;
	
    @Column(name="dataCancelamento", nullable=true)
	private Date dataCancelamento;

}
