package com.unla.SpringBootUnLa.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.SpringBootUnLa.entities.Event;
import com.unla.SpringBootUnLa.entities.MedicionSensorHumedad;

@Repository("eventHumedadRepository")
public interface IEventHumedadRepository extends JpaRepository<Event, Integer> {
	
	public abstract Event getById(long id);
	 
}
