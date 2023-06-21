package com.unla.SpringBootUnLa.models;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventHumedadModel {
	private int id;
	
	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	
	private String descripcion;
	
	private SensorHumedadModel sensorm;
	public EventHumedadModel(SensorHumedadModel device, String descripcion) {
		super();
		this.sensorm=device;
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "EventHumedadModel [id=" + id + ", createdAt=" + createdAt + ", descripcion=" + descripcion
				+ ", sensorm=" + sensorm + "]";
	}


	

	
	
	
	
	
	
}
