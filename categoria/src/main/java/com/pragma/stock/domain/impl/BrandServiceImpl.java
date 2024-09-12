package com.pragma.stock.domain.impl;

import com.pragma.stock.domain.model.Brand;
import org.springframework.data.domain.Page;

public interface BrandServiceImpl {

    Brand createBrand(Brand brand);

    Page<Brand> listBrand(String order, int begin, int end);
}
