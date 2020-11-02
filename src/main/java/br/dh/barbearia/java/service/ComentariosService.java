package br.dh.barbearia.java.service;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import br.dh.barbearia.java.entity.Comentarios;
import br.dh.barbearia.java.repository.ComentariosRepository;

@Service
public class ComentariosService {
	@Resource
	private ComentariosRepository comentariosRepository;
	public List<Comentarios> buscarComentarios(){
		return comentariosRepository.findAll();
	}
	

}
