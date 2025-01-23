package com.backend.shopee.shopee_backend.application.services.interfaces;

import com.backend.shopee.shopee_backend.application.dto.UserSellerProductDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.UserSellerProductValidationDTOs.UserSellerProductCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import org.springframework.validation.BindingResult;

import java.util.UUID;

public interface IUserSellerProductService {
    ResultService<UserSellerProductDTO> GetByUserSellerProductId(UUID userSellerProductId);
    ResultService<UserSellerProductDTO> CreateAsync(UserSellerProductCreateDTOValidator userSellerProductCreateDTOValidator, BindingResult result);
    ResultService<UserSellerProductDTO> Delete(UUID userSellerProductId);
}
