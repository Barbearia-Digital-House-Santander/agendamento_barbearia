package br.dh.barbearia.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dh.barbearia.java.entity.NivelHierarquico;

public interface NivelRepository extends JpaRepository<NivelHierarquico, Long>{

	List<NivelHierarquico> findAll();


}
