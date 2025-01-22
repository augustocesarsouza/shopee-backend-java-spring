package com.backend.shopee.shopee_backend.application.services;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashTypeDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductOfferFlashTypeValidationDTOs.ProductOfferFlashTypeCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.interfaces.IProductOfferFlashTypeService;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryCreate;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.shopee.shopee_backend.domain.entities.ProductOfferFlashType;
import com.backend.shopee.shopee_backend.domain.repositories.IProductOfferFlashTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

@Service
public class ProductOfferFlashTypeService implements IProductOfferFlashTypeService {
    private final IProductOfferFlashTypeRepository productOfferFlashTypeRepository;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final ModelMapper modelMapper;
    private final ICloudinaryUti cloudinaryUti;

    @Autowired
    public ProductOfferFlashTypeService(IProductOfferFlashTypeRepository productOfferFlashTypeRepository, IValidateErrorsDTO validateErrorsDTO,
                                        ModelMapper modelMapper, ICloudinaryUti cloudinaryUti) {
        this.productOfferFlashTypeRepository = productOfferFlashTypeRepository;
        this.validateErrorsDTO = validateErrorsDTO;
        this.modelMapper = modelMapper;
        this.cloudinaryUti = cloudinaryUti;
    }


    @Override
    public ResultService<List<ProductOfferFlashTypeDTO>> GetAllProductOfferFlashTypeByProductsOfferFlashId(UUID productsOfferFlashId) {
        try {
            var productsOfferFlashDTO = productOfferFlashTypeRepository.GetAllProductOfferFlashTypeByProductsOfferFlashId(productsOfferFlashId);

            if(productsOfferFlashDTO == null)
                return ResultService.Fail("not found productsOfferFlash list");

            return ResultService.Ok(productsOfferFlashDTO);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<ProductOfferFlashTypeDTO> CreateAsync(ProductOfferFlashTypeCreateDTOValidator productOfferFlashTypeCreateDTOValidator,
                                                               BindingResult result) {
        if(productOfferFlashTypeCreateDTOValidator == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try {
            var resultCreate = new CloudinaryCreate();

            resultCreate = cloudinaryUti.CreateMedia(productOfferFlashTypeCreateDTOValidator.getImgProduct(),
                    "product-offer-flash-type-java", 450, 450);

            if (resultCreate.getImgUrl() == null || resultCreate.getPublicId() == null)
                return ResultService.Fail("error when create Img product offer flash type");

            UUID productOfferFlashTypeId = UUID.randomUUID();
            var productOfferFlashType = new ProductOfferFlashType(productOfferFlashTypeId, resultCreate.getImgUrl(),
                    productOfferFlashTypeCreateDTOValidator.getOptionType(),
                    UUID.fromString(productOfferFlashTypeCreateDTOValidator.getProductsOfferFlashId()),
                    productOfferFlashTypeCreateDTOValidator.getTitleImg());

            var createProductOfferFlashType = productOfferFlashTypeRepository.create(productOfferFlashType);

            var productOfferFlashTypeDTOCreateMap = modelMapper.map(createProductOfferFlashType, ProductOfferFlashTypeDTO.class);

            return ResultService.Ok(productOfferFlashTypeDTOCreateMap);

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<ProductOfferFlashTypeDTO> Delete(UUID productOfferFlashTypeId) {
        return null;
    }
}
