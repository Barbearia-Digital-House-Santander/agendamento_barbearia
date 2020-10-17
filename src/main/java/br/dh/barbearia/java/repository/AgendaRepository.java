package br.dh.barbearia.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dh.barbearia.java.entity.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long>{
	
	List<Agenda> findByChaveDeCancelamento(String chaveDeCancelamento);
	
	List<Agenda> findAll();
}
