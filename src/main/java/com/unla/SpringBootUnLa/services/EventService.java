package com.unla.SpringBootUnLa.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unla.SpringBootUnLa.entities.Device;
import com.unla.SpringBootUnLa.entities.Event;

import com.unla.SpringBootUnLa.repositories.IEventRepository;

@Service
public class EventService {

    private final IEventRepository eventRepository;

    public EventService(IEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }
    
    public Event getEventById(int eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }

    public List<Event> getEventsByDevice(Device device) {
        return eventRepository.findByDeviceAndActivoTrue(device);
    }
}
