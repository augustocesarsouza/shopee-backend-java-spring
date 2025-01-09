package com.backend.shopee.shopee_backend.application.services.interfaces;

import com.backend.shopee.shopee_backend.application.dto.CuponDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.CuponValidationDTOs.CuponCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import org.springframework.validation.BindingResult;

import java.util.UUID;

public interface ICuponService {
    ResultService<CuponDTO> GetCuponById(UUID cuponId);
    ResultService<CuponDTO> CreateAsync(CuponCreateDTOValidator cuponCreateDTOValidator, BindingResult result);
    ResultService<CuponDTO> Delete(UUID cuponId);
}
