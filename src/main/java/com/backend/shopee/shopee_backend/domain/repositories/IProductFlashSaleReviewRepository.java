package com.backend.shopee.shopee_backend.domain.repositories;

import com.backend.shopee.shopee_backend.application.dto.ProductFlashSaleReviewDTO;
import com.backend.shopee.shopee_backend.domain.entities.ProductFlashSaleReview;

import java.util.List;
import java.util.UUID;

public interface IProductFlashSaleReviewRepository {
    ProductFlashSaleReviewDTO GetByProductFlashSaleReviewIdToDelete(UUID productFlashSaleReviewId);
    List<ProductFlashSaleReviewDTO> GetAllProductFlashSaleReviewsByProductsOfferFlashId(UUID productsOfferFlashId);
    ProductFlashSaleReview create(ProductFlashSaleReview productFlashSaleReview);
    ProductFlashSaleReview update(ProductFlashSaleReview productFlashSaleReview);
    ProductFlashSaleReview delete(UUID productFlashSaleReviewId);
}
