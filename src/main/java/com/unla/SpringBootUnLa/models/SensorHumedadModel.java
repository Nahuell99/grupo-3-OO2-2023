package com.unla.SpringBootUnLa.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SensorHumedadModel {
	private int id;
	private String nombre;
	private String descripcion;
	private int valorMinHumedad;
	private boolean activo;
	private int valorMaxHumedad;
	
	private MedicionHumedadModel medicion;
	public SensorHumedadModel(String nombre,String descripcion,int valorMinHumedad, int valorMaxHumedad,MedicionHumedadModel medicion,boolean activo) {
		super();
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.valorMinHumedad = valorMinHumedad;
		this.valorMaxHumedad = valorMaxHumedad;
		this.medicion=medicion;
		this.activo=true;
	}
	@Override
	public String toString() {
		return "SensorHumedadModel [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", valorMinHumedad=" + valorMinHumedad + ", valorMaxHumedad=" + valorMaxHumedad + ", medicion="
				+ medicion + "]";
	}
	
	
	
}
