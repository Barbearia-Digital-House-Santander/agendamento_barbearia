package br.dh.barbearia.java.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Agenda {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idAgendamento;
	
	@Column(name="cpf", nullable=false)
	private String cpf;
	
    @Column(name="nome", nullable=false)
	private String nome;

	@Column(name="servico", nullable=false)
	private String servico;
	
    @Column(name="dataAgendamento")
	private LocalDate dataAgendamento;
	
    @Column(name="horaAgendamento")
	private String horaAgendamento;
	
    @Column(name="genero", nullable=false)
	private String genero;

    @Column(name="email", nullable=false)
	private String email;
	
    @Column(name="telefone", nullable=false)
	private String telefone;
	
    @Column(name="nomeFunc", nullable = true)
	private String nomeFunc;
    
    // cancelamentos 
    
    @Column(name="chave_de_cancelamento", nullable=false)
	private String chaveDeCancelamento;
    
    @Column(name="cancelado", nullable=true)
  	private  String cancelado;
	
    @Column(name="dataCancelamento", nullable=true)
	private LocalDate dataCancelamento;

}
