package com.unla.SpringBootUnLa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/sensorAlumbradoInteligente/crear")
	public String crearSensorAlumbradoInteligente(Model model) {
		model.addAttribute("sensorAlumbradoInteligente", new SensorAlumbradoInteligente());
		return ViewRouteHelper.CREAR_ALUMBRADO_INTELIGENTE;
	}

	// CREAR
	//@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/sensorAlumbradoInteligente/newSensorAlumbradoInteligente")
	public ModelAndView newSensorAlumbradoInteligente(
			@Valid @ModelAttribute("sensorAlumbradoInteligente") SensorAlumbradoInteligente sensor,
			BindingResult bindingResult) {

		ModelAndView mV = new ModelAndView();
		System.out.println(bindingResult);
		if (bindingResult.hasErrors()) {
			// Si hay errores de validación, vuelve a cargar el formulario con los errores
			mV.setViewName(ViewRouteHelper.CREAR_ALUMBRADO_INTELIGENTE);
		} else {
			// Si no hay errores de validación, guarda el sensor en la base de datos
			sensorService.saveSensor(sensor);
			mV.setViewName(ViewRouteHelper.NUEVO_ALUMBRADO_INTELIGENTE);
			mV.addObject("sensorAlumbradoInteligente", sensor);
		}
		return mV;
	}

	// ELIMINAR
	@GetMapping("/sensorAlumbradoInteligente/eliminar")
	public String eliminarSensorAlumbradoInteligente(Model model) {
	    List<SensorAlumbradoInteligente> devices = sensorService.getAllActiveSensors(); // Obtener la lista de dispositivos
	    model.addAttribute("devices", devices); // Pasar la lista al modelo
	    return ViewRouteHelper.ELIMINAR_ALUMBRADO_INTELIGENTE;
	}
	
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
	@GetMapping("/sensorAlumbradoInteligente/editar")
	public String editarSensorAlumbradoInteligente(Model model) {
		List<SensorAlumbradoInteligente> devices = sensorService.getAllActiveSensors(); // Obtener la lista de dispositivos
	    model.addAttribute("devices", devices); // Pasar la lista al modelo
		return ViewRouteHelper.EDITAR_ALUMBRADO_INTELIGENTE;
	}
	
	// MODIFICAR Formulario
	@GetMapping("/sensorAlumbradoInteligente/editar/{id}")
	public String editarSensorAlumbrado(@PathVariable int id, Model model) {
	    SensorAlumbradoInteligente device = sensorService.getSensorById(id); // Obtener el dispositivo por ID
	    model.addAttribute("sensorAlumbradoInteligente", device); // Pasar el dispositivo al modelo
	    return ViewRouteHelper.FORMULARIO_EDITAR_ALUMBRADO_INTELIGENTE; 
	}
	
	//MODIFICAR GUARDADO
	@PostMapping("/sensorAlumbradoInteligente/editar/{id}/guardar")
	public ModelAndView guardarSensorAlumbrado(@PathVariable int id,@ModelAttribute("device") SensorAlumbradoInteligente device) {
	    // Guardar los cambios en el dispositivo en la base de datos
		device.setId(id);
		device.setCreatedAt(sensorService.getSensorById(id).getCreatedAt());
	    sensorService.saveSensor(device);

	    ModelAndView mV = new ModelAndView();
	    mV.setViewName(ViewRouteHelper.NUEVO_ALUMBRADO_INTELIGENTE);
	    mV.addObject("sensorAlumbradoInteligente", device);
	    return mV;
	}
	
	// Lista plana
		@GetMapping("/sensorAlumbradoInteligente/lista")
		public String listaSensorAlumbradoInteligente(Model model) {
			List<SensorAlumbradoInteligente> devices = sensorService.getAllActiveSensors(); // Obtener la lista de dispositivos activos
			model.addAttribute("devices", devices); // Pasar la lista al modelo
			return ViewRouteHelper.LISTA_ALUMBRADO_INTELIGENTE;
		}
		
	

}
