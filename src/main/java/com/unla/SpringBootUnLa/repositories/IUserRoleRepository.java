package com.unla.SpringBootUnLa.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.SpringBootUnLa.entities.UserRole;

@Repository("userRoleRepository")
public interface IUserRoleRepository extends JpaRepository<UserRole, Serializable> {
	
	@Query("SELECT ur FROM UserRole ur WHERE ur.user.id = :userId")
	public abstract UserRole findByUserId(@Param("userId") int userId);
	
}
