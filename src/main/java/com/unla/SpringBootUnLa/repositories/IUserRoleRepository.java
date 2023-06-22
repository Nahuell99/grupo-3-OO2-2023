package com.unla.SpringBootUnLa.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.SpringBootUnLa.entities.UserRole;

@Repository("userRoleRepository")
public interface IUserRoleRepository extends JpaRepository<UserRole, Serializable> {

}
