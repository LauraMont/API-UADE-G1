package com.uade.tpo.marketplace.controllers;

import com.uade.tpo.marketplace.entity.Categoria;
import com.uade.tpo.marketplace.entity.dto.CategoriaRequest;
import com.uade.tpo.marketplace.exceptions.CategoryDuplicateException;
import com.uade.tpo.marketplace.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> getCategorias() { // all users can see all categories
        return ResponseEntity.ok(categoriaService.getCategorias());
    }

    @GetMapping("/nombre/{nombre}") // all users can see all categories
    public ResponseEntity<Categoria> getCategoryByName(@PathVariable String nombre) {
        Optional<Categoria> result = categoriaService.getCategoriaByName(nombre);
        return result.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody CategoriaRequest categoriaRequest) // only admin can create categories
            throws CategoryDuplicateException {
        Categoria result = categoriaService.createCategoria(categoriaRequest.getNombre(), categoriaRequest.getDescripcion());
        return ResponseEntity.created(URI.create("/categoria/" + result.getId())).body(result);
    }

}