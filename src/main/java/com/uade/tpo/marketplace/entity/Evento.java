package com.uade.tpo.marketplace.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

import com.uade.tpo.marketplace.enums.EstadoEvento;
import lombok.Data;

@Data
@Entity
public class Evento {

    public Evento() {
    }
    public Evento(String nombre, String descripcion, Date fecha_hora, Artista artista, Locacion locacion, EstadoEvento estado, Categoria categoria, int cant_entradas, int pdescuento, byte[] imagenEvento, byte[] imagenZonas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaHora = fecha_hora;
        this.artista = artista;
        this.locacion = locacion;
        this.estado = estado;
        this.categoria = categoria;
        this.stockEntradas = cant_entradas;
        this.pdescuento = pdescuento;
        this.imagenEvento = imagenEvento;
        this.imagenZonas = imagenZonas;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String descripcion;
    
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    
    @ManyToOne
    @JoinColumn(name = "locacion_id")
    private Locacion locacion;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;

    @Column
    private int stockEntradas;
    
    @Enumerated(EnumType.STRING)
    private EstadoEvento estado;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Column
    private int pdescuento;

    @Lob
    @Column(name = "imagen_evento", columnDefinition="LONGBLOB")
    private byte[] imagenEvento;

    @Lob
    @Column(name = "imagen_zonas", columnDefinition="LONGBLOB")
    private byte[] imagenZonas;
}