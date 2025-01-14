package com.backend.shopee.shopee_backend.application.services;

import com.backend.shopee.shopee_backend.application.dto.AddressDTO;
import com.backend.shopee.shopee_backend.application.dto.PromotionDTO;
import com.backend.shopee.shopee_backend.application.dto.UserDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.PromotionValidationDTOs.PromotionCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.interfaces.IPromotionService;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryCreate;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.shopee.shopee_backend.domain.entities.Promotion;
import com.backend.shopee.shopee_backend.domain.repositories.IPromotionRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionService implements IPromotionService {
    private final IPromotionRepository promotionRepository;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final ModelMapper modelMapper;
    private final ICloudinaryUti cloudinaryUti;

    @Autowired
    public PromotionService(IPromotionRepository promotionRepository, IValidateErrorsDTO validateErrorsDTO,
                            ModelMapper modelMapper, ICloudinaryUti cloudinaryUti) {
        this.promotionRepository = promotionRepository;
        this.validateErrorsDTO = validateErrorsDTO;
        this.modelMapper = modelMapper;
        this.cloudinaryUti = cloudinaryUti;
    }

    @Override
    @Transactional
    public ResultService<PromotionDTO> GetById(UUID promotionId) {
        try {
            PromotionDTO promotionDTO = promotionRepository.GetById(promotionId);

            if(promotionDTO == null)
                return ResultService.Fail("not found promotion");

            return ResultService.Ok(promotionDTO);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public ResultService<PromotionDTO> CreateAsync(PromotionCreateDTOValidator promotionCreateDTOValidator, BindingResult result) {
        if(promotionCreateDTOValidator == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            List<ObjectError> errorsDTO = result.getAllErrors();
            List<ErrorValidation> errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try {
            int quantityListImgInner = promotionCreateDTOValidator.getListImgInner().size();

            if(promotionCreateDTOValidator.getWhatIsThePromotion() == 2 && quantityListImgInner < 3)
                return ResultService.Fail("list img inner is less than 3, must be greater than 2");

            UUID promotionId = UUID.randomUUID();

            var stringSplit = promotionCreateDTOValidator.getDateCreate().split(" ");
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

            resultCreate = cloudinaryUti.CreateMedia(promotionCreateDTOValidator.getImg(), "img-shopee", 360, 360);

            if (resultCreate.getImgUrl() == null || resultCreate.getPublicId() == null)
                return ResultService.Fail("error when create Img Promotion");

            Promotion promotion = new Promotion(promotionId, promotionCreateDTOValidator.getWhatIsThePromotion(), promotionCreateDTOValidator.getTitle(),
                    promotionCreateDTOValidator.getDescription(), birthDate, resultCreate.getImgUrl(), null);

            if(promotionCreateDTOValidator.getWhatIsThePromotion() == 2){
                List<String> listImgInner = promotionCreateDTOValidator.getListImgInner();

                List<String> listImgInnerAfterCreateCloudinary = new ArrayList<>();

                for (String el : listImgInner) {
                    var resultCreateFor = cloudinaryUti.CreateMedia(el, "img-shopee", 360, 360);

                    if (resultCreateFor.getImgUrl() == null || resultCreateFor.getPublicId() == null)
                        return ResultService.Fail("error when create Img Promotion");

                    listImgInnerAfterCreateCloudinary.add(resultCreateFor.getImgUrl());
                }

                promotion.setListImgInner(listImgInnerAfterCreateCloudinary);
            }

            var createPromotion = promotionRepository.create(promotion);

            return ResultService.Ok(modelMapper.map(createPromotion, PromotionDTO.class));

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<PromotionDTO> Delete(UUID promotionId) {
        try {
            var promotionDelete = promotionRepository.GetById(promotionId);

            if(promotionDelete == null)
                return ResultService.Fail("User not found");

            if(promotionDelete.getImg() != null){
                var deleteFound = cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(promotionDelete.getImg());

                if(!deleteFound.getDeleteSuccessfully())
                    return ResultService.Fail(deleteFound.getMessage());
            }

            int quantityListImgInner = promotionDelete.getListImgInner().size();

            if(promotionDelete.getWhatIsThePromotion() == 2 && quantityListImgInner > 0){
                List<String> listImgInner = promotionDelete.getListImgInner();

                for (String el : listImgInner) {
                    var deleteFound = cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(el);

                    if(!deleteFound.getDeleteSuccessfully())
                        return ResultService.Fail(deleteFound.getMessage());
                }
            }

            var promotionDeleteSuccessfully = promotionRepository.delete(promotionDelete.getId());

            return ResultService.Ok(modelMapper.map(promotionDeleteSuccessfully, PromotionDTO.class));

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }
}
