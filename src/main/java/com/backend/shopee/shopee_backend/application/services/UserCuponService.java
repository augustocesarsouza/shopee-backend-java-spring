package com.backend.shopee.shopee_backend.application.services;

import com.backend.shopee.shopee_backend.application.dto.UserCuponDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.UserCuponValidationDTOs.UserCuponCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.interfaces.IUserCuponService;
import com.backend.shopee.shopee_backend.domain.entities.UserCupon;
import com.backend.shopee.shopee_backend.domain.repositories.IUserCuponRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

@Service
public class UserCuponService implements IUserCuponService {
    private final IUserCuponRepository userCuponRepository;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final ModelMapper modelMapper;

    @Autowired
    public UserCuponService(IUserCuponRepository userCuponRepository, IValidateErrorsDTO validateErrorsDTO, ModelMapper modelMapper) {
        this.userCuponRepository = userCuponRepository;
        this.validateErrorsDTO = validateErrorsDTO;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResultService<List<UserCuponDTO>> GetAllCuponByUserId(UUID userCuponId) {
        try {
            var shopeeUpdateDTOS = userCuponRepository.GetAllCuponByUserId(userCuponId);

            if(shopeeUpdateDTOS == null)
                return ResultService.Fail("not found promotion");

            return ResultService.Ok(shopeeUpdateDTOS);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<UserCuponDTO> CreateAsync(UserCuponCreateDTOValidator userCuponCreateDTOValidator, BindingResult result) {
        if(userCuponCreateDTOValidator == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try  {
            UUID userCuponId = UUID.randomUUID();

            var userCuponCreate = new UserCupon(userCuponId, UUID.fromString(userCuponCreateDTOValidator.getCuponId()), null,
                    UUID.fromString(userCuponCreateDTOValidator.getUserId()), null);

            var createCupon = userCuponRepository.create(userCuponCreate);

            var cuponMap = modelMapper.map(createCupon, UserCuponDTO.class);

            return ResultService.Ok(cuponMap);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<UserCuponDTO> Delete(UUID userCuponId) {
        return null;
    }
}
