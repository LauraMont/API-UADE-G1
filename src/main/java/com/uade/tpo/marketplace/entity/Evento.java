package com.uade.tpo.marketplace.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import com.uade.tpo.marketplace.enums.EstadoEvento;
import lombok.Data;

@Data
@Entity
public class Evento {

    public Evento() {
    }
    public Evento(String nombre, String descripcion, Date fecha_hora, String artista, EstadoEvento estado, Categoria categoria, int cant_entradas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaHora = fecha_hora;
        this.artista = artista;
        this.estado = estado;
        this.categoria = categoria;
        this.cantEntradas = cant_entradas;
        this.stockEntradas = cant_entradas;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String descripcion;
    
    @Column
    private Date fechaHora;
    
    @Column
    private int stockEntradas;
    
    @Column
    private int cantEntradas; // total entradas disponibles

    @Column
    private String artista;
    
    @Enumerated(EnumType.STRING)
    private EstadoEvento estado;

    @OneToMany(mappedBy = "evento")
    private List<Compra> compra;


    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

}
