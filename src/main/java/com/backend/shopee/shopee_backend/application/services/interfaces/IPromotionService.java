package com.backend.shopee.shopee_backend.application.services.interfaces;

import com.backend.shopee.shopee_backend.application.dto.PromotionDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.PromotionValidationDTOs.PromotionCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import org.springframework.validation.BindingResult;

import java.util.UUID;

public interface IPromotionService {
    ResultService<PromotionDTO> GetById(UUID promotionId);
    ResultService<PromotionDTO> CreateAsync(PromotionCreateDTOValidator promotionCreateDTOValidator, BindingResult result);
    ResultService<PromotionDTO> Delete(UUID promotionId);
}
