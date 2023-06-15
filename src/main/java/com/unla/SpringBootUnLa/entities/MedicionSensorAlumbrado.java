package com.unla.SpringBootUnLa.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
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
	
	//indica si la medicion ya fue analizada por el dispositivo 
	//y registrado los correspondientes eventos de ser necesarios
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
		return "\nMedicionSensorAlumbrado [id=" + id + ", sensor=" + sensor.getId() + ", fecha=" + fecha + ", intensidadLuz="
				+ intensidadLuz + ", analizada=" + analizada + "]";
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
		MedicionSensorAlumbrado other = (MedicionSensorAlumbrado) obj;
		return Objects.equals(fecha, other.fecha);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SensorAlumbradoInteligente getSensor() {
		return sensor;
	}

	public void setSensor(SensorAlumbradoInteligente sensor) {
		this.sensor = sensor;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public int getIntensidadLuz() {
		return intensidadLuz;
	}

	public void setIntensidadLuz(int intensidadLuz) {
		this.intensidadLuz = intensidadLuz;
	}

	public boolean isAnalizada() {
		return analizada;
	}

	public void setAnalizada(boolean analizada) {
		this.analizada = analizada;
	}
}