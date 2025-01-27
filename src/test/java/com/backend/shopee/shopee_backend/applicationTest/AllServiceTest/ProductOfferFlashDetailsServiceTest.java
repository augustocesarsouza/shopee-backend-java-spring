package com.backend.shopee.shopee_backend.applicationTest.AllServiceTest;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashDetailsDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductOfferFlashDetailsValidationDTOs.ProductOfferFlashDetailsCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ErrorValidation;
import com.backend.shopee.shopee_backend.application.services.ProductOfferFlashDetailsService;
import com.backend.shopee.shopee_backend.domain.entities.ProductOfferFlashDetails;
import com.backend.shopee.shopee_backend.domain.repositories.IProductOfferFlashDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BeanPropertyBindingResult;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class ProductOfferFlashDetailsServiceTest {
    @Mock
    private IProductOfferFlashDetailsRepository productOfferFlashDetailsRepository;
    @Mock
    private IValidateErrorsDTO validateErrorsDTO;
    @Mock
    private ModelMapper modelMapper;

    private ProductOfferFlashDetailsService productOfferFlashDetailsService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        productOfferFlashDetailsService = new ProductOfferFlashDetailsService(productOfferFlashDetailsRepository, validateErrorsDTO, modelMapper);
    }

    @Test
    public void should_GetByProductsOfferFlashId_WithoutErrors(){
        String entityId = "602cc0f0-58d7-4472-aa65-45f8fa1daa02";
        var entityDTO = new ProductOfferFlashDetailsDTO();

        when(productOfferFlashDetailsRepository.GetByProductsOfferFlashId(any())).thenReturn(entityDTO);

        // Act
        var result = productOfferFlashDetailsService.GetByProductsOfferFlashId(UUID.fromString(entityId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(entityDTO, result.Data);
    }

    @Test
    public void should_Error_When_GetByProductsOfferFlashId_Return_Null(){
        String entityId = "9c1314a4-d9a8-44ef-a99f-04ee7e454d09";

        when(productOfferFlashDetailsRepository.GetByProductsOfferFlashId(any())).thenReturn(null);

        // Act
        var result = productOfferFlashDetailsService.GetByProductsOfferFlashId(UUID.fromString(entityId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "not found productOfferFlashDetails");
    }

    @Test
    public void should_ThrowException_When_GetByProductsOfferFlashId(){
        String entityId = "1afe91c7-bf24-4dcf-a7a0-3870ee873f5d";
        String expectedErrorMessage = "Database connection error";

        when(productOfferFlashDetailsRepository.GetByProductsOfferFlashId(any()))
                .thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = productOfferFlashDetailsService.GetByProductsOfferFlashId(UUID.fromString(entityId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_create_Successfully_ProductOfferFlashDetails(){
        Map<String, Object> details = new HashMap<>();
        details.put("Marca", "seila");
        var entityCreateDTOValidator = new ProductOfferFlashDetailsCreateDTOValidator("eb951732-c27d-4ba9-bf5b-7f293f8ce355",
                details);

        var resultError = new BeanPropertyBindingResult(entityCreateDTOValidator, "ProductOfferFlashDetailsCreateDTOValidator");

        var entityDTOCreateMap = new ProductOfferFlashDetailsDTO();

        when(productOfferFlashDetailsRepository.create(any())).thenReturn(new ProductOfferFlashDetails());
        when(modelMapper.map(any(ProductOfferFlashDetails.class), eq(ProductOfferFlashDetailsDTO.class))).thenReturn(entityDTOCreateMap);

        // Act
        var result = productOfferFlashDetailsService.CreateAsync(entityCreateDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, entityDTOCreateMap);
    }

    @Test
    public void should_Return_Error_DTO_Is_Null_create_ProductOfferFlashDetails(){
        var resultError = new BeanPropertyBindingResult(new ProductOfferFlashDetailsCreateDTOValidator(), "ProductOfferFlashDetailsCreateDTOValidator");

        // Act
        var result = productOfferFlashDetailsService.CreateAsync(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error DTO Is Null");
    }

    @Test
    public void error_validate_DTO_When_Create_ProductOfferFlashDetails(){
        Map<String, Object> details = new HashMap<>();
        details.put("Marca", "seila");
        var entityCreateDTOValidator = new ProductOfferFlashDetailsCreateDTOValidator(null, details);

        var resultError = new BeanPropertyBindingResult(entityCreateDTOValidator, "ProductOfferFlashDetailsCreateDTOValidator");

        resultError.rejectValue("productsOfferFlashId", "NotEmpty", "productsOfferFlashId should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("productsOfferFlashId", "productsOfferFlashId should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = productOfferFlashDetailsService.CreateAsync(entityCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void should_ThrowException_When_create_ProductOfferFlashDetails(){
        Map<String, Object> details = new HashMap<>();
        details.put("Marca", "seila");
        var entityCreateDTOValidator = new ProductOfferFlashDetailsCreateDTOValidator("eb951732-c27d-4ba9-bf5b-7f293f8ce355",
                details);

        var resultError = new BeanPropertyBindingResult(entityCreateDTOValidator, "ProductOfferFlashDetailsCreateDTOValidator");

        String expectedErrorMessage = "Database connection error";

        when(productOfferFlashDetailsRepository.create(any()))
                .thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = productOfferFlashDetailsService.CreateAsync(entityCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_Delete_WithoutErrors(){
        String entityId = "b1a01457-f0e1-4fb4-8079-763a51a341e8";
        var entityDTO = new ProductOfferFlashDetailsDTO();
        entityDTO.setId(UUID.fromString("ff2df10b-cac9-4c16-b697-a4045f4fdf39"));

        var entityDTODelete = new ProductOfferFlashDetailsDTO();

        when(productOfferFlashDetailsRepository.GetByProductOfferFlashDetailsId(any())).thenReturn(entityDTO);
        when(productOfferFlashDetailsRepository.delete(any())).thenReturn(new ProductOfferFlashDetails());
        when(modelMapper.map(any(ProductOfferFlashDetails.class), eq(ProductOfferFlashDetailsDTO.class))).thenReturn(entityDTODelete);

        // Act
        var result = productOfferFlashDetailsService.Delete(UUID.fromString(entityId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, entityDTODelete);
    }

    @Test
    public void should_Return_Null_When_GetCuponByIdToVerifyIfExist_Delete(){
        String entityId = "60700b1a-3d5c-4d87-91d6-2c8f4ed2f6dc";

        when(productOfferFlashDetailsRepository.GetByProductOfferFlashDetailsId(any())).thenReturn(null);

        // Act
        var result = productOfferFlashDetailsService.Delete(UUID.fromString(entityId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "entity not found");
    }

    @Test
    public void should_ThrowException_When_Delete(){
        String entityId = "b1a01457-f0e1-4fb4-8079-763a51a341e8";
        var entityDTO = new ProductOfferFlashDetailsDTO();
        entityDTO.setId(UUID.fromString("ff2df10b-cac9-4c16-b697-a4045f4fdf39"));

        String expectedErrorMessage = "Database connection error";

        when(productOfferFlashDetailsRepository.GetByProductOfferFlashDetailsId(any())).thenReturn(entityDTO);
        when(productOfferFlashDetailsRepository.delete(any()))
                .thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = productOfferFlashDetailsService.Delete(UUID.fromString(entityId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(expectedErrorMessage, result.Message);
    }
}
