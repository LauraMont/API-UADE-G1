package com.uade.tpo.marketplace.entity;

import com.uade.tpo.marketplace.enums.EstadoButaca;

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

    @OneToOne
    @JoinColumn(name = "renglondecompra_id", referencedColumnName = "id", nullable = false)
    private RenglonDeCompra renglondecompra;

    @OneToOne
    @JoinColumn(name = "butaca_id", referencedColumnName = "id")
    private Butaca butaca;

    @ManyToOne(optional = false)
    @JoinColumn(name = "evento_id", referencedColumnName = "id", nullable = false)
    private Evento evento;

    Entrada() {
    }
    public Entrada(Butaca butaca) {
        this.renglondecompra = null;
        this.butaca = butaca;
    }

}
