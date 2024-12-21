package com.backend.shopee.shopee_backend.application.services;

import com.backend.shopee.shopee_backend.application.dto.UserDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs.UserCreateValidatorDTO;
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

    private static String generateRandomName(int length) {
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
}
