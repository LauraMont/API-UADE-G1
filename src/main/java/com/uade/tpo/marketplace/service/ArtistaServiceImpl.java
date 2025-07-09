package com.uade.tpo.marketplace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Artista;
import com.uade.tpo.marketplace.exceptions.CategoryDuplicateException;
import com.uade.tpo.marketplace.repository.ArtistaRepository;

@Service
public class ArtistaServiceImpl implements ArtistaService {
    @Autowired
    private ArtistaRepository artistaRepository;

    public Artista createArtista(String nombre, String description, String genero) throws CategoryDuplicateException {
        return artistaRepository.save(new Artista(nombre, description, genero));
    }
    
    public List<Artista> findAll() {
    return artistaRepository.findAll();
}
}
