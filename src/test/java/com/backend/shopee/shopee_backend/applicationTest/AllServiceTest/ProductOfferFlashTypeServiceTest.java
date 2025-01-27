package com.backend.shopee.shopee_backend.applicationTest.AllServiceTest;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashTypeDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductOfferFlashTypeValidationDTOs.ProductOfferFlashTypeCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ErrorValidation;
import com.backend.shopee.shopee_backend.application.services.ProductOfferFlashTypeService;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryCreate;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.shopee.shopee_backend.domain.entities.ProductOfferFlashType;
import com.backend.shopee.shopee_backend.domain.repositories.IProductOfferFlashTypeRepository;
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

public class ProductOfferFlashTypeServiceTest {
    @Mock
    private IProductOfferFlashTypeRepository productOfferFlashTypeRepository;
    @Mock
    private IValidateErrorsDTO validateErrorsDTO;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private ICloudinaryUti cloudinaryUti;

    private ProductOfferFlashTypeService productOfferFlashTypeService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        productOfferFlashTypeService = new ProductOfferFlashTypeService(productOfferFlashTypeRepository, validateErrorsDTO,
                modelMapper, cloudinaryUti);
    }

    @Test
    public void should_GetAllProductOfferFlashTypeByProductsOfferFlashId_WithoutErrors(){
        var productOfferFlashTypeId = "7bfdc595-b111-489b-b6a7-72265943011d";

        var productOfferFlashTypeDTO = List.of(new ProductOfferFlashTypeDTO());

        when(productOfferFlashTypeRepository.GetAllProductOfferFlashTypeByProductsOfferFlashId(any()))
                .thenReturn(productOfferFlashTypeDTO);

        // Act
        var result = productOfferFlashTypeService.GetAllProductOfferFlashTypeByProductsOfferFlashId(UUID.fromString(productOfferFlashTypeId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, productOfferFlashTypeDTO);
    }

    @Test
    public void should_GetAllProductOfferFlashTypeByProductsOfferFlashId_Return_Null(){
        var productOfferFlashTypeId = "7bfdc595-b111-489b-b6a7-72265943011d";

        when(productOfferFlashTypeRepository.GetAllProductOfferFlashTypeByProductsOfferFlashId(any())).thenReturn(null);

        // Act
        var result = productOfferFlashTypeService.GetAllProductOfferFlashTypeByProductsOfferFlashId(UUID.fromString(productOfferFlashTypeId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "not found productsOfferFlash list");
    }

    @Test
    public void should_ThrowException_When_GetAllProductOfferFlashTypeByProductsOfferFlashId(){
        var productOfferFlashTypeId = "7bfdc595-b111-489b-b6a7-72265943011d";

        String expectedErrorMessage = "Database connection error";

        when(productOfferFlashTypeRepository.GetAllProductOfferFlashTypeByProductsOfferFlashId(any()))
                .thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = productOfferFlashTypeService.GetAllProductOfferFlashTypeByProductsOfferFlashId(UUID.fromString(productOfferFlashTypeId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_create_Successfully_ProductOfferFlashType(){
        var productOfferFlashTypeValidator = new ProductOfferFlashTypeCreateDTOValidator("imgProduct1",
                "optionType1", "8c8648aa-87df-4b81-bcac-a9028494211d", "titleImg1");

        var resultError = new BeanPropertyBindingResult(productOfferFlashTypeValidator, "ProductOfferFlashTypeCreateDTOValidator");

        var productOfferFlashTypeMap = new ProductOfferFlashTypeDTO();
        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate("publicId", "img_url", true);

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);
        when(productOfferFlashTypeRepository.create(any())).thenReturn(new ProductOfferFlashType());
        when(modelMapper.map(any(ProductOfferFlashType.class), eq(ProductOfferFlashTypeDTO.class))).thenReturn(productOfferFlashTypeMap);

        var result = productOfferFlashTypeService.CreateAsync(productOfferFlashTypeValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, productOfferFlashTypeMap);
    }

    @Test
    public void should_Return_Error_DTO_Is_Null_create_ProductOfferFlashType(){
        var resultError = new BeanPropertyBindingResult(new ProductOfferFlashTypeCreateDTOValidator(), "ProductOfferFlashTypeCreateDTOValidator");

        // Act
        var result = productOfferFlashTypeService.CreateAsync(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error DTO Is Null");
    }

    @Test
    public void error_validate_DTO_When_Create_ProductOfferFlashType(){
        var productOfferFlashTypeValidator = new ProductOfferFlashTypeCreateDTOValidator("imgProduct1",
                null, "8c8648aa-87df-4b81-bcac-a9028494211d", "titleImg1");

        var resultError = new BeanPropertyBindingResult(productOfferFlashTypeValidator, "ProductOfferFlashTypeCreateDTOValidator");

        resultError.rejectValue("OptionType", "NotEmpty", "OptionType should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("OptionType", "OptionType should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = productOfferFlashTypeService.CreateAsync(productOfferFlashTypeValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void should_Error_When_Create_Img_Cloudinary_create_ProductOfferFlashType(){
        var productOfferFlashTypeValidator = new ProductOfferFlashTypeCreateDTOValidator("imgProduct1",
                "optionType1", "8c8648aa-87df-4b81-bcac-a9028494211d", "titleImg1");

        var resultError = new BeanPropertyBindingResult(productOfferFlashTypeValidator, "ProductOfferFlashTypeCreateDTOValidator");

        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate(null, null, false);

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);

        var result = productOfferFlashTypeService.CreateAsync(productOfferFlashTypeValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error when create Img product offer flash type");
    }

    @Test
    public void should_ThrowException_create_ProductOfferFlashType(){
        var productOfferFlashTypeValidator = new ProductOfferFlashTypeCreateDTOValidator("imgProduct1",
                "optionType1", "8c8648aa-87df-4b81-bcac-a9028494211d", "titleImg1");

        var resultError = new BeanPropertyBindingResult(productOfferFlashTypeValidator, "ProductOfferFlashTypeCreateDTOValidator");

        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate("publicId", "img_url", true);

        String expectedErrorMessage = "Database connection error";

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);
        when(productOfferFlashTypeRepository.create(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        var result = productOfferFlashTypeService.CreateAsync(productOfferFlashTypeValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }
}
