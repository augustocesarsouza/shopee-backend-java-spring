package com.backend.shopee.shopee_backend.domain.repositories;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashDescriptionDTO;
import com.backend.shopee.shopee_backend.domain.entities.ProductOfferFlashDescription;

import java.util.UUID;

public interface IProductOfferFlashDescriptionRepository {
    ProductOfferFlashDescriptionDTO GetByProductsOfferFlashId(UUID productsOfferFlashId);
    ProductOfferFlashDescriptionDTO GetByProductsOfferFlashIdIfExist(UUID productOfferFlashDescriptionId);
    ProductOfferFlashDescription create(ProductOfferFlashDescription productOfferFlashDescription);
    ProductOfferFlashDescription update(ProductOfferFlashDescription productOfferFlashDescription);
    ProductOfferFlashDescription delete(UUID productOfferFlashDescriptionId);
}
