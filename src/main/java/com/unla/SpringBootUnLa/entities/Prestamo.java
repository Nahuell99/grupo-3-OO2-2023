package com.unla.SpringBootUnLa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Prestamo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	public int nroPrestamo;
	public double monto;


	public Prestamo(int nroPrestamo, int id) {
		super();
		this.nroPrestamo = nroPrestamo;
		this.id = id;
	}

}
