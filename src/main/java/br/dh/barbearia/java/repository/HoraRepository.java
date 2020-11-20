package br.dh.barbearia.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.dh.barbearia.java.entity.Hora;

@Repository
public interface HoraRepository extends JpaRepository<Hora, Long>{

	List<Hora> findAll();

}
