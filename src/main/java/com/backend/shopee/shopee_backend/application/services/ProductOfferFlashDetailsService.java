package com.backend.shopee.shopee_backend.application.services;

import com.backend.shopee.shopee_backend.application.dto.CuponDTO;
import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashDetailsDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductOfferFlashDetailsValidationDTOs.ProductOfferFlashDetailsCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.interfaces.IProductOfferFlashDetailsService;
import com.backend.shopee.shopee_backend.domain.entities.ProductOfferFlashDetails;
import com.backend.shopee.shopee_backend.domain.repositories.IProductOfferFlashDetailsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.UUID;

@Service
public class ProductOfferFlashDetailsService implements IProductOfferFlashDetailsService {
    private final IProductOfferFlashDetailsRepository productOfferFlashDetailsRepository;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductOfferFlashDetailsService(IProductOfferFlashDetailsRepository productOfferFlashDetailsRepository,
                                           IValidateErrorsDTO validateErrorsDTO, ModelMapper modelMapper) {
        this.productOfferFlashDetailsRepository = productOfferFlashDetailsRepository;
        this.validateErrorsDTO = validateErrorsDTO;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResultService<ProductOfferFlashDetailsDTO> GetByProductsOfferFlashId(UUID productsOfferFlashId) {
        try {
            var entityDTO = productOfferFlashDetailsRepository.GetByProductsOfferFlashId(productsOfferFlashId);

            if(entityDTO == null)
                return ResultService.Fail("not found productOfferFlashDetails");

            return ResultService.Ok(entityDTO);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<ProductOfferFlashDetailsDTO> CreateAsync(ProductOfferFlashDetailsCreateDTOValidator productOfferFlashDetailsCreateDTOValidator,
                                                                  BindingResult result) {
        if(productOfferFlashDetailsCreateDTOValidator == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try {

            UUID entityId = UUID.randomUUID();
            String detailsJson = productOfferFlashDetailsCreateDTOValidator.getDetailsAsJson();

            var entity = new ProductOfferFlashDetails(entityId, UUID.fromString(productOfferFlashDetailsCreateDTOValidator.getProductsOfferFlashId()),
                    detailsJson);
//            ProductOfferFlashDetails(UUID id, UUID productsOfferFlashId, String details)

            var entityCreated = productOfferFlashDetailsRepository.create(entity);

            var entityMap = modelMapper.map(entityCreated, ProductOfferFlashDetailsDTO.class);

            return ResultService.Ok(entityMap);

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<ProductOfferFlashDetailsDTO> Delete(UUID productOfferFlashDetailsId) {
        try {
            var entity = productOfferFlashDetailsRepository.GetByProductOfferFlashDetailsId(productOfferFlashDetailsId);

            if(entity == null)
                return ResultService.Fail("entity not found");

            var entityDeleteSuccessfully = productOfferFlashDetailsRepository.delete(entity.getId());

            var entityDeleteMap = modelMapper.map(entityDeleteSuccessfully, ProductOfferFlashDetailsDTO.class);

            return ResultService.Ok(entityDeleteMap);

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }
}
