package com.uade.tpo.marketplace.entity;

import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import lombok.Builder;

@Data // trae todos los Getters y Setters
// @Builder //patrón que nos permite construir objetos
@Entity // entidad persistida en la base de datos
public class Categories {

    public Categories(){} //hibernate necesita que el constructor este vacio

    @Id //define que la siguiente propiedad va a ser la primary key de mi entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) //cuando se transforme de un modelo de clases a uno de bd relacionales, se define que se va a autogenerar
    private Long id; // por convencion los ids se trabajan en Long

    @Column
    private String description;

}
