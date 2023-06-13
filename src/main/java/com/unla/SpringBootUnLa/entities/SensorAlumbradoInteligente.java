package com.unla.SpringBootUnLa.entities;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter
public class SensorAlumbradoInteligente extends Device {

	@Column
	private String establecimiento; //Por ejemplo, Cancha de Boca, Universidad de Lanus, Embajada de Bangladesh.
	
	@Column
	private String ubicacionCordenada; //Dentro de un establecimineto en particular, las cordenadas de ubicacion
	
	@Column
	private boolean estado; // Prendido o Apagado
	
	@Column
	private int umbralLuz; //Minimo de luz para uqe se active el sensor
	
	@Column
	private int intensidadLuz; //Ultima medidcion de luz registrada para este dispositivo
	
	@OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicionSensorAlumbrado> mediciones = new ArrayList<>();

	public SensorAlumbradoInteligente(String nombre, String descripcion, String establecimiento, String ubicacionCordenada,
			int umbralLuz) {
		super(nombre, descripcion);
		this.establecimiento = establecimiento;
		this.ubicacionCordenada = ubicacionCordenada;
		this.estado = false; //Por defecto se crea apagado
		this.umbralLuz = umbralLuz;
		this.intensidadLuz = 0; //Ultima medicion de luz registrada, se crea en CERO
	}
	
	public SensorAlumbradoInteligente() {
		super();
		this.estado = false; //Por defecto se crea apagado
		this.intensidadLuz = 0; //Ultima medicion de luz registrada, se crea en CERO
	}

	@Override
	public String toString() {
		return 	super.toString() 
				+ "\nSensorAlumbradoInteligente [establecimiento=" + establecimiento + ", ubicacionCordenada="
				+ ubicacionCordenada + ", estado=" + estado + ", umbralLuz=" + umbralLuz + ", intensidadLuz="
				+ intensidadLuz + "]";
	}

	public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public String getUbicacionCordenada() {
		return ubicacionCordenada;
	}

	public void setUbicacionCordenada(String ubicacionCordenada) {
		this.ubicacionCordenada = ubicacionCordenada;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public int getUmbralLuz() {
		return umbralLuz;
	}

	public void setUmbralLuz(int umbralLuz) {
		this.umbralLuz = umbralLuz;
	}

	public int getIntensidadLuz() {
		return intensidadLuz;
	}

	public void setIntensidadLuz(int intensidadLuz) {
		this.intensidadLuz = intensidadLuz;
	}

	public List<MedicionSensorAlumbrado> getMediciones() {
		return mediciones;
	}

	public void setMediciones(List<MedicionSensorAlumbrado> mediciones) {
		this.mediciones = mediciones;
	}
}