package com.backend.shopee.shopee_backend.applicationTest.AllServiceTest;

import com.backend.shopee.shopee_backend.application.dto.ShopeeUpdateUserDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ShopeeUpdateUserValidationDTOs.PromotionUpdateUserCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ErrorValidation;
import com.backend.shopee.shopee_backend.application.services.ShopeeUpdateUserService;
import com.backend.shopee.shopee_backend.domain.entities.ShopeeUpdateUser;
import com.backend.shopee.shopee_backend.domain.repositories.IShopeeUpdateUserRepository;
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

public class ShopeeUpdateUserServiceTest {
    @Mock
    private IShopeeUpdateUserRepository shopeeUpdateUserRepository;
    @Mock
    private IValidateErrorsDTO validateErrorsDTO;
    @Mock
    private ModelMapper modelMapper;

    private ShopeeUpdateUserService shopeeUpdateUserService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        shopeeUpdateUserService = new ShopeeUpdateUserService(shopeeUpdateUserRepository, validateErrorsDTO, modelMapper);
    }

    @Test
    public void should_GetById_WithoutErrors(){
        String userId = "44bb510c-3e32-4946-b626-0cc2096f1e13";
        var shopeeUpdateUserDTOList = List.of(new ShopeeUpdateUserDTO());

        when(shopeeUpdateUserRepository.GetByUserIdAll(any())).thenReturn(shopeeUpdateUserDTOList);

        // Act
        var result = shopeeUpdateUserService.GetByUserIdAll(UUID.fromString(userId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, shopeeUpdateUserDTOList);
    }

    @Test
    public void should_GetById_Return_Null(){
        String userId = "44bb510c-3e32-4946-b626-0cc2096f1e13";

        when(shopeeUpdateUserRepository.GetByUserIdAll(any())).thenReturn(null);

        // Act
        var result = shopeeUpdateUserService.GetByUserIdAll(UUID.fromString(userId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "not found promotion");
    }

    @Test
    public void should_ThrowException_When_GetById(){
        String promotionId = "cc27392b-944a-4e22-b399-231387bdd984";
        String expectedErrorMessage = "Database connection error";

        when(shopeeUpdateUserRepository.GetByUserIdAll(any()))
                .thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = shopeeUpdateUserService.GetByUserIdAll(UUID.fromString(promotionId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_create_Successfully_ShopeeUpdateUser(){
        var promotionUpdateUserCreateDTOValidator = new PromotionUpdateUserCreateDTOValidator("c19d8a84-5284-4af6-8744-42963c3f4ed9",
                "ff22b6fa-947d-4a29-8f1d-8e5060d88a3b");

        var resultError = new BeanPropertyBindingResult(promotionUpdateUserCreateDTOValidator, "PromotionUpdateUserCreateDTOValidator");

        var shopeeUpdateUserMap = new ShopeeUpdateUserDTO();

        when(shopeeUpdateUserRepository.create(any())).thenReturn(new ShopeeUpdateUser());
        when(modelMapper.map(any(ShopeeUpdateUser.class), eq(ShopeeUpdateUserDTO.class))).thenReturn(shopeeUpdateUserMap);

        // Act
        var result = shopeeUpdateUserService.CreateAsync(promotionUpdateUserCreateDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, shopeeUpdateUserMap);
    }

    @Test
    public void should_Return_Error_DTO_Is_Null_create_ShopeeUpdateUser(){
        var resultError = new BeanPropertyBindingResult(new PromotionUpdateUserCreateDTOValidator(), "PromotionUpdateUserCreateDTOValidator");

        // Act
        var result = shopeeUpdateUserService.CreateAsync(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error DTO Is Null");
    }

    @Test
    public void error_validate_DTO_When_Create_ProductsOfferFlash(){
        var promotionUpdateUserCreateDTOValidator = new PromotionUpdateUserCreateDTOValidator("c19d8a84-5284-4af6-8744-42963c3f4ed9",
                "ff22b6fa-947d-4a29-8f1d-8e5060d88a3b");

        var resultError = new BeanPropertyBindingResult(promotionUpdateUserCreateDTOValidator, "PromotionUpdateUserCreateDTOValidator");

        resultError.rejectValue("ShopeeUpdateId", "NotEmpty", "ShopeeUpdateId should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("ShopeeUpdateId", "shopeeUpdateId should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = shopeeUpdateUserService.CreateAsync(promotionUpdateUserCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void should_ThrowException_create_ShopeeUpdateUser(){
        var promotionUpdateUserCreateDTOValidator = new PromotionUpdateUserCreateDTOValidator("c19d8a84-5284-4af6-8744-42963c3f4ed9",
                "ff22b6fa-947d-4a29-8f1d-8e5060d88a3b");

        var resultError = new BeanPropertyBindingResult(promotionUpdateUserCreateDTOValidator, "PromotionUpdateUserCreateDTOValidator");

        var shopeeUpdateUserMap = new ShopeeUpdateUserDTO();
        String expectedErrorMessage = "Database connection error";

        when(shopeeUpdateUserRepository.create(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = shopeeUpdateUserService.CreateAsync(promotionUpdateUserCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }
}