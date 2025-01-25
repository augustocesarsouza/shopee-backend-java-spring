package com.backend.shopee.shopee_backend.data.repositories;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashDescriptionDTO;
import com.backend.shopee.shopee_backend.data.context.ProductOfferFlashDescriptionRepositoryJPA;
import com.backend.shopee.shopee_backend.domain.entities.ProductOfferFlashDescription;
import com.backend.shopee.shopee_backend.domain.repositories.IProductOfferFlashDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductOfferFlashDescriptionRepository implements IProductOfferFlashDescriptionRepository {
    private final ProductOfferFlashDescriptionRepositoryJPA productOfferFlashDescriptionRepositoryJPA;

    @Autowired
    public ProductOfferFlashDescriptionRepository(ProductOfferFlashDescriptionRepositoryJPA productOfferFlashDescriptionRepositoryJPA) {
        this.productOfferFlashDescriptionRepositoryJPA = productOfferFlashDescriptionRepositoryJPA;
    }

    @Override
    public ProductOfferFlashDescriptionDTO GetByProductsOfferFlashId(UUID productsOfferFlashId) {
        return productOfferFlashDescriptionRepositoryJPA.GetByProductsOfferFlashId(productsOfferFlashId);
    }

    @Override
    public ProductOfferFlashDescriptionDTO GetByProductsOfferFlashIdIfExist(UUID productOfferFlashDescriptionId) {
        return productOfferFlashDescriptionRepositoryJPA.GetByProductsOfferFlashIdIfExist(productOfferFlashDescriptionId);
    }

    @Override
    public ProductOfferFlashDescription create(ProductOfferFlashDescription productOfferFlashDescription) {
        if(productOfferFlashDescription == null)
            return null;

        return productOfferFlashDescriptionRepositoryJPA.save(productOfferFlashDescription);
    }

    @Override
    public ProductOfferFlashDescription update(ProductOfferFlashDescription productOfferFlashDescription) {
        return null;
    }

    @Override
    public ProductOfferFlashDescription delete(UUID productOfferFlashDescriptionId) {
        if(productOfferFlashDescriptionId == null)
            return null;

        var entity = productOfferFlashDescriptionRepositoryJPA.findById(productOfferFlashDescriptionId).orElse(null);

        if(entity == null)
            return null;

        productOfferFlashDescriptionRepositoryJPA.delete(entity);

        return entity;
    }
}
