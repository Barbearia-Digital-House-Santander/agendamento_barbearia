package br.dh.barbearia.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.dh.barbearia.java.entity.Servicos;

@Repository
public interface ServicosRepository extends JpaRepository<Servicos, Long>{

	List<Servicos> findAll();
	
	List<Servicos> findByIdCategoria(Integer id);
	
	List<Servicos> findByIdServico(Integer id);
}
