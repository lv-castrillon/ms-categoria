package com.pragma.stock.domain.usecase;

import com.pragma.stock.domain.enums.ErrorCodes;
import com.pragma.stock.domain.exception.BrandException;
import com.pragma.stock.domain.model.Brand;
import com.pragma.stock.infraestructure.driven_adapters.BrandRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;

    @Spy
    @InjectMocks
    private BrandService brandService;

    @Test
    void createBrandSuccessful() {
        Brand brandTest = new Brand();
        brandTest.setId(1);
        brandTest.setName("test1");
        brandTest.setDescription("test1");

        Brand brandTest2 = new Brand();
        brandTest2.setId(2);
        brandTest2.setName("test2");
        brandTest2.setDescription("test2");

        List<Brand> brandListTest = new ArrayList<>();
        brandListTest.add(brandTest);

        Mockito.when(brandRepository.findAll()).thenReturn(brandListTest);

        Brand brandResult = brandService.createBrand(brandTest2);

        Assertions.assertEquals(brandResult.getDescription(), brandTest2.getDescription());
    }

    @Test
    void createBrandNameDuplicated() {
        Brand brandTest = new Brand();
        brandTest.setId(1);
        brandTest.setName("test1");
        brandTest.setDescription("test1");

        Brand brandTest2 = new Brand();
        brandTest2.setId(2);
        brandTest2.setName("test2");
        brandTest2.setDescription("test2");

        List<Brand> brandListTest = new ArrayList<>();
        brandListTest.add(brandTest);

        Mockito.when(brandRepository.findAll()).thenReturn(brandListTest);

        BrandException ex = Assertions.assertThrows(BrandException.class,
                () -> brandService.createBrand(brandTest));

        Assertions.assertEquals(ex.getMessage(), ErrorCodes.BrandError.DUPLICATE_NAMES.getValue());
    }

    @Test
    void createBrandExceedLength() {
        Brand brandTest = new Brand();
        brandTest.setId(1);
        brandTest.setName("test1");
        brandTest.setDescription("test1");

        Brand brandTest2 = new Brand();
        brandTest2.setId(2);
        brandTest2.setName("qwertyuioplsdamvnxjasndlasxsdakasdmajsmdjqndquwndunsaunda");
        brandTest2.setDescription("test2");

        List<Brand> brandListTest = new ArrayList<>();
        brandListTest.add(brandTest);

        Mockito.when(brandRepository.findAll()).thenReturn(brandListTest);

        BrandException ex = Assertions.assertThrows(BrandException.class,
                () -> brandService.createBrand(brandTest2));

        Assertions.assertEquals(ex.getMessage(), ErrorCodes.BrandError.DESCRIPTION_NAME_LENGTH.getValue());
    }

    @Test
    void createBrandDescriptionNull() {

        Brand brandTest2 = new Brand();

        BrandException ex = Assertions.assertThrows(BrandException.class,
                () -> brandService.createBrand(brandTest2));

        Assertions.assertEquals(ex.getMessage(), ErrorCodes.BrandError.DESCRIPTION_NULL.getValue());
    }
}
