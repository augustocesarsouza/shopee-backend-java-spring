package com.backend.shopee.shopee_backend.domain.repositories;

import com.backend.shopee.shopee_backend.application.dto.ProductsOfferFlashDTO;
import com.backend.shopee.shopee_backend.domain.entities.ProductsOfferFlash;

import java.util.List;
import java.util.UUID;

public interface IProductsOfferFlashRepository {
    ProductsOfferFlashDTO GetProductsOfferFlashById(String productsOfferFlashId);
    List<ProductsOfferFlashDTO> GetAllProduct();
    List<ProductsOfferFlashDTO> GetAllByTagProduct(String hourFlashOffer, String tagProduct, int pageNumber, int pageSize);
    ProductsOfferFlash create(ProductsOfferFlash productsOfferFlash);
    ProductsOfferFlash update(ProductsOfferFlash productsOfferFlash);
    ProductsOfferFlash delete(UUID productsOfferFlashId);
}
