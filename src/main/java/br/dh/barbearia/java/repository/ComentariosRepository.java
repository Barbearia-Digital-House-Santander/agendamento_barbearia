package br.dh.barbearia.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.dh.barbearia.java.entity.Comentarios;

@Repository
public interface ComentariosRepository extends JpaRepository<Comentarios, Long> {
	
	List<Comentarios> findAll();

}
