package com.backend.shopee.shopee_backend.applicationTest.AllServiceTest;

import com.backend.shopee.shopee_backend.application.dto.ProductsOfferFlashDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductsOfferFlashValidator.ProductsOfferFlashDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ErrorValidation;
import com.backend.shopee.shopee_backend.application.services.ProductsOfferFlashService;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryCreate;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryResult;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.shopee.shopee_backend.domain.entities.ProductsOfferFlash;
import com.backend.shopee.shopee_backend.domain.repositories.IProductsOfferFlashRepository;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ProductsOfferFlashServiceTest {
    @Mock
    private IProductsOfferFlashRepository productsOfferFlashRepository;
    @Mock
    private IValidateErrorsDTO validateErrorsDTO;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private ICloudinaryUti cloudinaryUti;

    private ProductsOfferFlashService productsOfferFlashService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        productsOfferFlashService = new ProductsOfferFlashService(productsOfferFlashRepository, validateErrorsDTO, modelMapper,
                cloudinaryUti);
    }

    @Test
    public void should_GetAllProduct_WithoutErrors(){
        var productsOfferFlashDTOS = List.of(new ProductsOfferFlashDTO());

        when(productsOfferFlashRepository.GetAllProduct()).thenReturn(productsOfferFlashDTOS);

        // Act
        var result = productsOfferFlashService.GetAllProduct();

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, productsOfferFlashDTOS);
    }

    @Test
    public void should_GetAllProduct_Return_Null(){
        when(productsOfferFlashRepository.GetAllProduct()).thenReturn(null);

        // Act
        var result = productsOfferFlashService.GetAllProduct();

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "not found promotion");
    }

    @Test
    public void should_ThrowException_When_GetAllProduct(){
        String expectedErrorMessage = "Database connection error";

        when(productsOfferFlashRepository.GetAllProduct()).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = productsOfferFlashService.GetAllProduct();

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_GetAllByTagProduct_WithoutErrors(){
        var productsOfferFlashDTOS = List.of(new ProductsOfferFlashDTO());

        var hourFlashOffer = "09:00";
        var tagProduct = "Mais_Populares";
        var pageNumber = 0;
        var pageSize = 10;

        when(productsOfferFlashRepository.GetAllByTagProduct(any(String.class), any(String.class), anyInt(), anyInt()))
                .thenReturn(productsOfferFlashDTOS);

        // Act
        var result = productsOfferFlashService.GetAllByTagProduct(hourFlashOffer, tagProduct, pageNumber, pageSize);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, productsOfferFlashDTOS);
    }

    @Test
    public void should_GetAllByTagProduct_Return_Null(){
        var hourFlashOffer = "09:00";
        var tagProduct = "Mais_Populares";
        var pageNumber = 0;
        var pageSize = 10;

        when(productsOfferFlashRepository.GetAllByTagProduct(any(String.class), any(String.class), anyInt(), anyInt()))
                .thenReturn(null);

        // Act
        var result = productsOfferFlashService.GetAllByTagProduct(hourFlashOffer, tagProduct, pageNumber, pageSize);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "not found promotion");
    }

    @Test
    public void should_ThrowException_When_GetAllByTagProduct(){
        var hourFlashOffer = "09:00";
        var tagProduct = "Mais_Populares";
        var pageNumber = 0;
        var pageSize = 10;

        String expectedErrorMessage = "Database connection error";

        when(productsOfferFlashRepository.GetAllByTagProduct(any(String.class), any(String.class), anyInt(), anyInt()))
                .thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = productsOfferFlashService.GetAllByTagProduct(hourFlashOffer, tagProduct, pageNumber, pageSize);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_create_Successfully_ProductsOfferFlash(){
        var productsOfferFlashDTOValidator = new ProductsOfferFlashDTOValidator("imgProduct1", "altValue1", 93.99,
                50,25, "hourFlashOffer1", "title1", "MOST_POPULAR");

        var resultError = new BeanPropertyBindingResult(productsOfferFlashDTOValidator, "productsOfferFlashDTOValidator");

        var ProductsOfferFlashDTOMap = new ProductsOfferFlashDTO();
        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate("publicId", "img_url", true);

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);
        when(productsOfferFlashRepository.create(any())).thenReturn(new ProductsOfferFlash());
        when(modelMapper.map(any(ProductsOfferFlash.class), eq(ProductsOfferFlashDTO.class))).thenReturn(ProductsOfferFlashDTOMap);

        var result = productsOfferFlashService.CreateAsync(productsOfferFlashDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, ProductsOfferFlashDTOMap);
    }

    @Test
    public void should_Return_Error_DTO_Is_Null_create_ProductsOfferFlash(){
        var resultError = new BeanPropertyBindingResult(new ProductsOfferFlashDTOValidator(), "ProductsOfferFlashDTOValidator");

        // Act
        var result = productsOfferFlashService.CreateAsync(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error DTO Is Null");
    }

    @Test
    public void error_validate_DTO_When_Create_ProductsOfferFlash(){
        var productsOfferFlashDTOValidator = new ProductsOfferFlashDTOValidator("imgProduct1", "altValue1", 93.99,
                50,25, "hourFlashOffer1", "title1", "MOST_POPULAR");

        var resultError = new BeanPropertyBindingResult(productsOfferFlashDTOValidator, "ProductsOfferFlashDTOValidator");

        resultError.rejectValue("ImgProduct", "NotEmpty", "ImgProduct should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("ImgProduct", "ImgProduct should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = productsOfferFlashService.CreateAsync(productsOfferFlashDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void should_Throw_Error_When_Validate_Tag_Product_create_ProductsOfferFlash(){
        var productsOfferFlashDTOValidator = new ProductsOfferFlashDTOValidator("imgProduct1", "altValue1", 93.99,
                50,25, "hourFlashOffer1", "title1", "sdsdsd");

        var resultError = new BeanPropertyBindingResult(productsOfferFlashDTOValidator, "productsOfferFlashDTOValidator");

        var result = productsOfferFlashService.CreateAsync(productsOfferFlashDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "provided type is not valid");
    }

    @Test
    public void should_Error_When_Create_Img_Cloudinary_create_ProductsOfferFlash(){
        var productsOfferFlashDTOValidator = new ProductsOfferFlashDTOValidator("imgProduct1", "altValue1", 93.99,
                50,25, "hourFlashOffer1", "title1", "MOST_POPULAR");

        var resultError = new BeanPropertyBindingResult(productsOfferFlashDTOValidator, "productsOfferFlashDTOValidator");

        var ProductsOfferFlashDTOMap = new ProductsOfferFlashDTO();
        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate(null, null, false);

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);

        var result = productsOfferFlashService.CreateAsync(productsOfferFlashDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error when create Img Promotion");
    }

    @Test
    public void should_ThrowException_create_ProductsOfferFlash(){
        var productsOfferFlashDTOValidator = new ProductsOfferFlashDTOValidator("imgProduct1", "altValue1", 93.99,
                50,25, "hourFlashOffer1", "title1", "MOST_POPULAR");

        var resultError = new BeanPropertyBindingResult(productsOfferFlashDTOValidator, "productsOfferFlashDTOValidator");

        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate("publicId", "img_url", true);

        String expectedErrorMessage = "Database connection error";

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);
        when(productsOfferFlashRepository.create(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        var result = productsOfferFlashService.CreateAsync(productsOfferFlashDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_Delete_Successfully_ProductsOfferFlash(){
        var productsOfferFlashId = "5fba6450-5b40-4bff-8a7c-7330d5e20ea6";

        var productsOfferFlashDelete = new ProductsOfferFlashDTO();
        productsOfferFlashDelete.setImgProduct("imgProduct1");
        productsOfferFlashDelete.setId(UUID.fromString(productsOfferFlashId));

        var productsOfferFlashDeleteSuccessfully = new ProductsOfferFlashDTO();

        var cloudinaryResult = new CloudinaryResult(true, false, "delete Successfully");

        when(productsOfferFlashRepository.GetProductsOfferFlashById(anyString())).thenReturn(productsOfferFlashDelete);
        when(cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(anyString())).thenReturn(cloudinaryResult);
        when(productsOfferFlashRepository.delete(any())).thenReturn(new ProductsOfferFlash());
        when(modelMapper.map(any(ProductsOfferFlash.class), eq(ProductsOfferFlashDTO.class))).thenReturn(productsOfferFlashDeleteSuccessfully);

        var result = productsOfferFlashService.Delete(productsOfferFlashId);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, productsOfferFlashDeleteSuccessfully);
    }

    @Test
    public void should_Return_Null_When_ProductsOfferFlash_Delete(){
        var productsOfferFlashId = "5fba6450-5b40-4bff-8a7c-7330d5e20ea6";

        when(productsOfferFlashRepository.GetProductsOfferFlashById(anyString())).thenReturn(null);

        var result = productsOfferFlashService.Delete(productsOfferFlashId);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "User not found");
    }

    @Test
    public void should_Error_When_Delete_Cloudinary_ProductsOfferFlash_Delete(){
        var productsOfferFlashId = "5fba6450-5b40-4bff-8a7c-7330d5e20ea6";

        var productsOfferFlashDelete = new ProductsOfferFlashDTO();
        productsOfferFlashDelete.setImgProduct("imgProduct1");
        productsOfferFlashDelete.setId(UUID.fromString(productsOfferFlashId));

        var cloudinaryResult = new CloudinaryResult(false, false, "error delete img");

        when(productsOfferFlashRepository.GetProductsOfferFlashById(anyString())).thenReturn(productsOfferFlashDelete);
        when(cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(anyString())).thenReturn(cloudinaryResult);

        var result = productsOfferFlashService.Delete(productsOfferFlashId);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error delete img");
    }

    @Test
    public void should_ThrowException_Delete_ProductsOfferFlash(){
        var productsOfferFlashId = "5fba6450-5b40-4bff-8a7c-7330d5e20ea6";

        var productsOfferFlashDelete = new ProductsOfferFlashDTO();
        productsOfferFlashDelete.setImgProduct("imgProduct1");
        productsOfferFlashDelete.setId(UUID.fromString(productsOfferFlashId));

        var productsOfferFlashDeleteSuccessfully = new ProductsOfferFlashDTO();

        var cloudinaryResult = new CloudinaryResult(true, false, "delete Successfully");

        String expectedErrorMessage = "Database connection error";

        when(productsOfferFlashRepository.GetProductsOfferFlashById(anyString())).thenReturn(productsOfferFlashDelete);
        when(cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(anyString())).thenReturn(cloudinaryResult);
        when(productsOfferFlashRepository.delete(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        var result = productsOfferFlashService.Delete(productsOfferFlashId);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }
}