package br.dh.barbearia.java.commun;

import java.util.Random;

public class RandomCommun {

	public String geradorLetrasNumerosAleatorios(String caracteres, int tamanho) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < tamanho; i++) {
			sb.append(caracteres.charAt(random.nextInt(caracteres.length())));
		}
		return sb.toString();
	}

}
