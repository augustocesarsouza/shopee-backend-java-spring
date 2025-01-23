package com.backend.shopee.shopee_backend.application.services;

import com.backend.shopee.shopee_backend.application.dto.UserSellerProductDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.UserSellerProductValidationDTOs.UserSellerProductCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.interfaces.IUserSellerProductService;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.shopee.shopee_backend.domain.entities.UserSellerProduct;
import com.backend.shopee.shopee_backend.domain.repositories.IUserSellerProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
public class UserSellerProductService implements IUserSellerProductService {
    private final IUserSellerProductRepository userSellerProductRepository;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final ModelMapper modelMapper;
    private final ICloudinaryUti cloudinaryUti;

    @Autowired
    public UserSellerProductService(IUserSellerProductRepository userSellerProductRepository, IValidateErrorsDTO validateErrorsDTO,
                                    ModelMapper modelMapper, ICloudinaryUti cloudinaryUti) {
        this.userSellerProductRepository = userSellerProductRepository;
        this.validateErrorsDTO = validateErrorsDTO;
        this.modelMapper = modelMapper;
        this.cloudinaryUti = cloudinaryUti;
    }

    @Override
    public ResultService<UserSellerProductDTO> GetByUserSellerProductId(UUID userSellerProductId) {
        try {
            var userSellerProductDTO = userSellerProductRepository.GetByUserSellerProductId(userSellerProductId);

            if(userSellerProductDTO == null)
                return ResultService.Fail("not found userSellerProduct");

            userSellerProductDTO.setLastLogin(userSellerProductDTO.convertLastLoginFromUtcToLocal());
            userSellerProductDTO.setAccountCreationDate(userSellerProductDTO.convertAccountCreationDateFromUtcToLocal());
            return ResultService.Ok(userSellerProductDTO);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<UserSellerProductDTO> CreateAsync(UserSellerProductCreateDTOValidator userSellerProductCreateDTOValidator,
                                                           BindingResult result) {
        if(userSellerProductCreateDTOValidator == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try {
            LocalDateTime DateNow = LocalDateTime.now();

            ZonedDateTime DateNowUtc = DateNow.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
            // talvez tenha que conver de "ZonedDateTime" para "LocalDateTime"

            //ZonedDateTime birthDateUtc = birthDate.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));

            var resultCreate = cloudinaryUti.CreateMedia(userSellerProductCreateDTOValidator.getImgProfile(),
                    "img-user-sold-product-only-img-java", 360, 360);

            if (resultCreate.getImgUrl() == null || resultCreate.getPublicId() == null)
                return ResultService.Fail("error when create Img Promotion");

            UUID entityId = UUID.randomUUID();

            var standardUsuallyRespondsToChatIn = "dentro de minutos";

            var userSellerProduct = new UserSellerProduct(entityId, userSellerProductCreateDTOValidator.getName(), resultCreate.getImgUrl(),
                    userSellerProductCreateDTOValidator.getImgFloating(),
                    DateNowUtc, userSellerProductCreateDTOValidator.getReviews(), userSellerProductCreateDTOValidator.getChatResponseRate(),
                    DateNowUtc, userSellerProductCreateDTOValidator.getQuantityOfProductSold(), standardUsuallyRespondsToChatIn,
                    userSellerProductCreateDTOValidator.getFollowers());
//            UserSellerProduct(UUID id, String name, String imgProfile, String imgFloating, ZonedDateTime lastLogin,
//                    Integer reviews, Integer chatResponseRate, ZonedDateTime accountCreationDate,
//                    Integer quantityOfProductSold, String usuallyRespondsToChatIn, Integer followers)

            var createUserSellerProduct = userSellerProductRepository.create(userSellerProduct);

            var userSellerProductDTOCreateMap = modelMapper.map(createUserSellerProduct, UserSellerProductDTO.class);

            return ResultService.Ok(userSellerProductDTOCreateMap);

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<UserSellerProductDTO> Delete(UUID userSellerProductId) {
        return null;
    }
}
