package com.backend.shopee.shopee_backend.applicationTest.userServiceTest;

import com.backend.shopee.shopee_backend.application.dto.ShopeeUpdateDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ShopeeUpdateValidationDTOs.ShopeeUpdateCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ErrorValidation;
import com.backend.shopee.shopee_backend.application.services.ShopeeUpdateService;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryCreate;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryResult;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.shopee.shopee_backend.domain.entities.ShopeeUpdate;
import com.backend.shopee.shopee_backend.domain.repositories.IShopeeUpdateRepository;
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

public class ShopeeUpdateServiceTest {
    @Mock
    private IShopeeUpdateRepository shopeeUpdateRepository;
    @Mock
    private IValidateErrorsDTO validateErrorsDTO;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private ICloudinaryUti cloudinaryUti;

    private ShopeeUpdateService shopeeUpdateService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        shopeeUpdateService = new ShopeeUpdateService(shopeeUpdateRepository, validateErrorsDTO, modelMapper, cloudinaryUti);
    }

    @Test
    public void should_GetById_WithoutErrors(){
        String shopeeUpdateId = "cc27392b-944a-4e22-b399-231387bdd984";
        var shopeeUpdateDTO = new ShopeeUpdateDTO();

        when(shopeeUpdateRepository.GetById(any())).thenReturn(shopeeUpdateDTO);

        // Act
        var result = shopeeUpdateService.GetById(UUID.fromString(shopeeUpdateId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, shopeeUpdateDTO);
    }

    @Test
    public void should_GetById_Return_Null(){
        String shopeeUpdateId = "cc27392b-944a-4e22-b399-231387bdd984";

        when(shopeeUpdateRepository.GetById(any())).thenReturn(null);

        // Act
        var result = shopeeUpdateService.GetById(UUID.fromString(shopeeUpdateId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "not found shopeeUpdate");
    }

    @Test
    public void should_ThrowException_When_GetById(){
        String promotionId = "cc27392b-944a-4e22-b399-231387bdd984";

        String expectedErrorMessage = "Database connection error";

        when(shopeeUpdateRepository.GetById(any()))
                .thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = shopeeUpdateService.GetById(UUID.fromString(promotionId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_create_Successfully_ShopeeUpdateService(){
        var shopeeUpdateCreateDTOValidator = new ShopeeUpdateCreateDTOValidator("title1",
                "description1", "05/10/1999 10:00", "img1");

        var resultError = new BeanPropertyBindingResult(shopeeUpdateCreateDTOValidator, "ShopeeUpdateCreateDTOValidator");

        var shopeeUpdateDTOMap = new ShopeeUpdateDTO();
        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate("publicId", "img_url", true);

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);
        when(shopeeUpdateRepository.create(any())).thenReturn(new ShopeeUpdate());
        when(modelMapper.map(any(ShopeeUpdate.class), eq(ShopeeUpdateDTO.class))).thenReturn(shopeeUpdateDTOMap);

        // Act
        var result = shopeeUpdateService.CreateAsync(shopeeUpdateCreateDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, shopeeUpdateDTOMap);
    }

    @Test
    public void should_Return_Error_DTO_Is_Null_create_ShopeeUpdateService(){
        var resultError = new BeanPropertyBindingResult(new ShopeeUpdateCreateDTOValidator(), "ShopeeUpdateCreateDTOValidator");

        // Act
        var result = shopeeUpdateService.CreateAsync(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error DTO Is Null");
    }

    @Test
    public void error_validate_DTO_When_Create_ShopeeUpdateService(){
        var shopeeUpdateCreateDTOValidator = new ShopeeUpdateCreateDTOValidator("title1",
                "description1", "05/10/1999 10:00", "img1");

        var resultError = new BeanPropertyBindingResult(shopeeUpdateCreateDTOValidator, "ShopeeUpdateCreateDTOValidator");

        resultError.rejectValue("Title", "NotEmpty", "Title should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("Title", "Title should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = shopeeUpdateService.CreateAsync(shopeeUpdateCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void should_Error_When_Create_Cloudinary_create_ShopeeUpdateService(){
        var shopeeUpdateCreateDTOValidator = new ShopeeUpdateCreateDTOValidator("title1",
                "description1", "05/10/1999 10:00", "img1");

        var resultError = new BeanPropertyBindingResult(shopeeUpdateCreateDTOValidator, "ShopeeUpdateCreateDTOValidator");

        var shopeeUpdateDTOMap = new ShopeeUpdateDTO();
        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate(null, null, false);

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);

        // Act
        var result = shopeeUpdateService.CreateAsync(shopeeUpdateCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error when create Img Promotion");
    }

    @Test
    public void should_ThrowException_create_ShopeeUpdateService(){
        var shopeeUpdateCreateDTOValidator = new ShopeeUpdateCreateDTOValidator("title1",
                "description1", "05/10/1999 10:00", "img1");

        var resultError = new BeanPropertyBindingResult(shopeeUpdateCreateDTOValidator, "ShopeeUpdateCreateDTOValidator");

        var shopeeUpdateDTOMap = new ShopeeUpdateDTO();
        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate("publicId", "img_url", true);

        String expectedErrorMessage = "Database connection error";

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);
        when(shopeeUpdateRepository.create(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = shopeeUpdateService.CreateAsync(shopeeUpdateCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_Delete_Successfully_ShopeeUpdateService(){
        var shopeeUpdateId = "349d6358-563b-4f89-bee4-f47ace5db0e8";

        var shopeeUpdateDelete = new ShopeeUpdateDTO();
        shopeeUpdateDelete.setImg("img1");

        var cloudinaryResult = new CloudinaryResult(true, false, "delete Successfully");

        var shopeeUpdateDTOToDelete = new ShopeeUpdateDTO();

        when(shopeeUpdateRepository.GetShopeeUpdateToDelete(any())).thenReturn(shopeeUpdateDelete);
        when(cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(anyString())).thenReturn(cloudinaryResult);
        when(shopeeUpdateRepository.delete(any())).thenReturn(new ShopeeUpdate());
        when(modelMapper.map(any(ShopeeUpdate.class), eq(ShopeeUpdateDTO.class))).thenReturn(shopeeUpdateDTOToDelete);

        var result = shopeeUpdateService.Delete(UUID.fromString(shopeeUpdateId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, shopeeUpdateDTOToDelete);
    }

    @Test
    public void should_Return_Null_When_GetShopeeUpdateToDelete_Delete_ShopeeUpdateService(){
        var shopeeUpdateId = "349d6358-563b-4f89-bee4-f47ace5db0e8";

        when(shopeeUpdateRepository.GetShopeeUpdateToDelete(any())).thenReturn(null);

        var result = shopeeUpdateService.Delete(UUID.fromString(shopeeUpdateId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "User not found");
    }

    @Test
    public void should_Error_When_Delete_Cloudinary_Delete_ShopeeUpdateService(){
        var shopeeUpdateId = "349d6358-563b-4f89-bee4-f47ace5db0e8";

        var shopeeUpdateDelete = new ShopeeUpdateDTO();
        shopeeUpdateDelete.setImg("img1");

        var cloudinaryResult = new CloudinaryResult(false, false, "error when delete img cloudinary");

        when(shopeeUpdateRepository.GetShopeeUpdateToDelete(any())).thenReturn(shopeeUpdateDelete);
        when(cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(anyString())).thenReturn(cloudinaryResult);

        var result = shopeeUpdateService.Delete(UUID.fromString(shopeeUpdateId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error when delete img cloudinary");
    }

    @Test
    public void should_ThrowException_Delete_ShopeeUpdateService(){
        var shopeeUpdateId = "349d6358-563b-4f89-bee4-f47ace5db0e8";

        var shopeeUpdateDelete = new ShopeeUpdateDTO();
        shopeeUpdateDelete.setImg("img1");

        var cloudinaryResult = new CloudinaryResult(true, false, "delete Successfully");

        String expectedErrorMessage = "Database connection error";

        when(shopeeUpdateRepository.GetShopeeUpdateToDelete(any())).thenReturn(shopeeUpdateDelete);
        when(cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(anyString())).thenReturn(cloudinaryResult);
        when(shopeeUpdateRepository.delete(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        var result = shopeeUpdateService.Delete(UUID.fromString(shopeeUpdateId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }
}