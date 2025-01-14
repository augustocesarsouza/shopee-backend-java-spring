package com.backend.shopee.shopee_backend.applicationTest.userServiceTest;

import com.backend.shopee.shopee_backend.application.dto.PromotionUserDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.PromotionUserValidationDTOs.PromotionUserCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ErrorValidation;
import com.backend.shopee.shopee_backend.application.services.PromotionUserService;
import com.backend.shopee.shopee_backend.domain.entities.PromotionUser;
import com.backend.shopee.shopee_backend.domain.repositories.IPromotionUserRepository;
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

public class PromotionUserServiceTest {
    @Mock
    private IPromotionUserRepository promotionUserRepository;
    @Mock
    private IValidateErrorsDTO validateErrorsDTO;
    @Mock
    private ModelMapper modelMapper;

    private PromotionUserService promotionUserService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        promotionUserService = new PromotionUserService(promotionUserRepository, validateErrorsDTO, modelMapper);
    }

    @Test
    public void should_GetByUserIdAll_WithoutErrors(){
        String userId = "8fd4dc6a-4830-48f7-98fe-9cdee3ec3e79";

        var PromotionUserDTOList = List.of(new PromotionUserDTO());

        when(promotionUserRepository.GetByUserIdAll(any())).thenReturn(PromotionUserDTOList);

        // Act
        var result = promotionUserService.GetByUserIdAll(UUID.fromString(userId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, PromotionUserDTOList);
    }

    @Test
    public void should_GetByUserIdAll_Return_Null(){
        String userId = "8fd4dc6a-4830-48f7-98fe-9cdee3ec3e79";

        when(promotionUserRepository.GetByUserIdAll(any())).thenReturn(null);

        // Act
        var result = promotionUserService.GetByUserIdAll(UUID.fromString(userId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "not found promotionUser");
    }

    @Test
    public void should_ThrowException_When_GetByUserIdAll(){
        String userId = "8fd4dc6a-4830-48f7-98fe-9cdee3ec3e79";
        String expectedErrorMessage = "Database connection error";

        when(promotionUserRepository.GetByUserIdAll(any()))
                .thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = promotionUserService.GetByUserIdAll(UUID.fromString(userId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_create_Successfully_PromotionUser(){
        var promotionUserCreateDTOValidator = new PromotionUserCreateDTOValidator("15953214-12f7-4158-a049-0153c9bff56a",
                "a407dfed-b43e-4657-9fd8-c08ff978360e");

        var resultError = new BeanPropertyBindingResult(promotionUserCreateDTOValidator, "PromotionUserCreateDTOValidator");

        var promotionUserMap = new PromotionUserDTO();

        when(promotionUserRepository.create(any())).thenReturn(new PromotionUser());
        when(modelMapper.map(any(PromotionUser.class), eq(PromotionUserDTO.class))).thenReturn(promotionUserMap);

        // Act
        var result = promotionUserService.CreateAsync(promotionUserCreateDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, promotionUserMap);
    }

    @Test
    public void should_Return_Error_DTO_Is_Null_create_PromotionUser(){
        var resultError = new BeanPropertyBindingResult(new PromotionUserCreateDTOValidator(), "PromotionUserCreateDTOValidator");

        // Act
        var result = promotionUserService.CreateAsync(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error DTO Is Null");
    }

    @Test
    public void error_validate_DTO_When_Create_PromotionUser(){
        var promotionUserCreateDTOValidator = new PromotionUserCreateDTOValidator("15953214-12f7-4158-a049-0153c9bff56a",
                "a407dfed-b43e-4657-9fd8-c08ff978360e");

        var resultError = new BeanPropertyBindingResult(promotionUserCreateDTOValidator, "PromotionUserCreateDTOValidator");

        resultError.rejectValue("promotionId", "NotEmpty", "promotionId should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("promotionId", "promotionId should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = promotionUserService.CreateAsync(promotionUserCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void should_ThrowException_create_PromotionUser(){
        var promotionUserCreateDTOValidator = new PromotionUserCreateDTOValidator("15953214-12f7-4158-a049-0153c9bff56a",
                "a407dfed-b43e-4657-9fd8-c08ff978360e");

        var resultError = new BeanPropertyBindingResult(promotionUserCreateDTOValidator, "PromotionUserCreateDTOValidator");

        var promotionUserMap = new PromotionUserDTO();
        String expectedErrorMessage = "Database connection error";

        when(promotionUserRepository.create(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = promotionUserService.CreateAsync(promotionUserCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }
}