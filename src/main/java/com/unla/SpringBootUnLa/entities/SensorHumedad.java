package com.unla.SpringBootUnLa.entities;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter
@NoArgsConstructor
public class SensorHumedad extends Device{
	
	
	private int valorMinHumedad;
	
	
	private int	valorMaxHumedad;
	
	
		public SensorHumedad(String nombre, String descripcion, int valorMinHumedad, int valorMaxHumedad) {
		super(nombre, descripcion);
		this.valorMinHumedad = valorMinHumedad;
		this.valorMaxHumedad = valorMaxHumedad;
	}

	
}
