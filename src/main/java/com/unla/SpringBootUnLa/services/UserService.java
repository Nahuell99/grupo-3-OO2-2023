package com.unla.SpringBootUnLa.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unla.SpringBootUnLa.entities.UserRole;
import com.unla.SpringBootUnLa.repositories.IUserRepository;

@Service("userService")
public class UserService implements UserDetailsService {

	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.unla.SpringBootUnLa.entities.User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return buildUser(user, buildGrantedAuthorities(user.getUserRoles()));
	}

	private User buildUser(com.unla.SpringBootUnLa.entities.User user, List<GrantedAuthority> grantedAuthorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(),
				true, true, true, // accountNonExpired, credentialsNonExpired, accountNonLocked,
				grantedAuthorities);
	}

	private List<GrantedAuthority> buildGrantedAuthorities(Set<UserRole> userRoles) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (UserRole userRole : userRoles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return new ArrayList<>(grantedAuthorities);
	}
	
	public List<com.unla.SpringBootUnLa.entities.User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public com.unla.SpringBootUnLa.entities.User getUserById(int userId) {
		Optional<com.unla.SpringBootUnLa.entities.User> user = userRepository.findById(userId);
		return user.orElse(null);
	}
	
	public void createUser(com.unla.SpringBootUnLa.entities.User user) {
		userRepository.save(user);
	}
	
	public void updateUser(com.unla.SpringBootUnLa.entities.User user) {
		userRepository.save(user);
	}
	
	public void deleteReactiveUser(int userId, boolean status) {		
		com.unla.SpringBootUnLa.entities.User user = this.getUserById(userId);
		user.setId(userId);
		user.setEnabled(status);
    	this.updateUser(user);
	}

	public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
	
}