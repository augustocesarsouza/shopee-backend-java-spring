package com.backend.shopee.shopee_backend.application.services;

import com.backend.shopee.shopee_backend.application.dto.PromotionUserDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.PromotionUserValidationDTOs.PromotionUserCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.interfaces.IPromotionUserService;
import com.backend.shopee.shopee_backend.domain.entities.PromotionUser;
import com.backend.shopee.shopee_backend.domain.repositories.IPromotionUserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

@Service
public class PromotionUserService implements IPromotionUserService {
    private final IPromotionUserRepository promotionUserRepository;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final ModelMapper modelMapper;

    @Autowired
    public PromotionUserService(IPromotionUserRepository promotionUserRepository, IValidateErrorsDTO validateErrorsDTO, ModelMapper modelMapper) {
        this.promotionUserRepository = promotionUserRepository;
        this.validateErrorsDTO = validateErrorsDTO;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public ResultService<List<PromotionUserDTO>> GetByUserIdAll(UUID userId) {
        try {
            var promotionUserDTOS = promotionUserRepository.GetByUserIdAll(userId);

            if(promotionUserDTOS == null)
                return ResultService.Fail("not found promotionUser");

//            var promotionUserDTOSMap = modelMapper.map(promotionUserDTOS, PromotionUserDTO.class);
            return ResultService.Ok(promotionUserDTOS);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<PromotionUserDTO> CreateAsync(PromotionUserCreateDTOValidator promotionUserCreateDTOValidator, BindingResult result) {
        if(promotionUserCreateDTOValidator == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try  {
            UUID promotionUserId = UUID.randomUUID();

            var promotionUser = new PromotionUser(promotionUserId, UUID.fromString(promotionUserCreateDTOValidator.getPromotionId()), null,
                    UUID.fromString(promotionUserCreateDTOValidator.getUserId()), null);

            var createPromotionUser = promotionUserRepository.create(promotionUser);

            var promotionUserMap = modelMapper.map(createPromotionUser, PromotionUserDTO.class);

            return ResultService.Ok(promotionUserMap);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<PromotionUserDTO> Delete(UUID promotionUserId) {
        return null;
    }
}
