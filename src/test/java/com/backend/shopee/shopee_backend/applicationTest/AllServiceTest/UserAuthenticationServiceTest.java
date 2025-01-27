package com.backend.shopee.shopee_backend.applicationTest.AllServiceTest;

import com.backend.shopee.shopee_backend.application.dto.UserDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs.CodeSendEmailUserValidatorDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs.CodeSendPhoneDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs.UserConfirmCodeEmailValidatorDTO;
import com.backend.shopee.shopee_backend.application.services.ErrorValidation;
import com.backend.shopee.shopee_backend.application.services.UserAuthenticationService;
import com.backend.shopee.shopee_backend.application.util.interfaces.IDictionaryCode;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ISendEmailUser;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ISendSmsTwilio;
import com.backend.shopee.shopee_backend.domain.InfoErrors.InfoErrors;
import com.backend.shopee.shopee_backend.domain.authentication.ITokenGenerator;
import com.backend.shopee.shopee_backend.domain.authentication.TokenOutValue;
import com.backend.shopee.shopee_backend.domain.entities.User;
import com.backend.shopee.shopee_backend.domain.repositories.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BeanPropertyBindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserAuthenticationServiceTest {
    @Mock
    private IUserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private IValidateErrorsDTO validateErrorsDTO;
    @Mock
    private ISendEmailUser sendEmailUser;
    @Mock
    private ISendSmsTwilio sendSmsTwilio;
    @Mock
    private IDictionaryCode dictionaryCode;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private ITokenGenerator tokenGenerator;
    private Authentication authenticate;

    private UserAuthenticationService userAuthenticationService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        userAuthenticationService = new UserAuthenticationService(userRepository, modelMapper, sendEmailUser,
                sendSmsTwilio, dictionaryCode, authenticationManager, tokenGenerator, validateErrorsDTO);

        authenticate = mock(Authentication.class);
    }

    @Test
    public void should_SendCodePhone_Successfully() {
        var codeSendPhoneDTOValidator = new CodeSendPhoneDTOValidator("(+55) 67 98114 5503", "334567",
                false, false);
        var resultError = new BeanPropertyBindingResult(codeSendPhoneDTOValidator, "codeSendPhoneDTOValidator");

        when(userRepository.GetUserByPhone(anyString())).thenReturn(null);
        when(sendSmsTwilio.SendSms(anyString(), anyString())).thenReturn(true);

        // Act
        var result = userAuthenticationService.SendCodePhone(codeSendPhoneDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data.getCodeSendToPhone(), true);
    }

    @Test
    public void error_SendCodePhone_DTO_Is_Null(){
        var codeSendPhoneDTOValidator = new CodeSendPhoneDTOValidator();

        var resultError = new BeanPropertyBindingResult(codeSendPhoneDTOValidator, "CodeSendPhoneDTOValidator");

        // Act
        var result = userAuthenticationService.SendCodePhone(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "Error DTO Informed is null");
    }

    @Test
    public void error_validate_DTO_SendCodePhone(){
        var codeSendPhoneDTOValidator = new CodeSendPhoneDTOValidator("(+55) 67 98114 5503", "334567",
                false, false);
        var resultError = new BeanPropertyBindingResult(codeSendPhoneDTOValidator, "CodeSendPhoneDTOValidator");

        resultError.rejectValue("Phone", "NotEmpty", "phone should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("Phone", "phone should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = userAuthenticationService.SendCodePhone(codeSendPhoneDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void error_GetUserByPhone_Return_Obj_Phone_Already_Registered_SendCodePhone() {
        var codeSendPhoneDTOValidator = new CodeSendPhoneDTOValidator("(+55) 67 98114 5503", "334567",
                false, false);
        var resultError = new BeanPropertyBindingResult(codeSendPhoneDTOValidator, "codeSendPhoneDTOValidator");

        when(userRepository.GetUserByPhone(anyString())).thenReturn(new User());

        // Act
        var result = userAuthenticationService.SendCodePhone(codeSendPhoneDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data.getPhoneAlreadyRegistered(), true);
    }

    @Test
    public void SendSms_should_Return_False_SendCodePhone() {
        var codeSendPhoneDTOValidator = new CodeSendPhoneDTOValidator("(+55) 67 98114 5503", "334567",
                false, false);
        var resultError = new BeanPropertyBindingResult(codeSendPhoneDTOValidator, "codeSendPhoneDTOValidator");

        when(userRepository.GetUserByPhone(anyString())).thenReturn(null);
        when(sendSmsTwilio.SendSms(anyString(), anyString())).thenReturn(false);

        // Act
        var result = userAuthenticationService.SendCodePhone(codeSendPhoneDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data.getCodeSendToPhone(), false);
    }

    @Test
    public void should_ThrowException_When_GetUserByPhone_SendCodePhone(){
        var codeSendPhoneDTOValidator = new CodeSendPhoneDTOValidator("(+55) 67 98114 5503", "334567",
                false, false);
        String expectedErrorMessage = "Database connection error";

        var resultError = new BeanPropertyBindingResult(codeSendPhoneDTOValidator, "codeSendPhoneDTOValidator");

        when(userRepository.GetUserByPhone(anyString())).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = userAuthenticationService.SendCodePhone(codeSendPhoneDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(expectedErrorMessage, result.Message);
    }

    @Test
    public void should_SendCodeEmail_Successfully() {
        var codeSendEmailUserValidatorDTO = new CodeSendEmailUserValidatorDTO("name1", "email@gmail.com",
                "234455", false, false, false);
        var resultError = new BeanPropertyBindingResult(codeSendEmailUserValidatorDTO, "CodeSendEmailUserValidatorDTO");

        var userGetUserByName = new User();
        userGetUserByName.setId(UUID.fromString("6dcb3504-9ea1-4c0b-bfd5-8d4a3831a128"));

        when(userRepository.GetUserByName(anyString())).thenReturn(userGetUserByName);
        when(userRepository.GetIfUserExistEmail(anyString())).thenReturn(null);
        when(sendEmailUser.sendCodeRandom(any(), anyInt()))
                .thenReturn(new InfoErrors<String>(true, "all very well", null));

        // Act
        var result = userAuthenticationService.SendCodeEmail(codeSendEmailUserValidatorDTO, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data.getCodeSendToEmailSuccessfully(), true);
    }

    @Test
    public void error_SendCodeEmail_DTO_Is_Null(){
        var codeSendEmailUserValidatorDTO = new CodeSendEmailUserValidatorDTO();

        var resultError = new BeanPropertyBindingResult(codeSendEmailUserValidatorDTO, "CodeSendEmailUserValidatorDTO");

        // Act
        var result = userAuthenticationService.SendCodeEmail(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "Error DTO Informed is null");
    }

    @Test
    public void error_validate_DTO_SendCodeEmail(){
        var codeSendEmailUserValidatorDTO = new CodeSendEmailUserValidatorDTO("name1", "email@gmail.com",
                "234455", false, false, false);
        var resultError = new BeanPropertyBindingResult(codeSendEmailUserValidatorDTO, "CodeSendEmailUserValidatorDTO");

        resultError.rejectValue("Name", "NotEmpty", "Name should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("Name", "Name should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = userAuthenticationService.SendCodeEmail(codeSendEmailUserValidatorDTO, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void GetIfUserExistEmail_should_Return_User_AlreadyExist_SendCodeEmail() {
        var codeSendEmailUserValidatorDTO = new CodeSendEmailUserValidatorDTO("name1", "email@gmail.com",
                "234455", false, false, false);
        var resultError = new BeanPropertyBindingResult(codeSendEmailUserValidatorDTO, "CodeSendEmailUserValidatorDTO");

        var userReturn = new User();

        when(userRepository.GetUserByName(anyString())).thenReturn(new User());
        when(userRepository.GetIfUserExistEmail(anyString())).thenReturn(userReturn);

        // Act
        var result = userAuthenticationService.SendCodeEmail(codeSendEmailUserValidatorDTO, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data.getUserAlreadyExist(), true);
    }

    @Test
    public void GetIfUserExistEmail_should_Return_User_EmailAlreadyRegistered_SendCodeEmail() {
        var codeSendEmailUserValidatorDTO = new CodeSendEmailUserValidatorDTO("name1", "augusto@gmail.com",
                "234455", false, false, false);
        var resultError = new BeanPropertyBindingResult(codeSendEmailUserValidatorDTO, "CodeSendEmailUserValidatorDTO");

        var userAlready = new User();
        userAlready.setEmail("augusto@gmail.com");

        when(userRepository.GetUserByName(anyString())).thenReturn(userAlready);
        when(userRepository.GetIfUserExistEmail(anyString())).thenReturn(null);

        // Act
        var result = userAuthenticationService.SendCodeEmail(codeSendEmailUserValidatorDTO, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data.getEmailAlreadyRegistered(), true);
    }

    @Test
    public void should_SendCodeEmail_Error_Code_Not_Send_To_Email() {
        var codeSendEmailUserValidatorDTO = new CodeSendEmailUserValidatorDTO("name1", "email@gmail.com",
                "234455", false, false, false);
        var resultError = new BeanPropertyBindingResult(codeSendEmailUserValidatorDTO, "CodeSendEmailUserValidatorDTO");

        var userGetUserByName = new User();
        userGetUserByName.setId(UUID.fromString("6dcb3504-9ea1-4c0b-bfd5-8d4a3831a128"));

        when(userRepository.GetUserByName(anyString())).thenReturn(userGetUserByName);
        when(userRepository.GetIfUserExistEmail(anyString())).thenReturn(null);
        when(sendEmailUser.sendCodeRandom(any(), anyInt()))
                .thenReturn(new InfoErrors<String>(false, "error", null));

        // Act
        var result = userAuthenticationService.SendCodeEmail(codeSendEmailUserValidatorDTO, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Data.getCodeSendToEmailSuccessfully(), false);
    }

    @Test
    public void should_VerifyEmailAlreadySetUp_Email_Is_Not_Null_Successfully() {
        var userConfirmCodeEmailValidatorDTO = new UserConfirmCodeEmailValidatorDTO("234566",
                "6dcb3504-9ea1-4c0b-bfd5-8d4a3831a128", "augusto@gmail.com", "(+55) 67 98114 5503");
//        UserConfirmCodeEmailValidatorDTO(String code, String userId, String email, String phone)
        var resultError = new BeanPropertyBindingResult(userConfirmCodeEmailValidatorDTO, "UserConfirmCodeEmailValidatorDTO");

        User user = new User();
        var userUpdate = new UserDTO();
        userUpdate.setId(UUID.fromString("219d0f58-fad1-4339-b477-20b977021046"));

        when(dictionaryCode.getKeyDictionary(anyString())).thenReturn(323);
        when(userRepository.GetUserById(any())).thenReturn(new User());
        when(userRepository.update(any())).thenReturn(user);
        when(modelMapper.map(any(User.class), eq(UserDTO.class))).thenReturn(userUpdate);

        // Act
        var result = userAuthenticationService.VerifyEmailAlreadySetUp(userConfirmCodeEmailValidatorDTO, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, userUpdate);
    }

    @Test
    public void should_VerifyEmailAlreadySetUp_Phone_Is_Not_Null_And_Email_Is_Null_Successfully() {
        var userConfirmCodeEmailValidatorDTO = new UserConfirmCodeEmailValidatorDTO("234566",
                "6dcb3504-9ea1-4c0b-bfd5-8d4a3831a128", null, "(+55) 67 98114 5503");
//        UserConfirmCodeEmailValidatorDTO(String code, String userId, String email, String phone)
        var resultError = new BeanPropertyBindingResult(userConfirmCodeEmailValidatorDTO, "UserConfirmCodeEmailValidatorDTO");

        User user = new User();
        var userUpdate = new UserDTO();
        userUpdate.setId(UUID.fromString("219d0f58-fad1-4339-b477-20b977021046"));

        when(dictionaryCode.getKeyDictionary(anyString())).thenReturn(323);
        when(userRepository.GetUserById(any())).thenReturn(new User());
        when(userRepository.update(any())).thenReturn(user);
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userUpdate);

        // Act
        var result = userAuthenticationService.VerifyEmailAlreadySetUp(userConfirmCodeEmailValidatorDTO, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, userUpdate);
    }

    @Test
    public void should_VerifyEmailAlreadySetUp_Phone_Is_Null_And_Email_Is_Null_Successfully() {
        var userConfirmCodeEmailValidatorDTO = new UserConfirmCodeEmailValidatorDTO("234566",
                "6dcb3504-9ea1-4c0b-bfd5-8d4a3831a128", null, null);
//        UserConfirmCodeEmailValidatorDTO(String code, String userId, String email, String phone)
        var resultError = new BeanPropertyBindingResult(userConfirmCodeEmailValidatorDTO, "UserConfirmCodeEmailValidatorDTO");

        User user = new User();
        var userDTO = new UserDTO();
        userDTO.setId(UUID.fromString("219d0f58-fad1-4339-b477-20b977021046"));

        when(dictionaryCode.getKeyDictionary(anyString())).thenReturn(323);
        when(userRepository.GetUserById(any())).thenReturn(user);
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        // Act
        var result = userAuthenticationService.VerifyEmailAlreadySetUp(userConfirmCodeEmailValidatorDTO, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, userDTO);
    }

    @Test
    public void should_VerifyEmailAlreadySetUp_DictionaryCode_Return_Null_Successfully() {
        var userConfirmCodeEmailValidatorDTO = new UserConfirmCodeEmailValidatorDTO("234566",
                "6dcb3504-9ea1-4c0b-bfd5-8d4a3831a128", null, null);
//        UserConfirmCodeEmailValidatorDTO(String code, String userId, String email, String phone)
        var resultError = new BeanPropertyBindingResult(userConfirmCodeEmailValidatorDTO, "UserConfirmCodeEmailValidatorDTO");

        when(dictionaryCode.getKeyDictionary(anyString())).thenReturn(null);

        // Act
        var result = userAuthenticationService.VerifyEmailAlreadySetUp(userConfirmCodeEmailValidatorDTO, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "Error Code Not Found");
    }

    @Test
    public void should_Login_Successfully() {
        String phone = "(+55) 67 98114 5503";
        String password = "password1";

        var userGetUserByName = new User();
        userGetUserByName.setId(UUID.fromString("cf0a72ef-9864-4a9b-93aa-2d1a3eaa0654"));

        var TokenOutValue = new TokenOutValue("access_token_random", null);

        var userDTO = new UserDTO();
        userDTO.setId(UUID.fromString("219d0f58-fad1-4339-b477-20b977021046"));

        when(userRepository.GetUserInfoToLogin(anyString())).thenReturn(userGetUserByName);

        when(authenticate.isAuthenticated()).thenReturn(true);
        when(authenticate.getPrincipal()).thenReturn(userGetUserByName);
        when(authenticationManager.authenticate(any())).thenReturn(authenticate);

        when(tokenGenerator.generatorTokenUser(any()))
                .thenReturn(InfoErrors.Ok(TokenOutValue));

        when(modelMapper.map(any(User.class), eq(UserDTO.class))).thenReturn(userDTO);

        // Act
        var result = userAuthenticationService.Login(phone, password);

        // Assert
        assertTrue(result.IsSuccess);
        assertTrue(result.Data.getPasswordIsCorrect());
    }

    @Test
    public void error_GetUserInfoToLogin_Return_Null_Login() {
        String phone = "(+55) 67 98114 5503";
        String password = "password1";

        when(userRepository.GetUserInfoToLogin(anyString())).thenReturn(null);

        // Act
        var result = userAuthenticationService.Login(phone, password);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "Error user info login is null");
    }

    @Test
    public void error_Credentials_User_Is_Wrong_Login() {
        String phone = "(+55) 67 98114 5503";
        String password = "password1";

        var userGetUserByName = new User();
        userGetUserByName.setId(UUID.fromString("cf0a72ef-9864-4a9b-93aa-2d1a3eaa0654"));

        when(userRepository.GetUserInfoToLogin(anyString())).thenReturn(userGetUserByName);

        when(authenticate.isAuthenticated()).thenReturn(false);
        when(authenticationManager.authenticate(any())).thenThrow(new BadCredentialsException("User Not Exist or password invalid"));

        // Act
        var result = userAuthenticationService.Login(phone, password);

        // Assert
        assertFalse(result.IsSuccess);
        assertFalse(result.Data.getPasswordIsCorrect());
        assertEquals(result.Data.getMessage(), "User Not Exist or password invalid");
    }

    @Test
    public void error_Token_Generate_Login() {
        String phone = "(+55) 67 98114 5503";
        String password = "password1";

        var userGetUserByName = new User();
        userGetUserByName.setId(UUID.fromString("cf0a72ef-9864-4a9b-93aa-2d1a3eaa0654"));

        var TokenOutValue = new TokenOutValue("access_token_random", null);

        when(userRepository.GetUserInfoToLogin(anyString())).thenReturn(userGetUserByName);

        when(authenticate.isAuthenticated()).thenReturn(true);
        when(authenticate.getPrincipal()).thenReturn(userGetUserByName);
        when(authenticationManager.authenticate(any())).thenReturn(authenticate);

        when(tokenGenerator.generatorTokenUser(any()))
                .thenReturn(InfoErrors.Fail(TokenOutValue, "error token"));

        // Act
        var result = userAuthenticationService.Login(phone, password);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error token");
    }

    @Test
    public void error_Map_Obj_To_DTO_Login() {
        String phone = "(+55) 67 98114 5503";
        String password = "password1";

        var userGetUserByName = new User();
        userGetUserByName.setId(UUID.fromString("cf0a72ef-9864-4a9b-93aa-2d1a3eaa0654"));

        var TokenOutValue = new TokenOutValue("access_token_random", null);

        when(userRepository.GetUserInfoToLogin(anyString())).thenReturn(userGetUserByName);

        when(authenticate.isAuthenticated()).thenReturn(true);
        when(authenticate.getPrincipal()).thenReturn(userGetUserByName);
        when(authenticationManager.authenticate(any())).thenReturn(authenticate);

        when(tokenGenerator.generatorTokenUser(any()))
                .thenReturn(InfoErrors.Ok(TokenOutValue));

        when(modelMapper.map(any(User.class), eq(UserDTO.class))).thenReturn(null);

        // Act
        var result = userAuthenticationService.Login(phone, password);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error in null class mapping");
    }

    @Test
    public void error_Throw_Exception_GetUserInfoToLogin_Login() {
        String phone = "(+55) 67 98114 5503";
        String password = "password1";

        String expectedErrorMessage = "Database connection error";

        when(userRepository.GetUserInfoToLogin(anyString())).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = userAuthenticationService.Login(phone, password);

        // Assert
        assertFalse(result.IsSuccess);
        assertFalse(result.Data.getPasswordIsCorrect());
        assertEquals(result.Data.getMessage(), expectedErrorMessage);
    }

    @Test
    public void should_VerifyPasswordUser_Successfully() {
        String phone = "(+55) 67 98114 5503";
        String password = "password1";

        var userGetUserInfoToLogin = new User();
        userGetUserInfoToLogin.setId(UUID.fromString("cf0a72ef-9864-4a9b-93aa-2d1a3eaa0654"));

        var userDTO = new UserDTO();
        userDTO.setId(UUID.fromString("219d0f58-fad1-4339-b477-20b977021046"));

        when(userRepository.GetUserInfoToLogin(anyString())).thenReturn(userGetUserInfoToLogin);
        when(authenticate.isAuthenticated()).thenReturn(true);
        when(authenticate.getPrincipal()).thenReturn(userGetUserInfoToLogin);
        when(authenticationManager.authenticate(any())).thenReturn(authenticate);
        when(modelMapper.map(any(User.class), eq(UserDTO.class))).thenReturn(userDTO);

        // Act
        var result = userAuthenticationService.VerifyPasswordUser(phone, password);

        // Assert
        assertTrue(result.IsSuccess);
        assertTrue(result.Data.getPasswordIsCorrect());
    }

    @Test
    public void error_GetUserInfoToLogin_Return_Null_VerifyPasswordUser() {
        String phone = "(+55) 67 98114 5503";
        String password = "password1";

        when(userRepository.GetUserInfoToLogin(anyString())).thenReturn(null);

        // Act
        var result = userAuthenticationService.VerifyPasswordUser(phone, password);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "User not found");
    }

    @Test
    public void error_Credentials_User_Is_Wrong_VerifyPasswordUser() {
        String phone = "(+55) 67 98114 5503";
        String password = "password1";

        var userGetUserByName = new User();
        userGetUserByName.setId(UUID.fromString("cf0a72ef-9864-4a9b-93aa-2d1a3eaa0654"));

        when(userRepository.GetUserInfoToLogin(anyString())).thenReturn(userGetUserByName);

        when(authenticate.isAuthenticated()).thenReturn(false);
        when(authenticationManager.authenticate(any())).thenThrow(new BadCredentialsException("User Not Exist or password invalid"));

        // Act
        var result = userAuthenticationService.VerifyPasswordUser(phone, password);

        // Assert
        assertFalse(result.IsSuccess);
        assertFalse(result.Data.getPasswordIsCorrect());
        assertEquals(result.Data.getMessage(), "User Not Exist or password invalid");
    }

    @Test
    public void error_Throw_Exception_GetUserInfoToLogin_VerifyPasswordUser() {
        String phone = "(+55) 67 98114 5503";
        String password = "password1";

        String expectedErrorMessage = "Database connection error";

        when(userRepository.GetUserInfoToLogin(anyString())).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = userAuthenticationService.VerifyPasswordUser(phone, password);

        // Assert
        assertFalse(result.IsSuccess);
        assertFalse(result.Data.getPasswordIsCorrect());
        assertEquals(result.Data.getMessage(), expectedErrorMessage);
    }
}