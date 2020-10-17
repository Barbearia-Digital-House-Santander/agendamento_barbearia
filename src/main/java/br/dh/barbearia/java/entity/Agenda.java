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
@Table(name="agenda")
@Data
public class Agenda {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_agendamento")
	private Long idAgendamento;
	
	@Column(name="cpf", nullable=false)
	private String cpf;
	
    @Column(name="nome", nullable=false)
	private String nome;

	@Column(name="servico", nullable=false)
	private String servico;
	
    @Column(name="data_agendamento")
	private LocalDate dataAgendamento;
	
    @Column(name="hora_agendamento")
	private String horaAgendamento;
	
    @Column(name="genero", nullable=false)
	private String genero;

    @Column(name="email", nullable=false)
	private String email;
	
    @Column(name="telefone", nullable=false)
	private String telefone;
	
    @Column(name="nome_func", nullable = true)
	private String nomeFunc;
    
    // cancelamentos 
    
    @Column(name="chave_de_cancelamento", nullable=false)
	private String chaveDeCancelamento;
    
    @Column(name="cancelado")
  	private  String cancelado;
	
    @Column(name="data_cancelamento", nullable=true)
	private LocalDate dataCancelamento;

}
