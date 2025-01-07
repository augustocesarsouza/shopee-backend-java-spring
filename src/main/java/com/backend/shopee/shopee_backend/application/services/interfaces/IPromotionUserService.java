package com.backend.shopee.shopee_backend.application.services.interfaces;

import com.backend.shopee.shopee_backend.application.dto.PromotionUserDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.PromotionUserValidationDTOs.PromotionUserCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

public interface IPromotionUserService {
    ResultService<List<PromotionUserDTO>> GetByUserIdAll(UUID userId);
    ResultService<PromotionUserDTO> CreateAsync(PromotionUserCreateDTOValidator promotionUserCreateDTOValidator, BindingResult result);
    ResultService<PromotionUserDTO> Delete(UUID promotionUserId);
}
