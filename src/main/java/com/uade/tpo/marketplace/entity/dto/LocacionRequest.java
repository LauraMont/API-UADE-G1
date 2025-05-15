package com.uade.tpo.marketplace.entity.dto;

import lombok.Data;

@Data
public class LocacionRequest {
    private String nombre;
    private String direccion;
    private int capacidad_total;
    private String categoriaId;
}

