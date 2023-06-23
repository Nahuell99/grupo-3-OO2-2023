package com.unla.SpringBootUnLa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.SpringBootUnLa.entities.RecolectorInteligente;
import com.unla.SpringBootUnLa.helpers.ViewRouteHelper;
import com.unla.SpringBootUnLa.services.EventService;
import com.unla.SpringBootUnLa.services.RecolectorInteligenteService;
import com.unla.SpringBootUnLa.services.VerificacionRecolectorService;

@Controller
@RequestMapping("/device/recolectorInteligente")
public class ChequeoRecolectorController {

    @Autowired
    public VerificacionRecolectorService verificacion;
    @Autowired
    private EventService eventService;
    @Autowired
    private RecolectorInteligenteService recolectorservice;

    @PreAuthorize("hasRole('ROLE_AUDITOR')")
    @GetMapping("/lista/actualizar/{id}")
    public String verificarRecolector(@PathVariable int id, Model model) {
        verificacion.verificarContenedorLlenoPorId(id);
        verificacion.verificarUsoIndebidoPorId(id);
        RecolectorInteligente r = recolectorservice.getRecolectorById(id);
        model.addAttribute("recolectorInteligente", r);
		model.addAttribute("eventos", eventService.getEventsByDevice(r));
        if(eventService.getEventsByDevice(r).size() == 0){
			return ViewRouteHelper.RECOLECTOR_SIN_EVENTOS;
		}
        return ViewRouteHelper.EVENTO_RECOLECTOR_INTELIGENTE;
    }

}
