package com.unla.SpringBootUnLa.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.SpringBootUnLa.entities.SensorHumedad;

@Repository("sensorHumedadRepository")
public interface ISensorHumedadRepository extends JpaRepository<SensorHumedad,Serializable> {
	//public abstract SensorHumedad findByName(String nombre);
	/*public abstract SensorHumedad findByInstitutionAndYear(String institution, int year);
	public abstract List<SensorHumedad> findByInstitutionAndYearOrderByYearDesc(String institution, int year);
	*/
}
