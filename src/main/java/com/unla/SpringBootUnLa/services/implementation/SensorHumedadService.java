package com.unla.SpringBootUnLa.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.SpringBootUnLa.entities.SensorHumedad;
import com.unla.SpringBootUnLa.models.SensorHumedadModel;
import com.unla.SpringBootUnLa.services.ISensorHumedadService;
import org.modelmapper.ModelMapper;
import repositories.ISensorHumedadRepositorio;

@Service("sensorHumedadService")
public class SensorHumedadService implements ISensorHumedadService {
	@Autowired
	@Qualifier("sensorHumedadRepository")
	private ISensorHumedadRepositorio sensorHumedadRepository;
	
	private ModelMapper modelMapper=new ModelMapper();
			
	@Override
	public List<SensorHumedad> getAll() {
		
		return sensorHumedadRepository.findAll();
	}

	@Override
	public SensorHumedadModel insertOrUpdate(SensorHumedadModel sensorModelo) {
		SensorHumedad sensor=sensorHumedadRepository.save(modelMapper.map(sensorModelo, SensorHumedad.class));
		
		return modelMapper.map(sensor, SensorHumedadModel.class);
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
