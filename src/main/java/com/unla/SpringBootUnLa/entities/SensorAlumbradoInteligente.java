package com.unla.SpringBootUnLa.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter
@NoArgsConstructor
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

	public SensorAlumbradoInteligente(String nombre, String descripcion, boolean activo, String establecimiento, String ubicacionCordenada,
			boolean estado, int umbralLuz, int intensidadLuz) {
		super(nombre, descripcion, activo);
		this.establecimiento = establecimiento;
		this.ubicacionCordenada = ubicacionCordenada;
		this.estado = estado;
		this.umbralLuz = umbralLuz;
		this.intensidadLuz = intensidadLuz;
	}

	@Override
	public String toString() {
		return "SensorAlumbradoInteligente [establecimiento=" + establecimiento + ", ubicacionCordenada="
				+ ubicacionCordenada + ", estado=" + estado + ", umbralLuz=" + umbralLuz + ", intensidadLuz="
				+ intensidadLuz + "]";
	}
}