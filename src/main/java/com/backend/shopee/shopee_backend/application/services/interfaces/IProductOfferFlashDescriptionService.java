package com.backend.shopee.shopee_backend.application.services.interfaces;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashDescriptionDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductOfferFlashDescriptionValidationDTOs.ProductOfferFlashDescriptionCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import org.springframework.validation.BindingResult;

import java.util.UUID;

public interface IProductOfferFlashDescriptionService {
    ResultService<ProductOfferFlashDescriptionDTO> GetByProductsOfferFlashId(UUID productsOfferFlashId);
    ResultService<ProductOfferFlashDescriptionDTO> CreateAsync(ProductOfferFlashDescriptionCreateDTOValidator productOfferFlashDescriptionCreateDTOValidator,
                                                               BindingResult result);
    ResultService<ProductOfferFlashDescriptionDTO> Delete(UUID productOfferFlashDescriptionId);
}
