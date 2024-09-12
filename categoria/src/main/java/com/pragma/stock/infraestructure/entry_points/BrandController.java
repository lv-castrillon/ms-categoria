package com.pragma.stock.infraestructure.entry_points;

import com.pragma.stock.domain.impl.BrandServiceImpl;
import com.pragma.stock.domain.model.Brand;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/brand", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class BrandController {

    @Autowired
    private final BrandServiceImpl brandService;

    @PostMapping(path = "/create-brand")
    public ResponseEntity<String> createBrand(@RequestBody Brand body) {
        Brand newBrand = brandService.createBrand(body);
        if(newBrand == null) {
            return ResponseEntity.badRequest().body("No se puede repetir el nombre de la marca!");
        } else {
            return ResponseEntity.ok("Marca creada: " + newBrand.toString());
        }
    }
}
