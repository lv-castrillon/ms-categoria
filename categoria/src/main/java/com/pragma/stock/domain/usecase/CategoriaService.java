package com.pragma.stock.domain.usecase;

import com.pragma.stock.domain.enums.ErrorCodes;
import com.pragma.stock.domain.exception.CategoryException;
import com.pragma.stock.domain.impl.CategoriaServiceImpl;
import com.pragma.stock.domain.model.Categoria;
import com.pragma.stock.infraestructure.driven_adapters.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService implements CategoriaServiceImpl {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Categoria crearCategoria(Categoria categoria) {
        if (categoria.getDescripcion() == null) {
            throw new CategoryException(ErrorCodes.CategoryError.DESCRIPTION_NULL);
        }

        List<Categoria> categoriaList = categoriaRepository.findAll();
        for(Categoria categoriaOnList: categoriaList) {
            if(categoriaOnList.getNombre().equals(categoria.getNombre())) {
                throw new CategoryException(ErrorCodes.CategoryError.DUPLICATE_NAMES);
            }
        }

        if (categoria.getDescripcion().length() > 90 || categoria.getNombre().length() > 50) {
            throw new CategoryException(ErrorCodes.CategoryError.DESCRIPTION_NAME_LENGTH);
        }

        Categoria nuevaCategoria = new Categoria();
        nuevaCategoria.setNombre(categoria.getNombre());
        nuevaCategoria.setDescripcion(categoria.getDescripcion());
        categoriaRepository.save(nuevaCategoria);
        return nuevaCategoria;
    }

    @Override
    public Page<Categoria> listarCategorias(String orden, int inicio, int fin) {
        Pageable any = PageRequest.of(inicio, fin, Sort.by("nombre"));
        Page<Categoria> lista = categoriaRepository.findAll(any);
        if (orden.equals("asc")) {
            Pageable asc = PageRequest.of(inicio, fin, Sort.by("nombre").ascending());
            lista = categoriaRepository.findAll(asc);
        } else if (orden.equals("desc")){
            Pageable desc = PageRequest.of(inicio, fin, Sort.by("nombre").descending());
            lista = categoriaRepository.findAll(desc);
        }
        return lista;
    }

}
