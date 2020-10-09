package br.dh.barbearia.java.commun;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DataUtils {

	  public static LocalDate DataSemHoras(Date dataComHoras) {
	        Instant instant = Instant.ofEpochMilli(dataComHoras.getTime());
	        LocalDate dataSemHoras = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
	        return dataSemHoras;
	    }
}
