package br.dh.barbearia.java.controller;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.dh.barbearia.java.service.AgendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "Cancelamento")
@RequestMapping("/cancelar")
public class CancelamentoController implements WebMvcConfigurer  {

    @Resource
	private AgendaService agendaService;

    @Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
	}

    @ApiOperation(value = "Cancelar agendamento")
	@RequestMapping(value = "/cancelaAgendamento/{chave}", method = RequestMethod.POST)
	public  ResponseEntity<?> adicionaAgenda(@PathVariable("chave") String chaveDeCancelamento) {

		String msg = agendaService.atualizarMarcacaoNaAgendaCancelamento(chaveDeCancelamento);
		 
		 return ResponseEntity.ok(msg);
	}
}