package com.backend.shopee.shopee_backend.domain.repositories;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashDetailsDTO;
import com.backend.shopee.shopee_backend.domain.entities.ProductOfferFlashDetails;

import java.util.UUID;

public interface IProductOfferFlashDetailsRepository {
    ProductOfferFlashDetailsDTO GetByProductsOfferFlashId(UUID productsOfferFlashId);
    ProductOfferFlashDetailsDTO GetByProductOfferFlashDetailsId(UUID productOfferFlashDetailsId);
    ProductOfferFlashDetails create(ProductOfferFlashDetails productOfferFlashDetails);
    ProductOfferFlashDetails update(ProductOfferFlashDetails productOfferFlashDetails);
    ProductOfferFlashDetails delete(UUID productOfferFlashDetailsId);
}
