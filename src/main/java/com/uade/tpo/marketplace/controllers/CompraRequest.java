package com.uade.tpo.marketplace.controllers;

public class CompraRequest {
    private Long usuarioId;
    private Long eventoId;
    private int cantidad;
    private float total;

    // Getters y setters
    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getEventoId() {
        return eventoId;
    }

    public void setEventoId(Long eventoId) {
        this.eventoId = eventoId;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }       
    public float getTotal() {
        return total;
    }
    public void setTotal(float total) {
        this.total = total;
    }
}
