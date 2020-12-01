package br.dh.barbearia.java.config;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Password {
	
	private static int workload = 12;
	
	public static String encryptPassword(String password) {
		String salt = BCrypt.gensalt(workload);
		String hashedPassword = BCrypt.hashpw(password, salt);
		return hashedPassword;
	}
	
	public static boolean checkPassword(String password, String passwordHash) {
		if(passwordHash == null || !passwordHash.startsWith("$2a$"))
			throw new IllegalArgumentException("Invalid hash");
		
		return BCrypt.checkpw(password, passwordHash);
	}
}
