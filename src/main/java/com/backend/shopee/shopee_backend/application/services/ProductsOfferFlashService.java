package com.backend.shopee.shopee_backend.application.services;

import com.backend.shopee.shopee_backend.application.dto.ProductsOfferFlashDTO;
import com.backend.shopee.shopee_backend.application.dto.PromotionDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductsOfferFlashValidator.ProductsOfferFlashDTOValidator;
import com.backend.shopee.shopee_backend.application.services.interfaces.IProductsOfferFlashService;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryCreate;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.shopee.shopee_backend.domain.entities.ProductsOfferFlash;
import com.backend.shopee.shopee_backend.domain.enums.ProductOfferFlashType;
import com.backend.shopee.shopee_backend.domain.repositories.IProductsOfferFlashRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

@Service
public class ProductsOfferFlashService implements IProductsOfferFlashService {
    private final IProductsOfferFlashRepository productsOfferFlashRepository;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final ModelMapper modelMapper;
    private final ICloudinaryUti cloudinaryUti;

    @Autowired
    public ProductsOfferFlashService(IProductsOfferFlashRepository productsOfferFlashRepository, IValidateErrorsDTO validateErrorsDTO,
                                     ModelMapper modelMapper, ICloudinaryUti cloudinaryUti) {
        this.productsOfferFlashRepository = productsOfferFlashRepository;
        this.validateErrorsDTO = validateErrorsDTO;
        this.modelMapper = modelMapper;
        this.cloudinaryUti = cloudinaryUti;
    }

    @Override
    public ResultService<List<ProductsOfferFlashDTO>> GetAllProduct() {
        try {
            var productsOfferFlashDTOs = productsOfferFlashRepository.GetAllProduct();

            if(productsOfferFlashDTOs == null)
                return ResultService.Fail("not found promotion");

            return ResultService.Ok(productsOfferFlashDTOs);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<List<ProductsOfferFlashDTO>> GetAllByTagProduct(String hourFlashOffer, String tagProduct, int pageNumber, int pageSize) {
        try {
            var productsOfferFlashDTOs = productsOfferFlashRepository.GetAllByTagProduct(hourFlashOffer, tagProduct, pageNumber, pageSize);

            if(productsOfferFlashDTOs == null)
                return ResultService.Fail("not found promotion");

            return ResultService.Ok(productsOfferFlashDTOs);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<ProductsOfferFlashDTO> CreateAsync(ProductsOfferFlashDTOValidator productsOfferFlashDTOValidator, BindingResult result) {
        if(productsOfferFlashDTOValidator == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try {
            String tag = productsOfferFlashDTOValidator.getTagProduct();

            boolean isValidTag = ProductOfferFlashType.isValidTagProduct(tag);

            if (!isValidTag)
                return ResultService.Fail("provided type is not valid");

//            ProductOfferFlashType type = ProductOfferFlashType.fromDescription(tag);
            ProductOfferFlashType type = ProductOfferFlashType.valueOf(tag);
            String description = type.getDescription();
            UUID idEntity = UUID.randomUUID();

            var resultCreate = new CloudinaryCreate();

            resultCreate = cloudinaryUti.CreateMedia(productsOfferFlashDTOValidator.getImgProduct(), "img-flash-deals", 320, 320);

            if (resultCreate.getImgUrl() == null || resultCreate.getPublicId() == null)
                return ResultService.Fail("error when create Img Promotion");

            ProductsOfferFlash productsOfferFlash = new ProductsOfferFlash(idEntity, resultCreate.getImgUrl(), productsOfferFlashDTOValidator.getAltValue(),
                    productsOfferFlashDTOValidator.getImgPartBottom(), productsOfferFlashDTOValidator.getPriceProduct(),
                    productsOfferFlashDTOValidator.getPopularityPercentage(), productsOfferFlashDTOValidator.getDiscountPercentage(),
                    productsOfferFlashDTOValidator.getHourFlashOffer(), productsOfferFlashDTOValidator.getTitle(), description);

            var createProductsOfferFlash = productsOfferFlashRepository.create(productsOfferFlash);

            return ResultService.Ok(modelMapper.map(createProductsOfferFlash, ProductsOfferFlashDTO.class));
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<ProductsOfferFlashDTO> Delete(String productsOfferFlashId) {
        try {
            var productsOfferFlashDelete = productsOfferFlashRepository.GetProductsOfferFlashById(productsOfferFlashId);

            if(productsOfferFlashDelete == null)
                return ResultService.Fail("User not found");

            if(productsOfferFlashDelete.getImgProduct() != null){
                var deleteFound = cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(productsOfferFlashDelete.getImgProduct());

                if(!deleteFound.getDeleteSuccessfully())
                    return ResultService.Fail(deleteFound.getMessage());
            }

            var productsOfferFlashDeleteSuccessfully = productsOfferFlashRepository.delete(productsOfferFlashDelete.getId());

            return ResultService.Ok(modelMapper.map(productsOfferFlashDeleteSuccessfully, ProductsOfferFlashDTO.class));

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }
}
