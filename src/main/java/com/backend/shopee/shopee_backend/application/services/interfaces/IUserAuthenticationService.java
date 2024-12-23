package com.backend.shopee.shopee_backend.application.services.interfaces;

import com.backend.shopee.shopee_backend.application.dto.UserChangePasswordDTO;
import com.backend.shopee.shopee_backend.application.dto.UserDTO;
import com.backend.shopee.shopee_backend.application.dto.UserLoginDTO;
import com.backend.shopee.shopee_backend.application.dto.UserPasswordUpdateDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs.CodeSendEmailUserValidatorDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs.CodeSendPhoneDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs.UserConfirmCodeEmailValidatorDTO;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import org.springframework.validation.BindingResult;

import java.util.UUID;

public interface IUserAuthenticationService {
    ResultService<UserDTO> GetByIdInfoUser(UUID userId);
    ResultService<CodeSendEmailUserValidatorDTO> SendCodeEmail(CodeSendEmailUserValidatorDTO codeSendEmailUserValidatorDTO, BindingResult result);
    ResultService<CodeSendPhoneDTOValidator> SendCodePhone(CodeSendPhoneDTOValidator codeSendPhoneDTOValidator, BindingResult result);
    ResultService<UserDTO> VerifyEmailAlreadySetUp(UserConfirmCodeEmailValidatorDTO userConfirmCodeEmailValidatorDTO, BindingResult result);
    ResultService<UserLoginDTO> Login(String phone, String password);
    ResultService<UserLoginDTO> VerifyPasswordUser(String phone,String password);
}
