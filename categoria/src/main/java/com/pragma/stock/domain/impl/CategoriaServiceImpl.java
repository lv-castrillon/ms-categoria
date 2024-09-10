package com.pragma.stock.domain.impl;

import com.pragma.stock.domain.model.Categoria;
import org.springframework.data.domain.Page;

public interface CategoriaServiceImpl {

    Categoria crearCategoria(Categoria categoria);

    Page<Categoria> listarCategorias(String orden, int inicio, int fin);
}
