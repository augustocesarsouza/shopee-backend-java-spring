package com.backend.shopee.shopee_backend.application.services.interfaces;

import com.backend.shopee.shopee_backend.application.dto.UserCuponDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.UserCuponValidationDTOs.UserCuponCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

public interface IUserCuponService {
    ResultService<List<UserCuponDTO>> GetAllCuponByUserId(UUID userCuponId);
    ResultService<UserCuponDTO> CreateAsync(UserCuponCreateDTOValidator userCuponCreateDTOValidator, BindingResult result);
    ResultService<UserCuponDTO> Delete(UUID userCuponId);
}
