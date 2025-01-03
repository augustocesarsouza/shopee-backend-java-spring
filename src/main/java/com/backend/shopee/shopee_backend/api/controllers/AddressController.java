package com.backend.shopee.shopee_backend.api.controllers;

import com.backend.shopee.shopee_backend.application.dto.AddressDTO;
import com.backend.shopee.shopee_backend.application.dto.UserChangePasswordDTO;
import com.backend.shopee.shopee_backend.application.dto.UserDTO;
import com.backend.shopee.shopee_backend.application.dto.UserPasswordUpdateDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.AddressValidationDTOs.AddressCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.AddressValidationDTOs.AddressUpdateDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.AddressValidationDTOs.AddressUpdateOnlyDefaultDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs.UserCreateValidatorDTO;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import com.backend.shopee.shopee_backend.application.services.interfaces.IAddressService;
import com.backend.shopee.shopee_backend.application.services.interfaces.IUserManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Component
@RestController
@CrossOrigin
@RequestMapping("/v1")
public class AddressController {
    private final IAddressService addressService;

    @Autowired
    public AddressController(IAddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/public/address/get-address-by-id/{addressId}")
    public ResponseEntity<ResultService<AddressDTO>> findById(@PathVariable String addressId){
        var result = addressService.GetAddressById(UUID.fromString(addressId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/public/address/get-address-by-id-and-user-property/{addressId}")
    public ResponseEntity<ResultService<AddressDTO>> GetAddressByIdWithUserProperty(@PathVariable String addressId){
        var result = addressService.GetAddressByIdWithUserProperty(UUID.fromString(addressId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/address/get-address-by-user-id/{userId}")
    public ResponseEntity<ResultService<List<AddressDTO>>> GetAddressByUserId(@PathVariable String userId){
        var result = addressService.GetAddressByUserId(UUID.fromString(userId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/public/address/create")
    public ResponseEntity<ResultService<AddressDTO>> Create(@Valid @RequestBody AddressCreateDTOValidator addressCreateDTOValidator, BindingResult resultValid){
        var result = addressService.CreateAsync(addressCreateDTOValidator, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/public/address/update")
    public ResponseEntity<ResultService<AddressDTO>> UpdateAsync(@Valid @RequestBody AddressUpdateDTOValidator updateDTOValidator, BindingResult resultValid){
        var result = addressService.UpdateAddressUser(updateDTOValidator, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/public/address/update-only-default-address")
    public ResponseEntity<ResultService<AddressDTO>> UpdateAsyncOnlyDefaultAddress(@Valid @RequestBody AddressUpdateOnlyDefaultDTOValidator updateOnlyDefaultDTOValidator,
                                                                                   BindingResult resultValid){
        var result = addressService.UpdateAsyncOnlyDefaultAddress(updateOnlyDefaultDTOValidator, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/public/address/delete/{addressId}")
    public ResponseEntity<ResultService<AddressDTO>> DeleteAsync(@PathVariable String addressId){
        var result = addressService.Delete(UUID.fromString(addressId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
