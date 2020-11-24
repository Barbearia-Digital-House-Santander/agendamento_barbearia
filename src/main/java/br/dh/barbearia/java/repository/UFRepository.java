package br.dh.barbearia.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.dh.barbearia.java.entity.UF;

@Repository
public interface UFRepository extends JpaRepository<UF, Long>{

	List<UF> findAll();

}
