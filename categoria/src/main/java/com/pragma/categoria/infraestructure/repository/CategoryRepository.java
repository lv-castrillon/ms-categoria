package com.pragma.categoria.infraestructure.repository;

import com.pragma.categoria.domain.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Integer, Categoria> {
}
