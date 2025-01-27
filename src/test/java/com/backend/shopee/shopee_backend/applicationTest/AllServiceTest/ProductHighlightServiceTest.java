package com.backend.shopee.shopee_backend.applicationTest.AllServiceTest;

import com.backend.shopee.shopee_backend.application.dto.ProductHighlightDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductHighlightValidatorDTOs.ProductHighlightDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ErrorValidation;
import com.backend.shopee.shopee_backend.application.services.ProductHighlightService;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryCreate;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryResult;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.shopee.shopee_backend.domain.entities.ProductHighlight;
import com.backend.shopee.shopee_backend.domain.repositories.IProductHighlightRepository;
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

public class ProductHighlightServiceTest {
    @Mock
    private IProductHighlightRepository productHighlightRepository;
    @Mock
    private IValidateErrorsDTO validateErrorsDTO;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private ICloudinaryUti cloudinaryUti;

    private ProductHighlightService productHighlightService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        productHighlightService = new ProductHighlightService(productHighlightRepository, validateErrorsDTO,
                modelMapper, cloudinaryUti);
    }

    @Test
    public void should_GetProductHighlightById_WithoutErrors(){
        var productHighlightId = "1ec67d67-1f53-46f4-815c-55401e247358";

        var productHighlightDTO = new ProductHighlightDTO();

        when(productHighlightRepository.GetProductHighlightById(any())).thenReturn(productHighlightDTO);

        // Act
        var result = productHighlightService.GetProductHighlightById(UUID.fromString(productHighlightId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, productHighlightDTO);
    }

    @Test
    public void should_GetProductHighlightById_Return_Null(){
        var productHighlightId = "1ec67d67-1f53-46f4-815c-55401e247358";

        when(productHighlightRepository.GetProductHighlightById(any())).thenReturn(null);

        // Act
        var result = productHighlightService.GetProductHighlightById(UUID.fromString(productHighlightId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "not found productHighlight");
    }

    @Test
    public void should_ThrowException_When_GetProductHighlightById(){
        var productHighlightId = "1ec67d67-1f53-46f4-815c-55401e247358";

        String expectedErrorMessage = "Database connection error";

        when(productHighlightRepository.GetProductHighlightById(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = productHighlightService.GetProductHighlightById(UUID.fromString(productHighlightId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_GetAllProductHighlights_WithoutErrors(){
        var productHighlightDTOs = List.of(new ProductHighlightDTO());

        when(productHighlightRepository.GetAllProductHighlight()).thenReturn(productHighlightDTOs);

        // Act
        var result = productHighlightService.GetAllProductHighlights();

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, productHighlightDTOs);
    }

    @Test
    public void should_GetAllProductHighlights_Return_Null(){
        when(productHighlightRepository.GetAllProductHighlight()).thenReturn(null);

        // Act
        var result = productHighlightService.GetAllProductHighlights();

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "not found productHighlight");
    }

    @Test
    public void should_ThrowException_When_GetAllProductHighlights(){
        String expectedErrorMessage = "Database connection error";

        when(productHighlightRepository.GetAllProductHighlight()).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = productHighlightService.GetAllProductHighlights();

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_create_Successfully_ProductHighlight(){
        var productHighlightDTOValidator = new ProductHighlightDTOValidator("title1", "imgProduct1",
                "imgTop1", 80.000);

        var resultError = new BeanPropertyBindingResult(productHighlightDTOValidator, "ProductHighlightDTOValidator");

        var productHighlightMap = new ProductHighlightDTO();
        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate("publicId", "img_url", true);

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);
        when(productHighlightRepository.create(any())).thenReturn(new ProductHighlight());
        when(modelMapper.map(any(ProductHighlight.class), eq(ProductHighlightDTO.class))).thenReturn(productHighlightMap);

        var result = productHighlightService.CreateAsync(productHighlightDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, productHighlightMap);
    }

    @Test
    public void should_Return_Error_DTO_Is_Null_create_ProductHighlight(){
        var resultError = new BeanPropertyBindingResult(new ProductHighlightDTOValidator(), "ProductHighlightDTOValidator");

        // Act
        var result = productHighlightService.CreateAsync(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error DTO Is Null");
    }

    @Test
    public void error_validate_DTO_When_Create_ProductHighlight(){
        var productHighlightDTOValidator = new ProductHighlightDTOValidator(null, "imgProduct1",
                "imgTop1", 80.000);

        var resultError = new BeanPropertyBindingResult(productHighlightDTOValidator, "ProductHighlightDTOValidator");

        resultError.rejectValue("Title", "NotEmpty", "Title should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("Title", "Title should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = productHighlightService.CreateAsync(productHighlightDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void should_Error_When_Create_Img_Cloudinary_create_ProductHighlight(){
        var productHighlightDTOValidator = new ProductHighlightDTOValidator("title1", "imgProduct1",
                "imgTop1", 80.000);

        var resultError = new BeanPropertyBindingResult(productHighlightDTOValidator, "ProductHighlightDTOValidator");

        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate(null, null, false);

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);

        var result = productHighlightService.CreateAsync(productHighlightDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error when create Img category");
    }

    @Test
    public void should_ThrowException_create_ProductHighlight(){
        var productHighlightDTOValidator = new ProductHighlightDTOValidator("title1", "imgProduct1",
                "imgTop1", 80.000);

        var resultError = new BeanPropertyBindingResult(productHighlightDTOValidator, "ProductHighlightDTOValidator");

        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate("publicId", "img_url", true);

        String expectedErrorMessage = "Database connection error";

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);
        when(productHighlightRepository.create(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        var result = productHighlightService.CreateAsync(productHighlightDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_Delete_Successfully_ProductHighlight(){
        var productHighlightId = "c549b038-9902-490b-888f-de220eb29934";

        var productHighlightDTO = new ProductHighlightDTO();
        productHighlightDTO.setId(UUID.fromString("f2753c7a-f4a5-4baf-b256-4f02168afd41"));
        productHighlightDTO.setImgProduct("ImgProduct1");

        var productHighlightDTOMap = new ProductHighlightDTO();
        var cloudinaryResult = new CloudinaryResult(true, false, "delete Successfully");

        when(productHighlightRepository.GetProductHighlightToDelete(any())).thenReturn(productHighlightDTO);
        when(cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(anyString())).thenReturn(cloudinaryResult);
        when(productHighlightRepository.delete(any())).thenReturn(new ProductHighlight());
        when(modelMapper.map(any(ProductHighlight.class), eq(ProductHighlightDTO.class))).thenReturn(productHighlightDTOMap);

        var result = productHighlightService.Delete(UUID.fromString(productHighlightId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, productHighlightDTOMap);
    }

    @Test
    public void should_Error_GetProductHighlightToDelete_Return_Null_Delete_ProductHighlight(){
        var productHighlightId = "c549b038-9902-490b-888f-de220eb29934";

        when(productHighlightRepository.GetProductHighlightToDelete(any())).thenReturn(null);

        var result = productHighlightService.Delete(UUID.fromString(productHighlightId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "productHighlight not found");
    }

    @Test
    public void should_Throw_Error_When_Delete_Img_Cloudinary_Delete_ProductHighlight(){
        var productHighlightId = "c549b038-9902-490b-888f-de220eb29934";

        var productHighlightDTO = new ProductHighlightDTO();
        productHighlightDTO.setId(UUID.fromString("f2753c7a-f4a5-4baf-b256-4f02168afd41"));
        productHighlightDTO.setImgProduct("ImgProduct1");

        var cloudinaryResult = new CloudinaryResult(false, false, "error when delete img cloudinary");

        when(productHighlightRepository.GetProductHighlightToDelete(any())).thenReturn(productHighlightDTO);
        when(cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(anyString())).thenReturn(cloudinaryResult);

        var result = productHighlightService.Delete(UUID.fromString(productHighlightId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error when delete img cloudinary");
    }

    @Test
    public void should_ThrowException_Delete_ProductHighlight(){
        var productHighlightId = "c549b038-9902-490b-888f-de220eb29934";

        var productHighlightDTO = new ProductHighlightDTO();
        productHighlightDTO.setId(UUID.fromString("f2753c7a-f4a5-4baf-b256-4f02168afd41"));
        productHighlightDTO.setImgProduct("ImgProduct1");

        var cloudinaryResult = new CloudinaryResult(true, false, "delete Successfully");

        String expectedErrorMessage = "Database connection error";

        when(productHighlightRepository.GetProductHighlightToDelete(any())).thenReturn(productHighlightDTO);
        when(cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(anyString())).thenReturn(cloudinaryResult);
        when(productHighlightRepository.delete(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        var result = productHighlightService.Delete(UUID.fromString(productHighlightId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }
}
