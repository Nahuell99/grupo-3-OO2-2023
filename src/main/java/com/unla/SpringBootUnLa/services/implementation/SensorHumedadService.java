package com.unla.SpringBootUnLa.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.SpringBootUnLa.entities.SensorHumedad;
import com.unla.SpringBootUnLa.models.SensorHumedadModel;
import com.unla.SpringBootUnLa.repositories.ISensorHumedadRepository;
import com.unla.SpringBootUnLa.services.ISensorHumedadService;



import org.modelmapper.ModelMapper;


@Service("sensorHumedadService")
public class SensorHumedadService implements ISensorHumedadService {
	
	@Autowired
	@Qualifier("sensorHumedadRepository")
	private ISensorHumedadRepository sensorHumedadRepository;
	
	private ModelMapper modelMapper=new ModelMapper();
			
	@Override
	public List<SensorHumedad> getAll() {
		
		return sensorHumedadRepository.findAll();
	}

	@Override
	public SensorHumedadModel insertOrUpdate(SensorHumedad sensor) {
		SensorHumedad sensorNew=sensorHumedadRepository.save(sensor);
		
		return modelMapper.map(sensorNew, SensorHumedadModel.class);
	}

	@Override
	public boolean remove(int id) {
		try {
			sensorHumedadRepository.deleteById(id);
			return true;
		} catch (Exception e) {
					
			return false;

		}
	}
	

}
