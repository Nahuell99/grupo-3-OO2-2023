package com.unla.SpringBootUnLa.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.SpringBootUnLa.entities.Event;
import com.unla.SpringBootUnLa.entities.RecolectorInteligente;

@Service
public class VerificacionRecolectorService {

    @Autowired
    private RecolectorInteligenteService recolectorInteligenteService;
    @Autowired
	private EventService eventService;



    public void verificarUsoIndebidoPorId(int id) {
        RecolectorInteligente recolectorOptional = recolectorInteligenteService.getRecolectorById(id);
        if(recolectorOptional.isUsoIndebido()){ 
					Event eventoPrenderLuz = new Event(recolectorOptional, "El recolector esta siendo usado indebidamente", LocalDateTime.now()); 
					eventService.saveEvent(eventoPrenderLuz); 
		}
        
    }

    public void verificarContenedorLlenoPorId(int id) {
       RecolectorInteligente recolectorOptional = recolectorInteligenteService.getRecolectorById(id);
        if(recolectorOptional.isContenedorLleno()){ 
					Event eventoPrenderLuz = new Event(recolectorOptional, "El contenedor del recolector esta lleno", LocalDateTime.now()); 
					eventService.saveEvent(eventoPrenderLuz); 
		}
    }
}

