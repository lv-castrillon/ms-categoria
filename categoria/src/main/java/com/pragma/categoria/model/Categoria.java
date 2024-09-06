package com.pragma.categoria.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    private Integer id;
    private String nombre;
    private String descripcion;

}
