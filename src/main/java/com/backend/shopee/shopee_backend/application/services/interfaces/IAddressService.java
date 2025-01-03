package com.backend.shopee.shopee_backend.application.services.interfaces;

import com.backend.shopee.shopee_backend.application.dto.AddressDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.AddressValidationDTOs.AddressCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.AddressValidationDTOs.AddressUpdateDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.AddressValidationDTOs.AddressUpdateOnlyDefaultDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

public interface IAddressService {
    ResultService<AddressDTO> GetAddressById(UUID addressId);
    ResultService<AddressDTO> GetAddressByIdWithUserProperty(UUID addressId);
    ResultService<List<AddressDTO>> GetAddressByUserId(UUID userId);
    ResultService<AddressDTO> CreateAsync(AddressCreateDTOValidator addressCreateDTOValidator, BindingResult result);
    ResultService<AddressDTO> UpdateAddressUser(AddressUpdateDTOValidator updateDTOValidator, BindingResult result);
    ResultService<AddressDTO> UpdateAsyncOnlyDefaultAddress(AddressUpdateOnlyDefaultDTOValidator updateOnlyDefaultDTOValidator, BindingResult result);
    ResultService<AddressDTO> Delete(UUID addressId);
}
