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
@Table(name="servicos")
public class Servicos {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_servico")
	private Integer idServico;
	
    @Column(name="nome", nullable=false)
	private String nome;
    
    @Column(name="descricao")
	private String descricao;
    
    @Column(name="categoria", nullable=false)
    private int numeroCategoria;
    
    public Servicos() {
    	
    }
    
    public Servicos(Integer idServico, String nome) {
        this.idServico = idServico;
        this.nome = nome;
    }
}
