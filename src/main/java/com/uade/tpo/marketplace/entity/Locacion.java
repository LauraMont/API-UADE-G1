package com.uade.tpo.marketplace.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "locacion", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Zona> zonas;

    public Locacion(String nombre, String direccion, int capacidad_total) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.capacidad_total = capacidad_total;
    }

    public Locacion() {
    this.zonas = new ArrayList<>();
    }
    
}
