package com.backend.shopee.shopee_backend.application.services;

import com.backend.shopee.shopee_backend.application.dto.UserChangePasswordDTO;
import com.backend.shopee.shopee_backend.application.dto.UserDTO;
import com.backend.shopee.shopee_backend.application.dto.UserPasswordUpdateDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs.UserCreateValidatorDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs.UserUpdateAllDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs.UserUpdateFillDTOValidator;
import com.backend.shopee.shopee_backend.application.services.interfaces.IUserManagementService;
import com.backend.shopee.shopee_backend.application.util.interfaces.IBCryptPasswordEncoderUtil;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.shopee.shopee_backend.domain.entities.User;
import com.backend.shopee.shopee_backend.domain.repositories.IUserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Random;
import java.util.UUID;

@Service
public class UserManagementService implements IUserManagementService {
    private final IUserRepository userRepository;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final IBCryptPasswordEncoderUtil bCryptPasswordEncoder;
    private final ICloudinaryUti cloudinaryUti;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public UserManagementService(IUserRepository userRepository, IValidateErrorsDTO validateErrorsDTO, IBCryptPasswordEncoderUtil bCryptPasswordEncoder,
                                 ICloudinaryUti cloudinaryUti) {
        this.userRepository = userRepository;
        this.validateErrorsDTO = validateErrorsDTO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.cloudinaryUti = cloudinaryUti;
    }

    @Override
    @Transactional
    public ResultService<UserDTO> findById(String phone) {
        try {
            User user = userRepository.GetUserByPhoneInfoUpdate(phone);

            if(user == null){
                return ResultService.Fail("not found");
            }

            var userMap = modelMapper.map(user, UserDTO.class);
            return ResultService.Ok(userMap); // testar isso para ver se o "Mapper" vai funcionar
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<UserDTO> create(UserCreateValidatorDTO userCreateValidatorDTO, BindingResult result) {
        if(userCreateValidatorDTO == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        User userCreate = new User();
        // fazer login, tem que testar o "DELETE" quando deletar um registro e deletar a imagem, porque tem que retirar da img o "publicID"
        try {
            String passwordEncoder = bCryptPasswordEncoder.encodePassword(userCreateValidatorDTO.getPassword());
            UUID uuid_user_id = UUID.randomUUID();

            String randomName = generateRandomName(8);

            if(userCreateValidatorDTO.getBase64ImageUser() != null){

                var resultCreate = cloudinaryUti.CreateMedia(userCreateValidatorDTO.getBase64ImageUser(), "img-user", 320, 320);

                if (resultCreate.getImgUrl() == null || resultCreate.getPublicId() == null)
                {
                    return ResultService.Fail("error when create ImgPerfil");
                }

                userCreate = new User(uuid_user_id, randomName, null, null, userCreateValidatorDTO.getPhone(),
                        passwordEncoder, null, null, (short)0, resultCreate.getImgUrl());

            }else {
                userCreate = new User(uuid_user_id, randomName, null, null, userCreateValidatorDTO.getPhone(),
                        passwordEncoder, null, null, (short)0, null);
            }

            var userData = userRepository.create(userCreate);

            var userMap = modelMapper.map(userData, UserDTO.class);

            return ResultService.Ok(userMap);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    private static String generateRandomName(Integer length) {
        Random random = new Random();

        // Definir os caracteres possíveis para a string aleatória
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";

        // Prefixo fixo
        String prefix = "e_";

        // Gerar a parte aleatória da string
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            randomString.append(chars.charAt(index));
        }

        // Retornar a string com o prefixo
        return prefix + randomString.toString();
    }

    @Override
    public ResultService<UserPasswordUpdateDTO> ChangePasswordUser(UserChangePasswordDTO userChangePasswordDTO, BindingResult resultValid) {
        if(userChangePasswordDTO == null)
            return ResultService.Fail("obj null");

        try {
            if(resultValid.hasErrors()){
                var errorsDTO = resultValid.getAllErrors();
                var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

                return ResultService.RequestError("error validate DTO", errors);
            }

            var user = userRepository.GetUserByPhoneInfoUpdate(userChangePasswordDTO.getPhone());

            if(user == null)
                return ResultService.Fail("user not found");

            String passwordEncoder = bCryptPasswordEncoder.encodePassword(userChangePasswordDTO.getConfirmPassword());

            user.setPasswordHash(passwordEncoder);

            var userChange = userRepository.update(user);

            if(userChange == null)
                return ResultService.Fail("error when updating user");

            var modelUser = new UserPasswordUpdateDTO(true);

            return ResultService.Ok(modelUser);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<UserDTO> UpdateUserAll(UserUpdateAllDTOValidator userUpdateAllDTOValidator, BindingResult resultValid) {
        if(userUpdateAllDTOValidator == null)
            return ResultService.Fail("obj null");

        try {
            if(resultValid.hasErrors()){
                var errorsDTO = resultValid.getAllErrors();
                var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

                return ResultService.RequestError("error validate DTO", errors);
            }

            var userToUpdate = userRepository.GetUserById(UUID.fromString(userUpdateAllDTOValidator.getUserId()));

            if(userToUpdate == null)
                return ResultService.Fail("Error UserToUpdate Is null");

            if(userUpdateAllDTOValidator.getBase64StringImage() != null){
                var deleteFound = cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(userToUpdate.getUserImage());

                if(!deleteFound.getDeleteSuccessfully())
                    return ResultService.Fail(deleteFound.getMessage());

                var resultCreate = cloudinaryUti.CreateMedia(userUpdateAllDTOValidator.getBase64StringImage(), "img-user", 320, 320);

                if (!resultCreate.getCreatedSuccessfully())
                    return ResultService.Fail("Invalid media type. Only images and videos are supported");

                if (resultCreate.getImgUrl() == null || resultCreate.getPublicId() == null)
                    return ResultService.Fail("error when create ImgPerfil");

                userToUpdate.setName(userUpdateAllDTOValidator.getName());
                userToUpdate.setEmail(userUpdateAllDTOValidator.getEmail());
                userToUpdate.setGender(userUpdateAllDTOValidator.getGender());
                userToUpdate.setPhone(userUpdateAllDTOValidator.getPhone());
                userToUpdate.setUserImage(resultCreate.getImgUrl());

                var updateUser = userRepository.update(userToUpdate);

                if(updateUser == null)
                    return ResultService.Fail("error updateUser is null");

                return ResultService.Ok(modelMapper.map(updateUser, UserDTO.class));
            }

            return ResultService.Fail("Error Base64StringImage Is null");
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<UserDTO> UpdateUser(UserUpdateFillDTOValidator userUpdateFillDTOValidator, BindingResult resultValid) {
        return null; // fazer isso amanha e Ver o bagui de mandar codigo para Numero de Celular
        // Mais eu acho que o bagui do celular nao vai dar porque foi dificil de achar e nao tem gratis
        // no frontend aqui nesse component "FirstStepCreateAccount" tem como eu fiz o codigo e tal sem precisar mandar para celular nessa func "onClickResendCode"
    }

    @Override
    public ResultService<UserDTO> DeleteUser(UUID userID) {
        try {
            var userDelete = userRepository.GetUserByIdForDeleteImg(userID);

            if(userDelete == null)
                return ResultService.Fail("User not found");

            var deleteFound = cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(userDelete.getUserImage());

            if(!deleteFound.getDeleteSuccessfully())
                return ResultService.Fail(deleteFound.getMessage());

            var userDeleteSuccessfully = userRepository.delete(userDelete.getId());

            return ResultService.Ok(modelMapper.map(userDeleteSuccessfully, UserDTO.class));

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }
}
