package com.uade.tpo.marketplace.service;

import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Artista;
import com.uade.tpo.marketplace.exceptions.CategoryDuplicateException;

@Service
public interface ArtistaService {
    public Artista createArtista(String nombre, String description, String genero) throws CategoryDuplicateException;

}
