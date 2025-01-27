package com.backend.shopee.shopee_backend.applicationTest.AllServiceTest;

import com.backend.shopee.shopee_backend.application.dto.ProductDiscoveriesOfDayDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductDiscoveriesOfDayValidationDTOs.ProductDiscoveriesOfDayCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ErrorValidation;
import com.backend.shopee.shopee_backend.application.services.ProductDiscoveriesOfDayService;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryCreate;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryResult;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.shopee.shopee_backend.domain.entities.ProductDiscoveriesOfDay;
import com.backend.shopee.shopee_backend.domain.repositories.IProductDiscoveriesOfDayRepository;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class ProductDiscoveriesOfDayServiceTest {
    @Mock
    private IProductDiscoveriesOfDayRepository productDiscoveriesOfDayRepository;
    @Mock
    private IValidateErrorsDTO validateErrorsDTO;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private ICloudinaryUti cloudinaryUti;

    private ProductDiscoveriesOfDayService productDiscoveriesOfDayService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        productDiscoveriesOfDayService = new ProductDiscoveriesOfDayService(productDiscoveriesOfDayRepository, validateErrorsDTO, modelMapper,
                cloudinaryUti);
    }

    @Test
    public void should_GetProductDiscoveriesOfDayById_WithoutErrors(){
        var productDiscoveriesOfDayId = "ef68f2fb-0312-4010-975e-d3f45e4dd944";

        var productDiscoveriesOfDayDTO = new ProductDiscoveriesOfDayDTO();

        when(productDiscoveriesOfDayRepository.GetProductDiscoveriesOfDayById(any())).thenReturn(productDiscoveriesOfDayDTO);

        // Act
        var result = productDiscoveriesOfDayService.GetProductDiscoveriesOfDayById(UUID.fromString(productDiscoveriesOfDayId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, productDiscoveriesOfDayDTO);
    }

    @Test
    public void should_GetProductDiscoveriesOfDayById_Return_Null(){
        var productDiscoveriesOfDayId = "ef68f2fb-0312-4010-975e-d3f45e4dd944";

        when(productDiscoveriesOfDayRepository.GetProductDiscoveriesOfDayById(any())).thenReturn(null);

        // Act
        var result = productDiscoveriesOfDayService.GetProductDiscoveriesOfDayById(UUID.fromString(productDiscoveriesOfDayId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "not found productDiscoveriesOfDay");
    }

    @Test
    public void should_ThrowException_When_GetProductDiscoveriesOfDayById(){
        var productDiscoveriesOfDayId = "ef68f2fb-0312-4010-975e-d3f45e4dd944";

        String expectedErrorMessage = "Database connection error";

        when(productDiscoveriesOfDayRepository.GetProductDiscoveriesOfDayById(any()))
                .thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = productDiscoveriesOfDayService.GetProductDiscoveriesOfDayById(UUID.fromString(productDiscoveriesOfDayId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_GetAllProductDiscoveriesOfDays_WithoutErrors(){
        var productDiscoveriesOfDayDTOList = List.of(new ProductDiscoveriesOfDayDTO());

        when(productDiscoveriesOfDayRepository.GetAllProductDiscoveriesOfDay()).thenReturn(productDiscoveriesOfDayDTOList);

        // Act
        var result = productDiscoveriesOfDayService.GetAllProductDiscoveriesOfDays();

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, productDiscoveriesOfDayDTOList);
    }

    @Test
    public void should_GetAllProductDiscoveriesOfDays_Return_Null(){
        when(productDiscoveriesOfDayRepository.GetAllProductDiscoveriesOfDay()).thenReturn(null);

        // Act
        var result = productDiscoveriesOfDayService.GetAllProductDiscoveriesOfDays();

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "not found productDiscoveriesOfDay");
    }

    @Test
    public void should_ThrowException_When_GetAllProductDiscoveriesOfDays(){
        String expectedErrorMessage = "Database connection error";

        when(productDiscoveriesOfDayRepository.GetAllProductDiscoveriesOfDay())
                .thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = productDiscoveriesOfDayService.GetAllProductDiscoveriesOfDays();

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_create_Successfully_ProductDiscoveriesOfDay(){
        var productDiscoveriesOfDayCreateDTOValidator = new ProductDiscoveriesOfDayCreateDTOValidator("title1", "imgProduct1",
                false, 25.50, "imgPartBottom1", 50, 12.500);

        var resultError = new BeanPropertyBindingResult(productDiscoveriesOfDayCreateDTOValidator, "ProductDiscoveriesOfDayCreateDTOValidator");

        var productDiscoveriesOfDayDTOMap = new ProductDiscoveriesOfDayDTO();
        var cloudinaryCreate = new CloudinaryCreate("publicId", "img_url", true);

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);
        when(productDiscoveriesOfDayRepository.create(any())).thenReturn(new ProductDiscoveriesOfDay());
        when(modelMapper.map(any(ProductDiscoveriesOfDay.class), eq(ProductDiscoveriesOfDayDTO.class)))
                .thenReturn(productDiscoveriesOfDayDTOMap);

        var result = productDiscoveriesOfDayService.CreateAsync(productDiscoveriesOfDayCreateDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, productDiscoveriesOfDayDTOMap);
    }

    @Test
    public void should_Return_Error_DTO_Is_Null_create_ProductDiscoveriesOfDay(){
        var resultError = new BeanPropertyBindingResult(new ProductDiscoveriesOfDayCreateDTOValidator(), "ProductDiscoveriesOfDayCreateDTOValidator");

        // Act
        var result = productDiscoveriesOfDayService.CreateAsync(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error DTO Is Null");
    }

    @Test
    public void error_validate_DTO_When_Create_ProductDiscoveriesOfDay(){
        var productDiscoveriesOfDayCreateDTOValidator = new ProductDiscoveriesOfDayCreateDTOValidator(null, "imgProduct1",
                false, 25.50, "imgPartBottom1", 50, 12.500);

        var resultError = new BeanPropertyBindingResult(productDiscoveriesOfDayCreateDTOValidator, "ProductDiscoveriesOfDayCreateDTOValidator");

        resultError.rejectValue("Title", "NotEmpty", "Title should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("Title", "Title should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = productDiscoveriesOfDayService.CreateAsync(productDiscoveriesOfDayCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void should_Error_When_Create_Img_Cloudinary_create_ProductDiscoveriesOfDay(){
        var productDiscoveriesOfDayCreateDTOValidator = new ProductDiscoveriesOfDayCreateDTOValidator("title1", "imgProduct1",
                false, 25.50, "imgPartBottom1", 50, 12.500);

        var resultError = new BeanPropertyBindingResult(productDiscoveriesOfDayCreateDTOValidator, "ProductDiscoveriesOfDayCreateDTOValidator");

        var cloudinaryCreate = new CloudinaryCreate(null, null, false);

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);

        var result = productDiscoveriesOfDayService.CreateAsync(productDiscoveriesOfDayCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error when create Img category");
    }

    @Test
    public void should_ThrowException_create_ProductDiscoveriesOfDay(){
        var productDiscoveriesOfDayCreateDTOValidator = new ProductDiscoveriesOfDayCreateDTOValidator("title1", "imgProduct1",
                false, 25.50, "imgPartBottom1", 50, 12.500);

        var resultError = new BeanPropertyBindingResult(productDiscoveriesOfDayCreateDTOValidator, "ProductDiscoveriesOfDayCreateDTOValidator");

        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate("publicId", "img_url", true);

        String expectedErrorMessage = "Database connection error";

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);
        when(productDiscoveriesOfDayRepository.create(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        var result = productDiscoveriesOfDayService.CreateAsync(productDiscoveriesOfDayCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_Delete_Successfully_ProductDiscoveriesOfDay(){
        var productDiscoveriesOfDayId = "01f316f5-6965-4ee4-a4c8-bfcf42311b02";

        var productDiscoveriesOfDayDTO = new ProductDiscoveriesOfDayDTO();
        productDiscoveriesOfDayDTO.setId(UUID.fromString("01f396d5-6965-4ee4-a4c8-bfcf42311b02"));
        productDiscoveriesOfDayDTO.setImgProduct("imgProduct1");

        var productDiscoveriesOfDayDTOMap = new ProductDiscoveriesOfDayDTO();
        var cloudinaryResult = new CloudinaryResult(true, false, "delete Successfully");

        when(productDiscoveriesOfDayRepository.GetProductDiscoveriesOfDayToDelete(any()))
                .thenReturn(productDiscoveriesOfDayDTO);

        when(cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(anyString()))
                .thenReturn(cloudinaryResult);

        when(productDiscoveriesOfDayRepository.delete(any())).thenReturn(new ProductDiscoveriesOfDay());
        when(modelMapper.map(any(ProductDiscoveriesOfDay.class), eq(ProductDiscoveriesOfDayDTO.class)))
                .thenReturn(productDiscoveriesOfDayDTOMap);

        var result = productDiscoveriesOfDayService.Delete(UUID.fromString(productDiscoveriesOfDayId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, productDiscoveriesOfDayDTOMap);
    }

    @Test
    public void should_Error_GetCategoriesToDelete_Return_Null_Delete_ProductDiscoveriesOfDay(){
        var productDiscoveriesOfDayId = "25dc9cae-6d33-4a49-aa50-408bbc1f27c5";

        when(productDiscoveriesOfDayRepository.GetProductDiscoveriesOfDayToDelete(any()))
                .thenReturn(null);

        var result = productDiscoveriesOfDayService.Delete(UUID.fromString(productDiscoveriesOfDayId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "ProductDiscoveriesOfDay not found");
    }

    @Test
    public void should_Throw_Error_When_Delete_Img_Cloudinary_Delete_ProductDiscoveriesOfDay(){
        var productDiscoveriesOfDayId = "25dc9cae-6d33-4a49-aa50-408bbc1f27c5";

        var productDiscoveriesOfDayDTO = new ProductDiscoveriesOfDayDTO();
        productDiscoveriesOfDayDTO.setId(UUID.fromString("01f396d5-6965-4ee4-a4c8-bfcf42311b02"));
        productDiscoveriesOfDayDTO.setImgProduct("imgProduct1");

        var cloudinaryResult = new CloudinaryResult(false, false, "error when delete img cloudinary");

        when(productDiscoveriesOfDayRepository.GetProductDiscoveriesOfDayToDelete(any()))
                .thenReturn(productDiscoveriesOfDayDTO);

        when(cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(anyString()))
                .thenReturn(cloudinaryResult);

        var result = productDiscoveriesOfDayService.Delete(UUID.fromString(productDiscoveriesOfDayId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error when delete img cloudinary");
    }

    @Test
    public void should_ThrowException_Delete_ProductDiscoveriesOfDay(){
        var productDiscoveriesOfDayId = "25dc9cae-6d33-4a49-aa50-408bbc1f27c5";

        var productDiscoveriesOfDayDTO = new ProductDiscoveriesOfDayDTO();
        productDiscoveriesOfDayDTO.setId(UUID.fromString("01f396d5-6965-4ee4-a4c8-bfcf42311b02"));
        productDiscoveriesOfDayDTO.setImgProduct("imgProduct1");

        var cloudinaryResult = new CloudinaryResult(true, false, "delete Successfully");

        String expectedErrorMessage = "Database connection error";

        when(productDiscoveriesOfDayRepository.GetProductDiscoveriesOfDayToDelete(any())).thenReturn(productDiscoveriesOfDayDTO);
        when(cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(anyString())).thenReturn(cloudinaryResult);
        when(productDiscoveriesOfDayRepository.delete(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        var result = productDiscoveriesOfDayService.Delete(UUID.fromString(productDiscoveriesOfDayId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }
}
