package com.pragma.categoria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categoria {

    private Integer id;
    @Column(length = 50)
    private String nombre;
    @NonNull
    @Column(length = 90)
    private String descripcion;

}
