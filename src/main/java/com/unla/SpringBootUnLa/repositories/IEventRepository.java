package com.unla.SpringBootUnLa.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.SpringBootUnLa.entities.Device;
import com.unla.SpringBootUnLa.entities.Event;

@Repository("eventRepository")
public interface IEventRepository extends JpaRepository<Event, Integer> {
	
	 List<Event> findByCreatedAtBetween(LocalDateTime fechaInicio, LocalDateTime fechFin);
	 
	 //Obtener todos los eventos activos de un dispositivo en particular:
	 List<Event> findByDeviceAndActivoTrue(Device device);
	 
}
