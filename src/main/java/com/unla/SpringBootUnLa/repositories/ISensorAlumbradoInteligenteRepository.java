package com.unla.SpringBootUnLa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.SpringBootUnLa.entities.SensorAlumbradoInteligente;

@Repository("sensorAlumbradoInteligenteRepository")
public interface ISensorAlumbradoInteligenteRepository extends JpaRepository<SensorAlumbradoInteligente, Long> {

	
}
