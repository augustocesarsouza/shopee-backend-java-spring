package com.backend.shopee.shopee_backend.applicationTest.AllServiceTest;

import com.backend.shopee.shopee_backend.application.dto.ProductOptionImageBottomDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductOptionImageBottomValidationDTOs.ProductOptionImageBottomCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ErrorValidation;
import com.backend.shopee.shopee_backend.application.services.ProductOptionImageBottomService;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryCreate;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.shopee.shopee_backend.domain.entities.ProductOptionImageBottom;
import com.backend.shopee.shopee_backend.domain.repositories.IProductOptionImageBottomRepository;
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

public class ProductOptionImageBottomServiceTest {
    @Mock
    private IProductOptionImageBottomRepository productOptionImageBottomRepository;
    @Mock
    private IValidateErrorsDTO validateErrorsDTO;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private ICloudinaryUti cloudinaryUti;

    private ProductOptionImageBottomService productOptionImageBottomService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        productOptionImageBottomService = new ProductOptionImageBottomService(productOptionImageBottomRepository, validateErrorsDTO,
                modelMapper, cloudinaryUti);
    }

    @Test
    public void should_GetByListFlashSaleProductImageAllId_WithoutErrors(){
        var productsOfferFlashId = "1ec67d67-1f53-46f4-815c-55401e247358";

        var productOptionImageBottomDTO = new ProductOptionImageBottomDTO();

        when(productOptionImageBottomRepository.GetByListFlashSaleProductImageAllId(any()))
                .thenReturn(productOptionImageBottomDTO);

        // Act
        var result = productOptionImageBottomService.GetByListFlashSaleProductImageAllId(UUID.fromString(productsOfferFlashId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, productOptionImageBottomDTO);
    }

    @Test
    public void should_GetByListFlashSaleProductImageAllId_Return_Null(){
        var productsOfferFlashId = "1ec67d67-1f53-46f4-815c-55401e247358";

        when(productOptionImageBottomRepository.GetByListFlashSaleProductImageAllId(any())).thenReturn(null);

        // Act
        var result = productOptionImageBottomService.GetByListFlashSaleProductImageAllId(UUID.fromString(productsOfferFlashId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "not found productOptionImageBottom");
    }

    @Test
    public void should_ThrowException_When_GetByListFlashSaleProductImageAllId(){
        var productsOfferFlashId = "1ec67d67-1f53-46f4-815c-55401e247358";

        String expectedErrorMessage = "Database connection error";

        when(productOptionImageBottomRepository.GetByListFlashSaleProductImageAllId(any()))
                .thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = productOptionImageBottomService.GetByListFlashSaleProductImageAllId(UUID.fromString(productsOfferFlashId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_create_Successfully_ProductOptionImageBottom(){
        var arrayList = List.of("sadasdasdasdas");
        var productOptionImageBottomCreateDTOValidator = new ProductOptionImageBottomCreateDTOValidator("1da4fa83-ca7e-4f98-932d-95b33f4b88b8",
                arrayList);

        var resultError = new BeanPropertyBindingResult(productOptionImageBottomCreateDTOValidator, "ProductOptionImageBottomCreateDTOValidator");

        var productOptionImageBottomMap = new ProductOptionImageBottomDTO();
        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate("publicId", "img_url", true);

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);
        when(productOptionImageBottomRepository.create(any())).thenReturn(new ProductOptionImageBottom());
        when(modelMapper.map(any(ProductOptionImageBottom.class), eq(ProductOptionImageBottomDTO.class))).thenReturn(productOptionImageBottomMap);

        var result = productOptionImageBottomService.CreateAsync(productOptionImageBottomCreateDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, productOptionImageBottomMap);
    }

    @Test
    public void should_Return_Error_DTO_Is_Null_create_ProductOptionImageBottom(){
        var resultError = new BeanPropertyBindingResult(new ProductOptionImageBottomCreateDTOValidator(), "ProductOptionImageBottomCreateDTOValidator");

        // Act
        var result = productOptionImageBottomService.CreateAsync(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error DTO Is Null");
    }

    @Test
    public void error_validate_DTO_When_Create_ProductOptionImageBottom(){
        var arrayList = new ArrayList<String>();
        var productOptionImageBottomCreateDTOValidator = new ProductOptionImageBottomCreateDTOValidator("1da4fa83-ca7e-4f98-932d-95b33f4b88b8",
                arrayList);

        var resultError = new BeanPropertyBindingResult(productOptionImageBottomCreateDTOValidator, "ProductOptionImageBottomCreateDTOValidator");

        resultError.rejectValue("ListImageUrlBottom", "Size", "listImageUrlBottom must contain at least one URL");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("ListImageUrlBottom", "listImageUrlBottom must contain at least one URL");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = productOptionImageBottomService.CreateAsync(productOptionImageBottomCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void should_Error_When_Create_Img_Cloudinary_create_ProductOptionImageBottom(){
        var arrayList = List.of("sadasdasdasdas");
        var productOptionImageBottomCreateDTOValidator = new ProductOptionImageBottomCreateDTOValidator("1da4fa83-ca7e-4f98-932d-95b33f4b88b8",
                arrayList);

        var resultError = new BeanPropertyBindingResult(productOptionImageBottomCreateDTOValidator, "ProductOptionImageBottomCreateDTOValidator");

        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate(null, null, false);

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);

        var result = productOptionImageBottomService.CreateAsync(productOptionImageBottomCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "Error when creating Img product option");
    }

    @Test
    public void should_ThrowException_create_ProductOptionImageBottom(){
        var arrayList = List.of("sadasdasdasdas");
        var productOptionImageBottomCreateDTOValidator = new ProductOptionImageBottomCreateDTOValidator("1da4fa83-ca7e-4f98-932d-95b33f4b88b8",
                arrayList);

        var resultError = new BeanPropertyBindingResult(productOptionImageBottomCreateDTOValidator, "ProductOptionImageBottomCreateDTOValidator");

        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate("publicId", "img_url", true);

        String expectedErrorMessage = "Database connection error";

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);
        when(productOptionImageBottomRepository.create(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        var result = productOptionImageBottomService.CreateAsync(productOptionImageBottomCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }
}
