package com.unla.SpringBootUnLa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.SpringBootUnLa.entities.UserRole;
import com.unla.SpringBootUnLa.repositories.IUserRepository;
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

}
