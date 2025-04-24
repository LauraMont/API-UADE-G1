package com.uade.tpo.marketplace.entity.dto;

import com.uade.tpo.marketplace.entity.Categoria;

import lombok.Data;

@Data
public class LocacionRequest {

    private String nombre;
    private String descripcion;
    private String direccion;
    private String capacidad_total;
    private String categoria;
    private int cantEntradas;
    private int stockEntradas;

}

