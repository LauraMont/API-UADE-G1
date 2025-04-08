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
    public Compra( Long id_user, Long id_evento,int cantidad , float total) {
        this.fecha = new Date(System.currentTimeMillis());
        this.total = total;
        this.cantidad = cantidad;
        this.id_user = id_user;
        this.id_evento = id_evento;
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
    private Long id_user;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Long id_evento;

    public Long getIdCompra() {
        return id;
    }
    
}
