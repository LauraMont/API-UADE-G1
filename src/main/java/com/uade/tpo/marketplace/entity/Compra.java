package com.uade.tpo.marketplace.entity;

import java.sql.Date;

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
public class Compra {

    public Compra() {
    }
    public Compra( Usuario usuario, Evento evento,int cantidad , float total) {
        this.fecha = new Date(System.currentTimeMillis());
        this.total = total;
        this.cantidad = cantidad;
        this.usuario = usuario;
        this.evento = evento;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date fecha;

    @Column
    private float total;

    @Column
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    public Long getIdCompra() {
        return id;
    }
    public Long getIdUsuario() {
       return this.usuario.getId();
    }
    public Long getIdProducto() {
        return this.evento.getId();
    }
    
}
