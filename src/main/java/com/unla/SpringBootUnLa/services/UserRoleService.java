package com.unla.SpringBootUnLa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.SpringBootUnLa.entities.UserRole;
import com.unla.SpringBootUnLa.repositories.IUserRoleRepository;

@Service("userRoleService")
public class UserRoleService {
	
	@Autowired
	@Qualifier("userRoleRepository")
	private IUserRoleRepository userRoleRepository;
	
	public List<UserRole> getAllUserRoles() {
		return userRoleRepository.findAll();
	}
	
	public void createUserRole(UserRole userRole) {
		userRoleRepository.save(userRole);
	}
	
	public void updateUserRole(UserRole userRole) {
		userRoleRepository.save(userRole);
	}
	
	public void deleteUser(int userId) {
		userRoleRepository.deleteById(userId);
	}
	
	//Traer rol por id
	public UserRole getUserRoleById(int id) {
        return userRoleRepository.findById(id).orElse(null);
    }
	
	//Traer Rol de un usuario particular
	public UserRole findByUserId(int userId) {
		return userRoleRepository.findByUserId(userId);
	}

}
