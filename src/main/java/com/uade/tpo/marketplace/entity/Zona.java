package com.uade.tpo.marketplace.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Zona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private float precio_base;

    @ManyToOne
    @JoinColumn(name = "locacion", nullable = false)
    private Locacion locacion;

    private int cantidad_butacas;

    Zona() {
    }
    public Zona(float precio_base, Locacion locacion, int cantidad_butacas) {
        this.precio_base = precio_base;
        this.locacion = locacion;
        this.cantidad_butacas = cantidad_butacas;
    }
}
