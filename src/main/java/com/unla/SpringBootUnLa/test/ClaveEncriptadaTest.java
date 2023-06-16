package com.unla.SpringBootUnLa.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ClaveEncriptadaTest {

	public static void main(String[] args) {
		
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();		
		String claveEncriptada = "16523474";
		System.out.println("Clave: " + claveEncriptada + " Encriptada = " + pe.encode(claveEncriptada));
		
	}

}
