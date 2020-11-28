package br.dh.barbearia.java.commun;

import java.util.List;
import java.util.Random;

import br.dh.barbearia.java.entity.Funcionario;

public class RandomCommun {

	public String geradorLetrasNumerosAleatorios(String caracteres, int tamanho) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < tamanho; i++) {
			sb.append(caracteres.charAt(random.nextInt(caracteres.length())));
		}
		return sb.toString();
	}
	
	public String escolheFuncionario(List<Funcionario> func) {
		return  func.get(new Random().nextInt(func.size())).getNome();
		
	}

}
