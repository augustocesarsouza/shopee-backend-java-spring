package com.backend.shopee.shopee_backend.application.services.interfaces;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashTypeDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductOfferFlashTypeValidationDTOs.ProductOfferFlashTypeCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

public interface IProductOfferFlashTypeService {
    ResultService<List<ProductOfferFlashTypeDTO>> GetAllProductOfferFlashTypeByProductsOfferFlashId(UUID productsOfferFlashId);
    ResultService<ProductOfferFlashTypeDTO> CreateAsync(ProductOfferFlashTypeCreateDTOValidator productOfferFlashTypeCreateDTOValidator,
                                                        BindingResult result);
    ResultService<ProductOfferFlashTypeDTO> Delete(UUID productOfferFlashTypeId);
}
