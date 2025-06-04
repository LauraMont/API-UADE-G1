package com.uade.tpo.marketplace.entity;

import com.uade.tpo.marketplace.enums.EstadoButaca;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Butaca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String numero;

    @Column
    private EstadoButaca estado;

    @ManyToOne
    @JoinColumn(name = "zona_id")
    private Zona zona;

    Butaca() {
    }
    public Butaca(String numero, Zona zona) {
        this.numero = numero;
        this.estado = EstadoButaca.DISPONIBLE;
        this.zona = zona;
    }
    
}
