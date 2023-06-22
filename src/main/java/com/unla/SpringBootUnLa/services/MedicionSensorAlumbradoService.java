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
    
    public MedicionSensorAlumbrado getMedicionById(int sensorId) {
        return medicionAlumbradoRepository.findById(sensorId).orElse(null);
    }

    public MedicionSensorAlumbrado saveMedicion(MedicionSensorAlumbrado medicion) {
        return medicionAlumbradoRepository.save(medicion);
    }
    
 // Actualizar un registro existente
    public MedicionSensorAlumbrado updateMedicion(MedicionSensorAlumbrado medicion) {
        if (medicion.getId() == 0) {
            throw new IllegalArgumentException("La medicion debe tener un ID válido para ser actualizado");
        }

        // Verificar si la medicion existe en la base de datos
        MedicionSensorAlumbrado existeMedicion = this.getMedicionById(medicion.getId());
        if (existeMedicion == null) {
            throw new IllegalArgumentException("No se encontró la medicion con ID: " + medicion.getId());
        }

        // Actualizar los campos del sensor existente con los nuevos valores
        existeMedicion.setSensor(medicion.getSensor());
        existeMedicion.setFecha(medicion.getFecha());
        existeMedicion.setIntensidadLuz(medicion.getIntensidadLuz());
        existeMedicion.setAnalizada(medicion.isAnalizada());

        // Guardar el sensor actualizado en la base de datos
        return medicionAlumbradoRepository.save(medicion);
    }
    
    public List<MedicionSensorAlumbrado> getMedicionesBySensor(SensorAlumbradoInteligente sensor) {
        return medicionAlumbradoRepository.findBySensor(sensor);
    }
    
    public List<MedicionSensorAlumbrado> getMedicionesBySensorAndAnalizadaFalseOrderByFechaAsc(SensorAlumbradoInteligente sensor) {
        return medicionAlumbradoRepository.findBySensorAndAnalizadaFalseOrderByFechaAsc(sensor);
    }

    public List<MedicionSensorAlumbrado> getMedicionesBySensorAndFecha(SensorAlumbradoInteligente sensor, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return medicionAlumbradoRepository.findBySensorAndFechaBetween(sensor, fechaInicio, fechaFin);
    }
}