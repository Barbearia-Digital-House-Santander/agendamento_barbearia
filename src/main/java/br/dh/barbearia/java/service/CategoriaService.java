package br.dh.barbearia.java.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.dh.barbearia.java.entity.Categoria;
import br.dh.barbearia.java.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Resource
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> buscarCategoria() {
		return categoriaRepository.findAll();
	}
}
