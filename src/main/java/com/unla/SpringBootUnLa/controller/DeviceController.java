package com.unla.SpringBootUnLa.controller;


import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
