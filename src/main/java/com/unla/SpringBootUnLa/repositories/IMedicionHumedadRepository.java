package com.unla.SpringBootUnLa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.SpringBootUnLa.entities.MedicionSensorHumedad;
import com.unla.SpringBootUnLa.entities.SensorHumedad;

@Repository("medicionHumedadRepository")
public interface IMedicionHumedadRepository extends JpaRepository<MedicionSensorHumedad,Long>{
	
	public abstract MedicionSensorHumedad getById(long id);

}
