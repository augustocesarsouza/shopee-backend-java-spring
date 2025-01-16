package com.backend.shopee.shopee_backend.application.services.interfaces;

import com.backend.shopee.shopee_backend.application.dto.ProductHighlightDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductHighlightValidatorDTOs.ProductHighlightDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

public interface IProductHighlightService {
    ResultService<ProductHighlightDTO> GetProductHighlightById(UUID productHighlightId);
    ResultService<List<ProductHighlightDTO>> GetAllProductHighlights();
    ResultService<ProductHighlightDTO> CreateAsync(ProductHighlightDTOValidator productHighlightDTOValidator, BindingResult result);
    ResultService<ProductHighlightDTO> Delete(UUID productHighlightId);
}
