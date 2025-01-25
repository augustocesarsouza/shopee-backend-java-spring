package com.backend.shopee.shopee_backend.data.repositories;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashDetailsDTO;
import com.backend.shopee.shopee_backend.data.context.ProductOfferFlashDetailsRepositoryJPA;
import com.backend.shopee.shopee_backend.domain.entities.ProductOfferFlashDetails;
import com.backend.shopee.shopee_backend.domain.repositories.IProductOfferFlashDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductOfferFlashDetailsRepository implements IProductOfferFlashDetailsRepository {
    private final ProductOfferFlashDetailsRepositoryJPA productOfferFlashDetailsRepositoryJPA;

    @Autowired
    public ProductOfferFlashDetailsRepository(ProductOfferFlashDetailsRepositoryJPA productOfferFlashDetailsRepositoryJPA) {
        this.productOfferFlashDetailsRepositoryJPA = productOfferFlashDetailsRepositoryJPA;
    }

    @Override
    public ProductOfferFlashDetailsDTO GetByProductsOfferFlashId(UUID productsOfferFlashId) {
        return productOfferFlashDetailsRepositoryJPA.GetByProductsOfferFlashId(productsOfferFlashId);
    }

    @Override
    public ProductOfferFlashDetailsDTO GetByProductOfferFlashDetailsId(UUID productOfferFlashDetailsId) {
        return productOfferFlashDetailsRepositoryJPA.GetByProductOfferFlashDetailsId(productOfferFlashDetailsId);
    }

    @Override
    public ProductOfferFlashDetails create(ProductOfferFlashDetails productOfferFlashDetails) {
        if(productOfferFlashDetails == null)
            return null;

        return productOfferFlashDetailsRepositoryJPA.save(productOfferFlashDetails);
    }

    @Override
    public ProductOfferFlashDetails update(ProductOfferFlashDetails productOfferFlashDetails) {
        return null;
    }

    @Override
    public ProductOfferFlashDetails delete(UUID productOfferFlashDetailsId) {
        if(productOfferFlashDetailsId == null)
            return null;

        var entity = productOfferFlashDetailsRepositoryJPA.findById(productOfferFlashDetailsId).orElse(null);

        if(entity == null)
            return null;

        productOfferFlashDetailsRepositoryJPA.delete(entity);

        return entity;
    }
}
