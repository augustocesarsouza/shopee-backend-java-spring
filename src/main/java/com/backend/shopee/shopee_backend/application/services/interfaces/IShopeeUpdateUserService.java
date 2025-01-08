package com.backend.shopee.shopee_backend.application.services.interfaces;

import com.backend.shopee.shopee_backend.application.dto.ShopeeUpdateUserDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ShopeeUpdateUserValidationDTOs.PromotionUpdateUserCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

public interface IShopeeUpdateUserService {
    ResultService<List<ShopeeUpdateUserDTO>> GetByUserIdAll(UUID userId);
    ResultService<ShopeeUpdateUserDTO> CreateAsync(PromotionUpdateUserCreateDTOValidator promotionUpdateUserCreateDTOValidator, BindingResult result);
    ResultService<ShopeeUpdateUserDTO> Delete(UUID shopeeUpdateUserId);
}
