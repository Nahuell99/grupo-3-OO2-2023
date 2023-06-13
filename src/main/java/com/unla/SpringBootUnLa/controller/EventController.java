package com.unla.SpringBootUnLa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.SpringBootUnLa.entities.Device;
import com.unla.SpringBootUnLa.entities.Event;
import com.unla.SpringBootUnLa.entities.SensorAlumbradoInteligente;
import com.unla.SpringBootUnLa.helpers.ViewRouteHelper;
import com.unla.SpringBootUnLa.services.DeviceService;
import com.unla.SpringBootUnLa.services.EventService;
import com.unla.SpringBootUnLa.services.SensorAlumbradoInteligenteService;

@Controller
@RequestMapping("/device")
public class EventController {

    private final EventService eventService;
    private DeviceService deviceService;
    private SensorAlumbradoInteligenteService sensorService;

    public EventController(EventService eventService, DeviceService deviceService, SensorAlumbradoInteligenteService sensorService) {
        this.eventService = eventService;
        this.deviceService = deviceService;
        this.sensorService = sensorService;
    }

    
    // URL BASE
    @GetMapping("/sensorAlumbradoInteligente/lista/eventos/{id}")
    public String listaEventos(@PathVariable int id, Model model) {
        SensorAlumbradoInteligente device = sensorService.getSensorById(id);
        List<Event> eventos = eventService.getEventsByDevice(device);
        model.addAttribute("sensorAlumbradoInteligente", device);
        model.addAttribute("eventos", eventos);
        return ViewRouteHelper.EVENTO_ALUMBRADO_INTELIGENTE;
    }
    
    
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.saveEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> getEventById(@PathVariable int eventId) {
        Event event = eventService.getEventById(eventId);
        if (event != null) {
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/device/{deviceId}")
    public ResponseEntity<List<Event>> getEventsByDevice(@PathVariable int deviceId) {
        Device device = deviceService.getDeviceById(deviceId);
        if (device == null) {
            return ResponseEntity.notFound().build();
        }
        List<Event> events = eventService.getEventsByDevice(device);
        return ResponseEntity.ok(events);
    }
}
