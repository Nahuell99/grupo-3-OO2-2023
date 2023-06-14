package com.unla.SpringBootUnLa.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SensorHumedadModel {
	private String nombre;
	private String descripcion;
	private int valorMinHumedad;
	
	private int valorMaxHumedad;

	public SensorHumedadModel(String nombre,String descripcion,int valorMinHumedad, int valorMaxHumedad) {
		super();
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.valorMinHumedad = valorMinHumedad;
		this.valorMaxHumedad = valorMaxHumedad;
	}
	
	
	
}
