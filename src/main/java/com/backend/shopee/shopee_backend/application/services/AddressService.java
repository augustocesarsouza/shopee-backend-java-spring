package com.backend.shopee.shopee_backend.application.services;

import com.backend.shopee.shopee_backend.application.dto.AddressDTO;
import com.backend.shopee.shopee_backend.application.dto.UserDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.AddressValidationDTOs.AddressCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.AddressValidationDTOs.AddressUpdateDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.AddressValidationDTOs.AddressUpdateOnlyDefaultDTOValidator;
import com.backend.shopee.shopee_backend.application.services.interfaces.IAddressService;
import com.backend.shopee.shopee_backend.application.services.interfaces.IUserManagementService;
import com.backend.shopee.shopee_backend.domain.entities.Address;
import com.backend.shopee.shopee_backend.domain.entities.User;
import com.backend.shopee.shopee_backend.domain.repositories.IAddressRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;

@Service
public class AddressService implements IAddressService {
    private final IAddressRepository addressRepository;
    private final IUserManagementService userManagementService;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final ModelMapper modelMapper; // testar o map

    @Autowired
    public AddressService(IAddressRepository addressRepository, IUserManagementService userManagementService, IValidateErrorsDTO validateErrorsDTO, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.userManagementService = userManagementService;
        this.validateErrorsDTO = validateErrorsDTO;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public ResultService<AddressDTO> GetAddressById(UUID addressId) {
        try {
            AddressDTO addressDTO = addressRepository.GetAddressById(addressId);

            if(addressDTO == null){
                return ResultService.Fail("not found");
            }

            var addressMap = modelMapper.map(addressDTO, AddressDTO.class);
            return ResultService.Ok(addressMap);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public ResultService<AddressDTO> GetAddressByIdWithUserProperty(UUID addressId) {
        try {
            AddressDTO addressDTO = addressRepository.GetAddressByIdWithUserProperty(addressId);

            if(addressDTO == null){
                return ResultService.Fail("not found");
            }

            var addressMap = modelMapper.map(addressDTO, AddressDTO.class);
            return ResultService.Ok(addressMap);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public ResultService<List<AddressDTO>> GetAddressByUserId(UUID userId) {
        try {
            List<AddressDTO> addressDTO = addressRepository.GetAddressByUserId(userId);

            if(addressDTO == null){
                return ResultService.Fail("not found");
            }

            List<AddressDTO> addressDTOList = new ArrayList<>();

            addressDTO.forEach((el) -> {
                var addressMap = modelMapper.map(el, AddressDTO.class);
                addressDTOList.add(addressMap);
            });

            return ResultService.Ok(addressDTOList);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<AddressDTO> CreateAsync(AddressCreateDTOValidator addressCreateDTOValidator, BindingResult result) {
        if(addressCreateDTOValidator == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try  {
            UUID addressId = UUID.randomUUID();
            UUID userId = UUID.fromString(addressCreateDTOValidator.getUserId());

            var userVerify = userManagementService.VerifyWhetherUserExist(userId);

            if(!userVerify.IsSuccess)
                return ResultService.Fail("Error User not exist");

            var verifyIfExistAddressRegistered = addressRepository.VerifyIfUserAlreadyHaveAddress(userId);

            Address addressCreate;

            if(verifyIfExistAddressRegistered == null){
                addressCreate = new Address(addressId, addressCreateDTOValidator.getFullName(),
                        addressCreateDTOValidator.getPhoneNumber(), addressCreateDTOValidator.getCep(), addressCreateDTOValidator.getStateCity(),
                        addressCreateDTOValidator.getNeighborhood(), addressCreateDTOValidator.getStreet(), addressCreateDTOValidator.getNumberHome(),
                        addressCreateDTOValidator.getComplement(), (byte) 1, UUID.fromString(addressCreateDTOValidator.getUserId()), null,
                        addressCreateDTOValidator.getSaveAs());
            }else {
                addressCreate = new Address(addressId, addressCreateDTOValidator.getFullName(),
                        addressCreateDTOValidator.getPhoneNumber(), addressCreateDTOValidator.getCep(), addressCreateDTOValidator.getStateCity(),
                        addressCreateDTOValidator.getNeighborhood(), addressCreateDTOValidator.getStreet(), addressCreateDTOValidator.getNumberHome(),
                        addressCreateDTOValidator.getComplement(), (byte) 0, UUID.fromString(addressCreateDTOValidator.getUserId()), null,
                        addressCreateDTOValidator.getSaveAs());
            }

            var createAddress = addressRepository.create(addressCreate);

            var userMap = modelMapper.map(createAddress, AddressDTO.class);

            return ResultService.Ok(userMap);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<AddressDTO> UpdateAddressUser(AddressUpdateDTOValidator updateDTOValidator, BindingResult result) {
        if(updateDTOValidator == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try {
            var addressDb = addressRepository.GetAddressByIdToDelete(UUID.fromString(updateDTOValidator.getId()));

            if(addressDb == null)
                return ResultService.Fail("Error Address not found");

            var addressToUpdate = new Address(UUID.fromString(updateDTOValidator.getId()), updateDTOValidator.getFullName(),updateDTOValidator.getPhoneNumber(),
                    updateDTOValidator.getCep(), updateDTOValidator.getStateCity(), updateDTOValidator.getNeighborhood(),
                    updateDTOValidator.getStreet(), updateDTOValidator.getNumberHome(), updateDTOValidator.getComplement(),
                    updateDTOValidator.getDefaultAddress(), UUID.fromString(updateDTOValidator.getUserId()), null,
                    updateDTOValidator.getSaveAs());

            var updateAddress = addressRepository.update(addressToUpdate);

            var addressMap = modelMapper.map(updateAddress, AddressDTO.class);
            addressMap.getUserDTO().setPasswordHash(null);

            return ResultService.Ok(addressMap);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<AddressDTO> UpdateAsyncOnlyDefaultAddress(AddressUpdateOnlyDefaultDTOValidator updateOnlyDefaultDTOValidator, BindingResult result) {
        if(updateOnlyDefaultDTOValidator == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try {
            var addressDb = addressRepository.GetAddressById(UUID.fromString(updateOnlyDefaultDTOValidator.getId()));

            if(addressDb == null)
                return ResultService.Fail("Error Address not found");

            var addressUpdateDefault = addressRepository.GetAddressDefaultAllInfo();
            addressUpdateDefault.setDefaultAddress((byte)0);

            var updateAddressDefault = addressRepository.updateOnlyDefaultAddress(modelMapper.map(addressUpdateDefault, Address.class));
            int defaultAddress = updateOnlyDefaultDTOValidator.getDefaultAddress();
            addressDb.setDefaultAddress((byte) defaultAddress);
            var addressToUpdate = modelMapper.map(addressDb, Address.class);

            var updateAddress = addressRepository.update(addressToUpdate);

            var addressMap = modelMapper.map(updateAddress, AddressDTO.class);
            addressMap.getUserDTO().setPasswordHash(null);

            return ResultService.Ok(addressMap);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<AddressDTO> Delete(UUID addressId) {
        try {
            var addressToDelete = addressRepository.GetAddressByIdToDelete(addressId);

            if(addressToDelete == null)
                return ResultService.Fail("Address not found");

            Address addressDeleteSuccessfully = addressRepository.delete(addressId);
            addressDeleteSuccessfully.getUser().setPasswordHash(null);
            var addressMap = modelMapper.map(addressDeleteSuccessfully, AddressDTO.class);

            return ResultService.Ok(addressMap);

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }
}
