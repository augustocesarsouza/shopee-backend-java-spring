package com.backend.shopee.shopee_backend.application.services;

import com.backend.shopee.shopee_backend.application.dto.CuponDTO;
import com.backend.shopee.shopee_backend.application.dto.PromotionDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.CuponValidationDTOs.CuponCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.interfaces.ICuponService;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.shopee.shopee_backend.domain.entities.Cupon;
import com.backend.shopee.shopee_backend.domain.repositories.ICuponRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
public class CuponService implements ICuponService {
    private final ICuponRepository cuponRepository;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final ModelMapper modelMapper;

    @Autowired
    public CuponService(ICuponRepository cuponRepository, IValidateErrorsDTO validateErrorsDTO, ModelMapper modelMapper) {
        this.cuponRepository = cuponRepository;
        this.validateErrorsDTO = validateErrorsDTO;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResultService<CuponDTO> GetCuponById(UUID cuponId) {
        try {
            var cuponDTO = cuponRepository.GetCuponById(cuponId);

            if(cuponDTO == null)
                return ResultService.Fail("not found promotion");

            return ResultService.Ok(cuponDTO);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<CuponDTO> CreateAsync(CuponCreateDTOValidator cuponCreateDTOValidator, BindingResult result) {
        if(cuponCreateDTOValidator == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try {
            UUID cuponId = UUID.randomUUID();

            var stringSplit = cuponCreateDTOValidator.getDateValidateCupon().split("/");

            var day = stringSplit[0];
            var month = stringSplit[1];
            var year = stringSplit[2];

            int intDay = Integer.parseInt(day);
            int intMonth = Integer.parseInt(month);
            int intYear = Integer.parseInt(year);

            LocalDateTime birthDate = LocalDateTime.of(intYear, intMonth, intDay, 0, 0, 0);
            ZonedDateTime birthDateUtc = birthDate.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));

            var cupon = new Cupon(cuponId, cuponCreateDTOValidator.getFirstText(), cuponCreateDTOValidator.getSecondText(),
                    cuponCreateDTOValidator.getThirdText(), birthDate, cuponCreateDTOValidator.getQuantityCupons(),
                    cuponCreateDTOValidator.getWhatCuponNumber(), cuponCreateDTOValidator.getSecondImg());

            var createCupon = cuponRepository.create(cupon);

            var cuponDTOCreateMap = modelMapper.map(createCupon, CuponDTO.class);

            return ResultService.Ok(cuponDTOCreateMap);

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<CuponDTO> Delete(UUID cuponId) {
        try {
            var cuponDelete = cuponRepository.GetCuponByIdToVerifyIfExist(cuponId);

            if(cuponDelete == null)
                return ResultService.Fail("Cupon not found");

            var cuponDeleteSuccessfully = cuponRepository.delete(cuponDelete.getId());

            var cupon = modelMapper.map(cuponDeleteSuccessfully, CuponDTO.class);

            return ResultService.Ok(cupon);

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }
}
