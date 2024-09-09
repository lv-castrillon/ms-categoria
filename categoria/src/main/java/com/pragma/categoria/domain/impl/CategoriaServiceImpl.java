package com.pragma.categoria.domain.impl;

import com.pragma.categoria.domain.model.Categoria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoriaServiceImpl {

    Categoria crearCategoria(Categoria categoria);

    Page<Categoria> listarCategorias(String orden, int inicio, int fin);
}
