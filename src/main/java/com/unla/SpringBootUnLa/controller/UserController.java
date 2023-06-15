package com.unla.SpringBootUnLa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.unla.SpringBootUnLa.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/")
public class UserController {
	
	@GetMapping("/login")
	public String login(Model model,
						@RequestParam(name="error", required=false) String error,
						@RequestParam(name="logout", required=false) String logout) {
		System.out.println("Paso por /login");
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		
		return ViewRouteHelper.USER_LOGIN;
	}
	
	@GetMapping("/logout")
	public String logout(Model model) {
		System.out.println("Paso por /logout");
		return ViewRouteHelper.USER_LOGOUT;
	}
	
	public String loginCheck(){
		System.out.println("Paso por /loginCheck");
		return "redirect:/device";		
	}
}
