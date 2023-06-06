package com.unla.SpringBootUnLa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unla.SpringBootUnLa.entities.SensorAlumbradoInteligente;
import com.unla.SpringBootUnLa.helpers.ViewRouteHelper;
import com.unla.SpringBootUnLa.services.SensorAlumbradoInteligenteService;



@Controller
@RequestMapping("/")
public class HomeController {
	
	private final SensorAlumbradoInteligenteService sensorService;
	
	public HomeController(SensorAlumbradoInteligenteService sensorService) {
        this.sensorService = sensorService;
    }
	
	@GetMapping("/sensorAlumbradoInteligente")
	public String sensorAlumbradoInteligente(Model model) {
		model.addAttribute("sensorAlumbradoInteligente", new SensorAlumbradoInteligente());
		return ViewRouteHelper.ALUMBRADO_INTELIGENTE;
	}

	@PostMapping("/newSensorAlumbradoInteligente")
	public ModelAndView newSensorAlumbradoInteligente(@ModelAttribute("sensorAlumbradoInteligente") SensorAlumbradoInteligente sensor) {
		sensorService.saveSensor(sensor);
		System.out.println(sensor);
		ModelAndView mV = new ModelAndView();
		mV.setViewName(ViewRouteHelper.NEW_ALUMBRADO_INTELIGENTE);
		mV.addObject("sensorAlumbradoInteligente", sensor);
		return mV;
	}

}
