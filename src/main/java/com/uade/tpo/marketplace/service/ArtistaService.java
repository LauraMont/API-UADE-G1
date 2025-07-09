package com.uade.tpo.marketplace.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Artista;
import com.uade.tpo.marketplace.exceptions.CategoryDuplicateException;

public interface ArtistaService {
    public Artista createArtista(String nombre, String description, String genero) throws CategoryDuplicateException;

    public List<Artista> findAll();

}
