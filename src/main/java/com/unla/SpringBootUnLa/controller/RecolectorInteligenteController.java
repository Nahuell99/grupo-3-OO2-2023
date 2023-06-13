package com.unla.SpringBootUnLa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    private final IRecolectorInteligenteRepository recolectorRepository;

    public RecolectorInteligenteController(IRecolectorInteligenteRepository recolectorRepository) {
        this.recolectorRepository = recolectorRepository;
    }

    // URL BASE
    @GetMapping("/recolectorInteligente")
    public String recolectorInteligente(Model model) {
        model.addAttribute("recolectorInteligente", new RecolectorInteligente());
        return "menu_opciones";
    }

    // CREAR
    //@PreAuthorize("hasRole('Administrador')")
    @GetMapping("/recolectorInteligente/crear")
    public String crearRecolectorInteligente(Model model) {
        model.addAttribute("recolectorInteligente", new RecolectorInteligente());
        return "crear_recolector_inteligente";
    }

    // CREAR
    //@PreAuthorize("hasRole('Administrador')")
    @PostMapping("/recolectorInteligente/newRecolectorInteligente")
    public ModelAndView newRecolectorInteligente(
            @Valid @ModelAttribute("recolectorInteligente") RecolectorInteligente recolector,
            BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("crear_recolector_inteligente");
        } else {
            recolectorRepository.save(recolector);
            modelAndView.setViewName("nuevo_recolector_inteligente");
            modelAndView.addObject("recolectorInteligente", recolector);
        }
        return modelAndView;
    }

    // ELIMINAR
    @GetMapping("/recolectorInteligente/eliminar")
    public String eliminarRecolectorInteligente(Model model) {
        List<RecolectorInteligente> recolectores = recolectorRepository.findByActivoIsTrue();
        model.addAttribute("recolectores", recolectores);
        return "eliminar_recolector_inteligente";
    }
	
    @GetMapping("/recolectorInteligente/eliminar/{id}")
    public ModelAndView eliminarRecolectorInteligente(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        RecolectorInteligente recolector = recolectorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de RecolectorInteligente inválido: " + id));
        recolector.setActivo(false);
        recolectorRepository.save(recolector);
        modelAndView.setViewName("menu_opciones");
        modelAndView.addObject("recolectorInteligente", recolector);
        return modelAndView;
    }

    // MODIFICAR
    @GetMapping("/recolectorInteligente/editar")
    public String editarRecolectorInteligente(Model model) {
        List<RecolectorInteligente> recolectores = recolectorRepository.findByActivoIsTrue();
        model.addAttribute("recolectores", recolectores);
        return "editar_recolector_inteligente";
    }
	
    // MODIFICAR Formulario
    @GetMapping("/recolectorInteligente/editar/{id}")
    public String editarRecolectorInteligente(@PathVariable Long id, Model model) {
        RecolectorInteligente recolector = recolectorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de RecolectorInteligente inválido: " + id));
        model.addAttribute("recolectorInteligente", recolector);
        return "formulario_editar_recolector_inteligente";
    }
	
    // MODIFICAR GUARDADO
    @PostMapping("/recolectorInteligente/editar/{id}/guardar")
    public ModelAndView guardarRecolectorInteligente(@PathVariable Long id,
            @ModelAttribute("recolectorInteligente") RecolectorInteligente recolector) {
        recolector.setId(id);
        recolector.setCreatedAt(recolectorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de RecolectorInteligente inválido: " + id)).getCreatedAt());
        recolectorRepository.save(recolector);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("nuevo_recolector_inteligente");
        modelAndView.addObject("recolectorInteligente", recolector);
        return modelAndView;
    }
	
    // Lista plana
    @GetMapping("/recolectorInteligente/lista")
    public String listaRecolectorInteligente(Model model) {
        List<RecolectorInteligente> recolectores = recolectorRepository.findByActivoIsTrue();
        model.addAttribute("recolectores", recolectores);
        return "lista_recolector_inteligente";
    }
}
