package com.backend.shopee.shopee_backend.application.services;

import com.backend.shopee.shopee_backend.application.dto.CategoriesDTO;
import com.backend.shopee.shopee_backend.application.dto.ProductHighlightDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductHighlightValidatorDTOs.ProductHighlightDTOValidator;
import com.backend.shopee.shopee_backend.application.services.interfaces.IProductHighlightService;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryCreate;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.shopee.shopee_backend.domain.entities.ProductHighlight;
import com.backend.shopee.shopee_backend.domain.repositories.IProductHighlightRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

@Service
public class ProductHighlightService implements IProductHighlightService {
    private final IProductHighlightRepository productHighlightRepository;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final ModelMapper modelMapper;
    private final ICloudinaryUti cloudinaryUti;

    @Autowired
    public ProductHighlightService(IProductHighlightRepository productHighlightRepository, IValidateErrorsDTO validateErrorsDTO,
                                   ModelMapper modelMapper, ICloudinaryUti cloudinaryUti) {
        this.productHighlightRepository = productHighlightRepository;
        this.validateErrorsDTO = validateErrorsDTO;
        this.modelMapper = modelMapper;
        this.cloudinaryUti = cloudinaryUti;
    }

    @Override
    public ResultService<ProductHighlightDTO> GetProductHighlightById(UUID productHighlightId) {
        try {
            var productHighlightDTO = productHighlightRepository.GetProductHighlightById(productHighlightId);

            if(productHighlightDTO == null)
                return ResultService.Fail("not found productHighlight");

            return ResultService.Ok(productHighlightDTO);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<List<ProductHighlightDTO>> GetAllProductHighlights() {
        try {
            var productHighlightDTOs = productHighlightRepository.GetAllProductHighlight();

            if(productHighlightDTOs == null)
                return ResultService.Fail("not found productHighlight");

            return ResultService.Ok(productHighlightDTOs);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<ProductHighlightDTO> CreateAsync(ProductHighlightDTOValidator productHighlightDTOValidator, BindingResult result) {
        if(productHighlightDTOValidator == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try {
            var resultCreate = new CloudinaryCreate();

            resultCreate = cloudinaryUti.CreateMedia(productHighlightDTOValidator.getImgProduct(), "product-highlights-java", 500, 500);

            if (resultCreate.getImgUrl() == null || resultCreate.getPublicId() == null)
                return ResultService.Fail("error when create Img category");

            UUID productHighlightId = UUID.randomUUID();
            var categories = new ProductHighlight(productHighlightId, productHighlightDTOValidator.getTitle(), resultCreate.getImgUrl(),
                    productHighlightDTOValidator.getImgTop(), productHighlightDTOValidator.getQuantitySold());

            var createProductHighlight = productHighlightRepository.create(categories);

            var productHighlightDTOCreateMap = modelMapper.map(createProductHighlight, ProductHighlightDTO.class);

            return ResultService.Ok(productHighlightDTOCreateMap);

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<ProductHighlightDTO> Delete(UUID productHighlightId) {
        try {
            var productHighlightDelete = productHighlightRepository.GetProductHighlightToDelete(productHighlightId);

            if(productHighlightDelete == null)
                return ResultService.Fail("productHighlight not found");

            if(productHighlightDelete.getImgProduct() != null){
                var deleteFound = cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(productHighlightDelete.getImgProduct());

                if(!deleteFound.getDeleteSuccessfully())
                    return ResultService.Fail(deleteFound.getMessage());
            }

            var productHighlightDeleteSuccessfully = productHighlightRepository.delete(productHighlightDelete.getId());

            return ResultService.Ok(modelMapper.map(productHighlightDeleteSuccessfully, ProductHighlightDTO.class));
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }
}
