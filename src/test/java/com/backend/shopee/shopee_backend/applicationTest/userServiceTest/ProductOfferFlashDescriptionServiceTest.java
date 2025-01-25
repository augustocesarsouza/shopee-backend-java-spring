package com.backend.shopee.shopee_backend.applicationTest.userServiceTest;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashDescriptionDTO;
import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashDetailsDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductOfferFlashDescriptionValidationDTOs.ProductOfferFlashDescriptionCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductOfferFlashDetailsValidationDTOs.ProductOfferFlashDetailsCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ErrorValidation;
import com.backend.shopee.shopee_backend.application.services.ProductOfferFlashDescriptionService;
import com.backend.shopee.shopee_backend.domain.entities.ProductOfferFlashDescription;
import com.backend.shopee.shopee_backend.domain.entities.ProductOfferFlashDetails;
import com.backend.shopee.shopee_backend.domain.repositories.IProductOfferFlashDescriptionRepository;
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

public class ProductOfferFlashDescriptionServiceTest {
    @Mock
    private IProductOfferFlashDescriptionRepository productOfferFlashDescriptionRepository;
    @Mock
    private IValidateErrorsDTO validateErrorsDTO;
    @Mock
    private ModelMapper modelMapper;

    private ProductOfferFlashDescriptionService productOfferFlashDescriptionService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        productOfferFlashDescriptionService = new ProductOfferFlashDescriptionService(productOfferFlashDescriptionRepository, validateErrorsDTO, modelMapper);
    }

    @Test
    public void should_GetByProductsOfferFlashId_WithoutErrors(){
        String entityId = "602cc0f0-58d7-4472-aa65-45f8fa1daa02";
        var entityDTO = new ProductOfferFlashDescriptionDTO();

        when(productOfferFlashDescriptionRepository.GetByProductsOfferFlashId(any())).thenReturn(entityDTO);

        // Act
        var result = productOfferFlashDescriptionService.GetByProductsOfferFlashId(UUID.fromString(entityId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(entityDTO, result.Data);
    }

    @Test
    public void should_Error_When_GetByProductsOfferFlashId_Return_Null(){
        String entityId = "9c1314a4-d9a8-44ef-a99f-04ee7e454d09";

        when(productOfferFlashDescriptionRepository.GetByProductsOfferFlashId(any())).thenReturn(null);

        // Act
        var result = productOfferFlashDescriptionService.GetByProductsOfferFlashId(UUID.fromString(entityId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "not found ProductOfferFlashDescription");
    }

    @Test
    public void should_ThrowException_When_GetByProductsOfferFlashId(){
        String entityId = "1afe91c7-bf24-4dcf-a7a0-3870ee873f5d";
        String expectedErrorMessage = "Database connection error";

        when(productOfferFlashDescriptionRepository.GetByProductsOfferFlashId(any()))
                .thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = productOfferFlashDescriptionService.GetByProductsOfferFlashId(UUID.fromString(entityId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_create_Successfully_ProductOfferFlashDescription(){
        var entityCreateDTOValidator = new ProductOfferFlashDescriptionCreateDTOValidator("eb951732-c27d-4ba9-bf5b-7f293f8ce355",
                "sdsd");

        var resultError = new BeanPropertyBindingResult(entityCreateDTOValidator, "ProductOfferFlashDescriptionCreateDTOValidator");

        var entityDTOCreateMap = new ProductOfferFlashDescriptionDTO();

        when(productOfferFlashDescriptionRepository.create(any())).thenReturn(new ProductOfferFlashDescription());
        when(modelMapper.map(any(ProductOfferFlashDescription.class), eq(ProductOfferFlashDescriptionDTO.class))).thenReturn(entityDTOCreateMap);

        // Act
        var result = productOfferFlashDescriptionService.CreateAsync(entityCreateDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, entityDTOCreateMap);
    }

    @Test
    public void should_Return_Error_DTO_Is_Null_create_ProductOfferFlashDescription(){
        var resultError = new BeanPropertyBindingResult(new ProductOfferFlashDescriptionCreateDTOValidator(), "ProductOfferFlashDescriptionCreateDTOValidator");

        // Act
        var result = productOfferFlashDescriptionService.CreateAsync(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error DTO Is Null");
    }

    @Test
    public void error_validate_DTO_When_Create_ProductOfferFlashDetails(){
        var entityCreateDTOValidator = new ProductOfferFlashDescriptionCreateDTOValidator("eb951732-c27d-4ba9-bf5b-7f293f8ce355",
                null);

        var resultError = new BeanPropertyBindingResult(entityCreateDTOValidator, "ProductOfferFlashDescriptionCreateDTOValidator");

        resultError.rejectValue("Descriptions", "NotEmpty", "Descriptions should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("Descriptions", "Descriptions should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = productOfferFlashDescriptionService.CreateAsync(entityCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void should_ThrowException_When_create_ProductOfferFlashDescription(){
        var entityCreateDTOValidator = new ProductOfferFlashDescriptionCreateDTOValidator("eb951732-c27d-4ba9-bf5b-7f293f8ce355",
                "sdsd");

        var resultError = new BeanPropertyBindingResult(entityCreateDTOValidator, "ProductOfferFlashDescriptionCreateDTOValidator");

        String expectedErrorMessage = "Database connection error";

        when(productOfferFlashDescriptionRepository.create(any()))
                .thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = productOfferFlashDescriptionService.CreateAsync(entityCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_Delete_WithoutErrors(){
        String entityId = "b1a01457-f0e1-4fb4-8079-763a51a341e8";
        var entityDTO = new ProductOfferFlashDescriptionDTO();
        entityDTO.setId(UUID.fromString("ff2df10b-cac9-4c16-b697-a4045f4fdf39"));

        var entityDTODelete = new ProductOfferFlashDescriptionDTO();

        when(productOfferFlashDescriptionRepository.GetByProductsOfferFlashIdIfExist(any())).thenReturn(entityDTO);
        when(productOfferFlashDescriptionRepository.delete(any())).thenReturn(new ProductOfferFlashDescription());
        when(modelMapper.map(any(ProductOfferFlashDescription.class), eq(ProductOfferFlashDescriptionDTO.class))).thenReturn(entityDTODelete);

        // Act
        var result = productOfferFlashDescriptionService.Delete(UUID.fromString(entityId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, entityDTODelete);
    }

    @Test
    public void should_Return_Null_When_GetCuponByIdToVerifyIfExist_Delete(){
        String entityId = "60700b1a-3d5c-4d87-91d6-2c8f4ed2f6dc";

        when(productOfferFlashDescriptionRepository.GetByProductsOfferFlashIdIfExist(any())).thenReturn(null);

        // Act
        var result = productOfferFlashDescriptionService.Delete(UUID.fromString(entityId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "entity not found");
    }

    @Test
    public void should_ThrowException_When_Delete(){
        String entityId = "b1a01457-f0e1-4fb4-8079-763a51a341e8";
        var entityDTO = new ProductOfferFlashDescriptionDTO();
        entityDTO.setId(UUID.fromString("ff2df10b-cac9-4c16-b697-a4045f4fdf39"));

        String expectedErrorMessage = "Database connection error";

        when(productOfferFlashDescriptionRepository.GetByProductsOfferFlashIdIfExist(any())).thenReturn(entityDTO);
        when(productOfferFlashDescriptionRepository.delete(any()))
                .thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = productOfferFlashDescriptionService.Delete(UUID.fromString(entityId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(expectedErrorMessage, result.Message);
    }
}
