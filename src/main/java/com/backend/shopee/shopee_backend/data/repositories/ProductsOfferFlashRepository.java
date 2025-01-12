package com.backend.shopee.shopee_backend.data.repositories;

import com.backend.shopee.shopee_backend.application.dto.ProductsOfferFlashDTO;
import com.backend.shopee.shopee_backend.data.context.ProductsOfferFlashJPA;
import com.backend.shopee.shopee_backend.domain.entities.ProductsOfferFlash;
import com.backend.shopee.shopee_backend.domain.repositories.IProductsOfferFlashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ProductsOfferFlashRepository implements IProductsOfferFlashRepository {
    private final ProductsOfferFlashJPA productsOfferFlashJPA;

    @Autowired
    public ProductsOfferFlashRepository(ProductsOfferFlashJPA productsOfferFlashJPA) {
        this.productsOfferFlashJPA = productsOfferFlashJPA;
    }

    @Override
    public ProductsOfferFlashDTO GetProductsOfferFlashById(String productsOfferFlashId) {
        return productsOfferFlashJPA.GetProductsOfferFlashById(UUID.fromString(productsOfferFlashId));
    }

    @Override
    public List<ProductsOfferFlashDTO> GetAllProduct() {
        return productsOfferFlashJPA.GetAllProduct();
    }

    @Override
    public List<ProductsOfferFlashDTO> GetAllByTagProduct(String hourFlashOffer, String tagProduct, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return productsOfferFlashJPA.GetAllByTagProduct(hourFlashOffer, tagProduct, pageable);
    }

    @Override
    public ProductsOfferFlash create(ProductsOfferFlash productsOfferFlash) {
        if(productsOfferFlash == null)
            return null;

        return productsOfferFlashJPA.save(productsOfferFlash);
    }

    @Override
    public ProductsOfferFlash update(ProductsOfferFlash productsOfferFlash) {
        return null;
    }

    @Override
    public ProductsOfferFlash delete(UUID productsOfferFlashId) {
        if(productsOfferFlashId == null)
            return null;

        var entity = productsOfferFlashJPA.findById(productsOfferFlashId).orElse(null);

        if(entity == null)
            return null;

        productsOfferFlashJPA.delete(entity);

        return entity;
    }
}
