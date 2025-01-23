package com.backend.shopee.shopee_backend.domain.repositories;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashSellerDTO;
import com.backend.shopee.shopee_backend.domain.entities.ProductOfferFlashSeller;

import java.util.UUID;

public interface IProductOfferFlashSellerRepository {
    ProductOfferFlashSellerDTO GetByProductsOfferFlashId(UUID productsOfferFlashId);
    ProductOfferFlashSeller create(ProductOfferFlashSeller productOfferFlashSeller);
    ProductOfferFlashSeller update(ProductOfferFlashSeller productOfferFlashSeller);
    ProductOfferFlashSeller delete(UUID productOfferFlashSellerId);
}
