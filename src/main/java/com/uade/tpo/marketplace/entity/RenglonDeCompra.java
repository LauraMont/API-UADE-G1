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
public class RenglonDeCompra {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "butaca_id", referencedColumnName = "id", nullable = false)
    private Butaca butaca;

    @ManyToOne
    @JoinColumn(name = "compra_id", referencedColumnName = "id", nullable = false)
    private Compra compra;

    @OneToOne(mappedBy = "renglondecompra")
    private Entrada entrada;

    RenglonDeCompra() {
    }
    public RenglonDeCompra(Compra compra, Butaca butaca) {
        this.compra = compra;
        this.butaca = butaca;
    }

}
