package com.backend.shopee.shopee_backend.application.services.interfaces;

import com.backend.shopee.shopee_backend.application.dto.UserChangePasswordDTO;
import com.backend.shopee.shopee_backend.application.dto.UserDTO;
import com.backend.shopee.shopee_backend.application.dto.UserPasswordUpdateDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs.UserCreateValidatorDTO;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import com.backend.shopee.shopee_backend.domain.entities.User;
import org.springframework.validation.BindingResult;

import java.util.UUID;

public interface IUserManagementService {
    ResultService<UserDTO> findById(String phone);
    ResultService<UserDTO> create(UserCreateValidatorDTO userCreateValidatorDTO, BindingResult result);
    ResultService<UserPasswordUpdateDTO> ChangePasswordUser(UserChangePasswordDTO userChangePasswordDTO, BindingResult resultValid);
    ResultService<UserDTO> DeleteUser(UUID userID);
}
