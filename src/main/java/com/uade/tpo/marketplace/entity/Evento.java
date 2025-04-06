package com.uade.tpo.marketplace.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Evento {

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
    private int stock_entradas;

    @Column
    private int cant_entradas;

    @OneToOne(mappedBy = "evento")
    private Compra compra;


}
