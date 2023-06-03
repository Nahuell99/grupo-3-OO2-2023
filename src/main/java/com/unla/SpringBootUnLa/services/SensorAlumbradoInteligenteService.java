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

    public SensorAlumbradoInteligente saveSensor(SensorAlumbradoInteligente sensor) {
        return sensorRepository.save(sensor);
    }

    public SensorAlumbradoInteligente getSensorById(long sensorId) {
        return sensorRepository.findById(sensorId).orElse(null);
    }

    public List<SensorAlumbradoInteligente> getAllSensors() {
        return sensorRepository.findAll();
    }

    public void deleteSensor(long sensorId) {
        sensorRepository.deleteById(sensorId);
    }
}