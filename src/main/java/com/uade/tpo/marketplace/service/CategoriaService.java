package com.uade.tpo.marketplace.service;

import com.uade.tpo.marketplace.entity.Categoria;
import com.uade.tpo.marketplace.exceptions.CategoryDuplicateException;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    public List<Categoria> getCategorias();

    Optional<Categoria> getCategoriaByName(String nombre);

    public Categoria createCategoria(String nombre, String description) throws CategoryDuplicateException;

}
