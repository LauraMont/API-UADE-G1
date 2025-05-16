package com.uade.tpo.marketplace.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Entrada {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String estado;

    @OneToOne
    @JoinColumn(name = "renglondecompra_id", referencedColumnName = "id")
    private RenglonDeCompra renglondecompra;

    @OneToOne
    @JoinColumn(name = "zona_id", referencedColumnName = "id")
    private Zona zona;

    @OneToOne
    @JoinColumn(name = "butaca_id", referencedColumnName = "id")
    private Butaca butaca;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    
}
