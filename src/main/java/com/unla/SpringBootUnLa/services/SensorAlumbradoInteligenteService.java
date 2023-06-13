package com.unla.SpringBootUnLa.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unla.SpringBootUnLa.entities.SensorAlumbradoInteligente;

import com.unla.SpringBootUnLa.repositories.ISensorAlumbradoInteligenteRepository;

@Service
public class SensorAlumbradoInteligenteService {

	private final ISensorAlumbradoInteligenteRepository sensorRepository;

    public SensorAlumbradoInteligenteService(ISensorAlumbradoInteligenteRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    //guardar nuevo registro
    public SensorAlumbradoInteligente saveSensor(SensorAlumbradoInteligente sensor) {
        return sensorRepository.save(sensor);
    }
    
 // Actualizar un registro existente
    public SensorAlumbradoInteligente updateSensor(SensorAlumbradoInteligente sensor) {
        if (sensor.getId() == 0) {
            throw new IllegalArgumentException("El sensor debe tener un ID válido para ser actualizado");
        }

        // Verificar si el sensor existe en la base de datos
        SensorAlumbradoInteligente existingSensor = this.getSensorById(sensor.getId());
        if (existingSensor == null) {
            throw new IllegalArgumentException("No se encontró el sensor con ID: " + sensor.getId());
        }

        // Actualizar los campos del sensor existente con los nuevos valores
        existingSensor.setNombre(sensor.getNombre());
        existingSensor.setDescripcion(sensor.getDescripcion());
        existingSensor.setEstablecimiento(sensor.getEstablecimiento());
        existingSensor.setUbicacionCordenada(sensor.getUbicacionCordenada());
        existingSensor.setUmbralLuz(sensor.getUmbralLuz());

        // Guardar el sensor actualizado en la base de datos
        return sensorRepository.save(existingSensor);
    }

    public SensorAlumbradoInteligente getSensorById(long sensorId) {
        return sensorRepository.findById(sensorId).orElse(null);
    }

    public List<SensorAlumbradoInteligente> getAllSensors() {
        return sensorRepository.findAll();
    }
    
    public List<SensorAlumbradoInteligente> getAllActiveSensors() {
        return sensorRepository.findByTipoAndActivoIsTrue();
    }

    public void deleteSensor(long sensorId) {
    	SensorAlumbradoInteligente sensor = this.getSensorById(sensorId);
    	sensor.setActivo(false);
    	this.saveSensor(sensor);
    }
}