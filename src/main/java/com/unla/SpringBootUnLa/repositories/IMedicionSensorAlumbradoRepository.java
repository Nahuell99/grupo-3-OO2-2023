package com.unla.SpringBootUnLa.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.SpringBootUnLa.entities.MedicionSensorAlumbrado;
import com.unla.SpringBootUnLa.entities.SensorAlumbradoInteligente;

@Repository("medicionSensorAlumbradoRepository")
public interface IMedicionSensorAlumbradoRepository extends JpaRepository<MedicionSensorAlumbrado, Integer> {

	//Obtener todas las mediciones de un sensor de alumbrado inteligente en un rango de fechas
	List<MedicionSensorAlumbrado> findBySensorAndFechaBetween(SensorAlumbradoInteligente sensor, LocalDateTime fechaInicio, LocalDateTime fechaFin);

	List<MedicionSensorAlumbrado> findBySensor(SensorAlumbradoInteligente sensor);
	
	// Obtener todas las mediciones de un sensor de alumbrado inteligente no analizadas
    List<MedicionSensorAlumbrado> findBySensorAndAnalizadaFalseOrderByFechaAsc(SensorAlumbradoInteligente sensor);
	
}
