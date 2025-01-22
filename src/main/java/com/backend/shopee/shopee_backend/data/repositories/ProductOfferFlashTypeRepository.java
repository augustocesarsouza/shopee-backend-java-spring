package com.backend.shopee.shopee_backend.data.repositories;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashTypeDTO;
import com.backend.shopee.shopee_backend.data.context.ProductOfferFlashTypeRepositoryJPA;
import com.backend.shopee.shopee_backend.domain.entities.ProductOfferFlashType;
import com.backend.shopee.shopee_backend.domain.repositories.IProductOfferFlashTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ProductOfferFlashTypeRepository implements IProductOfferFlashTypeRepository {
    private final ProductOfferFlashTypeRepositoryJPA productOfferFlashTypeRepositoryJPA;

    @Autowired
    public ProductOfferFlashTypeRepository(ProductOfferFlashTypeRepositoryJPA productOfferFlashTypeRepositoryJPA) {
        this.productOfferFlashTypeRepositoryJPA = productOfferFlashTypeRepositoryJPA;
    }

    @Override
    public List<ProductOfferFlashTypeDTO> GetAllProductOfferFlashTypeByProductsOfferFlashId(UUID productsOfferFlashId) {
        return productOfferFlashTypeRepositoryJPA.GetAllProductOfferFlashTypeByProductsOfferFlashId(productsOfferFlashId);
    }

    @Override
    public ProductOfferFlashType create(ProductOfferFlashType productOfferFlashType) {
        if(productOfferFlashType == null)
            return null;

        return productOfferFlashTypeRepositoryJPA.save(productOfferFlashType);
    }

    @Override
    public ProductOfferFlashType update(ProductOfferFlashType productOfferFlashType) {
        return null;
    }

    @Override
    public ProductOfferFlashType delete(UUID productOfferFlashTypeId) {
        if(productOfferFlashTypeId == null)
            return null;

        var entity = productOfferFlashTypeRepositoryJPA.findById(productOfferFlashTypeId).orElse(null);

        if(entity == null)
            return null;

        productOfferFlashTypeRepositoryJPA.delete(entity);

        return entity;
    }
}
