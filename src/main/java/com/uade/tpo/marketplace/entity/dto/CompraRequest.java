package com.uade.tpo.marketplace.entity.dto;

import lombok.Data;

@Data
public class CompraRequest {
    private Long usuarioId;
    private Long eventoId;
    private int cantidad;
    private float total;

    CompraRequest() {
    }
    public CompraRequest(Long usuarioId, Long eventoId, int cantidad, float total) {
        this.usuarioId = usuarioId;
        this.eventoId = eventoId;
        this.cantidad = cantidad;
        this.total = total;
    }   
}
