package com.uade.tpo.marketplace.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column
    private String nombre;

    @Column
    private String email;

    @Column
    private String contraseña;

    @Column
    private String rol;

    @Column
    private Date fecha_registro;

}
