package com.backend.shopee.shopee_backend.applicationTest.userServiceTest;

import com.backend.shopee.shopee_backend.application.dto.*;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.AddressValidationDTOs.AddressCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.CuponValidationDTOs.CuponCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.FlashSaleProductAllInfoValidationDTO.FlashSaleProductAllInfoCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.CuponService;
import com.backend.shopee.shopee_backend.application.services.ErrorValidation;
import com.backend.shopee.shopee_backend.application.services.FlashSaleProductAllInfoService;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import com.backend.shopee.shopee_backend.application.services.interfaces.IProductsOfferFlashService;
import com.backend.shopee.shopee_backend.domain.entities.Cupon;
import com.backend.shopee.shopee_backend.domain.entities.FlashSaleProductAllInfo;
import com.backend.shopee.shopee_backend.domain.repositories.IFlashSaleProductAllInfoRepository;
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

public class FlashSaleProductAllInfoServiceTest {
    @Mock
    private IFlashSaleProductAllInfoRepository flashSaleProductAllInfoRepository;
    @Mock
    private IValidateErrorsDTO validateErrorsDTO;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private IProductsOfferFlashService productsOfferFlashService;

    private FlashSaleProductAllInfoService flashSaleProductAllInfoService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        flashSaleProductAllInfoService = new FlashSaleProductAllInfoService(flashSaleProductAllInfoRepository, validateErrorsDTO,
                modelMapper, productsOfferFlashService);
    }

    @Test
    public void should_GetFlashSaleProductByProductFlashSaleId_WithoutErrors(){
        String productFlashSaleId = "95ea1ca6-29ec-488b-90f7-50227e28dd37";
        var flashSaleProductAllInfoDTO = new FlashSaleProductAllInfoDTO();

        when(flashSaleProductAllInfoRepository.GetFlashSaleProductByProductFlashSaleId(any())).thenReturn(flashSaleProductAllInfoDTO);

        // Act
        var result = flashSaleProductAllInfoService.GetFlashSaleProductByProductFlashSaleId(UUID.fromString(productFlashSaleId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(flashSaleProductAllInfoDTO, result.Data);
    }

    @Test
    public void should_Error_When_GetFlashSaleProductByProductFlashSaleId_Return_Null(){
        String productFlashSaleId = "95ea1ca6-29ec-488b-90f7-50227e28dd37";

        when(flashSaleProductAllInfoRepository.GetFlashSaleProductByProductFlashSaleId(any())).thenReturn(null);

        // Act
        var result = flashSaleProductAllInfoService.GetFlashSaleProductByProductFlashSaleId(UUID.fromString(productFlashSaleId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "not found");
    }

    @Test
    public void should_ThrowException_When_GetFlashSaleProductByProductFlashSaleId(){
        String productFlashSaleId = "95ea1ca6-29ec-488b-90f7-50227e28dd37";
        String expectedErrorMessage = "Database connection error";

        when(flashSaleProductAllInfoRepository.GetFlashSaleProductByProductFlashSaleId(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = flashSaleProductAllInfoService.GetFlashSaleProductByProductFlashSaleId(UUID.fromString(productFlashSaleId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_create_Successfully_ProductsOfferFlash(){
        var flashSaleProductAllInfoCreateDTOValidator = new FlashSaleProductAllInfoCreateDTOValidator("2562f0a0-c47b-4ed4-8542-abc4f78ca4be",
                4.7, 36400, 33.100, 18400,
                500, "12x R$8,25", null, 536, null, false);

        var resultError = new BeanPropertyBindingResult(flashSaleProductAllInfoCreateDTOValidator, "FlashSaleProductAllInfoCreateDTOValidator");

        var flashSaleProductAllInfoDTOCreateMap = new FlashSaleProductAllInfoDTO();

        var mockResult = ResultService.Ok(new ProductsOfferFlashDTO());

        when(productsOfferFlashService.GetByProductsOfferFlashId(any())).thenReturn(mockResult);
        when(flashSaleProductAllInfoRepository.create(any())).thenReturn(new FlashSaleProductAllInfo());
        when(modelMapper.map(any(FlashSaleProductAllInfo.class), eq(FlashSaleProductAllInfoDTO.class))).thenReturn(flashSaleProductAllInfoDTOCreateMap);

        // Act
        var result = flashSaleProductAllInfoService.CreateAsync(flashSaleProductAllInfoCreateDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, flashSaleProductAllInfoDTOCreateMap);
    }

    @Test
    public void should_Return_Error_DTO_Is_Null_create_ProductsOfferFlash(){
        var resultError = new BeanPropertyBindingResult(new FlashSaleProductAllInfoCreateDTOValidator(), "FlashSaleProductAllInfoCreateDTOValidator");

        // Act
        var result = flashSaleProductAllInfoService.CreateAsync(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error DTO Is Null");
    }

    @Test
    public void error_validate_DTO_When_Create(){
        var flashSaleProductAllInfoCreateDTOValidator = new FlashSaleProductAllInfoCreateDTOValidator("",
                4.7, 36400, 33.100, 18400,
                500, "12x R$8,25", null, 536, null, false);

        var resultError = new BeanPropertyBindingResult(flashSaleProductAllInfoCreateDTOValidator, "FlashSaleProductAllInfoCreateDTOValidator");

        resultError.rejectValue("productsOfferFlashId", "productsOfferFlashId", "productsOfferFlashId should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("productsOfferFlashId", "productsOfferFlashId should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = flashSaleProductAllInfoService.CreateAsync(flashSaleProductAllInfoCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void should_Throw_Error_When_Verify_Whether_ProductsOfferFlash_Exist_create_ProductsOfferFlash(){
        var flashSaleProductAllInfoCreateDTOValidator = new FlashSaleProductAllInfoCreateDTOValidator("2562f0a0-c47b-4ed4-8542-abc4f78ca4be",
                4.7, 36400, 33.100, 18400,
                500, "12x R$8,25", null, 536, null, false);

        var resultError = new BeanPropertyBindingResult(flashSaleProductAllInfoCreateDTOValidator, "FlashSaleProductAllInfoCreateDTOValidator");

        ResultService<ProductsOfferFlashDTO> mockResult = ResultService.Fail("Error ProductsOfferFlash Not exist");

        when(productsOfferFlashService.GetByProductsOfferFlashId(any())).thenReturn(mockResult);
        when(flashSaleProductAllInfoRepository.create(any())).thenReturn(new FlashSaleProductAllInfo());

        // Act
        var result = flashSaleProductAllInfoService.CreateAsync(flashSaleProductAllInfoCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "Error ProductsOfferFlash Not exist");
    }

    @Test
    public void should_ThrowException_When_create_ProductsOfferFlash(){
        var flashSaleProductAllInfoCreateDTOValidator = new FlashSaleProductAllInfoCreateDTOValidator("2562f0a0-c47b-4ed4-8542-abc4f78ca4be",
                4.7, 36400, 33.100, 18400,
                500, "12x R$8,25", null, 536, null, false);

        var resultError = new BeanPropertyBindingResult(flashSaleProductAllInfoCreateDTOValidator, "FlashSaleProductAllInfoCreateDTOValidator");

        var mockResult = ResultService.Ok(new ProductsOfferFlashDTO());

        String expectedErrorMessage = "Database connection error";

        when(productsOfferFlashService.GetByProductsOfferFlashId(any())).thenReturn(mockResult);
        when(flashSaleProductAllInfoRepository.create(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = flashSaleProductAllInfoService.CreateAsync(flashSaleProductAllInfoCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(expectedErrorMessage, result.Message);
    }
}
