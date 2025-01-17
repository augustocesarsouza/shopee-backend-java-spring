package com.backend.shopee.shopee_backend.application.services.interfaces;

import com.backend.shopee.shopee_backend.application.dto.ProductDiscoveriesOfDayDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductDiscoveriesOfDayValidationDTOs.ProductDiscoveriesOfDayCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

public interface IProductDiscoveriesOfDayService {
    ResultService<ProductDiscoveriesOfDayDTO> GetProductDiscoveriesOfDayById(UUID productDiscoveriesOfDayId);
    ResultService<List<ProductDiscoveriesOfDayDTO>> GetAllProductDiscoveriesOfDays();
    ResultService<ProductDiscoveriesOfDayDTO> CreateAsync(ProductDiscoveriesOfDayCreateDTOValidator productDiscoveriesOfDayCreateDTOValidator, BindingResult result);
    ResultService<ProductDiscoveriesOfDayDTO> Delete(UUID productDiscoveriesOfDayId);
}
