package com.unla.SpringBootUnLa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.unla.SpringBootUnLa.entities.MedicionSensorAlumbrado;
import com.unla.SpringBootUnLa.entities.SensorAlumbradoInteligente;
import com.unla.SpringBootUnLa.helpers.ViewRouteHelper;
import com.unla.SpringBootUnLa.services.MedicionSensorAlumbradoService;
import com.unla.SpringBootUnLa.services.SensorAlumbradoInteligenteService;

@Controller
@RequestMapping("/device/sensorAlumbradoInteligente")
public class MedicionSensorAlumbradoController {

    private final MedicionSensorAlumbradoService medicionService;

    @Autowired
    private SensorAlumbradoInteligenteService sensorService;

    public MedicionSensorAlumbradoController(MedicionSensorAlumbradoService medicionService, SensorAlumbradoInteligenteService sensorService) {
        this.medicionService = medicionService;
        this.sensorService = sensorService;
    }
    
 // URL BASE
    @GetMapping("/lista/mediciones/{id}")
    public String listaMedicionesSensorAlumbrado(@PathVariable int id, Model model) {
        SensorAlumbradoInteligente device = sensorService.getSensorById(id);
        List<MedicionSensorAlumbrado> mediciones = medicionService.getMedicionesBySensor(device);
        System.out.println(mediciones);
        model.addAttribute("sensorAlumbradoInteligente", device);
        //model.addAttribute("medicionSensorAlumbrado", mediciones);
        return ViewRouteHelper.MEDICIONES_ALUMBRADO_INTELIGENTE;
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
