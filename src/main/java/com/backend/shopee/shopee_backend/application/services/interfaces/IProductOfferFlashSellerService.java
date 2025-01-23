package com.backend.shopee.shopee_backend.application.services.interfaces;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashSellerDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductOfferFlashSellerValidationDTOs.ProductOfferFlashSellerCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import org.springframework.validation.BindingResult;

import java.util.UUID;

public interface IProductOfferFlashSellerService {
    ResultService<ProductOfferFlashSellerDTO> GetByProductsOfferFlashId(UUID productsOfferFlashId);
    ResultService<ProductOfferFlashSellerDTO> CreateAsync(ProductOfferFlashSellerCreateDTOValidator productOfferFlashSellerCreateDTOValidator, BindingResult result);
    ResultService<ProductOfferFlashSellerDTO> Delete(UUID productOfferFlashSellerId);
}
