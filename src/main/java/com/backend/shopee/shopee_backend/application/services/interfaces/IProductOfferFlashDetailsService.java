package com.backend.shopee.shopee_backend.application.services.interfaces;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashDetailsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductOfferFlashDetailsValidationDTOs.ProductOfferFlashDetailsCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import org.springframework.validation.BindingResult;

import java.util.UUID;

public interface IProductOfferFlashDetailsService {
    ResultService<ProductOfferFlashDetailsDTO> GetByProductsOfferFlashId(UUID productsOfferFlashId);
    ResultService<ProductOfferFlashDetailsDTO> CreateAsync(ProductOfferFlashDetailsCreateDTOValidator productOfferFlashDetailsCreateDTOValidator, BindingResult result);
    ResultService<ProductOfferFlashDetailsDTO> Delete(UUID productOfferFlashDetailsId);
}
