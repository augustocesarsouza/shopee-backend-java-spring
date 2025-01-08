package com.backend.shopee.shopee_backend.application.services;

import com.backend.shopee.shopee_backend.application.dto.ShopeeUpdateDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ShopeeUpdateValidationDTOs.ShopeeUpdateCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.interfaces.IShopeeUpdateService;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryCreate;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.shopee.shopee_backend.domain.entities.ShopeeUpdate;
import com.backend.shopee.shopee_backend.domain.repositories.IShopeeUpdateRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
public class ShopeeUpdateService implements IShopeeUpdateService {
    private final IShopeeUpdateRepository shopeeUpdateRepository;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final ModelMapper modelMapper;
    private final ICloudinaryUti cloudinaryUti;

    public ShopeeUpdateService(IShopeeUpdateRepository shopeeUpdateRepository, IValidateErrorsDTO validateErrorsDTO,
                               ModelMapper modelMapper, ICloudinaryUti cloudinaryUti) {
        this.shopeeUpdateRepository = shopeeUpdateRepository;
        this.validateErrorsDTO = validateErrorsDTO;
        this.modelMapper = modelMapper;
        this.cloudinaryUti = cloudinaryUti;
    }

    @Override
    @Transactional
    public ResultService<ShopeeUpdateDTO> GetById(UUID shopeeUpdateId) {
        try {
            ShopeeUpdateDTO shopeeUpdateDTO = shopeeUpdateRepository.GetById(shopeeUpdateId);

            if(shopeeUpdateDTO == null)
                return ResultService.Fail("not found promotion");

            var shopeeUpdateMap = modelMapper.map(shopeeUpdateDTO, ShopeeUpdateDTO.class);
            return ResultService.Ok(shopeeUpdateMap);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public ResultService<ShopeeUpdateDTO> CreateAsync(ShopeeUpdateCreateDTOValidator shopeeUpdateCreateDTOValidator, BindingResult result) {
        if(shopeeUpdateCreateDTOValidator == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try {
            var stringSplit = shopeeUpdateCreateDTOValidator.getDateCreate().split(" ");
            var stringSplitDayMonthYear = stringSplit[0];
            var stringHourMin = stringSplit[1];

            var stringSplitDayMonthYearBar = stringSplitDayMonthYear.split("/");
            var stringHourMinTwoPoints = stringHourMin.split(":");

            var day = stringSplitDayMonthYearBar[0];
            var month = stringSplitDayMonthYearBar[1];
            var year = stringSplitDayMonthYearBar[2];

            var hour = stringHourMinTwoPoints[0];
            var min = stringHourMinTwoPoints[1];

            int intDay = Integer.parseInt(day);
            int intMonth = Integer.parseInt(month);
            int intYear = Integer.parseInt(year);
            int intHour = Integer.parseInt(hour);
            int intMin = Integer.parseInt(min);

            LocalDateTime birthDate = LocalDateTime.of(intYear, intMonth, intDay, intHour, intMin);
            ZonedDateTime birthDateUtc = birthDate.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));

            var resultCreate = new CloudinaryCreate();

            resultCreate = cloudinaryUti.CreateMedia(shopeeUpdateCreateDTOValidator.getImg(), "img-shopee", 360, 360);

            if (resultCreate.getImgUrl() == null || resultCreate.getPublicId() == null)
                return ResultService.Fail("error when create Img Promotion");

            UUID shopeeUpdateId = UUID.randomUUID();

            var shopeeUpdate = new ShopeeUpdate(shopeeUpdateId, shopeeUpdateCreateDTOValidator.getTitle(),
                    shopeeUpdateCreateDTOValidator.getDescription(), birthDate, resultCreate.getImgUrl());

            var createShopeeUpdate = shopeeUpdateRepository.create(shopeeUpdate);

            return ResultService.Ok(modelMapper.map(createShopeeUpdate, ShopeeUpdateDTO.class));

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public ResultService<ShopeeUpdateDTO> Delete(UUID shopeeUpdateId) {
        try {
            var shopeeUpdateDelete = shopeeUpdateRepository.GetShopeeUpdateToDelete(shopeeUpdateId);

            if(shopeeUpdateDelete == null)
                return ResultService.Fail("User not found");

            if(shopeeUpdateDelete.getImg() != null){
                var deleteFound = cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(shopeeUpdateDelete.getImg());

                if(!deleteFound.getDeleteSuccessfully())
                    return ResultService.Fail(deleteFound.getMessage());
            }

            var shopeeUpdateDeleteSuccessfully = shopeeUpdateRepository.delete(shopeeUpdateDelete.getId());

            return ResultService.Ok(modelMapper.map(shopeeUpdateDeleteSuccessfully, ShopeeUpdateDTO.class));

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }
}
