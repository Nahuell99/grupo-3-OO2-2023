package com.unla.SpringBootUnLa.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MedicionHumedadModel {
	private int valor;

	public MedicionHumedadModel(int valor) {
		super();
		this.valor = valor;
	}

	@Override
	public String toString() {
		return valor+"%";
	}

	
	
	
}
