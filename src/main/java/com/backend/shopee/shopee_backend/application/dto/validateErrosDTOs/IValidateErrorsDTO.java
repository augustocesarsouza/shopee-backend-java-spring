package com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs;

import com.backend.shopee.shopee_backend.application.services.ErrorValidation;
import org.springframework.validation.ObjectError;

import java.util.List;

public interface IValidateErrorsDTO {
    List<ErrorValidation> ValidateDTO(List<ObjectError> errorsDTO);
}
