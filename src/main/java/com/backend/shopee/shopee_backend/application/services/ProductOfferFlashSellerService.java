package com.backend.shopee.shopee_backend.application.services;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashSellerDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductOfferFlashSellerValidationDTOs.ProductOfferFlashSellerCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.interfaces.IProductOfferFlashSellerService;
import com.backend.shopee.shopee_backend.domain.entities.ProductOfferFlashSeller;
import com.backend.shopee.shopee_backend.domain.repositories.IProductOfferFlashSellerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.UUID;

@Service
public class ProductOfferFlashSellerService implements IProductOfferFlashSellerService {
    private final IProductOfferFlashSellerRepository productSellerRepository;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductOfferFlashSellerService(IProductOfferFlashSellerRepository productSellerRepository,
                                          IValidateErrorsDTO validateErrorsDTO, ModelMapper modelMapper) {
        this.productSellerRepository = productSellerRepository;
        this.validateErrorsDTO = validateErrorsDTO;
        this.modelMapper = modelMapper;
    }


    @Override
    public ResultService<ProductOfferFlashSellerDTO> GetByProductsOfferFlashId(UUID productsOfferFlashId) {
        try {
            var productOfferFlashSellerDTO = productSellerRepository.GetByProductsOfferFlashId(productsOfferFlashId);

            if(productOfferFlashSellerDTO == null){
                return ResultService.Fail("not found entity");
            }

            return ResultService.Ok(productOfferFlashSellerDTO);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<ProductOfferFlashSellerDTO> CreateAsync(ProductOfferFlashSellerCreateDTOValidator productOfferFlashSellerCreateDTOValidator,
                                                                 BindingResult result) {
        if(productOfferFlashSellerCreateDTOValidator == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try {
            UUID entityId = UUID.randomUUID();
            var entity = new ProductOfferFlashSeller(entityId, UUID.fromString(productOfferFlashSellerCreateDTOValidator.getUserSellerProductId()),
                    null, UUID.fromString(productOfferFlashSellerCreateDTOValidator.getProductsOfferFlashId()));

            var createEntity = productSellerRepository.create(entity);

            var entityDTOCreateMap = modelMapper.map(createEntity, ProductOfferFlashSellerDTO.class);

            return ResultService.Ok(entityDTOCreateMap);

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<ProductOfferFlashSellerDTO> Delete(UUID productOfferFlashSellerId) {
        return null;
    }
}
