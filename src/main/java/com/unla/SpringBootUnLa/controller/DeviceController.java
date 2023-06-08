package com.unla.SpringBootUnLa.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.SpringBootUnLa.entities.SensorAlumbradoInteligente;
import com.unla.SpringBootUnLa.services.SensorAlumbradoInteligenteService;
import com.unla.SpringBootUnLa.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/")
public class DeviceController {

    private final SensorAlumbradoInteligenteService sensorService;

    public DeviceController(SensorAlumbradoInteligenteService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping("/device")
    public String deviceList(Model model) {
        List<SensorAlumbradoInteligente> devices = sensorService.getAllSensors();
        model.addAttribute("devices", devices);
        return ViewRouteHelper.DEVICE_LIST;
    }
    
    @GetMapping("/")
    public String redireccionDevice() {
        return "redirect:/device";
    }

}
