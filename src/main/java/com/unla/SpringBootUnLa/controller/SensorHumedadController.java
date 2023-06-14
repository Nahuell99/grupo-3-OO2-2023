package com.unla.SpringBootUnLa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.SpringBootUnLa.entities.SensorHumedad;
import com.unla.SpringBootUnLa.helper.ViewRouteHelper;
import com.unla.SpringBootUnLa.models.SensorHumedadModel;
import com.unla.SpringBootUnLa.services.ISensorHumedadService;
import com.unla.SpringBootUnLa.services.implementation.SensorHumedadService;
import org.modelmapper.ModelMapper;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/sensorHumedad")
public class SensorHumedadController {
	@Autowired
	@Qualifier("sensorHumedadService")
	private ISensorHumedadService	sensorHumedadService;
	
	private ModelMapper modelMapper=new ModelMapper();

	
	@GetMapping("/form")
	public String AñadirSensor(Model model) {
		model.addAttribute("sensor",new SensorHumedadModel());
		return ViewRouteHelper.ALTASENSOR;
	}
	
	@PostMapping("/nuevoSensor")
	public ModelAndView nuevoSensor(@Valid @ModelAttribute("sensor") SensorHumedadModel sensorModelo, BindingResult b) {
		ModelAndView mv=new ModelAndView();
		if(b.hasErrors()) {
			mv.setViewName(ViewRouteHelper.ALTASENSOR);
		}
		else {
			sensorHumedadService.insertOrUpdate(modelMapper.map(sensorModelo, SensorHumedad.class));
			mv.setViewName(ViewRouteHelper.NUEVOSENSOR);
			mv.addObject("sensor",sensorModelo);
			mv.addObject("listaSensores", sensorHumedadService.getAll());
		}
		
		return mv;
	}
	
	
	
}
