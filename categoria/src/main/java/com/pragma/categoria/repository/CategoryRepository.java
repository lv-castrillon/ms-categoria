package com.pragma.categoria.repository;

import com.pragma.categoria.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Integer, Categoria> {
}
