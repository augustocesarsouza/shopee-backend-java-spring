package com.backend.shopee.shopee_backend.application.services;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashDescriptionDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductOfferFlashDescriptionValidationDTOs.ProductOfferFlashDescriptionCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.interfaces.IProductOfferFlashDescriptionService;
import com.backend.shopee.shopee_backend.domain.entities.ProductOfferFlashDescription;
import com.backend.shopee.shopee_backend.domain.repositories.IProductOfferFlashDescriptionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.UUID;

@Service
public class ProductOfferFlashDescriptionService implements IProductOfferFlashDescriptionService {
    private final IProductOfferFlashDescriptionRepository productOfferFlashDescriptionRepository;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductOfferFlashDescriptionService(IProductOfferFlashDescriptionRepository productOfferFlashDescriptionRepository,
                                               IValidateErrorsDTO validateErrorsDTO, ModelMapper modelMapper) {
        this.productOfferFlashDescriptionRepository = productOfferFlashDescriptionRepository;
        this.validateErrorsDTO = validateErrorsDTO;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResultService<ProductOfferFlashDescriptionDTO> GetByProductsOfferFlashId(UUID productsOfferFlashId) {
        try {
            var entityDTO = productOfferFlashDescriptionRepository.GetByProductsOfferFlashId(productsOfferFlashId);

            if(entityDTO == null)
                return ResultService.Fail("not found ProductOfferFlashDescription");

            return ResultService.Ok(entityDTO);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<ProductOfferFlashDescriptionDTO> CreateAsync(
            ProductOfferFlashDescriptionCreateDTOValidator productOfferFlashDescriptionCreateDTOValidator, BindingResult result) {
        if(productOfferFlashDescriptionCreateDTOValidator == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try {
            UUID entityId = UUID.randomUUID();
            UUID productsOfferFlashId = UUID.fromString(productOfferFlashDescriptionCreateDTOValidator.getProductsOfferFlashId());

            var entity = new ProductOfferFlashDescription(entityId, productsOfferFlashId, productOfferFlashDescriptionCreateDTOValidator.getDescriptions());

            var createEntity = productOfferFlashDescriptionRepository.create(entity);

            var entityDTOCreateMap = modelMapper.map(createEntity, ProductOfferFlashDescriptionDTO.class);

            return ResultService.Ok(entityDTOCreateMap);

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<ProductOfferFlashDescriptionDTO> Delete(UUID productOfferFlashDescriptionId) {
        try {
            var entityDelete = productOfferFlashDescriptionRepository.GetByProductsOfferFlashIdIfExist(productOfferFlashDescriptionId);

            if(entityDelete == null)
                return ResultService.Fail("entity not found");

            var entityDeleteSuccessfully = productOfferFlashDescriptionRepository.delete(entityDelete.getId());

            var entityMap = modelMapper.map(entityDeleteSuccessfully, ProductOfferFlashDescriptionDTO.class);

            return ResultService.Ok(entityMap);

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }
}
