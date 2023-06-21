package com.unla.SpringBootUnLa.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.SpringBootUnLa.entities.MedicionSensorHumedad;
import com.unla.SpringBootUnLa.entities.SensorHumedad;
import com.unla.SpringBootUnLa.models.SensorHumedadModel;
import com.unla.SpringBootUnLa.repositories.IMedicionHumedadRepository;
import com.unla.SpringBootUnLa.services.IMedicionHumedadService;

@Service("medicionHumedadService")
public class MedicionHumedadService implements IMedicionHumedadService {
	
	@Autowired
	@Qualifier("medicionHumedadRepository")
	private IMedicionHumedadRepository medicionHumedadRepository;

	@Override
	public List<MedicionSensorHumedad> getAll() {
		
		return medicionHumedadRepository.findAll();
	}

	@Override
	public MedicionSensorHumedad insertOrUpdate(MedicionSensorHumedad medicion) {
		
		return medicionHumedadRepository.save(medicion);
	}

	@Override
	public boolean remove(long id) {
		try {
			medicionHumedadRepository.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public MedicionSensorHumedad getdById(long id) {
	
		return medicionHumedadRepository.findById(id).orElse(null);
	}
	
}
