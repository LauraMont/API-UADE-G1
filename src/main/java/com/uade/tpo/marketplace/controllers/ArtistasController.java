package com.uade.tpo.marketplace.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.Artista;
import com.uade.tpo.marketplace.entity.dto.ArtistaRequest;
import com.uade.tpo.marketplace.exceptions.CategoryDuplicateException;
import com.uade.tpo.marketplace.service.ArtistaService;

@RestController
@RequestMapping("/artista")
public class ArtistasController {
    @Autowired
    private ArtistaService artistaService;

    @PostMapping
    public ResponseEntity<Artista> createArtista(@RequestBody ArtistaRequest artistaRequest) // only admin can create categories
            throws CategoryDuplicateException {
        Artista result = artistaService.createArtista(artistaRequest.getNombre(), artistaRequest.getDescription(), artistaRequest.getGenero());
        return ResponseEntity.created(URI.create("/artista/" + result.getId())).body(result);
    }
}
