package com.backend.shopee.shopee_backend.application.services;

import com.backend.shopee.shopee_backend.application.dto.ProductDiscoveriesOfDayDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductDiscoveriesOfDayValidationDTOs.ProductDiscoveriesOfDayCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.interfaces.IProductDiscoveriesOfDayService;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryCreate;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.shopee.shopee_backend.domain.entities.ProductDiscoveriesOfDay;
import com.backend.shopee.shopee_backend.domain.repositories.IProductDiscoveriesOfDayRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

@Service
public class ProductDiscoveriesOfDayService implements IProductDiscoveriesOfDayService {
    private final IProductDiscoveriesOfDayRepository productDiscoveriesOfDayRepository;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final ModelMapper modelMapper;
    private final ICloudinaryUti cloudinaryUti;

    @Autowired
    public ProductDiscoveriesOfDayService(IProductDiscoveriesOfDayRepository productDiscoveriesOfDayRepository,
                                          IValidateErrorsDTO validateErrorsDTO, ModelMapper modelMapper, ICloudinaryUti cloudinaryUti) {
        this.productDiscoveriesOfDayRepository = productDiscoveriesOfDayRepository;
        this.validateErrorsDTO = validateErrorsDTO;
        this.modelMapper = modelMapper;
        this.cloudinaryUti = cloudinaryUti;
    }

    @Override
    public ResultService<ProductDiscoveriesOfDayDTO> GetProductDiscoveriesOfDayById(UUID productDiscoveriesOfDayId) {
        try {
            var productDiscoveriesOfDayDTO = productDiscoveriesOfDayRepository.GetProductDiscoveriesOfDayById(productDiscoveriesOfDayId);

            if(productDiscoveriesOfDayDTO == null)
                return ResultService.Fail("not found productDiscoveriesOfDay");

            return ResultService.Ok(productDiscoveriesOfDayDTO);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<List<ProductDiscoveriesOfDayDTO>> GetAllProductDiscoveriesOfDays() {
        try {
            var productDiscoveriesOfDayDTOS = productDiscoveriesOfDayRepository.GetAllProductDiscoveriesOfDay();

            if(productDiscoveriesOfDayDTOS == null)
                return ResultService.Fail("not found productDiscoveriesOfDay");

            return ResultService.Ok(productDiscoveriesOfDayDTOS);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<ProductDiscoveriesOfDayDTO> CreateAsync(ProductDiscoveriesOfDayCreateDTOValidator productDiscoveriesOfDayCreateDTOValidator,
                                                                 BindingResult result) {
        if(productDiscoveriesOfDayCreateDTOValidator == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try {
            var resultCreate = new CloudinaryCreate();

            resultCreate = cloudinaryUti.CreateMedia(productDiscoveriesOfDayCreateDTOValidator.getImgProduct(), "product-discoveries-of-day-java",
                    320, 320);

            if (resultCreate.getImgUrl() == null || resultCreate.getPublicId() == null)
                return ResultService.Fail("error when create Img category");

            UUID productDiscoveriesOfDayId = UUID.randomUUID();

            var productDiscoveriesOfDay = new ProductDiscoveriesOfDay(productDiscoveriesOfDayId, productDiscoveriesOfDayCreateDTOValidator.getTitle(),
                    resultCreate.getImgUrl(), productDiscoveriesOfDayCreateDTOValidator.getImgPartBottom(),
                    productDiscoveriesOfDayCreateDTOValidator.getDiscountPercentage(),
                    productDiscoveriesOfDayCreateDTOValidator.getAd(), productDiscoveriesOfDayCreateDTOValidator.getPrice(),
                    productDiscoveriesOfDayCreateDTOValidator.getQuantitySold());

            var createProductDiscoveriesOfDay = productDiscoveriesOfDayRepository.create(productDiscoveriesOfDay);

            var productDiscoveriesOfDayDTOCreateMap = modelMapper.map(createProductDiscoveriesOfDay, ProductDiscoveriesOfDayDTO.class);

            return ResultService.Ok(productDiscoveriesOfDayDTOCreateMap);

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<ProductDiscoveriesOfDayDTO> Delete(UUID productDiscoveriesOfDayId) {
        try {
            var productDiscoveriesOfDayDelete = productDiscoveriesOfDayRepository.GetProductDiscoveriesOfDayToDelete(productDiscoveriesOfDayId);

            if(productDiscoveriesOfDayDelete == null)
                return ResultService.Fail("ProductDiscoveriesOfDay not found");

            if(productDiscoveriesOfDayDelete.getImgProduct() != null){
                var deleteFound = cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(productDiscoveriesOfDayDelete.getImgProduct());

                if(!deleteFound.getDeleteSuccessfully())
                    return ResultService.Fail(deleteFound.getMessage());
            }

            var productDiscoveriesOfDayDeleteSuccessfully = productDiscoveriesOfDayRepository.delete(productDiscoveriesOfDayDelete.getId());

            return ResultService.Ok(modelMapper.map(productDiscoveriesOfDayDeleteSuccessfully, ProductDiscoveriesOfDayDTO.class));

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }
}
