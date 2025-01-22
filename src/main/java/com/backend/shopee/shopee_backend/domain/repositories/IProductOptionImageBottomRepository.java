package com.backend.shopee.shopee_backend.domain.repositories;

import com.backend.shopee.shopee_backend.application.dto.ProductOptionImageBottomDTO;
import com.backend.shopee.shopee_backend.domain.entities.ProductOptionImageBottom;

import java.util.List;
import java.util.UUID;

public interface IProductOptionImageBottomRepository {
    ProductOptionImageBottomDTO GetByListFlashSaleProductImageAllId(UUID productsOfferFlashId);
    List<ProductOptionImageBottomDTO> GetAllByProductsOfferFlashId(UUID productsOfferFlashId);
    ProductOptionImageBottom create(ProductOptionImageBottom productOptionImageBottom);
    ProductOptionImageBottom update(ProductOptionImageBottom productOptionImageBottom);
    ProductOptionImageBottom delete(UUID productOptionImageBottomId);
}
