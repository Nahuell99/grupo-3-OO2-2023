package com.unla.SpringBootUnLa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.SpringBootUnLa.entities.RecolectorInteligente;

@Repository("recolectorAlumbradoInteligenteRepository")
public interface IRecolectorInteligenteRepository extends JpaRepository<RecolectorInteligente, Long> {

}