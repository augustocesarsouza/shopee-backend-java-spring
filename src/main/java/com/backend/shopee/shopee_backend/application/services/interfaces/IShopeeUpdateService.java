package com.backend.shopee.shopee_backend.application.services.interfaces;

import com.backend.shopee.shopee_backend.application.dto.ShopeeUpdateDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ShopeeUpdateValidationDTOs.ShopeeUpdateCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import org.springframework.validation.BindingResult;

import java.util.UUID;

public interface IShopeeUpdateService {
    ResultService<ShopeeUpdateDTO> GetById(UUID shopeeUpdateId);
    ResultService<ShopeeUpdateDTO> CreateAsync(ShopeeUpdateCreateDTOValidator shopeeUpdateCreateDTOValidator, BindingResult result);
    ResultService<ShopeeUpdateDTO> Delete(UUID shopeeUpdateId);
}
