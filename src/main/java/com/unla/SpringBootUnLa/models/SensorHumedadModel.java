package com.unla.SpringBootUnLa.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SensorHumedadModel {
	
	
	private int valorMinHumedad;
	
	private int valorMaxHumedad;

	public SensorHumedadModel(int valorMinHumedad, int valorMaxHumedad) {
		super();
		this.valorMinHumedad = valorMinHumedad;
		this.valorMaxHumedad = valorMaxHumedad;
	}
	
	
	
}
