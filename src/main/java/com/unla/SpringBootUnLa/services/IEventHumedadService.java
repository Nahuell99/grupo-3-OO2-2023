package com.unla.SpringBootUnLa.services;

import java.util.List;

import com.unla.SpringBootUnLa.entities.EventHumedad;
import com.unla.SpringBootUnLa.entities.MedicionSensorHumedad;
import com.unla.SpringBootUnLa.entities.SensorHumedad;
import com.unla.SpringBootUnLa.models.EventHumedadModel;
import com.unla.SpringBootUnLa.models.SensorHumedadModel;


public interface IEventHumedadService {
	
	public List<EventHumedad> getAll();
	public EventHumedadModel insertOrUpdate(EventHumedadModel	eventoModel);
	public boolean remove(int id);
	public abstract EventHumedad getById(int id);
	
}
