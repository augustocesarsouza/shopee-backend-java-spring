package com.backend.shopee.shopee_backend.application.services;

import com.backend.shopee.shopee_backend.application.dto.ProductOptionImageBottomDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductOptionImageBottomValidationDTOs.ProductOptionImageBottomCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.interfaces.IProductOptionImageBottomService;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.shopee.shopee_backend.domain.entities.ProductOptionImageBottom;
import com.backend.shopee.shopee_backend.domain.repositories.IProductOptionImageBottomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductOptionImageBottomService implements IProductOptionImageBottomService {
    private final IProductOptionImageBottomRepository productOptionImageBottomRepository;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final ModelMapper modelMapper;
    private final ICloudinaryUti cloudinaryUti;

    @Autowired
    public ProductOptionImageBottomService(IProductOptionImageBottomRepository productOptionImageBottomRepository, IValidateErrorsDTO validateErrorsDTO,
                                           ModelMapper modelMapper, ICloudinaryUti cloudinaryUti) {
        this.productOptionImageBottomRepository = productOptionImageBottomRepository;
        this.validateErrorsDTO = validateErrorsDTO;
        this.modelMapper = modelMapper;
        this.cloudinaryUti = cloudinaryUti;
    }

    @Override
    public ResultService<ProductOptionImageBottomDTO> GetByListFlashSaleProductImageAllId(UUID productsOfferFlashId) {
        try {
            var listProductOptionImageBottoms = productOptionImageBottomRepository.GetByListFlashSaleProductImageAllId(productsOfferFlashId);

            if(listProductOptionImageBottoms == null)
                return ResultService.Fail("not found productOptionImageBottom");

            return ResultService.Ok(listProductOptionImageBottoms);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<ProductOptionImageBottomDTO> CreateAsync(ProductOptionImageBottomCreateDTOValidator productOptionImageBottomCreateDTOValidator,
                                                                  BindingResult result) {
        if(productOptionImageBottomCreateDTOValidator == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try {
            UUID uuid = UUID.randomUUID();

            var listUrl = productOptionImageBottomCreateDTOValidator.getListImageUrlBottom();
            var newListImgCloudinary = new ArrayList<String>();

            listUrl.forEach((el) -> {
                var resultCreate = cloudinaryUti.CreateMedia(el, "product-option-image-java", 450, 450);

                if (resultCreate.getImgUrl() == null || resultCreate.getPublicId() == null) {
                    throw new RuntimeException("Error when creating Img product option");
                }

                newListImgCloudinary.add(resultCreate.getImgUrl());
            });

//            ProductOptionImageBottom(UUID id, UUID productsOfferFlashId, List<String> listImageUrlBottom)
            var productOptionImageBottom = new ProductOptionImageBottom(uuid,
                    UUID.fromString(productOptionImageBottomCreateDTOValidator.getProductsOfferFlashId()), newListImgCloudinary);

            var createProductOptionImageBottom = productOptionImageBottomRepository.create(productOptionImageBottom);

            var productOptionImageBottomDTOCreateMap = modelMapper.map(createProductOptionImageBottom, ProductOptionImageBottomDTO.class);

            return ResultService.Ok(productOptionImageBottomDTOCreateMap);

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<ProductOptionImageBottomDTO> Delete(UUID productOptionImageBottomId) {
        return null;
    }
}
