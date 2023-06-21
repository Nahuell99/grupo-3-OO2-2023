package com.unla.SpringBootUnLa.services;

import java.util.List;

import com.unla.SpringBootUnLa.entities.MedicionSensorHumedad;
import com.unla.SpringBootUnLa.entities.SensorHumedad;
import com.unla.SpringBootUnLa.models.SensorHumedadModel;

public interface IMedicionHumedadService {
	public List<MedicionSensorHumedad> getAll();
	public MedicionSensorHumedad insertOrUpdate(MedicionSensorHumedad medicion);
	public boolean remove(long id);
	public MedicionSensorHumedad getdById(long id);
	
}
