package com.backend.shopee.shopee_backend.applicationTest.AllServiceTest;

import com.backend.shopee.shopee_backend.application.dto.ProductFlashSaleReviewDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductFlashSaleReviewValidationDTOs.ProductFlashSaleReviewCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ErrorValidation;
import com.backend.shopee.shopee_backend.application.services.ProductFlashSaleReviewService;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryCreate;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryResult;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.shopee.shopee_backend.domain.entities.ProductFlashSaleReview;
import com.backend.shopee.shopee_backend.domain.repositories.IProductFlashSaleReviewRepository;
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

public class ProductFlashSaleReviewServiceTest {
    @Mock
    private IProductFlashSaleReviewRepository productFlashSaleReviewRepository;
    @Mock
    private IValidateErrorsDTO validateErrorsDTO;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private ICloudinaryUti cloudinaryUti;

    private ProductFlashSaleReviewService productFlashSaleReviewService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        productFlashSaleReviewService = new ProductFlashSaleReviewService(productFlashSaleReviewRepository, validateErrorsDTO, modelMapper, cloudinaryUti);
    }

    @Test
    public void should_GetAllProductFlashSaleReviewsByProductsOfferFlashId_WithoutErrors(){
        String entityId = "3525bf68-91fa-4232-8fe9-bc7db8b5654d";
        List<ProductFlashSaleReviewDTO> entityDTOs = new ArrayList<>();

        when(productFlashSaleReviewRepository.GetAllProductFlashSaleReviewsByProductsOfferFlashId(any()))
                .thenReturn(entityDTOs);

        // Act
        var result = productFlashSaleReviewService.GetAllProductFlashSaleReviewsByProductsOfferFlashId(UUID.fromString(entityId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, entityDTOs);
    }

    @Test
    public void should_GetByUserSellerProductId_Return_Null(){
        String entityId = "3525bf68-91fa-4232-8fe9-bc7db8b5654d";

        when(productFlashSaleReviewRepository.GetAllProductFlashSaleReviewsByProductsOfferFlashId(any()))
                .thenReturn(null);

        // Act
        var result = productFlashSaleReviewService.GetAllProductFlashSaleReviewsByProductsOfferFlashId(UUID.fromString(entityId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "not found ProductFlashSaleReview");
    }

    @Test
    public void should_ThrowException_When_GetByUserSellerProductId(){
        String entityId = "3525bf68-91fa-4232-8fe9-bc7db8b5654d";

        String expectedErrorMessage = "Database connection error";

        when(productFlashSaleReviewRepository.GetAllProductFlashSaleReviewsByProductsOfferFlashId(any()))
                .thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = productFlashSaleReviewService.GetAllProductFlashSaleReviewsByProductsOfferFlashId(UUID.fromString(entityId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_create_Successfully_ProductFlashSaleReviewService(){
        List<String> imgAndVideoReviewsProducts = new ArrayList<>();
        imgAndVideoReviewsProducts.add("sdsdsd");

        var entityCreateDTOValidator = new ProductFlashSaleReviewCreateDTOValidator("message1",
                "costBenefit1", "similarToAd1", 4, "2995724b-bc5b-4dc9-b52c-03985f06b807", "77133a75-df8c-438f-a711-da40b73d2910",
                imgAndVideoReviewsProducts, "variation1");

        var resultError = new BeanPropertyBindingResult(entityCreateDTOValidator, "ProductFlashSaleReviewCreateDTOValidator");

        var entityUpdateDTOMap = new ProductFlashSaleReviewDTO();
        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate("publicId", "img_url", true);

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);
        when(productFlashSaleReviewRepository.create(any())).thenReturn(new ProductFlashSaleReview());
        when(modelMapper.map(any(ProductFlashSaleReview.class), eq(ProductFlashSaleReviewDTO.class))).thenReturn(entityUpdateDTOMap);

        // Act
        var result = productFlashSaleReviewService.CreateAsync(entityCreateDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, entityUpdateDTOMap);
    }

    @Test
    public void should_Return_Error_DTO_Is_Null_create_ProductFlashSaleReviewService(){
        var resultError = new BeanPropertyBindingResult(new ProductFlashSaleReviewCreateDTOValidator(), "ProductFlashSaleReviewCreateDTOValidator");

        // Act
        var result = productFlashSaleReviewService.CreateAsync(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error DTO Is Null");
    }

    @Test
    public void error_validate_DTO_When_Create_ProductFlashSaleReviewService(){
        var entityCreateDTOValidator = new ProductFlashSaleReviewCreateDTOValidator(null,
                "costBenefit1", "similarToAd1", 4, "2995724b-bc5b-4dc9-b52c-03985f06b807", "77133a75-df8c-438f-a711-da40b73d2910",
                null, "variation1");

        var resultError = new BeanPropertyBindingResult(entityCreateDTOValidator, "ProductFlashSaleReviewCreateDTOValidator");

        resultError.rejectValue("Message", "NotEmpty", "Message should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("Message", "Message should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = productFlashSaleReviewService.CreateAsync(entityCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void should_ThrowException_create_ProductFlashSaleReviewService(){
        var entityCreateDTOValidator = new ProductFlashSaleReviewCreateDTOValidator("message1",
                "costBenefit1", "similarToAd1", 4, "2995724b-bc5b-4dc9-b52c-03985f06b807", "77133a75-df8c-438f-a711-da40b73d2910",
                null, "variation1");

        var resultError = new BeanPropertyBindingResult(entityCreateDTOValidator, "ProductFlashSaleReviewCreateDTOValidator");

        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate("publicId", "img_url", true);

        String expectedErrorMessage = "Database connection error";

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);
        when(productFlashSaleReviewRepository.create(any()))
                .thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = productFlashSaleReviewService.CreateAsync(entityCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_Delete_Successfully_ProductFlashSaleReviewService_With_List_Image_And_Video(){
        var entityUpdateId = "349d6358-563b-4f89-bee4-f47ace5db0e8";

        var entityUpdateDelete = new ProductFlashSaleReviewDTO();
        List<String> listS = new ArrayList<>();
        listS.add("sdssdsdsdsdsdsdssdsdsdsdsd");
        entityUpdateDelete.setImgAndVideoReviewsProduct(listS);
        entityUpdateDelete.setId(UUID.fromString("fc1841d1-f26f-4274-8267-06d9dec71242"));

        var cloudinaryResult = new CloudinaryResult(true, false, "delete Successfully");

        var entityUpdateDTOToDelete = new ProductFlashSaleReviewDTO();

        when(productFlashSaleReviewRepository.GetByProductFlashSaleReviewIdToDelete(any())).thenReturn(entityUpdateDelete);
        when(cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(anyString())).thenReturn(cloudinaryResult);
        when(productFlashSaleReviewRepository.delete(any())).thenReturn(new ProductFlashSaleReview());
        when(modelMapper.map(any(ProductFlashSaleReview.class), eq(ProductFlashSaleReviewDTO.class))).thenReturn(entityUpdateDTOToDelete);

        var result = productFlashSaleReviewService.Delete(UUID.fromString(entityUpdateId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, entityUpdateDTOToDelete);
    }

    @Test
    public void should_Delete_Successfully_ProductFlashSaleReviewService_With_List_Empty(){
        var entityUpdateId = "349d6358-563b-4f89-bee4-f47ace5db0e8";

        var entityUpdateDelete = new ProductFlashSaleReviewDTO();

        var entityUpdateDTOToDelete = new ProductFlashSaleReviewDTO();

        when(productFlashSaleReviewRepository.GetByProductFlashSaleReviewIdToDelete(any())).thenReturn(entityUpdateDelete);
        when(productFlashSaleReviewRepository.delete(any())).thenReturn(new ProductFlashSaleReview());
        when(modelMapper.map(any(ProductFlashSaleReview.class), eq(ProductFlashSaleReviewDTO.class))).thenReturn(entityUpdateDTOToDelete);

        var result = productFlashSaleReviewService.Delete(UUID.fromString(entityUpdateId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, entityUpdateDTOToDelete);
    }

    @Test
    public void should_Return_Null_When_GetByProductFlashSaleReviewIdToDelete_Delete_ProductFlashSaleReviewService(){
        var entityUpdateId = "6c1b866d-c90a-4eb3-9eea-a85df6455547";

        when(productFlashSaleReviewRepository.GetByProductFlashSaleReviewIdToDelete(any())).thenReturn(null);

        var result = productFlashSaleReviewService.Delete(UUID.fromString(entityUpdateId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "ProductFlashSaleReviewDTO not found");
    }

    @Test
    public void should_Error_When_Delete_Cloudinary_Delete_ProductFlashSaleReviewService(){
        var entityUpdateId = "349d6358-563b-4f89-bee4-f47ace5db0e8";

        var entityUpdateDelete = new ProductFlashSaleReviewDTO();
        List<String> listS = new ArrayList<>();
        listS.add("sdssdsdsdsdsdsdssdsdsdsdsd");
        entityUpdateDelete.setImgAndVideoReviewsProduct(listS);
        entityUpdateDelete.setId(UUID.fromString("fc1841d1-f26f-4274-8267-06d9dec71242"));

        var cloudinaryResult = new CloudinaryResult(false, false, "error when delete img cloudinary");

        var entityUpdateDTOToDelete = new ProductFlashSaleReviewDTO();

        when(productFlashSaleReviewRepository.GetByProductFlashSaleReviewIdToDelete(any())).thenReturn(entityUpdateDelete);
        when(cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(anyString())).thenReturn(cloudinaryResult);

        var result = productFlashSaleReviewService.Delete(UUID.fromString(entityUpdateId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error when delete img cloudinary");
    }

    @Test
    public void should_ThrowException_Delete_ProductFlashSaleReviewService(){
        var entityUpdateId = "349d6358-563b-4f89-bee4-f47ace5db0e8";

        var entityUpdateDelete = new ProductFlashSaleReviewDTO();

        var cloudinaryResult = new CloudinaryResult(true, false, "delete Successfully");

        String expectedErrorMessage = "Database connection error";

        when(productFlashSaleReviewRepository.GetByProductFlashSaleReviewIdToDelete(any())).thenReturn(entityUpdateDelete);
        when(cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(anyString())).thenReturn(cloudinaryResult);
        when(productFlashSaleReviewRepository.delete(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        var result = productFlashSaleReviewService.Delete(UUID.fromString(entityUpdateId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }
}
