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

import com.unla.SpringBootUnLa.entities.Device;
import com.unla.SpringBootUnLa.entities.Event;
import com.unla.SpringBootUnLa.services.DeviceService;
import com.unla.SpringBootUnLa.services.EventService;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    @Autowired
    private DeviceService deviceService;

    public EventController(EventService eventService, DeviceService deviceService) {
        this.eventService = eventService;
        this.deviceService = deviceService;
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
