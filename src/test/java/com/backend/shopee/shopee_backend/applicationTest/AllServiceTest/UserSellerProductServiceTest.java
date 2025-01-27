package com.backend.shopee.shopee_backend.applicationTest.AllServiceTest;

import com.backend.shopee.shopee_backend.application.dto.UserSellerProductDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.UserSellerProductValidationDTOs.UserSellerProductCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ErrorValidation;
import com.backend.shopee.shopee_backend.application.services.UserSellerProductService;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryCreate;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.shopee.shopee_backend.domain.entities.UserSellerProduct;
import com.backend.shopee.shopee_backend.domain.repositories.IUserSellerProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BeanPropertyBindingResult;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class UserSellerProductServiceTest {
    @Mock
    private IUserSellerProductRepository userSellerProductRepository;
    @Mock
    private IValidateErrorsDTO validateErrorsDTO;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private ICloudinaryUti cloudinaryUti;

    private UserSellerProductService userSellerProductService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        userSellerProductService = new UserSellerProductService(userSellerProductRepository, validateErrorsDTO, modelMapper, cloudinaryUti);
    }

    @Test
    public void should_GetByUserSellerProductId_WithoutErrors(){
        String entityId = "cc27392b-944a-4e22-b399-231387bdd984";
        var entityDTO = new UserSellerProductDTO();

        String lastLoginString = "2025-01-23T08:20:00.901784-04:00";
        String accountCreationDateString = "2025-01-23T08:20:00.901784-04:00";

        // Converter as Strings para ZonedDateTime
        var lastLogin = ZonedDateTime.parse(lastLoginString);
        var accountCreationDate = ZonedDateTime.parse(accountCreationDateString);

        entityDTO.setLastLogin(lastLogin);
        entityDTO.setAccountCreationDate(accountCreationDate);

        when(userSellerProductRepository.GetByUserSellerProductId(any())).thenReturn(entityDTO);

        // Act
        var result = userSellerProductService.GetByUserSellerProductId(UUID.fromString(entityId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, entityDTO);
    }

    @Test
    public void should_GetByUserSellerProductId_Return_Null(){
        String entityId = "cc27392b-944a-4e22-b399-231387bdd984";

        when(userSellerProductRepository.GetByUserSellerProductId(any())).thenReturn(null);

        // Act
        var result = userSellerProductService.GetByUserSellerProductId(UUID.fromString(entityId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "not found userSellerProduct");
    }

    @Test
    public void should_ThrowException_When_GetByUserSellerProductId(){
        String entityId = "cc27392b-944a-4e22-b399-231387bdd984";

        String expectedErrorMessage = "Database connection error";

        when(userSellerProductRepository.GetByUserSellerProductId(any()))
                .thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = userSellerProductService.GetByUserSellerProductId(UUID.fromString(entityId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_create_Successfully_UserSellerProduct(){
        var userSellerProductCreateDTOValidator = new UserSellerProductCreateDTOValidator("name1", "imgProfile1",
                "imgFloating1", null, 1000, 50, null,
                90000, "dentro de minutos", 30000);

        var resultError = new BeanPropertyBindingResult(userSellerProductCreateDTOValidator, "UserSellerProductCreateDTOValidator");

        var entityDTOMap = new UserSellerProductDTO();
        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate("publicId", "img_url", true);

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);
        when(userSellerProductRepository.create(any())).thenReturn(new UserSellerProduct());
        when(modelMapper.map(any(UserSellerProduct.class), eq(UserSellerProductDTO.class))).thenReturn(entityDTOMap);

        // Act
        var result = userSellerProductService.CreateAsync(userSellerProductCreateDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, entityDTOMap);
    }

    @Test
    public void should_Return_Error_DTO_Is_Null_create_UserSellerProduct(){
        var resultError = new BeanPropertyBindingResult(new UserSellerProductCreateDTOValidator(), "UserSellerProductCreateDTOValidator");

        // Act
        var result = userSellerProductService.CreateAsync(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error DTO Is Null");
    }

    @Test
    public void error_validate_DTO_When_Create_UserSellerProduct(){
        var userSellerProductCreateDTOValidator = new UserSellerProductCreateDTOValidator("name1", "imgProfile1",
                "imgFloating1", null, 1000, 50, null,
                90000, "dentro de minutos", 30000);

        var resultError = new BeanPropertyBindingResult(userSellerProductCreateDTOValidator, "UserSellerProductCreateDTOValidator");

        resultError.rejectValue("ImgFloating", "NotEmpty", "ImgFloating should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("ImgFloating", "ImgFloating should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = userSellerProductService.CreateAsync(userSellerProductCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void should_Return_Error_When_Create_Cloudinary_create_UserSellerProduct(){
        var userSellerProductCreateDTOValidator = new UserSellerProductCreateDTOValidator("name1", "imgProfile1",
                "imgFloating1", null, 1000, 50, null,
                90000, "dentro de minutos", 30000);

        var resultError = new BeanPropertyBindingResult(userSellerProductCreateDTOValidator, "UserSellerProductCreateDTOValidator");

        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate(null, null, false);

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);

        // Act
        var result = userSellerProductService.CreateAsync(userSellerProductCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error when create Img UserSellerProduct");
    }

    @Test
    public void should_ThrowException_create_Promotion(){
        var userSellerProductCreateDTOValidator = new UserSellerProductCreateDTOValidator("name1", "imgProfile1",
                "imgFloating1", null, 1000, 50, null,
                90000, "dentro de minutos", 30000);

        var resultError = new BeanPropertyBindingResult(userSellerProductCreateDTOValidator, "UserSellerProductCreateDTOValidator");

        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate("publicId", "img_url", true);

        String expectedErrorMessage = "Database connection error";

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);
        when(userSellerProductRepository.create(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = userSellerProductService.CreateAsync(userSellerProductCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }
}
