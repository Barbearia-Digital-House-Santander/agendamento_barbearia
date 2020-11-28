package br.dh.barbearia.java.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import br.dh.barbearia.java.entity.Contato;
import br.dh.barbearia.java.service.ContatoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "Contato")
@RequestMapping("/contato")
public class ContatoController implements WebMvcConfigurer{
	
	@Resource
	private ContatoService contatoService;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedMethods("GET", "POST");
	}
	
	@ResponseBody
	@ApiOperation(value = "Cria um contato")
	@RequestMapping(value = "/adicionar", method = RequestMethod.POST)
//	public Contato salvarContato(Contato contato) {
	public Contato salvarContato(String nome, String email, String assunto, String mensagem) {
		Contato contato = new Contato();
		contato.setNome(nome);
		contato.setAssunto(assunto);
		contato.setEmail(email);
		contato.setMensagem(mensagem);
		return contatoService.salvarContato(contato);
	}
	
	@ResponseBody
	@ApiOperation(value = "Listar contatos")
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public List<Contato> getAllContatos(){
		return contatoService.getAllContatos();
	}
}
