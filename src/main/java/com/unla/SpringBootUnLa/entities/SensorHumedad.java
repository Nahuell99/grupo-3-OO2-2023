package com.unla.SpringBootUnLa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter
@NoArgsConstructor
public class SensorHumedad extends Device {
	
	
	@Column
	private int valorMinHumedad;
	
	@Column
	private int	valorMaxHumedad;
	
	
	
	public SensorHumedad(String nombre,String descripcion,boolean activo
			,int valorMin,int valorMax) {
		super(nombre,descripcion,activo);
		this.valorMinHumedad=valorMin;
		this.valorMaxHumedad=valorMax;
	}
	
	
}
