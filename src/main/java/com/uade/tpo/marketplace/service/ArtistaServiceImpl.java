package com.uade.tpo.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.uade.tpo.marketplace.entity.Artista;
import com.uade.tpo.marketplace.exceptions.CategoryDuplicateException;
import com.uade.tpo.marketplace.repository.ArtistaRepository;

public class ArtistaServiceImpl implements ArtistaService {
    @Autowired
    private ArtistaRepository artistaRepository;

    public Artista createArtista(String nombre, String description, String genero) throws CategoryDuplicateException {
        return artistaRepository.save(new Artista(nombre, description, genero));
    }
    
}
