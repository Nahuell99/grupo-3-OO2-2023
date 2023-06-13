package com.unla.SpringBootUnLa.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "device_id")
	private Device device;

	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

	private String descripcion;

	private boolean activo; //Si el evento se encuentra activo o no

	public Event(Device device, String descripcion, LocalDateTime createdAt) {
		super();
		this.device = device;
		this.descripcion = descripcion;
		this.activo = true;
		this.createdAt = createdAt;
	}
	
	public Event() {
		this.activo = true;
	}
	
}
