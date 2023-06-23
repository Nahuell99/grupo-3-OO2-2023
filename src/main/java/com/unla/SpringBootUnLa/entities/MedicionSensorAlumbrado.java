package com.unla.SpringBootUnLa.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.Data;
@Entity @Data
public class MedicionSensorAlumbrado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "sensor_id")
	private SensorAlumbradoInteligente sensor;

	@Column
	private LocalDateTime fecha;

	@Column
	private int intensidadLuz;
	
	/**indica si la medicion ya fue analizada por el dispositivo 
	y registrado los correspondientes eventos de ser necesarios*/
	@Column
	private boolean analizada; 

	public MedicionSensorAlumbrado(SensorAlumbradoInteligente sensor, LocalDateTime fecha, int intensidadLuz) {
		this.sensor = sensor;
		this.fecha = fecha;
		this.intensidadLuz = intensidadLuz;
		this.analizada = false;
	}
	
	public MedicionSensorAlumbrado() {
	}

	@Override
	public String toString() {
		return "MedicionSensorAlumbrado [id=" + id + ", fecha=" + fecha + ", intensidadLuz=" + intensidadLuz
				+ ", analizada=" + analizada + "]";
	}
	
}