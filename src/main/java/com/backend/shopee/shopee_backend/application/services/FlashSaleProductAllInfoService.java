package com.backend.shopee.shopee_backend.application.services;

import com.backend.shopee.shopee_backend.application.dto.FlashSaleProductAllInfoDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.FlashSaleProductAllInfoValidationDTO.FlashSaleProductAllInfoCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.interfaces.IFlashSaleProductAllInfoService;
import com.backend.shopee.shopee_backend.application.services.interfaces.IProductsOfferFlashService;
import com.backend.shopee.shopee_backend.domain.entities.FlashSaleProductAllInfo;
import com.backend.shopee.shopee_backend.domain.repositories.IFlashSaleProductAllInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.UUID;

@Service
public class FlashSaleProductAllInfoService implements IFlashSaleProductAllInfoService {
    private final IFlashSaleProductAllInfoRepository flashSaleProductAllInfoRepository;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final ModelMapper modelMapper;
    private final IProductsOfferFlashService productsOfferFlashService;

    @Autowired
    public FlashSaleProductAllInfoService(IFlashSaleProductAllInfoRepository flashSaleProductAllInfoRepository,
                                          IValidateErrorsDTO validateErrorsDTO, ModelMapper modelMapper,
                                          IProductsOfferFlashService productsOfferFlashService) {
        this.flashSaleProductAllInfoRepository = flashSaleProductAllInfoRepository;
        this.validateErrorsDTO = validateErrorsDTO;
        this.modelMapper = modelMapper;
        this.productsOfferFlashService = productsOfferFlashService;
    }

    @Override
    public ResultService<FlashSaleProductAllInfoDTO> GetFlashSaleProductByProductFlashSaleId(UUID productFlashSaleId) {
        try {
            var flashSaleProductAllInfoDTO = flashSaleProductAllInfoRepository.GetFlashSaleProductByProductFlashSaleId(productFlashSaleId);

            if(flashSaleProductAllInfoDTO == null){
                return ResultService.Fail("not found");
            }

            return ResultService.Ok(flashSaleProductAllInfoDTO);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<FlashSaleProductAllInfoDTO> CreateAsync(FlashSaleProductAllInfoCreateDTOValidator flashSaleProductAllInfoCreateDTOValidator,
                                                                 BindingResult result) {
        if(flashSaleProductAllInfoCreateDTOValidator == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try  {
            UUID flashSaleProductAllInfoId = UUID.randomUUID();
            UUID productsOfferFlashId = UUID.fromString(flashSaleProductAllInfoCreateDTOValidator.getProductsOfferFlashId());

            var verifyWhetherExistProductsOfferFlash = productsOfferFlashService.GetByProductsOfferFlashId(productsOfferFlashId);

            if(!verifyWhetherExistProductsOfferFlash.IsSuccess)
                return ResultService.Fail("Error ProductsOfferFlash Not exist");

            var flashSaleProductAllInfo = new FlashSaleProductAllInfo(flashSaleProductAllInfoId, productsOfferFlashId,
                    null, flashSaleProductAllInfoCreateDTOValidator.getProductReviewsRate(), flashSaleProductAllInfoCreateDTOValidator.getQuantitySold(),
                    flashSaleProductAllInfoCreateDTOValidator.getFavoriteQuantity(), flashSaleProductAllInfoCreateDTOValidator.getQuantityEvaluation(),
                    flashSaleProductAllInfoCreateDTOValidator.getCoins(), flashSaleProductAllInfoCreateDTOValidator.getCreditCard(),
                    flashSaleProductAllInfoCreateDTOValidator.getVoltage(), flashSaleProductAllInfoCreateDTOValidator.getQuantityPiece(),
                    flashSaleProductAllInfoCreateDTOValidator.getSize(), flashSaleProductAllInfoCreateDTOValidator.getProductHaveInsurance());

            var createFlashSaleProductAllInfo = flashSaleProductAllInfoRepository.create(flashSaleProductAllInfo);

            var flashSaleProductAllInfoMap = modelMapper.map(createFlashSaleProductAllInfo, FlashSaleProductAllInfoDTO.class);

            return ResultService.Ok(flashSaleProductAllInfoMap);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<FlashSaleProductAllInfoDTO> Delete(UUID flashSaleProductAllInfoId) {
        return null;
    }
}
