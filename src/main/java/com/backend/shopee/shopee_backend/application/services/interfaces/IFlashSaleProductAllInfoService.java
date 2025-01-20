package com.backend.shopee.shopee_backend.application.services.interfaces;

import com.backend.shopee.shopee_backend.application.dto.FlashSaleProductAllInfoDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.FlashSaleProductAllInfoValidationDTO.FlashSaleProductAllInfoCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import com.backend.shopee.shopee_backend.domain.entities.FlashSaleProductAllInfo;
import org.springframework.validation.BindingResult;

import java.util.UUID;

public interface IFlashSaleProductAllInfoService {
    ResultService<FlashSaleProductAllInfoDTO> GetFlashSaleProductByProductFlashSaleId(UUID productFlashSaleId);
    ResultService<FlashSaleProductAllInfoDTO> CreateAsync(FlashSaleProductAllInfoCreateDTOValidator flashSaleProductAllInfoCreateDTOValidator,
                                                          BindingResult result);
    ResultService<FlashSaleProductAllInfoDTO> Delete(UUID flashSaleProductAllInfoId);
}
