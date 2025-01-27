package com.backend.shopee.shopee_backend.applicationTest.AllServiceTest;

import com.backend.shopee.shopee_backend.application.dto.UserChangePasswordDTO;
import com.backend.shopee.shopee_backend.application.dto.UserDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs.UserCreateValidatorDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs.UserUpdateAllDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs.UserUpdateFillDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ErrorValidation;
import com.backend.shopee.shopee_backend.application.services.UserManagementService;
import com.backend.shopee.shopee_backend.application.util.interfaces.IBCryptPasswordEncoderUtil;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryCreate;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryResult;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.shopee.shopee_backend.domain.entities.User;
import com.backend.shopee.shopee_backend.domain.repositories.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BeanPropertyBindingResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserManagementServiceTest {
    @Mock
    private IUserRepository userRepository;
    @Mock
    private IValidateErrorsDTO validateErrorsDTO;
    @Mock
    private IBCryptPasswordEncoderUtil bCryptPasswordEncoderUtil;
    @Mock
    private ICloudinaryUti cloudinaryUti;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private Authentication authentication;

    private UserManagementService userManagementService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        userManagementService = new UserManagementService(userRepository, validateErrorsDTO,bCryptPasswordEncoderUtil,
                cloudinaryUti, modelMapper);
        authentication = mock(Authentication.class);
    }

    @Test
    public void should_FindById_WithoutErrors(){
        String phone = "8273636736";
        User user = new User();
        UserDTO userDTO = new UserDTO();

        when(userRepository.GetUserByPhoneInfoUpdate(any())).thenReturn(user);
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        // Act
        var result = userManagementService.findById(phone);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(userDTO, result.Data);
    }

    @Test
    public void should_Return_Null_GetUserByPhoneInfoUpdate(){
        String phone = "8273636736";

        when(userRepository.GetUserByPhoneInfoUpdate(any())).thenReturn(null);

        // Act
        var result = userManagementService.findById(phone);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "not found");
    }

    @Test
    public void should_ThrowException_When_GetUserByPhoneInfoUpdate() {
        // Arrange
        String phone = "8273636736";
        String expectedErrorMessage = "Database connection error";

        when(userRepository.GetUserByPhoneInfoUpdate(any()))
                .thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = userManagementService.findById(phone);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(expectedErrorMessage, result.Message);
    }

    @Test
    public void should_findByIdOnly_WithoutErrors(){
        String userId = "235175e6-8e20-4b87-b19b-291a57eeb60d";
        User user = new User();
        UserDTO userDTO = new UserDTO();

        when(userRepository.GetUserById(any())).thenReturn(user);
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        // Act
        var result = userManagementService.findByIdOnly(userId);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(userDTO, result.Data);
    }

    @Test
    public void should_Return_Null_findByIdOnly(){
        String userId = "235175e6-8e20-4b87-b19b-291a57eeb60d";

        when(userRepository.GetUserByPhoneInfoUpdate(any())).thenReturn(null);

        // Act
        var result = userManagementService.findByIdOnly(userId);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "not found");
    }

    @Test
    public void should_ThrowException_When_findByIdOnly() {
        // Arrange
        String userId = "235175e6-8e20-4b87-b19b-291a57eeb60d";
        String expectedErrorMessage = "Database connection error";

        when(userRepository.GetUserById(any()))
                .thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = userManagementService.findByIdOnly(userId);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(expectedErrorMessage, result.Message);
    }

    @Test
    public void should_VerifyWhetherUserExist_WithoutErrors(){
        String userId = "235175e6-8e20-4b87-b19b-291a57eeb60d";
        User user = new User();
        UserDTO userDTO = new UserDTO();

        when(userRepository.GetUserByIdForDeleteImg(any())).thenReturn(user);
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        // Act
        var result = userManagementService.VerifyWhetherUserExist(UUID.fromString(userId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(userDTO, result.Data);
    }

    @Test
    public void should_Return_Null_VerifyWhetherUserExist(){
        String userId = "235175e6-8e20-4b87-b19b-291a57eeb60d";

        when(userRepository.GetUserByIdForDeleteImg(any())).thenReturn(null);

        // Act
        var result = userManagementService.VerifyWhetherUserExist(UUID.fromString(userId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "not found");
    }

    @Test
    public void should_ThrowException_When_VerifyWhetherUserExist() {
        // Arrange
        String userId = "235175e6-8e20-4b87-b19b-291a57eeb60d";
        String expectedErrorMessage = "Database connection error";

        when(userRepository.GetUserByIdForDeleteImg(any()))
                .thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = userManagementService.VerifyWhetherUserExist(UUID.fromString(userId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(expectedErrorMessage, result.Message);
    }

    @Test
    public void should_create_Successfully_User_Without_Img_Profile(){
        var userId = "bceff076-a1be-4c2b-a9e4-ff26ca4e2693";
        UserCreateValidatorDTO userCreateValidatorDTO = new UserCreateValidatorDTO(UUID.fromString(userId), "(+55) 67 98114 5503",
                "Augusto92349923",null);

        User user = new User();
        UserDTO userDTO = new UserDTO();

        when(bCryptPasswordEncoderUtil.encodePassword(any())).thenReturn("password");
        when(userRepository.create(any())).thenReturn(user);
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        var resultError = new BeanPropertyBindingResult(userCreateValidatorDTO, "userCreateValidatorDTO");

        // Act
        var result = userManagementService.create(userCreateValidatorDTO, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, userDTO);
    }

    @Test
    public void should_create_Successfully_User_With_Img_Profile(){
        var userId = "bceff076-a1be-4c2b-a9e4-ff26ca4e2693";
        UserCreateValidatorDTO userCreateValidatorDTO = new UserCreateValidatorDTO(UUID.fromString(userId), "(+55) 67 98114 5503",
                "Augusto92349923","data:image/jpeg;base64,UklGRvRJAABXRUJQVlA4WAoAAAAIAAAAKwEAhQ...");

        User user = new User();
        UserDTO userDTO = new UserDTO();

        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate("publicId", "img_url", true);

        when(bCryptPasswordEncoderUtil.encodePassword(any())).thenReturn("password");
        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);
        when(userRepository.create(any())).thenReturn(user);
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        var resultError = new BeanPropertyBindingResult(userCreateValidatorDTO, "userCreateValidatorDTO");

        // Act
        var result = userManagementService.create(userCreateValidatorDTO, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, userDTO);
    }

    @Test
    public void error_create_DTO_Is_Null(){
        var userId = "bceff076-a1be-4c2b-a9e4-ff26ca4e2693";
        UserCreateValidatorDTO userCreateValidatorDTO = new UserCreateValidatorDTO(UUID.fromString(userId), "(+55) 67 98114 5503",
                "Augusto92349923",null);

        var resultError = new BeanPropertyBindingResult(userCreateValidatorDTO, "userCreateValidatorDTO");

        // Act
        var result = userManagementService.create(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error DTO Is Null");
    }

    @Test
    public void error_validate_DTO_create(){
        UserCreateValidatorDTO userCreateValidatorDTO = new UserCreateValidatorDTO();
        var resultError = new BeanPropertyBindingResult(userCreateValidatorDTO, "userCreateValidatorDTO");

        resultError.rejectValue("Phone", "NotEmpty", "phone should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("Phone", "phone should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = userManagementService.create(userCreateValidatorDTO, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void should_ThrowException_When_create(){
        var userId = "bceff076-a1be-4c2b-a9e4-ff26ca4e2693";
        UserCreateValidatorDTO userCreateValidatorDTO = new UserCreateValidatorDTO(UUID.fromString(userId), "(+55) 67 98114 5503",
                "Augusto92349923",null);
        String expectedErrorMessage = "Database connection error";

        User user = new User();
        UserDTO userDTO = new UserDTO();

        when(bCryptPasswordEncoderUtil.encodePassword(any())).thenReturn("password");
        when(userRepository.create(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        var resultError = new BeanPropertyBindingResult(userCreateValidatorDTO, "userCreateValidatorDTO");

        // Act
        var result = userManagementService.create(userCreateValidatorDTO, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(expectedErrorMessage, result.Message);
    }

    @Test
    public void should_ChangePasswordUser_Successfully(){
        UserChangePasswordDTO userChangePasswordDTO = new UserChangePasswordDTO("(+55) 67 98114 5503", "ascascasc1212");
        var resultError = new BeanPropertyBindingResult(userChangePasswordDTO, "userCreateValidatorDTO");

        User user = new User();
        User userChange = new User();

        when(userRepository.GetUserByPhoneInfoUpdate(anyString())).thenReturn(user);
        when(bCryptPasswordEncoderUtil.encodePassword(anyString())).thenReturn("password");
        when(userRepository.update(any())).thenReturn(userChange);
//      when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        // Act
        var result = userManagementService.ChangePasswordUser(userChangePasswordDTO, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertTrue(result.Data.getPasswordUpdateSuccessfully());
    }

    @Test
    public void error_ChangePasswordUser_DTO_Is_Null(){
        UserChangePasswordDTO userChangePasswordDTO = new UserChangePasswordDTO("(+55) 67 98114 5503", "ascascasc1212");
        var resultError = new BeanPropertyBindingResult(userChangePasswordDTO, "userCreateValidatorDTO");

        // Act
        var result = userManagementService.ChangePasswordUser(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "DTO Is Null");
    }

    @Test
    public void error_validate_DTO_ChangePasswordUser(){
        UserChangePasswordDTO userChangePasswordDTO = new UserChangePasswordDTO("(+55) 67 98114 5503", "ascascasc1212");
        var resultError = new BeanPropertyBindingResult(userChangePasswordDTO, "UserChangePasswordDTO");

        resultError.rejectValue("Phone", "NotEmpty", "phone should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("Phone", "phone should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = userManagementService.ChangePasswordUser(userChangePasswordDTO, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void should_Throw_Error_GetUserByPhoneInfoUpdate_Return_Null_ChangePasswordUser(){
        UserChangePasswordDTO userChangePasswordDTO = new UserChangePasswordDTO("(+55) 67 98114 5503", "ascascasc1212");
        var resultError = new BeanPropertyBindingResult(userChangePasswordDTO, "userCreateValidatorDTO");

        when(userRepository.GetUserByPhoneInfoUpdate(anyString())).thenReturn(null);

        // Act
        var result = userManagementService.ChangePasswordUser(userChangePasswordDTO, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "user not found");
    }

    @Test
    public void should_Throw_Error_Update_Return_Null_ChangePasswordUser(){
        UserChangePasswordDTO userChangePasswordDTO = new UserChangePasswordDTO("(+55) 67 98114 5503", "ascascasc1212");
        var resultError = new BeanPropertyBindingResult(userChangePasswordDTO, "userCreateValidatorDTO");

        User user = new User();

        when(userRepository.GetUserByPhoneInfoUpdate(anyString())).thenReturn(user);
        when(bCryptPasswordEncoderUtil.encodePassword(anyString())).thenReturn("password");
        when(userRepository.update(any())).thenReturn(null);

        // Act
        var result = userManagementService.ChangePasswordUser(userChangePasswordDTO, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error when updating user");
    }

    @Test
    public void should_ThrowException_When_Update_ChangePasswordUser(){
        UserChangePasswordDTO userChangePasswordDTO = new UserChangePasswordDTO("(+55) 67 98114 5503", "ascascasc1212");
        var resultError = new BeanPropertyBindingResult(userChangePasswordDTO, "userCreateValidatorDTO");
        String expectedErrorMessage = "Database connection error";

        User user = new User();

        when(userRepository.GetUserByPhoneInfoUpdate(anyString())).thenReturn(user);
        when(bCryptPasswordEncoderUtil.encodePassword(anyString())).thenReturn("password");
        when(userRepository.update(any())).thenReturn(null);
        when(userRepository.update(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = userManagementService.ChangePasswordUser(userChangePasswordDTO, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(expectedErrorMessage, result.Message);
    }

    @Test
    public void should_UpdateUserAll_SuccessfullyBase64StringImage_Is_Null(){
        var userId = "6553520b-d0ca-4723-b111-bd736804f299";
        var birthDateString = "05/10/1999";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var birthDate = LocalDateTime.of(LocalDate.parse(birthDateString, formatter), LocalTime.MIN);

        var userUpdateAllDTOValidator = new UserUpdateAllDTOValidator(userId, "name1", "email@gmail.com",
                "m", "(+55) 67 98114 5503", "23456785643", birthDate, null);
        var resultError = new BeanPropertyBindingResult(userUpdateAllDTOValidator, "userUpdateAllDTOValidator");

        User user = new User();
        user.setName("seilaName");

        var updateUserDTO = new UserDTO();
        updateUserDTO.setName("seilaName");

        when(userRepository.GetUserById(any())).thenReturn(new User());
        when(userRepository.update(any())).thenReturn(user);
        when(modelMapper.map(user, UserDTO.class)).thenReturn(updateUserDTO);

        // Act
        var result = userManagementService.UpdateUserAll(userUpdateAllDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, updateUserDTO);
    }

    @Test
    public void should_UpdateUserAll_SuccessfullyBase64StringImage_Is_Not_Null(){
        var userId = "6553520b-d0ca-4723-b111-bd736804f299";
        var birthDateString = "05/10/1999";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var birthDate = LocalDateTime.of(LocalDate.parse(birthDateString, formatter), LocalTime.MIN);

        var userUpdateAllDTOValidator = new UserUpdateAllDTOValidator(userId, "name1", "email@gmail.com",
                "m", "(+55) 67 98114 5503", "23456785643", birthDate,
                "data:image/jpeg;base64,UklGRvRJAABXRUJQVlA4WAoAAAAIAAAAKwEAhQ...");
        var resultError = new BeanPropertyBindingResult(userUpdateAllDTOValidator, "userUpdateAllDTOValidator");

        var UserGetUserById = new User();
        UserGetUserById.setUserImage("imguserdb");

        User user = new User();
        user.setName("seilaName");

        var updateUserDTO = new UserDTO();
        updateUserDTO.setName("seilaName");

        var cloudinaryResult = new CloudinaryResult();
        cloudinaryResult.setDeleteSuccessfully(true);

        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate("publicId", "img_url", true);

        when(userRepository.GetUserById(any())).thenReturn(UserGetUserById);
        when(cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(any())).thenReturn(cloudinaryResult);
        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);
        when(userRepository.update(any())).thenReturn(user);
        when(modelMapper.map(user, UserDTO.class)).thenReturn(updateUserDTO);

        // Act
        var result = userManagementService.UpdateUserAll(userUpdateAllDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, updateUserDTO);
    }

    @Test
    public void error_UpdateUserAll_DTO_Is_Null(){
        var userUpdateAllDTOValidator = new UserUpdateAllDTOValidator();
        var resultError = new BeanPropertyBindingResult(userUpdateAllDTOValidator, "UserUpdateAllDTOValidator");

        // Act
        var result = userManagementService.UpdateUserAll(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "DTO Is Null");
    }

    @Test
    public void error_validate_DTO_UpdateUserAll(){
        var userId = "6553520b-d0ca-4723-b111-bd736804f299";
        var birthDateString = "10/11/1999";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var birthDate = LocalDateTime.of(LocalDate.parse(birthDateString, formatter), LocalTime.MIN);

        var userUpdateAllDTOValidator = new UserUpdateAllDTOValidator(userId, "name1", "email@gmail.com",
                "m", "(+55) 67 98114 5503", "23456785643", birthDate,
                "data:image/jpeg;base64,UklGRvRJAABXRUJQVlA4WAoAAAAIAAAAKwEAhQ...");

        var resultError = new BeanPropertyBindingResult(userUpdateAllDTOValidator, "UserUpdateAllDTOValidator");
        resultError.rejectValue("userId", "NotEmpty", "idGuid should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("Phone", "phone should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = userManagementService.UpdateUserAll(userUpdateAllDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void error_GetUserById_Return_Null_UpdateUserAll(){
        var userId = "6553520b-d0ca-4723-b111-bd736804f299";
        var birthDateString = "05/10/1999";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var birthDate = LocalDateTime.of(LocalDate.parse(birthDateString, formatter), LocalTime.MIN);

        var userUpdateAllDTOValidator = new UserUpdateAllDTOValidator(userId, "name1", "email@gmail.com",
                "m", "(+55) 67 98114 5503", "23456785643", birthDate, null);
        var resultError = new BeanPropertyBindingResult(userUpdateAllDTOValidator, "userUpdateAllDTOValidator");

        when(userRepository.GetUserById(any())).thenReturn(null);

        // Act
        var result = userManagementService.UpdateUserAll(userUpdateAllDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "Error UserToUpdate Is null");
    }

    @Test
    public void should_UpdateCpfAndBirthDayUser_Successfully(){
        var userId = "6553520b-d0ca-4723-b111-bd736804f299";
        var birthDateString = "05/10/1999";

        var userUpdateFillDTOValidator = new UserUpdateFillDTOValidator(userId, "34667854345", birthDateString);
        var resultError = new BeanPropertyBindingResult(userUpdateFillDTOValidator, "userUpdateFillDTOValidator");

        User user = new User();
        var userToUpdateDTO = new UserDTO();

        when(userRepository.GetUserById(any())).thenReturn(new User());
        when(userRepository.update(any())).thenReturn(user);
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userToUpdateDTO);

        // Act
        var result = userManagementService.UpdateCpfAndBirthDayUser(userUpdateFillDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, userToUpdateDTO);
    }

    @Test
    public void error_UpdateCpfAndBirthDayUser_DTO_Is_Null(){
        var userUpdateFillDTOValidator = new UserUpdateFillDTOValidator();
        var resultError = new BeanPropertyBindingResult(userUpdateFillDTOValidator, "UserUpdateFillDTOValidator");

        // Act
        var result = userManagementService.UpdateCpfAndBirthDayUser(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "Error DTO is null");
    }

    @Test
    public void error_validate_DTO_UpdateCpfAndBirthDayUser(){
        var birthDateString = "10/11/1999";

        var userUpdateAllDTOValidator = new UserUpdateFillDTOValidator(null, "34667854345", birthDateString);

        var resultError = new BeanPropertyBindingResult(userUpdateAllDTOValidator, "UserUpdateAllDTOValidator");
        resultError.rejectValue("userId", "NotEmpty", "idGuid should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("userId", "idGuid should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = userManagementService.UpdateCpfAndBirthDayUser(userUpdateAllDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void error_GetUserById_Return_Null_UpdateCpfAndBirthDayUser(){
        var userId = "6553520b-d0ca-4723-b111-bd736804f299";
        var birthDateString = "05/10/1999";

        var userUpdateFillDTOValidator = new UserUpdateFillDTOValidator(userId, "34667854345", birthDateString);
        var resultError = new BeanPropertyBindingResult(userUpdateFillDTOValidator, "userUpdateFillDTOValidator");

        when(userRepository.GetUserById(any())).thenReturn(null);

        // Act
        var result = userManagementService.UpdateCpfAndBirthDayUser(userUpdateFillDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "Error user not found");
    }

    @Test
    public void should_ThrowException_When_Update_UpdateCpfAndBirthDayUser(){
        var userId = "6553520b-d0ca-4723-b111-bd736804f299";
        var birthDateString = "05/10/1999";

        String expectedErrorMessage = "Database connection error";

        var userUpdateFillDTOValidator = new UserUpdateFillDTOValidator(userId, "34667854345", birthDateString);
        var resultError = new BeanPropertyBindingResult(userUpdateFillDTOValidator, "userUpdateFillDTOValidator");

        when(userRepository.GetUserById(any())).thenReturn(new User());
        when(userRepository.update(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = userManagementService.UpdateCpfAndBirthDayUser(userUpdateFillDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(expectedErrorMessage, result.Message);
    }

    @Test
    public void should_DeleteUser_Successfully(){
        var userId = "6553520b-d0ca-4723-b111-bd736804f299";

        var user = new User();
        var userDeleteSuccessfully = new UserDTO();

        when(userRepository.GetUserByIdForDeleteImg(any())).thenReturn(new User());
        when(userRepository.delete(any())).thenReturn(user);
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDeleteSuccessfully);

        // Act
        var result = userManagementService.DeleteUser(UUID.fromString(userId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, userDeleteSuccessfully);
    }

    @Test
    public void should_DeleteUser_And_ImgUser_Successfully(){
        var userId = "6553520b-d0ca-4723-b111-bd736804f299";

        var userDeleteSuccessfully = new UserDTO();

        var cloudinaryResult = new CloudinaryResult();
        cloudinaryResult.setDeleteSuccessfully(true);

        var user = new User();
        user.setUserImage("img-user");

        when(userRepository.GetUserByIdForDeleteImg(any())).thenReturn(user);
        when(cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(anyString())).thenReturn(cloudinaryResult);
        when(userRepository.delete(any())).thenReturn(user);
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDeleteSuccessfully);

        // Act
        var result = userManagementService.DeleteUser(UUID.fromString(userId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, userDeleteSuccessfully);
    }

    @Test
    public void error_when_DeleteImgUser_DeleteUser(){
        var userId = "6553520b-d0ca-4723-b111-bd736804f299";

        var cloudinaryResult = new CloudinaryResult();
        cloudinaryResult.setDeleteSuccessfully(false);
        cloudinaryResult.setMessage("error when delete img cloudinary");

        var user = new User();
        user.setUserImage("img-user");

        when(userRepository.GetUserByIdForDeleteImg(any())).thenReturn(user);
        when(cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(anyString())).thenReturn(cloudinaryResult);

        // Act
        var result = userManagementService.DeleteUser(UUID.fromString(userId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error when delete img cloudinary");
    }

    @Test
    public void error_GetUserByIdForDeleteImg_Return_Null_DeleteUser(){
        var userId = "6553520b-d0ca-4723-b111-bd736804f299";

        when(userRepository.GetUserByIdForDeleteImg(any())).thenReturn(null);

        // Act
        var result = userManagementService.DeleteUser(UUID.fromString(userId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "User not found");
    }

    @Test
    public void should_ThrowException_When_Delete_DeleteUser(){
        var userId = "6553520b-d0ca-4723-b111-bd736804f299";

        String expectedErrorMessage = "Database connection error";

        when(userRepository.GetUserByIdForDeleteImg(any())).thenReturn(new User());
        when(userRepository.delete(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = userManagementService.DeleteUser(UUID.fromString(userId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(expectedErrorMessage, result.Message);
    }
}