package com.unla.SpringBootUnLa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class RecolectorInteligente extends Device {

    @Column
    private boolean contenedorLleno;

    @Column
    private boolean limpiadorAutomaticoActivo;

    @Column
    private boolean usoIndebido;

    public RecolectorInteligente(String nombre, String descripcion, boolean activo, boolean contenedorLleno,
            boolean limpiadorAutomaticoActivo, boolean usoIndebido) {
        super(nombre, descripcion, activo);
        this.contenedorLleno = contenedorLleno;
        this.limpiadorAutomaticoActivo = limpiadorAutomaticoActivo;
        this.usoIndebido = usoIndebido;
    }

    @Override
    public String toString() {
        return super.toString() + "RecolectorInteligente{" +
                "contenedorLleno=" + contenedorLleno +
                ", limpiadorAutomaticoActivo=" + limpiadorAutomaticoActivo +
                ", usoIndebido=" + usoIndebido +
                '}';
    }
}
