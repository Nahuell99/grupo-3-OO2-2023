package com.unla.SpringBootUnLa.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.SpringBootUnLa.entities.EventHumedad;
import com.unla.SpringBootUnLa.entities.MedicionSensorHumedad;
import com.unla.SpringBootUnLa.models.EventHumedadModel;
import com.unla.SpringBootUnLa.models.SensorHumedadModel;
import com.unla.SpringBootUnLa.repositories.IEventHumedadRepository;
import com.unla.SpringBootUnLa.services.IEventHumedadService;
import com.unla.SpringBootUnLa.services.ISensorHumedadService;
import org.modelmapper.ModelMapper;

@Service("eventHumedadService")
public class EventHumedadService implements IEventHumedadService {
	
	@Autowired
	@Qualifier("eventHumedadRepository")
	private IEventHumedadRepository eventRepository;
	
	private ModelMapper modelMapper=new ModelMapper();
	
	@Override
	public List<EventHumedad> getAll() {
		
		return eventRepository.findAll();
		
	}

	@Override
	public EventHumedadModel insertOrUpdate(EventHumedadModel eventModel) {
		EventHumedad event= eventRepository.save(modelMapper.map(eventModel, EventHumedad.class));
		return modelMapper.map(event, EventHumedadModel.class);
	}

	@Override
	public boolean remove(int id) {
		try {
			eventRepository.deleteById(id);
			return true;
		} catch (Exception e) {
					
			return false;

		}
		
	}

	@Override
	public EventHumedad getById(int id) {
		
		return eventRepository.findById(id).orElse(null);
	}

	
}
