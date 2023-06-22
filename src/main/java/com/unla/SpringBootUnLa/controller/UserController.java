package com.unla.SpringBootUnLa.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.SpringBootUnLa.entities.User;
import com.unla.SpringBootUnLa.entities.UserRole;
import com.unla.SpringBootUnLa.helpers.ViewRouteHelper;
import com.unla.SpringBootUnLa.services.UserRoleService;
import com.unla.SpringBootUnLa.services.UserService;

@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;

	public UserController(UserService userService, UserRoleService userRoleService) {
		this.userService = userService;
		this.userRoleService = userRoleService;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/abmUser")
	public String abmUser(Model model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return ViewRouteHelper.USER_ABM_USER;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/abmUser/create")
	public String createUserForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("userRole",  new UserRole());
		return ViewRouteHelper.USER_CREATE;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/abmUser/newCreate")
    public String createUser(@ModelAttribute("user") User user, @ModelAttribute("userRole") UserRole userRole, Model model) {
        // Encriptar la contraseña antes de guardarla en la base de datos
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setEnabled(true);

        // Guardar el usuario en la base de datos
        userService.createUser(user);

        // Establecer la relación entre el usuario y el rol
        userRole.setUser(user);
        userRoleService.createUserRole(userRole);

        model.addAttribute("users", userService.getAllUsers());
        return ViewRouteHelper.USER_ABM_USER;
    }

	@GetMapping("/abmUser/delete/{id}")
	public String deleteUser(@PathVariable("id") int userId, Model model) {
		
		userService.deleteReactiveUser(userId, false);
		model.addAttribute("users", userService.getAllUsers());
        return ViewRouteHelper.USER_ABM_USER;
	}
	
	@GetMapping("/abmUser/reactivate/{id}")
	public String reactivUser(@PathVariable("id") int userId, Model model) {
		userService.deleteReactiveUser(userId, true);
		model.addAttribute("users", userService.getAllUsers());
        return ViewRouteHelper.USER_ABM_USER;
	}
	
	@GetMapping("/abmUser/edit/{id}")
	public String editUserForm(@PathVariable("id") int userId, Model model) {
		User user = userService.getUserById(userId);
		UserRole userRole = userRoleService.findByUserId(userId);
		model.addAttribute("user", user);
		model.addAttribute("userRole", userRole);
		return ViewRouteHelper.USER_EDIT;
	}

	@PostMapping("/abmUser/edit/{id}/guardar")
	public String editUser(@PathVariable("id") int userId, @ModelAttribute("user") User user, @ModelAttribute("userRole") UserRole userRole, Model model) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		user.setId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setCreatedAt(userService.getUserById(userId).getCreatedAt());
		user.setEnabled(userService.getUserById(userId).isEnabled());
		userService.updateUser(user);

		System.out.println(userRole);
		System.out.println(userRole.getUser());

		userRole.setCreatedAt(userRoleService.getUserRoleById(userRole.getId()).getCreatedAt());
		userRole.setUser(user);
		
		
		userRoleService.updateUserRole(userRole);
		
		
		model.addAttribute("users", userService.getAllUsers());
		return ViewRouteHelper.USER_ABM_USER;
	}

}
