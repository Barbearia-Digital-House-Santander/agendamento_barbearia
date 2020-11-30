package br.dh.barbearia.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.dh.barbearia.java.entity.Agenda;
import br.dh.barbearia.java.entity.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

		List<Funcionario> findAll();
		
		List<Funcionario> findByMatriculaAndSenha(String matricula, String senha);
		
		List<Funcionario> findByCategorias(Integer categorias);
		
		List<Funcionario> findByMatricula(String matricula);

}
