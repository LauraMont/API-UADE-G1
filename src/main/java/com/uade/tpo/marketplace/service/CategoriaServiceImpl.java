package com.uade.tpo.marketplace.service;

import com.uade.tpo.marketplace.entity.Categoria;
import com.uade.tpo.marketplace.exceptions.CategoryDuplicateException;
import com.uade.tpo.marketplace.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService{
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> getCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> getCategoriaByName(String nombre) {
        return categoriaRepository.findByNombreIgnoreCase(nombre);
    }

    @Override
    public Categoria createCategoria(String nombre, String descripcion) throws CategoryDuplicateException {
        Optional<Categoria> categories = categoriaRepository.findByNombreIgnoreCase(nombre);
        if (categories.isEmpty())
            return categoriaRepository.save(new Categoria(nombre, descripcion));
        throw new CategoryDuplicateException();
    }
}
