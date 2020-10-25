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

	public Long getIdAgendamento() {
		return idAgendamento;
	}

	public void setIdAgendamento(Long idAgendamento) {
		this.idAgendamento = idAgendamento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public LocalDate getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDate dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public String getHoraAgendamento() {
		return horaAgendamento;
	}

	public void setHoraAgendamento(String horaAgendamento) {
		this.horaAgendamento = horaAgendamento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNomeFunc() {
		return nomeFunc;
	}

	public void setNomeFunc(String nomeFunc) {
		this.nomeFunc = nomeFunc;
	}

	public String getChaveDeCancelamento() {
		return chaveDeCancelamento;
	}

	public void setChaveDeCancelamento(String chaveDeCancelamento) {
		this.chaveDeCancelamento = chaveDeCancelamento;
	}

	public String getCancelado() {
		return cancelado;
	}

	public void setCancelado(String cancelado) {
		this.cancelado = cancelado;
	}

	public LocalDate getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(LocalDate dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

    
    
}
