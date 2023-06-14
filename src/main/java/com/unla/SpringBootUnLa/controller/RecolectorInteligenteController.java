package com.unla.SpringBootUnLa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.SpringBootUnLa.entities.RecolectorInteligente;
import com.unla.SpringBootUnLa.helpers.ViewRouteHelper;
import com.unla.SpringBootUnLa.services.RecolectorInteligenteService;

@Controller
@RequestMapping("/device")
public class RecolectorInteligenteController {

    private final RecolectorInteligenteService recolectorService;

    public RecolectorInteligenteController(RecolectorInteligenteService recolectorService) {
        this.recolectorService = recolectorService;
    }

    // URL BASE
    @GetMapping("/recolectorInteligente")
    public String recolectorInteligente(Model model) {
        model.addAttribute("recolectorInteligente", new RecolectorInteligente());
        return ViewRouteHelper.MENU_OPCIONES_RECOLECTOR;
    }

    // CREAR
    //@PreAuthorize("hasRole('Administrador')")
    @GetMapping("/recolectorInteligente/crear")
    public String crearRecolectorInteligente(Model model) {
        model.addAttribute("recolectorInteligente", new RecolectorInteligente());
        return ViewRouteHelper.CREAR_RECOLECTOR_INTELIGENTE;
    }

    // CREAR
    //@PreAuthorize("hasRole('Administrador')")
    @PostMapping("/recolectorInteligente/newRecolectorInteligente")
    public ModelAndView newRecolectorInteligente(
            @Valid @ModelAttribute("recolectorInteligente") RecolectorInteligente recolector,
            BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(ViewRouteHelper.CREAR_RECOLECTOR_INTELIGENTE);
        } else {
            recolectorService.saveRecolector(recolector);
            modelAndView.setViewName(ViewRouteHelper.NUEVO_RECOLECTOR_INTELIGENTE);
            modelAndView.addObject("recolectorInteligente", recolector);
        }
        return modelAndView;
    }

    // ELIMINAR
    @GetMapping("/recolectorInteligente/eliminar")
    public String eliminarRecolectorInteligente(Model model) {
        List<RecolectorInteligente> recolectores = recolectorService.getAllActiveRecolectores();
        model.addAttribute("recolectores", recolectores);
        return ViewRouteHelper.ELIMINAR_RECOLECTOR_INTELIGENTE;
    }

    @DeleteMapping("/recolectorInteligente/eliminar/{id}")
    public ModelAndView eliminarRecolectorInteligente(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        RecolectorInteligente recolector = recolectorService.getRecolectorById(id);
        recolector.setActivo(false);
        recolectorService.deleteRecolector(id);
        modelAndView.setViewName(ViewRouteHelper.MENU_OPCIONES_RECOLECTOR);
        modelAndView.addObject("recolectorInteligente", recolector);
        return modelAndView;
    }

    // MODIFICAR
    @GetMapping("/recolectorInteligente/editar")
    public String editarRecolectorInteligente(Model model) {
        List<RecolectorInteligente> recolectores = recolectorService.getAllActiveRecolectores();
        model.addAttribute("recolectores", recolectores);
        return ViewRouteHelper.EDITAR_RECOLECTOR_INTELIGENTE;
    }

    // MODIFICAR Formulario
    @GetMapping("/recolectorInteligente/editar/{id}")
    public String editarRecolectorInteligente(@PathVariable Long id, Model model) {
        RecolectorInteligente recolector = recolectorService.getRecolectorById(id);
        model.addAttribute("recolectorInteligente", recolector);
        return ViewRouteHelper.FORMULARIO_EDITAR_RECOLECTOR_INTELIGENTE;
    }

    // MODIFICAR GUARDADO
    @PostMapping("/recolectorInteligente/editar/{id}/guardar")
    public ModelAndView guardarRecolectorInteligente(@PathVariable int id, @ModelAttribute("recolectorInteligente") RecolectorInteligente recolector) {
        recolector.setId(id);
        recolector.setCreatedAt(recolectorService.getRecolectorById(id).getCreatedAt());
        recolectorService.saveRecolector(recolector);
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ViewRouteHelper.NUEVO_RECOLECTOR_INTELIGENTE);
        modelAndView.addObject("recolectorInteligente", recolector);
        return modelAndView;
    }

    // Lista plana
    @GetMapping("/recolectorInteligente/lista")
    public String listaRecolectorInteligente(Model model) {
        List<RecolectorInteligente> recolectores = recolectorService.getAllActiveRecolectores();
        model.addAttribute("recolectores", recolectores);
        return ViewRouteHelper.LISTA_RECOLECTOR_INTELIGENTE;
    }
}
