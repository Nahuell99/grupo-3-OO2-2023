package com.unla.SpringBootUnLa.controller;
import com.unla.SpringBootUnLa.entities.EventHumedad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.SpringBootUnLa.entities.MedicionSensorHumedad;
import com.unla.SpringBootUnLa.entities.SensorHumedad;
import com.unla.SpringBootUnLa.helpers.ViewRouteHelper;
import com.unla.SpringBootUnLa.models.SensorHumedadModel;
import com.unla.SpringBootUnLa.services.IEventHumedadService;
import com.unla.SpringBootUnLa.services.IMedicionHumedadService;
import com.unla.SpringBootUnLa.services.ISensorHumedadService;
import com.unla.SpringBootUnLa.services.implementation.SensorHumedadService;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.unla.SpringBootUnLa.models.EventHumedadModel;
import com.unla.SpringBootUnLa.models.MedicionHumedadModel;
@Controller
@RequestMapping("/sensorHumedad")
public class SensorHumedadController {
	private MedicionHumedadModel medicion=new MedicionHumedadModel();
	@Autowired
	@Qualifier("sensorHumedadService")
	private ISensorHumedadService	sensorHumedadService;
	
	@Autowired
	@Qualifier("medicionHumedadService")
	private IMedicionHumedadService medicionHumedadService;
	
	@Autowired
	@Qualifier("eventHumedadService")
	private IEventHumedadService eventHumedadService;
	
	private ModelMapper modelMapper=new ModelMapper();

	@GetMapping(" ")
	public String sensorHumedad(Model model) {
		model.addAttribute("sensorHumedad",new SensorHumedadModel());
		return ViewRouteHelper.HOME_SENSOR;
	}
	@GetMapping("/form")
	public String AñadirSensor(Model model) {
		model.addAttribute("sensor",new SensorHumedadModel());
		return ViewRouteHelper.CREAR_SENSOR;
		
	}
	
	@PostMapping("/nuevoSensor")
	public ModelAndView nuevoSensor( @ModelAttribute("sensor") SensorHumedadModel sensorModelo, BindingResult b) {
		ModelAndView mv=new ModelAndView();
		
		if(b.hasErrors()) {
			mv.setViewName(ViewRouteHelper.CREAR_SENSOR);
		}
		else {
			
			sensorHumedadService.insertOrUpdate(modelMapper.map(sensorModelo, SensorHumedad.class));
			mv.setViewName(ViewRouteHelper.LISTA_SENSORES);
			mv.addObject("devices",sensorHumedadService.getAll());
			
		}
		
		return mv;
	}
	
	@GetMapping("/listaSensor")
	public ModelAndView mostrarSensores(@ModelAttribute("sensor")SensorHumedadModel sensorM ) {
		ModelAndView mv=new ModelAndView();
		mv.setViewName(ViewRouteHelper.LISTA_SENSORES);
		mv.addObject("devices",sensorHumedadService.getAll());
		return mv;
	}
	
	
	@GetMapping("/editar")
	public ModelAndView editarSensor() {
		ModelAndView mv=new ModelAndView(ViewRouteHelper.EDITAR_SENSOR);
		mv.addObject("devices",sensorHumedadService.getAll());
		return mv;
	}
	
	@GetMapping("/editar/{id}")
	public String editarSensor(@PathVariable int id,Model model) {
		
		SensorHumedad s=sensorHumedadService.getdById(id);
		model.addAttribute("sensor",modelMapper.map(s, SensorHumedadModel.class));
		return ViewRouteHelper.EDITAR_SENSOR_FORM;
	}
	
	@PostMapping("/editar/{id}/guardar")
	public ModelAndView guardarSensorModificado(@PathVariable int id, @ModelAttribute("sensor") SensorHumedadModel sensor) {

		sensorHumedadService.insertOrUpdate(modelMapper.map(sensor, SensorHumedad.class));
		ModelAndView mv=new ModelAndView();
		mv.setViewName(ViewRouteHelper.LISTA_SENSORES);
		mv.addObject("devices",sensorHumedadService.getAll());
		return mv;
	}
	
	
		
		@GetMapping("/eliminar")
		public ModelAndView eliminarSensor() {
			ModelAndView mv=new ModelAndView();
			mv.setViewName(ViewRouteHelper.ELIMINAR_SENSOR);
			mv.addObject("devices",sensorHumedadService.getAll());
			return mv;
		}

		
		@GetMapping("/eliminar/{id}")
		public ModelAndView eliminarSensor(@PathVariable long id) {
			sensorHumedadService.remove(id);
			ModelAndView mv=new ModelAndView();
			mv.setViewName(ViewRouteHelper.HOME_SENSOR); 
			
			return mv;
		}
	
	   @GetMapping("/listaSensor/actualizarLista/{id}")
	   public ModelAndView actualizarLista(@PathVariable long id) {
		   	
		   SensorHumedad s=sensorHumedadService.getdById(id);
		   MedicionSensorHumedad medicion= s.getMedicion();
		   
		 
		   medicion.setValorHumedad(medicion.getValorHumedad()-10);	
			  
		   if(medicion.getValorHumedad()<=medicion.getSensor().getValorMinHumedad()) {
				   medicion.setValorHumedad(medicion.getSensor().getValorMaxHumedad());
				   EventHumedad e=new EventHumedad(s,"se regó por nivel bajo de humedad");
				   s.getEventos().add(e);
				   sensorHumedadService.insertOrUpdate(s);
			   }
		  
		   medicionHumedadService.insertOrUpdate(medicion);
		   ModelAndView mv=new ModelAndView();
		   mv.setViewName(ViewRouteHelper.LISTA_SENSORES);
		   mv.addObject("devices",sensorHumedadService.getAll());
		  
		   return mv;
	   }
	   
	   @GetMapping("/listaSensor/eventos/{id}")
	   public ModelAndView mostrarEventos(@PathVariable int id) {  
		   ModelAndView mv=new ModelAndView();
		   SensorHumedad s=sensorHumedadService.getdById(id);
		   
		   if(s.getEventos().size()==0) {
			    mv.setViewName(ViewRouteHelper.SIN_EVENTOS);
		   }
		   else {
			    mv.setViewName(ViewRouteHelper.ListaDeEventos);
			    mv.addObject("eventos",s.getEventos());
		   }
		  
		   return mv;
	   }
	   
	   @GetMapping("/listaSensor/activar/{id}")
	   public ModelAndView activarSensor(@PathVariable int id) {
		   SensorHumedad s=sensorHumedadService.getdById(id);
		   if(s.getMedicion()==null) {
			   MedicionSensorHumedad m=new MedicionSensorHumedad(s,LocalDateTime.now(),50);
		       medicionHumedadService.insertOrUpdate(m);
		          
		   }
		  
		
		   if(s.isActivo()) {
			   s.setActivo(false);
		   }
		   else {
			   s.setActivo(true);
		   }
		   sensorHumedadService.insertOrUpdate(s);
		   ModelAndView mv= new ModelAndView();
		   mv.setViewName(ViewRouteHelper.LISTA_SENSORES);
		   mv.addObject("devices",sensorHumedadService.getAll());
		   return mv;
	   }
	
}
