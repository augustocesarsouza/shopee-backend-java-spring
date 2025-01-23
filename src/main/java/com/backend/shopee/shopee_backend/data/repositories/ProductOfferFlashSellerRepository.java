package com.backend.shopee.shopee_backend.data.repositories;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashSellerDTO;
import com.backend.shopee.shopee_backend.data.context.ProductSellerRepositoryJPA;
import com.backend.shopee.shopee_backend.domain.entities.ProductOfferFlashSeller;
import com.backend.shopee.shopee_backend.domain.repositories.IProductOfferFlashSellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductOfferFlashSellerRepository implements IProductOfferFlashSellerRepository {
    private final ProductSellerRepositoryJPA productSellerRepositoryJPA;

    @Autowired
    public ProductOfferFlashSellerRepository(ProductSellerRepositoryJPA productSellerRepositoryJPA) {
        this.productSellerRepositoryJPA = productSellerRepositoryJPA;
    }

    @Override
    public ProductOfferFlashSellerDTO GetByProductsOfferFlashId(UUID productsOfferFlashId) {
        return productSellerRepositoryJPA.GetByProductsOfferFlashId(productsOfferFlashId);
    }

    @Override
    public ProductOfferFlashSeller create(ProductOfferFlashSeller productOfferFlashSeller) {
        if(productOfferFlashSeller == null)
            return null;

        return productSellerRepositoryJPA.save(productOfferFlashSeller);
    }

    @Override
    public ProductOfferFlashSeller update(ProductOfferFlashSeller productOfferFlashSeller) {
        return null;
    }

    @Override
    public ProductOfferFlashSeller delete(UUID productOfferFlashSellerId) {
        if(productOfferFlashSellerId == null)
            return null;

        ProductOfferFlashSeller entity = productSellerRepositoryJPA.findById(productOfferFlashSellerId).orElse(null);

        if(entity == null)
            return null;

        productSellerRepositoryJPA.delete(entity);
//        address.setUser(null);

        return entity;
    }
}
