package com.pragma.stock.domain.usecase;

import com.pragma.stock.domain.enums.ErrorCodes;
import com.pragma.stock.domain.exception.BrandException;
import com.pragma.stock.domain.impl.BrandServiceImpl;
import com.pragma.stock.domain.model.Brand;
import com.pragma.stock.infraestructure.driven_adapters.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService implements BrandServiceImpl {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand createBrand(Brand brand) {
        if (brand.getDescription() == null) {
            throw new BrandException(ErrorCodes.BrandError.DESCRIPTION_NULL);
        }

        List<Brand> brandList = brandRepository.findAll();
        for(Brand brandOnList : brandList) {
            if(brandOnList.getName().equals(brand.getName())) {
                throw new BrandException(ErrorCodes.BrandError.DUPLICATE_NAMES);
            }
        }

        if (brand.getDescription().length() > 120 || brand.getName().length() > 50) {
            throw new BrandException(ErrorCodes.BrandError.DESCRIPTION_NAME_LENGTH);
        }

        Brand newBrand = new Brand();
        newBrand.setName(brand.getName());
        newBrand.setDescription(brand.getDescription());
        brandRepository.save(newBrand);
        return newBrand;
    }

    @Override
    public Page<Brand> listBrand(String order, int begin, int end) {
        String name = "name";
        Pageable any = PageRequest.of(begin, end, Sort.by(name));
        Page<Brand> list = brandRepository.findAll(any);
        if (order.equals("asc")) {
            Pageable asc = PageRequest.of(begin, end, Sort.by(name).ascending());
            list = brandRepository.findAll(asc);
        } else if (order.equals("desc")){
            Pageable desc = PageRequest.of(begin, end, Sort.by(name).descending());
            list = brandRepository.findAll(desc);
        }
        return list;
    }

}
