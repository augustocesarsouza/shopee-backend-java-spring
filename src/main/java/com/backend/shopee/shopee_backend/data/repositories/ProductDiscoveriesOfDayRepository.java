package com.backend.shopee.shopee_backend.data.repositories;

import com.backend.shopee.shopee_backend.application.dto.ProductDiscoveriesOfDayDTO;
import com.backend.shopee.shopee_backend.data.context.ProductDiscoveriesOfDayRepositoryJPA;
import com.backend.shopee.shopee_backend.domain.entities.ProductDiscoveriesOfDay;
import com.backend.shopee.shopee_backend.domain.repositories.IProductDiscoveriesOfDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ProductDiscoveriesOfDayRepository implements IProductDiscoveriesOfDayRepository {
    private final ProductDiscoveriesOfDayRepositoryJPA productDiscoveriesOfDayRepositoryJPA;

    @Autowired
    public ProductDiscoveriesOfDayRepository(ProductDiscoveriesOfDayRepositoryJPA productDiscoveriesOfDayRepositoryJPA) {
        this.productDiscoveriesOfDayRepositoryJPA = productDiscoveriesOfDayRepositoryJPA;
    }

    @Override
    public ProductDiscoveriesOfDayDTO GetProductDiscoveriesOfDayToDelete(UUID productDiscoveriesOfDayId) {
        return productDiscoveriesOfDayRepositoryJPA.GetProductDiscoveriesOfDayToDelete(productDiscoveriesOfDayId);
    }

    @Override
    public ProductDiscoveriesOfDayDTO GetProductDiscoveriesOfDayById(UUID productDiscoveriesOfDayId) {
        return productDiscoveriesOfDayRepositoryJPA.GetProductDiscoveriesOfDayById(productDiscoveriesOfDayId);
    }

    @Override
    public List<ProductDiscoveriesOfDayDTO> GetAllProductDiscoveriesOfDay() {
        return productDiscoveriesOfDayRepositoryJPA.GetAllProductDiscoveriesOfDay();
    }

    @Override
    public ProductDiscoveriesOfDay create(ProductDiscoveriesOfDay productDiscoveriesOfDay) {
        if(productDiscoveriesOfDay == null)
            return null;

        return productDiscoveriesOfDayRepositoryJPA.save(productDiscoveriesOfDay);
    }

    @Override
    public ProductDiscoveriesOfDay update(ProductDiscoveriesOfDay productDiscoveriesOfDay) {
        return null;
    }

    @Override
    public ProductDiscoveriesOfDay delete(UUID productDiscoveriesOfDayId) {
        if(productDiscoveriesOfDayId == null)
            return null;

        var productDiscoveriesOfDay = productDiscoveriesOfDayRepositoryJPA.findById(productDiscoveriesOfDayId).orElse(null);

        if(productDiscoveriesOfDay == null)
            return null;

        productDiscoveriesOfDayRepositoryJPA.delete(productDiscoveriesOfDay);

        return productDiscoveriesOfDay;
    }
}
