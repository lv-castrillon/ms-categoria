package com.pragma.stock.infraestructure.entry_points;

import com.pragma.stock.domain.impl.CategoriaServiceImpl;
import com.pragma.stock.domain.model.Categoria;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categoria", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CategoriaController {

    @Autowired
    private final CategoriaServiceImpl categoriaService;

    @PostMapping(path = "/crear-categoria")
    public ResponseEntity<String> crearCategoria(@RequestBody Categoria body) {
        Categoria nuevaCategoria = categoriaService.crearCategoria(body);
        if(nuevaCategoria == null) {
            return ResponseEntity.badRequest().body("No se puede repetir el nombre de categoria!");
        } else {
            return ResponseEntity.ok("Categoria creada: " + nuevaCategoria.toString());
        }
    }

    @GetMapping(path = "/listar-categoria")
    public ResponseEntity<List<Categoria>> listarCategoria(@RequestParam String orden, @RequestParam int inicio,
                                                           @RequestParam int fin) {
        Page<Categoria> listaCategoria = categoriaService.listarCategorias(orden, inicio, fin);
        if(listaCategoria == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(listaCategoria.getContent());
        }
    }

}
