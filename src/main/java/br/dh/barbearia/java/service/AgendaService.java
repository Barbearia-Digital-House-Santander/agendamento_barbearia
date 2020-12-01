package br.dh.barbearia.java.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.dh.barbearia.java.commun.Constantes;
import br.dh.barbearia.java.commun.GeneratorPDF;
import br.dh.barbearia.java.commun.RandomCommun;
import br.dh.barbearia.java.entity.Agenda;
import br.dh.barbearia.java.entity.DisponibilidadeFuncionario;
import br.dh.barbearia.java.entity.Funcionario;
import br.dh.barbearia.java.entity.Hora;
import br.dh.barbearia.java.repository.AgendaRepository;
import br.dh.barbearia.java.repository.DisponibilidadeFuncionarioRepository;
import br.dh.barbearia.java.repository.FuncionarioRepository;
import br.dh.barbearia.java.repository.HoraRepository;

@Service
public class AgendaService {

	@Resource
	private AgendaRepository agendaRepository;
	
	@Resource
	private FuncionarioRepository funcionarioRepository;

	@Resource
	private DisponibilidadeFuncionarioRepository disponibilidadeFuncionarioRepository;
	
	@Resource
	private HoraRepository horaRepository;

	
	private String geradorAleatorio() {
		RandomCommun random = new RandomCommun();
		return random.geradorLetrasNumerosAleatorios(Constantes.CARACTERES, Constantes.TAMANHO_RANDOM);
	}

	private List<Agenda> buscaDadosClientePelaChaveDeCancelamento(String chaveDeCancelamento) {
		return agendaRepository.findByChaveDeCancelamento(chaveDeCancelamento);
	}
	
	public void salvarMarcacaoNaAgenda(String cpf, String nome, String telefone, Integer categoria, String dataAgendamento, String email, String genero, String valor, String nomeFunc, Integer hora, Integer servico) {

		  Agenda agenda = new Agenda();
		  agenda.setCpf(cpf);
		  agenda.setNome(nome);
		  agenda.setServicos(servico);
		  agenda.setData(dataAgendamento);
		  agenda.setHora(hora);
		  agenda.setSexo(genero);
		  agenda.setEmail(email);
		  agenda.setTelefone(telefone);
		  agenda.setCategorias(categoria);
		  agenda.setValor(valor);
		  agenda.setFuncionario(this.setarFuncionario(nomeFunc, categoria));
		  agenda.setCancelado(Constantes.NAO);
		  agenda.setChaveDeCancelamento(geradorAleatorio());
		  agendaRepository.save(agenda);
	
        //  return "redirect:/barbearia/notificacaoAgendamentoOK";
	}
	
	public String setarFuncionario(String nomeFunc, Integer categoria) {
		if(nomeFunc != null ) {
			  return nomeFunc;
		}
		if(nomeFunc == null || nomeFunc.isEmpty()) {
			  RandomCommun random = new RandomCommun();
			  List<Funcionario> func = funcionarioRepository.findByCategorias(categoria);
			  List<Funcionario> funcNivelComum = func.stream().filter(f -> f.getNivels().equals(Constantes.NIVEL_COMUM)).collect(Collectors.toList());
			   nomeFunc = random.escolheFuncionario(funcNivelComum);
		  }
		return nomeFunc;
	}
	
	public String atualizarMarcacaoNaAgenda(String chaveDeCancelamento) {
		List<Agenda> dados = buscaDadosClientePelaChaveDeCancelamento(chaveDeCancelamento);
	    Agenda agenda = new Agenda();

		  if(!dados.isEmpty()) {
			  agenda.setIdAgendamento(dados.get(0).getIdAgendamento());
			  agenda.setCpf(dados.get(0).getCpf());
			  agenda.setNome(dados.get(0).getNome());
			  agenda.setServicos(dados.get(0).getServicos());
			  agenda.setData(dados.get(0).getData());
			  agenda.setHora(dados.get(0).getHora());
			  agenda.setSexo(dados.get(0).getSexo());
			  agenda.setEmail(dados.get(0).getEmail());
			  agenda.setTelefone(dados.get(0).getTelefone());
			  agenda.setChaveDeCancelamento(chaveDeCancelamento);
			  agenda.setCancelado(Constantes.SIM);
			  agenda.setDataCancelamento(LocalDate.now());
			  agendaRepository.save(agenda);
	          return "cancelamentoEfetuado";
		  }
		  else {
			  return "redirect:/barbearia/cancelamento/falha";
        }
	}
	
	public String agendamentoOK(String recibo) {
		 Agenda dados = buscarUltimosDadosPeloId();
		 List<Hora> horas = horaRepository.findAll();
		
		 List<Hora> hrs = horas.stream().filter(y -> y.getIdHora().equals(dados.getHora())).collect(Collectors.toList());
				for(Hora h : hrs) {
					dados.setHoras(h.getHora());
				}
		 
		 if(recibo.equals("recibo")) {
			  return GeneratorPDF.geraPDFagendamentoOK(dados);
		  }
		  return "index";
	}
		
	private Agenda buscarUltimosDadosPeloId(){
		List<Agenda> dados = agendaRepository.findAll();
		return dados.get(dados.size()-1);
	}
	
	public List<Agenda> buscarMarcacoesAgenda(){
		List<Agenda> dados = agendaRepository.findAll();
		return dados;
	}
	
	public List<DisponibilidadeFuncionario>  buscarTodasHorasDisponiveis(String data){
		List<DisponibilidadeFuncionario> horas = disponibilidadeFuncionarioRepository.findByData(data);
		return horas;
	}
	public List<Hora>  buscarHoras(){
		List<Hora> horas = horaRepository.findAll();
		return horas;
	}

	public List<DisponibilidadeFuncionario>  buscarTodasDatasDisponiveis(){
		
		List<DisponibilidadeFuncionario> dados = disponibilidadeFuncionarioRepository.findAll();
		List<DisponibilidadeFuncionario> dts = new ArrayList<DisponibilidadeFuncionario>();
		
				if(dts.isEmpty() || dts == null) {
					dts.add(dados.get(0));
				}
		
		
		for(int i = 0; i < dts.size(); i++) {
			if(dts.get(i) != null && !dts.isEmpty()) {
				String dt = dts.get(i).getData();
				for(DisponibilidadeFuncionario d : dados) {
					if(!d.getData().equals(dt)) {
						dts.add(d);
					}
				}
			}
		}

		
		return dts;
	}
	
	public String verificarSePodeSalvarAgendamento(String data, Integer hora, String funcionario) {
	  List<Agenda> agd = agendaRepository.findByDataAndHoraAndFuncionario(data, hora, funcionario);
			  if(agd.isEmpty() || agd == null) {
				  return "OK";
			  }
	  return "erro";
	}
	
	public String mensagemEmail(String nomeCliente, String dataAgendada, String horaAgendada, String funcionario) {
		String msg = "Olá " + nomeCliente + ",\n \n Sabemos o quão dificil está sendo este momento de pandemia e ficamos felizes em saber que"
				+ "você não deixou de lado seus cuidados com a beleza. \n \n" 
				+ "Esperamos você em nossa barbearia na:  \n \n"+
				"Data: "+ dataAgendada + "\n Hora: " + horaAgendada +
				"\n \n Você será atendido(a): " + funcionario +
				"\n \n Agradecemos imensamente por sua preferência.";
		
		return msg;
	}
}
