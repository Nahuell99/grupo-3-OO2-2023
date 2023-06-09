package com.unla.SpringBootUnLa.models;

import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventModel {
	private int id;
	private DeviceModel device;
	private String descripcion;
	
	public EventModel(DeviceModel device, String descripcion) {
		super();
		this.device = device;
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "EventModel [device=" + device + ", descripcion=" + descripcion + "]";
	}

	
	
	
	
}
