package com.unla.SpringBootUnLa.services;

import java.util.List;

import com.unla.SpringBootUnLa.entities.SensorHumedad;
import com.unla.SpringBootUnLa.models.SensorHumedadModel;

public interface ISensorHumedadService {
	
	public List<SensorHumedad> getAll();
	public SensorHumedadModel insertOrUpdate(SensorHumedad sensor);
	public boolean remove(int id);
	
	
}
