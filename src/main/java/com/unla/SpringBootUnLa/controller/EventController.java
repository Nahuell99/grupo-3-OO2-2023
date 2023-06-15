package com.unla.SpringBootUnLa.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private SensorAlumbradoInteligenteService sensorService;

    public EventController(EventService eventService, DeviceService deviceService, SensorAlumbradoInteligenteService sensorService) {
        this.eventService = eventService;
        this.sensorService = sensorService;
    }

    
    // URL BASE
    @PreAuthorize("hasRole('ROLE_AUDITOR')")
    @GetMapping("/sensorAlumbradoInteligente/lista/eventos/{id}")
    public String listaEventos(@PathVariable int id, Model model) {
        SensorAlumbradoInteligente device = sensorService.getSensorById(id);
        List<Event> eventos = eventService.getEventsByDevice(device);
        model.addAttribute("sensorAlumbradoInteligente", device);
        model.addAttribute("eventos", eventos);
        if(eventos.size() == 0){
			return ViewRouteHelper.SIN_EVENTOS;
		}
        return ViewRouteHelper.EVENTO_ALUMBRADO_INTELIGENTE;
    }
}
