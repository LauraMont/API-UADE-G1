package com.uade.tpo.marketplace.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Evento {

    public Evento() {
    }
    public Evento(String nombre, String descripcion, Date fecha_hora, String estado, String categoria, int cant_entradas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha_hora = fecha_hora;
        this.estado = estado;
        this.categoria = categoria;
        this.cant_entradas = cant_entradas;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private Date fecha_hora;

    @Column
    private String estado;
    
    @Column
    private String categoria;

    @Column
    private int cant_entradas;

    @OneToOne(mappedBy = "evento")
    private Compra compra;

    public int getStock() {
        return cant_entradas;
    }


}
