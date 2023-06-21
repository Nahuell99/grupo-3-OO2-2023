package com.unla.SpringBootUnLa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.SpringBootUnLa.entities.RecolectorInteligente;

@Repository("recolectorAlumbradoInteligenteRepository")
public interface IRecolectorInteligenteRepository extends JpaRepository<RecolectorInteligente, Long> {

}