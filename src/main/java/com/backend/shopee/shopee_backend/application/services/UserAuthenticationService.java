package com.backend.shopee.shopee_backend.application.services;

import com.backend.shopee.shopee_backend.application.dto.UserDTO;
import com.backend.shopee.shopee_backend.application.dto.UserLoginDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs.CodeSendEmailUserValidatorDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs.CodeSendPhoneDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs.UserConfirmCodeEmailValidatorDTO;
import com.backend.shopee.shopee_backend.application.services.interfaces.IUserAuthenticationService;
import com.backend.shopee.shopee_backend.application.util.interfaces.IDictionaryCode;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ISendEmailUser;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ISendSmsTwilio;
import com.backend.shopee.shopee_backend.domain.InfoErrors.InfoErrors;
import com.backend.shopee.shopee_backend.domain.authentication.ITokenGenerator;
import com.backend.shopee.shopee_backend.domain.authentication.TokenOutValue;
import com.backend.shopee.shopee_backend.domain.entities.User;
import com.backend.shopee.shopee_backend.domain.repositories.IUserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Service
public class UserAuthenticationService implements IUserAuthenticationService {
    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ISendEmailUser sendEmailUser;
    private final ISendSmsTwilio sendSmsTwilio;
    private final IDictionaryCode dictionaryCode;
    private final AuthenticationManager authenticationManager;
    private final ITokenGenerator tokenGenerator;
    private final IValidateErrorsDTO validateErrorsDTO;

    @Autowired
    public UserAuthenticationService(IUserRepository userRepository, ModelMapper modelMapper, ISendEmailUser sendEmailUser, ISendSmsTwilio sendSmsTwilio,
                                     IDictionaryCode dictionaryCode, AuthenticationManager authenticationManager, ITokenGenerator tokenGenerator,
                                     IValidateErrorsDTO validateErrorsDTO) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.sendEmailUser = sendEmailUser;
        this.sendSmsTwilio = sendSmsTwilio;
        this.dictionaryCode = dictionaryCode;
        this.authenticationManager = authenticationManager;
        this.tokenGenerator = tokenGenerator;
        this.validateErrorsDTO = validateErrorsDTO;
    }

    @Override
    public ResultService<UserDTO> GetByIdInfoUser(UUID userId) {
        try {
            var user = userRepository.GetUserByIdInfoUser(userId);

            if (user == null) return ResultService.Fail("User not found");

            return ResultService.Ok(modelMapper.map(user, UserDTO.class));
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<CodeSendPhoneDTOValidator> SendCodePhone(CodeSendPhoneDTOValidator codeSendPhoneDTOValidator, BindingResult result) {
        if(codeSendPhoneDTOValidator == null)
            return ResultService.Fail("Error DTO Informed is null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }
        // tem que ter no numero o "codigo" do pais tipo brasil "+55"
        try {
            var whetherExistPhoneAlready = userRepository.GetUserByPhone(codeSendPhoneDTOValidator.getPhone());

            if(whetherExistPhoneAlready != null)
                return ResultService.Ok(new CodeSendPhoneDTOValidator(codeSendPhoneDTOValidator.getPhone(), "",
                        false, true));

            int randomCode = generateRandomNumber();

            var codeSend = sendSmsTwilio.SendSms(codeSendPhoneDTOValidator.getPhone(), String.valueOf(randomCode));

            if(!codeSend)
                return ResultService.Ok(new CodeSendPhoneDTOValidator(codeSendPhoneDTOValidator.getPhone(), String.valueOf(randomCode),
                        false, false));

//            dictionaryCode.putKeyValueDictionary(codeSendPhoneDTOValidator.getPhone(), randomCode);
            dictionaryCode.putKeyValueDictionary(codeSendPhoneDTOValidator.getUserId(), randomCode);

            return ResultService.Ok(new CodeSendPhoneDTOValidator(codeSendPhoneDTOValidator.getPhone(), String.valueOf(randomCode),
                    true, false));
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<CodeSendEmailUserValidatorDTO> SendCodeEmail(CodeSendEmailUserValidatorDTO codeSendEmailUserValidatorDTO, BindingResult result) {
        if(codeSendEmailUserValidatorDTO == null)
            return ResultService.Fail("Error DTO Informed is null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try {
            User user = userRepository.GetUserByName(codeSendEmailUserValidatorDTO.getName());

            if (user == null)
                return ResultService.Fail("Error user info login is null");

//            if(user.getEmail() != null){
//                return ResultService.Ok(new CodeSendEmailUserValidatorDTO(null, null, null,
//                        false, true, false));
//            }

            var userEmailAlreadyRegistered = userRepository.GetIfUserExistEmail(codeSendEmailUserValidatorDTO.getEmail());

            if(userEmailAlreadyRegistered != null)
                return ResultService.Ok(new CodeSendEmailUserValidatorDTO(null, null, null,
                        false, true, false));

            if(Objects.equals(user.getEmail(), codeSendEmailUserValidatorDTO.getEmail()))
                return ResultService.Ok(new CodeSendEmailUserValidatorDTO(null, null, null,
                        false, false, true));


            user.setEmail(codeSendEmailUserValidatorDTO.getEmail());

            int randomCode = generateRandomNumber();
            dictionaryCode.putKeyValueDictionary(user.getId().toString(), randomCode);

            var resultSend = sendEmailUser.sendCodeRandom(user, randomCode);

            if(!resultSend.IsSuccess){
                return ResultService.Fail(new CodeSendEmailUserValidatorDTO(null, null, resultSend.Data,
                        false, false, false));
            }

             return ResultService.Ok(new CodeSendEmailUserValidatorDTO(null, null, String.valueOf(randomCode),
                    true, false, false));
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<UserDTO> VerifyEmailAlreadySetUp(UserConfirmCodeEmailValidatorDTO userConfirmCodeEmailValidatorDTO, BindingResult result) {
        if(userConfirmCodeEmailValidatorDTO == null)
            return ResultService.Fail("Error DTO Informed is null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try {
            var userId = userConfirmCodeEmailValidatorDTO.getUserId();
            var value = dictionaryCode.getKeyDictionary(String.valueOf(userId));

            if(value != null)
            {
                var user = userRepository.GetUserById(UUID.fromString(userId));

                if (user == null) return ResultService.Fail("user not found");

                if(userConfirmCodeEmailValidatorDTO.getEmail() != null){
                    user.setEmail(userConfirmCodeEmailValidatorDTO.getEmail());

                    var userUpdate = userRepository.update(user);
                    dictionaryCode.removeKeyDictionary(String.valueOf(userUpdate.getId()));

                    var userToReturn = modelMapper.map(userUpdate, UserDTO.class);

                    return ResultService.Ok(userToReturn);
                }

                if(userConfirmCodeEmailValidatorDTO.getPhone() != null){
                    user.setPhone(userConfirmCodeEmailValidatorDTO.getPhone());

                    var userUpdate = userRepository.update(user);
                    dictionaryCode.removeKeyDictionary(String.valueOf(userUpdate.getId()));

                    return ResultService.Ok(modelMapper.map(userUpdate, UserDTO.class));
                }

                dictionaryCode.removeKeyDictionary(userId);
                var userDtoMapper = modelMapper.map(user, UserDTO.class);
                return ResultService.Ok(userDtoMapper);
            }else {
                return ResultService.Fail("Error Code Not Found");
            }
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public ResultService<UserLoginDTO> Login(String phone, String password) {
        var userLoginDTO = new UserLoginDTO();

        try {
            User user = userRepository.GetUserInfoToLogin(phone);

            if (user == null) return ResultService.Fail("Error user info login is null");

            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(phone, password);
            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            User userAuth = (User) authenticate.getPrincipal();

            InfoErrors<TokenOutValue> tokenOut = tokenGenerator.generatorTokenUser(userAuth);

            if(!tokenOut.IsSuccess)
                return ResultService.Fail(tokenOut.Message);

            int randomCode = generateRandomNumber();
            dictionaryCode.putKeyValueDictionary(userAuth.getId().toString(), randomCode);
            //InfoErrors<String> resultSendCodeEmail = sendEmailUser.sendCodeRandom(userAuth, randomCode);

            userAuth.setName(user.getName());
            userAuth.setPasswordHash(null);
            var userDTO = modelMapper.map(userAuth, UserDTO.class);

            if(userDTO == null)
                return ResultService.Fail("error in null class mapping");

            userDTO.setToken(tokenOut.Data.getAccess_Token());

            userLoginDTO.setPasswordIsCorrect(true);
            userLoginDTO.setUserDTO(userDTO);

            return ResultService.Ok(userLoginDTO);
        } catch (Exception ex) {
            userLoginDTO.setPasswordIsCorrect(false);
            userLoginDTO.setMessage(ex.getMessage());
            return ResultService.Fail(userLoginDTO);
        }
    }

    @Override
    public ResultService<UserLoginDTO> VerifyPasswordUser(String phone, String password) {
        var userLoginDTO = new UserLoginDTO();

        try {
            var user = userRepository.GetUserInfoToLogin(phone);

            if(user == null) return ResultService.Fail("User not found");

            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(phone, password);
            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            User userAuth = (User) authenticate.getPrincipal();

            userAuth.setPasswordHash(null);

            userLoginDTO.setPasswordIsCorrect(true);
            userLoginDTO.setUserDTO(modelMapper.map(userAuth, UserDTO.class));

            return ResultService.Ok(userLoginDTO);
        }catch (Exception ex){
            userLoginDTO.setPasswordIsCorrect(false);
            userLoginDTO.setMessage(ex.getMessage());
            userLoginDTO.setUserDTO(null);
            return ResultService.Fail(userLoginDTO);
        }
    }

    private static int generateRandomNumber(){
        Random random = new Random();
        return random.nextInt(900000) + 100000;
    }
}
