package com.unla.SpringBootUnLa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.SpringBootUnLa.entities.SensorAlumbradoInteligente;
import com.unla.SpringBootUnLa.helpers.ViewRouteHelper;
import com.unla.SpringBootUnLa.services.SensorAlumbradoInteligenteService;

@Controller
@RequestMapping("/device")
public class SensorAlumbradoInteligenteController {

	private final SensorAlumbradoInteligenteService sensorService;

	public SensorAlumbradoInteligenteController(SensorAlumbradoInteligenteService sensorService) {
		this.sensorService = sensorService;
	}

	// URL BASE
	@GetMapping("/sensorAlumbradoInteligente")
	public String sensorAlumbradoInteligente(Model model) {
		model.addAttribute("sensorAlumbradoInteligente", new SensorAlumbradoInteligente());
		return ViewRouteHelper.MENU_OPCIONES;
	}

	// CREAR
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/sensorAlumbradoInteligente/crear")
	public String crearSensorAlumbradoInteligente(Model model) {
		model.addAttribute("sensorAlumbradoInteligente", new SensorAlumbradoInteligente());
		return ViewRouteHelper.CREAR_ALUMBRADO_INTELIGENTE;
	}

	// CREAR
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/sensorAlumbradoInteligente/newSensorAlumbradoInteligente")
	public ModelAndView newSensorAlumbradoInteligente(@Valid @ModelAttribute("sensorAlumbradoInteligente") SensorAlumbradoInteligente sensor) {

		ModelAndView mV = new ModelAndView();
		sensorService.saveSensor(sensor);
		mV.setViewName(ViewRouteHelper.NUEVO_ALUMBRADO_INTELIGENTE);
		mV.addObject("sensorAlumbradoInteligente", sensor);
		return mV;
	}

	// ELIMINAR
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/sensorAlumbradoInteligente/eliminar")
	public String eliminarSensorAlumbradoInteligente(Model model) {
		List<SensorAlumbradoInteligente> devices = sensorService.getAllActiveSensors(); // Obtener la lista de
																						// dispositivos
		if(devices.size() == 0){
			return ViewRouteHelper.SIN_DEVICE;
		}
		model.addAttribute("devices", devices); // Pasar la lista al modelo
		return ViewRouteHelper.ELIMINAR_ALUMBRADO_INTELIGENTE;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/sensorAlumbradoInteligente/eliminar/{id}")
	public ModelAndView eliminarSensorAlumbradoInteligente(@PathVariable int id) {
		ModelAndView mV = new ModelAndView();
		SensorAlumbradoInteligente sensor = sensorService.getSensorById(id);
		sensorService.deleteSensor(id);
		mV.setViewName(ViewRouteHelper.MENU_OPCIONES); // Cambia la vista de retorno según corresponda
		mV.addObject("sensorAlumbradoInteligente", sensor);
		return mV;
	}

	// MODIFICAR
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/sensorAlumbradoInteligente/editar")
	public String editarSensorAlumbradoInteligente(Model model) {
		List<SensorAlumbradoInteligente> devices = sensorService.getAllActiveSensors(); // Obtener la lista de
																						// dispositivos
		if(devices.size() == 0){
			return ViewRouteHelper.SIN_DEVICE;
		}
		model.addAttribute("devices", devices); // Pasar la lista al modelo
		return ViewRouteHelper.EDITAR_ALUMBRADO_INTELIGENTE;
	}

	// MODIFICAR Formulario
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/sensorAlumbradoInteligente/editar/{id}")
	public String editarSensorAlumbrado(@PathVariable int id, Model model) {
		SensorAlumbradoInteligente device = sensorService.getSensorById(id); // Obtener el dispositivo por ID
		model.addAttribute("sensorAlumbradoInteligente", device); // Pasar el dispositivo al modelo
		return ViewRouteHelper.FORMULARIO_EDITAR_ALUMBRADO_INTELIGENTE;
	}

	// MODIFICAR GUARDADO
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/sensorAlumbradoInteligente/editar/{id}/guardar")
	public ModelAndView guardarSensorAlumbrado(@PathVariable int id,
			@ModelAttribute("device") SensorAlumbradoInteligente device) {
		// Guardar los cambios en el dispositivo en la base de datos
		device.setId(id);
		device.setCreatedAt(sensorService.getSensorById(id).getCreatedAt());
		device.setMediciones(sensorService.getSensorById(id).getMediciones());
		device.setEventos(sensorService.getSensorById(id).getEventos());
		sensorService.saveSensor(device);

		ModelAndView mV = new ModelAndView();
		mV.setViewName(ViewRouteHelper.NUEVO_ALUMBRADO_INTELIGENTE);
		mV.addObject("sensorAlumbradoInteligente", device);
		return mV;
	}

	// Lista plana
	@PreAuthorize("hasRole('ROLE_AUDITOR')")
	@GetMapping("/sensorAlumbradoInteligente/lista")
	public String listaSensorAlumbradoInteligente(Model model) {
		List<SensorAlumbradoInteligente> devices = sensorService.getAllActiveSensors(); // Obtener la lista de
																						// dispositivos activos
		if(devices.size() == 0){
			return ViewRouteHelper.SIN_DEVICE;
		}
		model.addAttribute("devices", devices); // Pasar la lista al modelo
		return ViewRouteHelper.LISTA_ALUMBRADO_INTELIGENTE;
	}
	

}
