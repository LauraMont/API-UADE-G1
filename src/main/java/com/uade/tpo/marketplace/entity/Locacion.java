package com.uade.tpo.marketplace.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Locacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String direccion;

    @Column
    private int capacidad_total;

    public Locacion(String nombre, String direccion, int capacidad_total) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidad_total = capacidad_total;
    }
    public Locacion() {
    }

}
