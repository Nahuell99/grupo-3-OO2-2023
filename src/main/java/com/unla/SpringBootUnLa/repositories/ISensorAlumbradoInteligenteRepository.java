package com.unla.SpringBootUnLa.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.SpringBootUnLa.entities.MedicionSensorAlumbrado;
import com.unla.SpringBootUnLa.entities.SensorAlumbradoInteligente;

@Repository("sensorAlumbradoInteligenteRepository")
public interface ISensorAlumbradoInteligenteRepository extends JpaRepository<SensorAlumbradoInteligente, Long> {

	//List<SensorAlumbradoInteligente> findByTipoAndActivoIsTrue(String tipo);
	
	@Query("SELECT s FROM SensorAlumbradoInteligente s WHERE TYPE(s) = SensorAlumbradoInteligente AND s.activo = true")
    List<SensorAlumbradoInteligente> findByTipoAndActivoIsTrue();
	 
}
