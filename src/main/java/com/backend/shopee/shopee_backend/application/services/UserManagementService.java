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
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryCreate;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.shopee.shopee_backend.domain.entities.User;
import com.backend.shopee.shopee_backend.domain.repositories.IUserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;
import java.util.UUID;

@Service
public class UserManagementService implements IUserManagementService {
    private final IUserRepository userRepository;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final IBCryptPasswordEncoderUtil bCryptPasswordEncoder;
    private final ICloudinaryUti cloudinaryUti;
    private final ModelMapper modelMapper;

    @Autowired
    public UserManagementService(IUserRepository userRepository, IValidateErrorsDTO validateErrorsDTO, IBCryptPasswordEncoderUtil bCryptPasswordEncoder,
                                 ICloudinaryUti cloudinaryUti, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.validateErrorsDTO = validateErrorsDTO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.cloudinaryUti = cloudinaryUti;
        this.modelMapper = modelMapper;
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
    @Transactional
    public ResultService<UserDTO> findByIdOnly(String userId) {
        try {
            User user = userRepository.GetUserById(UUID.fromString(userId));

            if(user == null){
                return ResultService.Fail("not found");
            }

            user.setPasswordHash(null);

            var userMap = modelMapper.map(user, UserDTO.class);
            return ResultService.Ok(userMap); // testar isso para ver se o "Mapper" vai funcionar
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public ResultService<UserDTO> VerifyWhetherUserExist(UUID userId) {
        try {
            User user = userRepository.GetUserByIdForDeleteImg(userId);

            if(user == null){
                return ResultService.Fail("not found");
            }

            user.setPasswordHash(null);

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
            return ResultService.Fail("DTO Is Null");

        if(resultValid.hasErrors()){
            var errorsDTO = resultValid.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try {
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
            return ResultService.Fail("DTO Is Null");

        if(resultValid.hasErrors()){
            var errorsDTO = resultValid.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try {
            var userToUpdate = userRepository.GetUserById(UUID.fromString(userUpdateAllDTOValidator.getUserId()));

            if(userToUpdate == null)
                return ResultService.Fail("Error UserToUpdate Is null");

            if(userUpdateAllDTOValidator.getBase64StringImage() != null && !userUpdateAllDTOValidator.getBase64StringImage().isEmpty()){
                if(userToUpdate.getUserImage() != null && !userToUpdate.getUserImage().isEmpty()){
                    var deleteFound = cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(userToUpdate.getUserImage());

                    if(!deleteFound.getDeleteSuccessfully())
                        return ResultService.Fail(deleteFound.getMessage());
                }

                CloudinaryCreate resultCreate = cloudinaryUti.CreateMedia(userUpdateAllDTOValidator.getBase64StringImage(), "img-user", 320, 320);

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

                var updateUserDTO = modelMapper.map(updateUser, UserDTO.class);

                return ResultService.Ok(updateUserDTO);
            }

            userToUpdate.setName(userUpdateAllDTOValidator.getName());
            userToUpdate.setEmail(userUpdateAllDTOValidator.getEmail());
            userToUpdate.setGender(userUpdateAllDTOValidator.getGender());
            userToUpdate.setPhone(userUpdateAllDTOValidator.getPhone());

            var updateUser = userRepository.update(userToUpdate);

            var updateUserDTO = modelMapper.map(updateUser, UserDTO.class);

            return ResultService.Ok(updateUserDTO);
//            return ResultService.Fail("Error Base64StringImage Is null");
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<UserDTO> UpdateCpfAndBirthDayUser(UserUpdateFillDTOValidator userUpdateFillDTOValidator, BindingResult resultValid) {
        try  {
            if(userUpdateFillDTOValidator == null)
                return ResultService.Fail("Error DTO is null");

            if(resultValid.hasErrors()){
                var errorsDTO = resultValid.getAllErrors();
                var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

                return ResultService.RequestError("error validate DTO", errors);
            }

            var userToUpdate = userRepository.GetUserById(UUID.fromString(userUpdateFillDTOValidator.getUserId()));

            if(userToUpdate == null)
                return ResultService.Fail("Error user not found");

            var stringSplit = userUpdateFillDTOValidator.getBirthDate().split("/");
            int dia = Integer.parseInt(stringSplit[0]);
            int mes = Integer.parseInt(stringSplit[1]);
            int ano = Integer.parseInt(stringSplit[2]);

            LocalDate birthDate = LocalDate.of(ano, mes, dia);
            ZonedDateTime birthDateUtc = birthDate.atStartOfDay(ZoneId.of("UTC"));
            LocalDateTime birthDateLocalDateTime = birthDateUtc.toLocalDateTime();

            userToUpdate.setBirthDate(birthDateLocalDateTime);
            userToUpdate.setCpf(userUpdateFillDTOValidator.getCpf());

            var userUpdated = userRepository.update(userToUpdate);
            userUpdated.setPasswordHash(null);

            return ResultService.Ok(modelMapper.map(userUpdated, UserDTO.class));

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<UserDTO> DeleteUser(UUID userID) {
        try {
            var userDelete = userRepository.GetUserByIdForDeleteImg(userID);

            if(userDelete == null)
                return ResultService.Fail("User not found");

            if(userDelete.getUserImage() != null){
                var deleteFound = cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(userDelete.getUserImage());

                if(!deleteFound.getDeleteSuccessfully())
                    return ResultService.Fail(deleteFound.getMessage());
            }

            var userDeleteSuccessfully = userRepository.delete(userDelete.getId());

            userDeleteSuccessfully.setPasswordHash(null);

            return ResultService.Ok(modelMapper.map(userDeleteSuccessfully, UserDTO.class));

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }
}
