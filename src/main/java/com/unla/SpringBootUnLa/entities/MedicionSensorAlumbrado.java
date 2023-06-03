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
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter
@NoArgsConstructor
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

	public MedicionSensorAlumbrado(SensorAlumbradoInteligente sensor, LocalDateTime fecha, int intensidadLuz) {
		this.sensor = sensor;
		this.fecha = fecha;
		this.intensidadLuz = intensidadLuz;
	}

	@Override
	public String toString() {
		return "MedicionSensorAlumbrado [id=" + id + ", sensor=" + sensor + ", fecha=" + fecha + ", intensidadLuz="
				+ intensidadLuz + "]";
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
}