package com.uade.tpo.marketplace.entity.dto;
import java.sql.Date;

import com.uade.tpo.marketplace.enums.EstadoEvento;

import lombok.Data;

@Data
public class EventoRequest {

    private String nombre;
    private String descripcion;
    private Date fechaHora;
    private String artista;
    private String locacion;
    private EstadoEvento estado;
    private String categoria;
    private int pdescuento;
    private int stockEntradas;
    private String imagenEvento;
    private String imagenZonas;

    // Getters
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public Date getFechaHora() { return fechaHora; }
    public String getArtista() { return artista; }
    public String getLocacion() { return locacion; }
    public EstadoEvento getEstado() { return estado; }
    public String getCategoria() { return categoria; }
    public int getPdescuento() { return pdescuento; }
    public int getStockEntradas() { return stockEntradas; }
    public String getImagenEvento() { return imagenEvento; }
    public String getImagenZonas() { return imagenZonas; }
}

