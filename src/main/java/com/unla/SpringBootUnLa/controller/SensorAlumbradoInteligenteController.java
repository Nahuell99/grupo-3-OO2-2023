package com.unla.SpringBootUnLa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unla.SpringBootUnLa.entities.SensorAlumbradoInteligente;
import com.unla.SpringBootUnLa.services.SensorAlumbradoInteligenteService;

@RestController
@RequestMapping("/sensorAlumbrado")
public class SensorAlumbradoInteligenteController {

    private final SensorAlumbradoInteligenteService sensorService;

    public SensorAlumbradoInteligenteController(SensorAlumbradoInteligenteService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping
    public ResponseEntity<SensorAlumbradoInteligente> createSensor(@RequestBody SensorAlumbradoInteligente sensor) {
        SensorAlumbradoInteligente createdSensor = sensorService.saveSensor(sensor);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSensor);
    }

    @GetMapping("/{sensorId}")
    public ResponseEntity<SensorAlumbradoInteligente> getSensorById(@PathVariable long sensorId) {
        SensorAlumbradoInteligente sensor = sensorService.getSensorById(sensorId);
        if (sensor != null) {
            return ResponseEntity.ok(sensor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<SensorAlumbradoInteligente>> getAllSensors() {
        List<SensorAlumbradoInteligente> sensors = sensorService.getAllSensors();
        return ResponseEntity.ok(sensors);
    }
}
