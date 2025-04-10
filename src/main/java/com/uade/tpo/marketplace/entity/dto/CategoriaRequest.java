package com.uade.tpo.marketplace.entity.dto;

import lombok.Data;
import lombok.Getter;


@Data

public class CategoriaRequest {
    private String nombre;
    private String descripcion;

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
