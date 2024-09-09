package com.pragma.categoria.domain.service;

import com.pragma.categoria.domain.impl.CategoriaServiceImpl;
import com.pragma.categoria.domain.model.Categoria;
import com.pragma.categoria.infraestructure.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService implements CategoriaServiceImpl {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Categoria crearCategoria(Categoria categoria) {
        List<Categoria> categoriaList = categoriaRepository.findAll();
        for(Categoria categoriaOnList: categoriaList) {
            if(categoriaOnList.getNombre().equals(categoria.getNombre())) {
                return null;
            }
        }

        Categoria nuevaCategoria = new Categoria();
        nuevaCategoria.setNombre(categoria.getNombre());
        nuevaCategoria.setDescripcion(categoria.getDescripcion());
        categoriaRepository.save(nuevaCategoria);
        return nuevaCategoria;
    }

}
