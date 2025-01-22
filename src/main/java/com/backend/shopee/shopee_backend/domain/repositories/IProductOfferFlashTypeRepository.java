package com.backend.shopee.shopee_backend.domain.repositories;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashTypeDTO;
import com.backend.shopee.shopee_backend.domain.entities.ProductOfferFlashType;

import java.util.List;
import java.util.UUID;

public interface IProductOfferFlashTypeRepository {
    List<ProductOfferFlashTypeDTO> GetAllProductOfferFlashTypeByProductsOfferFlashId(UUID productsOfferFlashId);
    ProductOfferFlashType create(ProductOfferFlashType productOfferFlashType);
    ProductOfferFlashType update(ProductOfferFlashType productOfferFlashType);
    ProductOfferFlashType delete(UUID productOfferFlashTypeId);
}
