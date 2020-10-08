package br.dh.barbearia.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dh.barbearia.java.entity.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long>{
	

}
