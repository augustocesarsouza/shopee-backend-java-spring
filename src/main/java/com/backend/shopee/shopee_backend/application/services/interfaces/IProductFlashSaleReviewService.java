package com.backend.shopee.shopee_backend.application.services.interfaces;

import com.backend.shopee.shopee_backend.application.dto.ProductFlashSaleReviewDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductFlashSaleReviewValidationDTOs.ProductFlashSaleReviewCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

public interface IProductFlashSaleReviewService {
    ResultService<List<ProductFlashSaleReviewDTO>> GetAllProductFlashSaleReviewsByProductsOfferFlashId(UUID productsOfferFlashId);
    ResultService<ProductFlashSaleReviewDTO> CreateAsync(ProductFlashSaleReviewCreateDTOValidator productFlashSaleReviewCreateDTOValidator,
                                                         BindingResult result);
    ResultService<ProductFlashSaleReviewDTO> Delete(UUID productFlashSaleReviewId);
}
