package com.backend.shopee.shopee_backend.applicationTest.AllServiceTest;

import com.backend.shopee.shopee_backend.application.dto.AddressDTO;
import com.backend.shopee.shopee_backend.application.dto.UserDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.AddressValidationDTOs.AddressCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.AddressValidationDTOs.AddressUpdateDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.AddressValidationDTOs.AddressUpdateOnlyDefaultDTOValidator;
import com.backend.shopee.shopee_backend.application.services.AddressService;
import com.backend.shopee.shopee_backend.application.services.ErrorValidation;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import com.backend.shopee.shopee_backend.application.services.interfaces.IUserManagementService;
import com.backend.shopee.shopee_backend.domain.entities.Address;
import com.backend.shopee.shopee_backend.domain.entities.User;
import com.backend.shopee.shopee_backend.domain.repositories.IAddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BeanPropertyBindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class AddressServiceTest {
    @Mock
    private IAddressRepository addressRepository;
    @Mock
    private IUserManagementService userManagementService;
    @Mock
    private IValidateErrorsDTO validateErrorsDTO;
    @Mock
    private ModelMapper modelMapper;

    private AddressService addressService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        addressService = new AddressService(addressRepository, userManagementService, validateErrorsDTO,
                modelMapper);
    }

    @Test
    public void should_GetAddressById_WithoutErrors(){
        String addressId = "f8e9ffc5-93da-47f1-a916-d2a80226d473";
        AddressDTO addressDTO = new AddressDTO();

        when(addressRepository.GetAddressById(any())).thenReturn(new AddressDTO());
        when(modelMapper.map(any(), any())).thenReturn(addressDTO);

        // Act
        var result = addressService.GetAddressById(UUID.fromString(addressId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(addressDTO, result.Data);
    }

    @Test
    public void should_GetAddressById_Error_Return_Null(){
        String addressId = "f8e9ffc5-93da-47f1-a916-d2a80226d473";
        AddressDTO addressDTO = new AddressDTO();

        when(addressRepository.GetAddressById(any())).thenReturn(null);

        // Act
        var result = addressService.GetAddressById(UUID.fromString(addressId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals("not found", result.Message);
    }

    @Test
    public void should_ThrowException_When_GetAddressById(){
        String addressId = "f8e9ffc5-93da-47f1-a916-d2a80226d473";
        String expectedErrorMessage = "Database connection error";

        when(addressRepository.GetAddressById(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = addressService.GetAddressById(UUID.fromString(addressId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(expectedErrorMessage, result.Message);
    }

    @Test
    public void should_GetAddressByIdWithUserProperty_WithoutErrors(){
        String addressId = "f8e9ffc5-93da-47f1-a916-d2a80226d473";
        AddressDTO addressDTO = new AddressDTO();

        when(addressRepository.GetAddressByIdWithUserProperty(any())).thenReturn(addressDTO);

        // Act
        var result = addressService.GetAddressByIdWithUserProperty(UUID.fromString(addressId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(addressDTO, result.Data);
    }

    @Test
    public void should_GetAddressByIdWithUserProperty_Return_Null(){
        String addressId = "f8e9ffc5-93da-47f1-a916-d2a80226d473";

        when(addressRepository.GetAddressByIdWithUserProperty(any())).thenReturn(null);

        // Act
        var result = addressService.GetAddressByIdWithUserProperty(UUID.fromString(addressId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals("not found", result.Message);
    }

    @Test
    public void should_ThrowException_When_GetAddressByIdWithUserProperty(){
        String addressId = "f8e9ffc5-93da-47f1-a916-d2a80226d473";
        String expectedErrorMessage = "Database connection error";

        when(addressRepository.GetAddressByIdWithUserProperty(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = addressService.GetAddressByIdWithUserProperty(UUID.fromString(addressId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(expectedErrorMessage, result.Message);
    }

    @Test
    public void should_GetAddressByUserId_WithoutErrors(){
        String addressId = "f8e9ffc5-93da-47f1-a916-d2a80226d473";
        var addressDTO = List.of(new AddressDTO());

        when(addressRepository.GetAddressByUserId(any())).thenReturn(addressDTO);

        // Act
        var result = addressService.GetAddressByUserId(UUID.fromString(addressId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(addressDTO, result.Data);
    }

    @Test
    public void should_create_Successfully_Address(){
        var userId = "170d8925-8ed8-4c9d-a086-71f4bac90025";
        var addressCreateDTOValidator = new AddressCreateDTOValidator("fullName", "(+55) 67 98114 5503",
                "79083-590", "stateCity", "neighborhood", "Rua Cajazeira", Integer.toString(2420), (byte)1);
        addressCreateDTOValidator.setUserId(userId);
        var resultError = new BeanPropertyBindingResult(addressCreateDTOValidator, "AddressCreateDTOValidator");

        AddressDTO addressCreate = new AddressDTO();
        ResultService<UserDTO> mockResult = ResultService.Ok(new UserDTO());

        when(userManagementService.VerifyWhetherUserExist(any())).thenReturn(mockResult);
        when(addressRepository.VerifyIfUserAlreadyHaveAddress(any())).thenReturn(new AddressDTO());
        when(addressRepository.create(any())).thenReturn(new Address());
        when(modelMapper.map(any(Address.class), eq(AddressDTO.class))).thenReturn(addressCreate);

        // Act
        var result = addressService.CreateAsync(addressCreateDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, addressCreate);
    }

    @Test
    public void should_Return_Error_DTO_Is_Null_create(){
        var resultError = new BeanPropertyBindingResult(new AddressCreateDTOValidator(), "AddressCreateDTOValidator");

        // Act
        var result = addressService.CreateAsync(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error DTO Is Null");
    }

    @Test
    public void error_validate_DTO_When_Create(){
        var userId = "170d8925-8ed8-4c9d-a086-71f4bac90025";
        var addressCreateDTOValidator = new AddressCreateDTOValidator("fullName", null,
                "79083-590", "stateCity", "neighborhood", null, Integer.toString(2420), (byte)1);
        addressCreateDTOValidator.setUserId(userId);

        var resultError = new BeanPropertyBindingResult(addressCreateDTOValidator, "userCreateValidatorDTO");

        resultError.rejectValue("Street", "NotEmpty", "Street should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("Street", "Street should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = addressService.CreateAsync(addressCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void should_Throw_Error_When_Verify_Whether_User_Exist_create_Address(){
        var userId = "170d8925-8ed8-4c9d-a086-71f4bac90025";
        var addressCreateDTOValidator = new AddressCreateDTOValidator("fullName", "(+55) 67 98114 5503",
                "79083-590", "stateCity", "neighborhood", "Rua Cajazeira", Integer.toString(2420), (byte)1);
        addressCreateDTOValidator.setUserId(userId);
        var resultError = new BeanPropertyBindingResult(addressCreateDTOValidator, "AddressCreateDTOValidator");

        ResultService<UserDTO> mockResult = ResultService.Fail("Error User Not exist");

        when(userManagementService.VerifyWhetherUserExist(any())).thenReturn(mockResult);
        // Act
        var result = addressService.CreateAsync(addressCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "Error User not exist");
    }

    @Test
    public void should_ThrowException_When_create_Address(){
        var userId = "170d8925-8ed8-4c9d-a086-71f4bac90025";
        var addressCreateDTOValidator = new AddressCreateDTOValidator("fullName", "(+55) 67 98114 5503",
                "79083-590", "stateCity", "neighborhood", "Rua Cajazeira", Integer.toString(2420), (byte)1);
        addressCreateDTOValidator.setUserId(userId);
        var resultError = new BeanPropertyBindingResult(addressCreateDTOValidator, "AddressCreateDTOValidator");

        ResultService<UserDTO> mockResult = ResultService.Ok(new UserDTO());

        String expectedErrorMessage = "Database connection error";

        when(userManagementService.VerifyWhetherUserExist(any())).thenReturn(mockResult);
        when(addressRepository.VerifyIfUserAlreadyHaveAddress(any())).thenReturn(new AddressDTO());
        when(addressRepository.create(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = addressService.CreateAsync(addressCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(expectedErrorMessage, result.Message);
    }

    @Test
    public void should_UpdateAddressUser_Successfully(){
        var addressUpdateDTOValidator = new AddressUpdateDTOValidator("fullName", "(+55) 67 98114 5503",
                "79083-590", "stateCity", "neighborhood", "Rua Cajazeira",
                Integer.toString(2420), "complement", "170d8925-8ed8-4c9d-a086-71f4bac90025", (byte)1);
        addressUpdateDTOValidator.setId("a353e06f-c8b6-4029-93f8-4bda2a809067");

        var resultError = new BeanPropertyBindingResult(addressUpdateDTOValidator, "AddressCreateDTOValidator");

        AddressDTO addressCreate = new AddressDTO();
        addressCreate.setUserDTO(new UserDTO());

        when(addressRepository.GetAddressByIdToDelete(any(UUID.class))).thenReturn(new AddressDTO());
        when(addressRepository.update(any())).thenReturn(new Address());
        when(modelMapper.map(any(Address.class), eq(AddressDTO.class))).thenReturn(addressCreate);

        // Act
        var result = addressService.UpdateAddressUser(addressUpdateDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, addressCreate);
    }

    @Test
    public void should_Return_Null_DTO_UpdateAddressUser(){
        var resultError = new BeanPropertyBindingResult(new AddressUpdateDTOValidator(), "AddressCreateDTOValidator");

        // Act
        var result = addressService.UpdateAddressUser(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error DTO Is Null");
    }

    @Test
    public void error_validate_DTO_When_UpdateAddressUser(){
        var addressUpdateDTOValidator = new AddressUpdateDTOValidator("fullName", "(+55) 67 98114 5503",
                "79083-590", null, "neighborhood", "Rua Cajazeira",
                Integer.toString(2420), "complement", "170d8925-8ed8-4c9d-a086-71f4bac90025", (byte)1);
        addressUpdateDTOValidator.setId("a353e06f-c8b6-4029-93f8-4bda2a809067");

        var resultError = new BeanPropertyBindingResult(addressUpdateDTOValidator, "addressUpdateDTOValidator");

        resultError.rejectValue("StateCity", "NotEmpty", "StateCity should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("StateCity", "StateCity should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = addressService.UpdateAddressUser(addressUpdateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void should_Throw_Error_When_GetAddressByIdToDelete_Return_Null(){
        var addressUpdateDTOValidator = new AddressUpdateDTOValidator("fullName", "(+55) 67 98114 5503",
                "79083-590", "stateCity", "neighborhood", "Rua Cajazeira",
                Integer.toString(2420), "complement", "170d8925-8ed8-4c9d-a086-71f4bac90025", (byte)1);
        addressUpdateDTOValidator.setId("a353e06f-c8b6-4029-93f8-4bda2a809067");

        var resultError = new BeanPropertyBindingResult(addressUpdateDTOValidator, "AddressCreateDTOValidator");

        when(addressRepository.GetAddressByIdToDelete(any(UUID.class))).thenReturn(null);

        // Act
        var result = addressService.UpdateAddressUser(addressUpdateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "Error Address not found");
    }

    @Test
    public void should_ThrowException_Repository_UpdateAddressUser(){
        var addressUpdateDTOValidator = new AddressUpdateDTOValidator("fullName", "(+55) 67 98114 5503",
                "79083-590", "stateCity", "neighborhood", "Rua Cajazeira",
                Integer.toString(2420), "complement", "170d8925-8ed8-4c9d-a086-71f4bac90025", (byte)1);
        addressUpdateDTOValidator.setId("a353e06f-c8b6-4029-93f8-4bda2a809067");

        var resultError = new BeanPropertyBindingResult(addressUpdateDTOValidator, "AddressCreateDTOValidator");

        String expectedErrorMessage = "Database connection error";

        when(addressRepository.GetAddressByIdToDelete(any(UUID.class))).thenReturn(new AddressDTO());
        when(addressRepository.update(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = addressService.UpdateAddressUser(addressUpdateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(expectedErrorMessage, result.Message);
    }

    @Test
    public void should_UpdateAsyncOnlyDefaultAddress_Successfully(){
        var addressUpdateOnlyDefaultDTOValidator = new AddressUpdateOnlyDefaultDTOValidator("12f79406-1e22-4da8-b779-0784ab7cef23",
                1);

        var resultError = new BeanPropertyBindingResult(addressUpdateOnlyDefaultDTOValidator, "addressUpdateOnlyDefaultDTOValidator");

        var addressDb = new AddressDTO();
        AddressDTO addressUpdateDefault = new AddressDTO();
        var updateAddress = new AddressDTO();
        updateAddress.setUserDTO(new UserDTO());

        when(addressRepository.GetAddressById(any(UUID.class))).thenReturn(addressDb);
        when(addressRepository.GetAddressDefaultAllInfo()).thenReturn(addressUpdateDefault);
        when(modelMapper.map(any(AddressDTO.class), eq(Address.class))).thenReturn(new Address());
        when(addressRepository.update(any())).thenReturn(new Address());
        when(modelMapper.map(any(Address.class), eq(AddressDTO.class))).thenReturn(updateAddress);

        // Act
        var result = addressService.UpdateAsyncOnlyDefaultAddress(addressUpdateOnlyDefaultDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, updateAddress);
    }

    @Test
    public void should_Return_Null_DTO_UpdateAsyncOnlyDefaultAddress(){
        var resultError = new BeanPropertyBindingResult(new AddressUpdateOnlyDefaultDTOValidator(), "AddressUpdateOnlyDefaultDTOValidator");

        // Act
        var result = addressService.UpdateAsyncOnlyDefaultAddress(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error DTO Is Null");
    }

    @Test
    public void error_validate_DTO_When_UpdateAsyncOnlyDefaultAddress(){
        var addressUpdateOnlyDefaultDTOValidator = new AddressUpdateOnlyDefaultDTOValidator(null,
                1);

        var resultError = new BeanPropertyBindingResult(addressUpdateOnlyDefaultDTOValidator, "AddressUpdateOnlyDefaultDTOValidator");
        resultError.rejectValue("id", "NotEmpty", "id should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("id", "id should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = addressService.UpdateAsyncOnlyDefaultAddress(addressUpdateOnlyDefaultDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void should_Throw_Error_When_GetAddressById_UpdateAsyncOnlyDefaultAddress(){
        var addressUpdateOnlyDefaultDTOValidator = new AddressUpdateOnlyDefaultDTOValidator("12f79406-1e22-4da8-b779-0784ab7cef23",
                1);

        var resultError = new BeanPropertyBindingResult(addressUpdateOnlyDefaultDTOValidator, "addressUpdateOnlyDefaultDTOValidator");

        when(addressRepository.GetAddressById(any(UUID.class))).thenReturn(null);

        // Act
        var result = addressService.UpdateAsyncOnlyDefaultAddress(addressUpdateOnlyDefaultDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "Error Address not found");
    }

    @Test
    public void should_ThrowException_When_GetAddressById_UpdateAsyncOnlyDefaultAddress(){
        var addressUpdateOnlyDefaultDTOValidator = new AddressUpdateOnlyDefaultDTOValidator("12f79406-1e22-4da8-b779-0784ab7cef23",
                1);

        var resultError = new BeanPropertyBindingResult(addressUpdateOnlyDefaultDTOValidator, "addressUpdateOnlyDefaultDTOValidator");

        String expectedErrorMessage = "Database connection error";

        when(addressRepository.GetAddressById(any(UUID.class))).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = addressService.UpdateAsyncOnlyDefaultAddress(addressUpdateOnlyDefaultDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(expectedErrorMessage, result.Message);
    }

    @Test
    public void should_Delete_Address_Successfully(){
        var addressId = UUID.fromString("12f79406-1e22-4da8-b779-0784ab7cef23");

        var address = new Address();
        address.setUser(new User());

        var addressDTO = new AddressDTO();

        when(addressRepository.GetAddressByIdToDelete(any(UUID.class))).thenReturn(new AddressDTO());
        when(addressRepository.delete(any())).thenReturn(address);
        when(modelMapper.map(any(Address.class), eq(AddressDTO.class))).thenReturn(addressDTO);

        // Act
        var result = addressService.Delete(addressId);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, addressDTO);
    }

    @Test
    public void should_Error_When_GetAddressByIdToDelete_Return_Null(){
        var addressId = UUID.fromString("12f79406-1e22-4da8-b779-0784ab7cef23");

        when(addressRepository.GetAddressByIdToDelete(any(UUID.class))).thenReturn(null);

        // Act
        var result = addressService.Delete(addressId);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "Address not found");
    }

    @Test
    public void should_ThrowException_When_Delete_Address(){
        var addressId = UUID.fromString("12f79406-1e22-4da8-b779-0784ab7cef23");

        String expectedErrorMessage = "Database connection error";

        when(addressRepository.GetAddressByIdToDelete(any(UUID.class))).thenReturn(new AddressDTO());
        when(addressRepository.delete(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = addressService.Delete(addressId);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }
}