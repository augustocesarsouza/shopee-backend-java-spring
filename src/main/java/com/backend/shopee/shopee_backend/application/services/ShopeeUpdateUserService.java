package com.backend.shopee.shopee_backend.application.services;

import com.backend.shopee.shopee_backend.application.dto.ShopeeUpdateUserDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ShopeeUpdateUserValidationDTOs.PromotionUpdateUserCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.interfaces.IShopeeUpdateUserService;
import com.backend.shopee.shopee_backend.domain.entities.ShopeeUpdateUser;
import com.backend.shopee.shopee_backend.domain.repositories.IShopeeUpdateUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

@Service
public class ShopeeUpdateUserService implements IShopeeUpdateUserService {
    private final IShopeeUpdateUserRepository shopeeUpdateUserRepository;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final ModelMapper modelMapper;

    @Autowired
    public ShopeeUpdateUserService(IShopeeUpdateUserRepository shopeeUpdateUserRepository, IValidateErrorsDTO validateErrorsDTO, ModelMapper modelMapper) {
        this.shopeeUpdateUserRepository = shopeeUpdateUserRepository;
        this.validateErrorsDTO = validateErrorsDTO;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResultService<List<ShopeeUpdateUserDTO>> GetByUserIdAll(UUID userId) {
        try {
            var shopeeUpdateUserDTOS = shopeeUpdateUserRepository.GetByUserIdAll(userId);

            if(shopeeUpdateUserDTOS == null)
                return ResultService.Fail("not found promotion");

            return ResultService.Ok(shopeeUpdateUserDTOS);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<ShopeeUpdateUserDTO> CreateAsync(PromotionUpdateUserCreateDTOValidator promotionUpdateUserCreateDTOValidator,
                                                          BindingResult result) {
        if(promotionUpdateUserCreateDTOValidator == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try  {
            UUID shopeeUpdateUserId = UUID.randomUUID();

            var shopeeUpdateUser = new ShopeeUpdateUser(shopeeUpdateUserId, UUID.fromString(promotionUpdateUserCreateDTOValidator.getShopeeUpdateId()),
                    null, UUID.fromString(promotionUpdateUserCreateDTOValidator.getUserId()), null);

            var createShopeeUpdateUser = shopeeUpdateUserRepository.create(shopeeUpdateUser);

            var shopeeUpdateUserMap = modelMapper.map(createShopeeUpdateUser, ShopeeUpdateUserDTO.class);

            return ResultService.Ok(shopeeUpdateUserMap);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<ShopeeUpdateUserDTO> Delete(UUID shopeeUpdateUserId) {
        return null;
    }
}
