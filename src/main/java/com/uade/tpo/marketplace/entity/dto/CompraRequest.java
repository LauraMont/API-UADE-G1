package com.uade.tpo.marketplace.entity.dto;

import java.util.List;

import lombok.Data;

@Data
public class CompraRequest {
    private Long usuarioId;
    private Long eventoId;
    private List<String> butacas;

    CompraRequest() {
    }
    public CompraRequest(Long usuarioId, Long eventoId) {
        this.usuarioId = usuarioId;
        this.eventoId = eventoId;
    }   
}
