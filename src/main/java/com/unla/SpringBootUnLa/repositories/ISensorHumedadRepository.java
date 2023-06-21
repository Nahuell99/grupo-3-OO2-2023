package com.unla.SpringBootUnLa.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.SpringBootUnLa.entities.SensorHumedad;

@Repository("sensorHumedadRepository")
public interface ISensorHumedadRepository extends JpaRepository<SensorHumedad,Long> {
	
	public abstract SensorHumedad findByNombre(String nombre);
	
	public abstract SensorHumedad getById(long id);
	
	
	
}
