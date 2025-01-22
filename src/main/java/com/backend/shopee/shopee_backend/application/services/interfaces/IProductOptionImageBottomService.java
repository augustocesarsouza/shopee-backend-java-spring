package com.backend.shopee.shopee_backend.application.services.interfaces;

import com.backend.shopee.shopee_backend.application.dto.ProductOptionImageBottomDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductOptionImageBottomValidationDTOs.ProductOptionImageBottomCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

public interface IProductOptionImageBottomService {
    ResultService<ProductOptionImageBottomDTO> GetByListFlashSaleProductImageAllId(UUID productsOfferFlashId);
    ResultService<ProductOptionImageBottomDTO> CreateAsync(ProductOptionImageBottomCreateDTOValidator productOptionImageBottomCreateDTOValidator, BindingResult result);
    ResultService<ProductOptionImageBottomDTO> Delete(UUID productOptionImageBottomId);
}
