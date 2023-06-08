package com.unla.SpringBootUnLa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity 
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class SensorAlumbradoInteligente extends Device {

	@Column
	private String ubicacion;
	
	@Column
	private boolean estado;
	
	@Column
	private int umbralLuz; //Minimo de luz para uqe se active el sensor
	
	@Column
	private int intensidadLuz; //Ultima medidcion de luz registrada para este dispositivo
	
	@ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

	public SensorAlumbradoInteligente(String nombre, String descripcion, boolean activo, String ubicacion,
			boolean estado, int umbralLuz, int intensidadLuz) {
		super(nombre, descripcion, activo);
		this.ubicacion = ubicacion;
		this.estado = estado;
		this.umbralLuz = umbralLuz;
		this.intensidadLuz = intensidadLuz;
	}

	@Override
	public String toString() {
		return super.toString() + "SensorAlumbradoInteligente{" + "ubicacion='" + ubicacion + '\'' + ", estado="
				+ estado + ", umbralLuz=" + umbralLuz + ", intensidadLuz=" + intensidadLuz + '}';
	}

}