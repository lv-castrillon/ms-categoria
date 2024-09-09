package com.pragma.categoria.infraestructure.controller;

import com.pragma.categoria.domain.impl.CategoriaServiceImpl;
import com.pragma.categoria.domain.model.Categoria;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categoria", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CategoriaController {

    @Autowired
    private final CategoriaServiceImpl categoriaService;

    @PostMapping(path = "/crear-categoria")
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria body) {
        Categoria nuevaCategoria = categoriaService.crearCategoria(body);
        if(nuevaCategoria == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(nuevaCategoria);
        }
    }

}
