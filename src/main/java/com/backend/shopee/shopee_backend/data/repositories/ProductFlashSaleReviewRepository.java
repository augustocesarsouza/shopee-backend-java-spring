package com.backend.shopee.shopee_backend.data.repositories;

import com.backend.shopee.shopee_backend.application.dto.ProductFlashSaleReviewDTO;
import com.backend.shopee.shopee_backend.data.context.ProductFlashSaleReviewRepositoryJPA;
import com.backend.shopee.shopee_backend.domain.entities.ProductFlashSaleReview;
import com.backend.shopee.shopee_backend.domain.repositories.IProductFlashSaleReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ProductFlashSaleReviewRepository implements IProductFlashSaleReviewRepository {
    private final ProductFlashSaleReviewRepositoryJPA productFlashSaleReviewRepositoryJPA;

    @Autowired
    public ProductFlashSaleReviewRepository(ProductFlashSaleReviewRepositoryJPA productFlashSaleReviewRepositoryJPA) {
        this.productFlashSaleReviewRepositoryJPA = productFlashSaleReviewRepositoryJPA;
    }

    @Override
    public ProductFlashSaleReviewDTO GetByProductFlashSaleReviewIdToDelete(UUID productFlashSaleReviewId) {
        return productFlashSaleReviewRepositoryJPA.GetByProductFlashSaleReviewIdToDelete(productFlashSaleReviewId);
    }

    @Override
    public List<ProductFlashSaleReviewDTO> GetAllProductFlashSaleReviewsByProductsOfferFlashId(UUID productsOfferFlashId) {
        return productFlashSaleReviewRepositoryJPA.GetAllProductFlashSaleReviewsByProductsOfferFlashId(productsOfferFlashId);
    }

    @Override
    public ProductFlashSaleReview create(ProductFlashSaleReview productFlashSaleReview) {
        if(productFlashSaleReview == null)
            return null;

        return productFlashSaleReviewRepositoryJPA.save(productFlashSaleReview);
    }

    @Override
    public ProductFlashSaleReview update(ProductFlashSaleReview productFlashSaleReview) {
        return null;
    }

    @Override
    public ProductFlashSaleReview delete(UUID productFlashSaleReviewId) {
        if(productFlashSaleReviewId == null)
            return null;

        var entity = productFlashSaleReviewRepositoryJPA.findById(productFlashSaleReviewId).orElse(null);

        if(entity == null)
            return null;

        productFlashSaleReviewRepositoryJPA.delete(entity);

        return entity;
    }
}
