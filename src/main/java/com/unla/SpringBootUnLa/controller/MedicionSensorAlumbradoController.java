package com.unla.SpringBootUnLa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unla.SpringBootUnLa.entities.MedicionSensorAlumbrado;
import com.unla.SpringBootUnLa.entities.SensorAlumbradoInteligente;
import com.unla.SpringBootUnLa.services.MedicionSensorAlumbradoService;
import com.unla.SpringBootUnLa.services.SensorAlumbradoInteligenteService;

@RestController
@RequestMapping("/medicionesAlumbrado")
public class MedicionSensorAlumbradoController {

    private final MedicionSensorAlumbradoService medicionService;

    @Autowired
    private SensorAlumbradoInteligenteService sensorService;

    public MedicionSensorAlumbradoController(MedicionSensorAlumbradoService medicionService, SensorAlumbradoInteligenteService sensorService) {
        this.medicionService = medicionService;
        this.sensorService = sensorService;
    }

    @PostMapping
    public ResponseEntity<MedicionSensorAlumbrado> createMedicion(@RequestBody MedicionSensorAlumbrado medicion) {
        MedicionSensorAlumbrado createdMedicion = medicionService.saveMedicion(medicion);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMedicion);
    }

    @GetMapping("/sensor/{sensorId}")
    public ResponseEntity<List<MedicionSensorAlumbrado>> getMedicionesBySensor(@PathVariable long sensorId) {        
        SensorAlumbradoInteligente sensor = sensorService.getSensorById(sensorId);
        if (sensor == null) {
            return ResponseEntity.notFound().build();
        }
        List<MedicionSensorAlumbrado> mediciones = medicionService.getMedicionesBySensor(sensor);
        return ResponseEntity.ok(mediciones);
    }
}
