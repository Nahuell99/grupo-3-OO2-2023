package com.unla.SpringBootUnLa.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unla.SpringBootUnLa.entities.RecolectorInteligente;
import com.unla.SpringBootUnLa.repositories.IRecolectorInteligenteRepository;

@Service
public class RecolectorInteligenteService {

    private final IRecolectorInteligenteRepository recolectorRepository;

    public RecolectorInteligenteService(IRecolectorInteligenteRepository recolectorRepository) {
        this.recolectorRepository = recolectorRepository;
    }

    // Guardar un nuevo registro
    public RecolectorInteligente saveRecolector(RecolectorInteligente recolector) {
        return recolectorRepository.save(recolector);
    }

    // Actualizar un registro existente
    public RecolectorInteligente updateRecolector(RecolectorInteligente recolector) {
        if (recolector.getId() <= 0) {
            throw new IllegalArgumentException("El recolector debe tener un ID válido para ser actualizado");
        }

        // Verificar si el recolector existe en la base de datos
        RecolectorInteligente existingRecolector = this.getRecolectorById(recolector.getId());
        if (existingRecolector == null) {
            throw new IllegalArgumentException("No se encontró el recolector con ID: " + recolector.getId());
        }

        // Actualizar los campos del recolector existente con los nuevos valores
        existingRecolector.setNombre(recolector.getNombre());
        existingRecolector.setDescripcion(recolector.getDescripcion());
        existingRecolector.setContenedorLleno(recolector.isContenedorLleno());
        existingRecolector.setLimpiadorAutomaticoActivo(recolector.isLimpiadorAutomaticoActivo());
        existingRecolector.setUsoIndebido(recolector.isUsoIndebido());

        // Guardar el recolector actualizado en la base de datos
        return recolectorRepository.save(existingRecolector);
    }

    public RecolectorInteligente getRecolectorById(long recolectorId) {
        return recolectorRepository.findById(recolectorId).orElse(null);
    }

    public List<RecolectorInteligente> getAllRecolectores() {
        return recolectorRepository.findAll();
    }

    public void deleteRecolector(long recolectorId) {
        RecolectorInteligente re = this.getRecolectorById(recolectorId);
    	re.setActivo(false);
    	recolectorRepository.save(re);
    }

}

