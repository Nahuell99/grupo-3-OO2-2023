package com.unla.SpringBootUnLa.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.unla.SpringBootUnLa.entities.MedicionSensorAlumbrado;
import com.unla.SpringBootUnLa.entities.SensorAlumbradoInteligente;

import com.unla.SpringBootUnLa.repositories.IMedicionSensorAlumbradoRepository;

@Service
public class MedicionSensorAlumbradoService {

    private final IMedicionSensorAlumbradoRepository medicionAlumbradoRepository;

    public MedicionSensorAlumbradoService(IMedicionSensorAlumbradoRepository medicionAlumbradoRepository) {
        this.medicionAlumbradoRepository = medicionAlumbradoRepository;
    }

    public MedicionSensorAlumbrado saveMedicion(MedicionSensorAlumbrado medicion) {
        return medicionAlumbradoRepository.save(medicion);
    }
    
    public List<MedicionSensorAlumbrado> getMedicionesBySensor(SensorAlumbradoInteligente sensor) {
        return medicionAlumbradoRepository.findBySensor(sensor);
    }

    public List<MedicionSensorAlumbrado> getMedicionesBySensorAndFecha(SensorAlumbradoInteligente sensor, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return medicionAlumbradoRepository.findBySensorAndFechaBetween(sensor, fechaInicio, fechaFin);
    }
}