package com.unla.SpringBootUnLa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.SpringBootUnLa.helpers.ViewRouteHelper;
import com.unla.SpringBootUnLa.models.SensorHumedadModel;
import com.unla.SpringBootUnLa.services.ISensorHumedadService;


@Controller
@RequestMapping("/index")
public class HomeController {
	

	@GetMapping(" ")
	public ModelAndView index() {
		ModelAndView mv=new ModelAndView(ViewRouteHelper.INDEX);

		return mv;
	}
	
}
