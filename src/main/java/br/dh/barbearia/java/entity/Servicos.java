package br.dh.barbearia.java.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Servicos {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_servico")
	private Long idServico;
	
    @Column(name="nome", nullable=false)
	private String nome;

    @Column(name="nr_categoria", nullable=false)
    private int numeroCategoria;
    

}
