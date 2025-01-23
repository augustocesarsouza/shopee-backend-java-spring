package com.backend.shopee.shopee_backend.applicationTest.userServiceTest;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashSellerDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductOfferFlashSellerValidationDTOs.ProductOfferFlashSellerCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ErrorValidation;
import com.backend.shopee.shopee_backend.application.services.ProductOfferFlashSellerService;
import com.backend.shopee.shopee_backend.domain.entities.ProductOfferFlashSeller;
import com.backend.shopee.shopee_backend.domain.repositories.IProductOfferFlashSellerRepository;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class ProductOfferFlashSellerServiceTest {
    @Mock
    private IProductOfferFlashSellerRepository productOfferFlashSellerRepository;
    @Mock
    private IValidateErrorsDTO validateErrorsDTO;
    @Mock
    private ModelMapper modelMapper;

    private ProductOfferFlashSellerService productOfferFlashSellerService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        productOfferFlashSellerService = new ProductOfferFlashSellerService(productOfferFlashSellerRepository, validateErrorsDTO, modelMapper);
    }

    @Test
    public void should_GetByProductsOfferFlashId_WithoutErrors(){
        String entityId = "cc27392b-944a-4e22-b399-231387bdd984";
        var entityDTO = new ProductOfferFlashSellerDTO();

        when(productOfferFlashSellerRepository.GetByProductsOfferFlashId(any())).thenReturn(entityDTO);

        // Act
        var result = productOfferFlashSellerService.GetByProductsOfferFlashId(UUID.fromString(entityId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, entityDTO);
    }

    @Test
    public void should_GetById_Return_Null(){
        String entityId = "cc27392b-944a-4e22-b399-231387bdd984";

        when(productOfferFlashSellerRepository.GetByProductsOfferFlashId(any())).thenReturn(null);

        // Act
        var result = productOfferFlashSellerService.GetByProductsOfferFlashId(UUID.fromString(entityId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "not found entity");
    }

    @Test
    public void should_ThrowException_When_GetById(){
        String entityId = "cc27392b-944a-4e22-b399-231387bdd984";

        String expectedErrorMessage = "Database connection error";

        when(productOfferFlashSellerRepository.GetByProductsOfferFlashId(any()))
                .thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = productOfferFlashSellerService.GetByProductsOfferFlashId(UUID.fromString(entityId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_create_Successfully_ProductOfferFlashSeller(){
        var productOfferFlashSellerCreateDTOValidator = new ProductOfferFlashSellerCreateDTOValidator("fdd5976c-4660-457b-8fcb-c87361eac906",
                "6557e4d6-b8db-4e39-b8a7-0f4118e01abe");

        var resultError = new BeanPropertyBindingResult(productOfferFlashSellerCreateDTOValidator, "ProductOfferFlashSellerCreateDTOValidator");

        var entityDTOMap = new ProductOfferFlashSellerDTO();

        when(productOfferFlashSellerRepository.create(any())).thenReturn(new ProductOfferFlashSeller());
        when(modelMapper.map(any(ProductOfferFlashSeller.class), eq(ProductOfferFlashSellerDTO.class))).thenReturn(entityDTOMap);

        // Act
        var result = productOfferFlashSellerService.CreateAsync(productOfferFlashSellerCreateDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, entityDTOMap);
    }

    @Test
    public void should_Return_Error_DTO_Is_Null_create_ProductOfferFlashSeller(){
        var resultError = new BeanPropertyBindingResult(new ProductOfferFlashSellerCreateDTOValidator(), "ProductOfferFlashSellerCreateDTOValidator");

        // Act
        var result = productOfferFlashSellerService.CreateAsync(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error DTO Is Null");
    }

    @Test
    public void error_validate_DTO_When_Create_ProductOfferFlashSeller(){
        var productOfferFlashSellerCreateDTOValidator = new ProductOfferFlashSellerCreateDTOValidator("fdd5976c-4660-457b-8fcb-c87361eac906",
                null);

        var resultError = new BeanPropertyBindingResult(productOfferFlashSellerCreateDTOValidator, "ProductOfferFlashSellerCreateDTOValidator");

        resultError.rejectValue("ProductsOfferFlashId", "NotEmpty", "ProductsOfferFlashId should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("ProductsOfferFlashId", "ProductsOfferFlashId should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = productOfferFlashSellerService.CreateAsync(productOfferFlashSellerCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void should_ThrowException_create_Promotion(){
        var productOfferFlashSellerCreateDTOValidator = new ProductOfferFlashSellerCreateDTOValidator("fdd5976c-4660-457b-8fcb-c87361eac906",
                "6557e4d6-b8db-4e39-b8a7-0f4118e01abe");

        var resultError = new BeanPropertyBindingResult(productOfferFlashSellerCreateDTOValidator, "ProductOfferFlashSellerCreateDTOValidator");

        String expectedErrorMessage = "Database connection error";

        when(productOfferFlashSellerRepository.create(any()))
                .thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = productOfferFlashSellerService.CreateAsync(productOfferFlashSellerCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }
}
