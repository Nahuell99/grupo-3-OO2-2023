package com.unla.SpringBootUnLa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.SpringBootUnLa.models.SensorHumedadModel;
import com.unla.SpringBootUnLa.services.ISensorHumedadService;
import com.unla.ghsicilianotfi.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	@Qualifier("sensorHumedadService")
	private ISensorHumedadService sensorHumedadService;
	
	@GetMapping("/index")
	public String index() {
		return com.unla.SpringBootUnLa.helpers.ViewRouteHelper.INDEX;
	}
	
	@PostMapping("/sensoresH")
	public ModelAndView mostrarSensores() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName(com.unla.SpringBootUnLa.helpers.ViewRouteHelper.SENSORES);
		mv.addObject("listaSensores", sensorHumedadService.getAll());
		return mv;
	}
}
