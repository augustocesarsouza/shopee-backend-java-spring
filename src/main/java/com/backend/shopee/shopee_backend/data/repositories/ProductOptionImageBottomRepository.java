package com.backend.shopee.shopee_backend.data.repositories;

import com.backend.shopee.shopee_backend.application.dto.ProductOptionImageBottomDTO;
import com.backend.shopee.shopee_backend.data.context.ProductOptionImageBottomRepositoryJPA;
import com.backend.shopee.shopee_backend.domain.entities.ProductOptionImageBottom;
import com.backend.shopee.shopee_backend.domain.repositories.IProductOptionImageBottomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ProductOptionImageBottomRepository implements IProductOptionImageBottomRepository {
    private final ProductOptionImageBottomRepositoryJPA productOptionImageBottomRepositoryJPA;

    @Autowired
    public ProductOptionImageBottomRepository(ProductOptionImageBottomRepositoryJPA productOptionImageBottomRepositoryJPA) {
        this.productOptionImageBottomRepositoryJPA = productOptionImageBottomRepositoryJPA;
    }

    @Override
    public ProductOptionImageBottomDTO GetByListFlashSaleProductImageAllId(UUID productsOfferFlashId) {
        return productOptionImageBottomRepositoryJPA.GetByListFlashSaleProductImageAllId(productsOfferFlashId);
    }

    @Override
    public List<ProductOptionImageBottomDTO> GetAllByProductsOfferFlashId(UUID productsOfferFlashId) {
        return productOptionImageBottomRepositoryJPA.GetAllByProductsOfferFlashId(productsOfferFlashId);
    }

    @Override
    public ProductOptionImageBottom create(ProductOptionImageBottom productOptionImageBottom) {
        if(productOptionImageBottom == null)
            return null;

        return productOptionImageBottomRepositoryJPA.save(productOptionImageBottom);
    }

    @Override
    public ProductOptionImageBottom update(ProductOptionImageBottom productOptionImageBottom) {
        return null;
    }

    @Override
    public ProductOptionImageBottom delete(UUID productOptionImageBottomId) {
        if(productOptionImageBottomId == null)
            return null;

        var productOptionImageBottom = productOptionImageBottomRepositoryJPA.findById(productOptionImageBottomId).orElse(null);

        if(productOptionImageBottom == null)
            return null;

        productOptionImageBottomRepositoryJPA.delete(productOptionImageBottom);

        return productOptionImageBottom;
    }
}
