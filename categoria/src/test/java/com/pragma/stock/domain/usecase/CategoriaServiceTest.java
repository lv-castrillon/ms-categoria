package com.pragma.stock.domain.usecase;

import com.pragma.stock.domain.enums.ErrorCodes;
import com.pragma.stock.domain.exception.CategoryException;
import com.pragma.stock.domain.model.Categoria;
import com.pragma.stock.infraestructure.driven_adapters.CategoriaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @Spy
    @InjectMocks
    private CategoriaService categoriaService;

    @Test
    void createCategorySuccessful() {
        Categoria categoriaTest = new Categoria();
        categoriaTest.setId(1);
        categoriaTest.setNombre("test1");
        categoriaTest.setDescripcion("test1");

        Categoria categoriaTest2 = new Categoria();
        categoriaTest2.setId(2);
        categoriaTest2.setNombre("test2");
        categoriaTest2.setDescripcion("test2");

        List<Categoria> categoriaListTest = new ArrayList<>();
        categoriaListTest.add(categoriaTest);

        Mockito.when(categoriaRepository.findAll()).thenReturn(categoriaListTest);

        Categoria categoriaResult = categoriaService.crearCategoria(categoriaTest2);

        Assertions.assertEquals(categoriaResult.getDescripcion(), categoriaTest2.getDescripcion());
    }

    @Test
    void createCategoryNameDuplicated() {
        Categoria categoriaTest = new Categoria();
        categoriaTest.setId(1);
        categoriaTest.setNombre("test1");
        categoriaTest.setDescripcion("test1");

        Categoria categoriaTest2 = new Categoria();
        categoriaTest2.setId(2);
        categoriaTest2.setNombre("test1");
        categoriaTest2.setDescripcion("test2");

        List<Categoria> categoriaListTest = new ArrayList<>();
        categoriaListTest.add(categoriaTest);

        Mockito.when(categoriaRepository.findAll()).thenReturn(categoriaListTest);

        CategoryException ex = Assertions.assertThrows(CategoryException.class,
                () -> categoriaService.crearCategoria(categoriaTest2));

        Assertions.assertEquals(ex.getMessage(), ErrorCodes.CategoryError.DUPLICATE_NAMES.getValue());
    }

    @Test
    void createCategoryExceedLength() {
        Categoria categoriaTest = new Categoria();
        categoriaTest.setId(1);
        categoriaTest.setNombre("qwertyuioplsdamvnxjasndlasxsdakasdmajsmdjqndquwndunsaunda");
        categoriaTest.setDescripcion("test1");

        Categoria categoriaTest2 = new Categoria();
        categoriaTest2.setId(2);
        categoriaTest2.setNombre("test1");
        categoriaTest2.setDescripcion("test2");

        List<Categoria> categoriaListTest = new ArrayList<>();
        categoriaListTest.add(categoriaTest2);

        Mockito.when(categoriaRepository.findAll()).thenReturn(categoriaListTest);

        CategoryException ex = Assertions.assertThrows(CategoryException.class,
                () -> categoriaService.crearCategoria(categoriaTest));

        Assertions.assertEquals(ex.getMessage(), ErrorCodes.CategoryError.DESCRIPTION_NAME_LENGTH.getValue());
    }

    @Test
    void createCategoryDescripcionNull() {

        Categoria categoriaTest2 = new Categoria();

        List<Categoria> categoriaListTest = new ArrayList<>();
        categoriaListTest.add(categoriaTest2);

        CategoryException ex = Assertions.assertThrows(CategoryException.class,
                () -> categoriaService.crearCategoria(categoriaTest2));

        Assertions.assertEquals(ex.getMessage(), ErrorCodes.CategoryError.DESCRIPTION_NULL.getValue());
    }

    @Test
    void listCategoriesSuccessful() {
        List<Categoria> categoriaList = new ArrayList<>();
        Pageable any = PageRequest.of(0, 2, Sort.by("nombre"));
        Page<Categoria> lista = new PageImpl<>(categoriaList, any, 2);

        Mockito.when(categoriaRepository.findAll(any)).thenReturn(lista);

        Page<Categoria> result = categoriaService.listarCategorias("any", 0, 2);

        Assertions.assertEquals(result.getTotalElements(), lista.getTotalElements());
    }

    @Test
    void listCategoriesDesc() {
        List<Categoria> categoriaList = new ArrayList<>();
        Pageable any = PageRequest.of(0, 2, Sort.by("nombre"));
        Page<Categoria> lista = new PageImpl<>(categoriaList, any, 2);

        Mockito.when(categoriaRepository.findAll(any)).thenReturn(lista);

        Pageable desc = PageRequest.of(0, 2, Sort.by("nombre").descending());
        Page<Categoria> listaDesc = new PageImpl<>(categoriaList, desc, 2);

        Mockito.when(categoriaRepository.findAll(desc)).thenReturn(listaDesc);

        Page<Categoria> result = categoriaService.listarCategorias("desc", 0, 2);

        Assertions.assertEquals(result.getTotalElements(), listaDesc.getTotalElements());
    }

    @Test
    void listCategoriesAsc() {
        List<Categoria> categoriaList = new ArrayList<>();
        Pageable any = PageRequest.of(0, 2, Sort.by("nombre").ascending());
        Page<Categoria> lista = new PageImpl<>(categoriaList, any, 2);

        Mockito.when(categoriaRepository.findAll(any)).thenReturn(lista);

        Page<Categoria> result = categoriaService.listarCategorias("asc", 0, 2);

        Assertions.assertEquals(result.getTotalElements(), lista.getTotalElements());
    }

}
