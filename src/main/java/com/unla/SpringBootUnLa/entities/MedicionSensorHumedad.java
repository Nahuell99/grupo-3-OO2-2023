package com.unla.SpringBootUnLa.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class MedicionSensorHumedad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sensor_id")
	private SensorHumedad sensor;


	@Column
	private LocalDateTime fecha;

	@Column
	private int valorHumedad;
	
	//indica si la medicion ya fue analizada por el dispositivo 
	//y registrado los correspondientes eventos de ser necesarios
	@Column
	private boolean analizada; 
	

	public MedicionSensorHumedad(SensorHumedad sensor, LocalDateTime fecha,int valor) {
		this.sensor = sensor;
		this.fecha = fecha;
		this.valorHumedad=valor;
		this.analizada = false;
	}
	
	public MedicionSensorHumedad() {
	}
	

	@Override
	public String toString() {
		return "\nMedicionSensorAlumbrado [id=" + id + ", sensor=" + sensor.getId() + ", fecha=" + fecha + 
				  ", analizada=" + analizada + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicionSensorHumedad other = (MedicionSensorHumedad) obj;
		return Objects.equals(fecha, other.fecha);
	}

	
}