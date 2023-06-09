package com.unla.SpringBootUnLa.models;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeviceModel {
	private int id;
	private String nombre;
	private String descripcion;
	private boolean activo;
	private List<EventModel> eventos;
	
	public DeviceModel(String nombre, String descripcion, boolean activo, List<EventModel> eventos) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.activo = activo;
		this.eventos = eventos;
	}

	@Override
	public String toString() {
		return "DeviceModel [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", activo=" + activo
				+ ", eventos=" + eventos + "]";
	}
	
	
}