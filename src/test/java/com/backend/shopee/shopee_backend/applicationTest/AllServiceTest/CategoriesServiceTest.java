package com.backend.shopee.shopee_backend.applicationTest.AllServiceTest;

import com.backend.shopee.shopee_backend.application.dto.CategoriesDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.CategoriesValidationDTOs.CategoriesCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.CategoriesService;
import com.backend.shopee.shopee_backend.application.services.ErrorValidation;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryCreate;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryResult;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.shopee.shopee_backend.domain.entities.Categories;
import com.backend.shopee.shopee_backend.domain.repositories.ICategoriesRepository;
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

public class CategoriesServiceTest {
    @Mock
    private ICategoriesRepository categoriesRepository;
    @Mock
    private IValidateErrorsDTO validateErrorsDTO;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private ICloudinaryUti cloudinaryUti;

    private CategoriesService categoriesService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        categoriesService = new CategoriesService(categoriesRepository, validateErrorsDTO, modelMapper,
                cloudinaryUti);
    }

    @Test
    public void should_GetCategoriesById_WithoutErrors(){
        var categoriesId = "11195abf-d14f-45ec-8802-ad64c66a92f7";

        var categoryDTO = new CategoriesDTO();

        when(categoriesRepository.GetCategoriesById(any())).thenReturn(categoryDTO);

        // Act
        var result = categoriesService.GetCategoriesById(UUID.fromString(categoriesId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, categoryDTO);
    }

    @Test
    public void should_GetCategoriesById_Return_Null(){
        var categoriesId = "11195abf-d14f-45ec-8802-ad64c66a92f7";

        when(categoriesRepository.GetCategoriesById(any())).thenReturn(null);

        // Act
        var result = categoriesService.GetCategoriesById(UUID.fromString(categoriesId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "not found categories");
    }

    @Test
    public void should_ThrowException_When_GetCategoriesById(){
        var categoriesId = "11195abf-d14f-45ec-8802-ad64c66a92f7";

        String expectedErrorMessage = "Database connection error";

        when(categoriesRepository.GetCategoriesById(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = categoriesService.GetCategoriesById(UUID.fromString(categoriesId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_GetAllCategories_WithoutErrors(){
        var categoryDTOs = List.of(new CategoriesDTO());

        when(categoriesRepository.GetAllCategories()).thenReturn(categoryDTOs);

        // Act
        var result = categoriesService.GetAllCategories();

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, categoryDTOs);
    }

    @Test
    public void should_GetAllCategoriesReturn_Null(){
        when(categoriesRepository.GetAllCategories()).thenReturn(null);

        // Act
        var result = categoriesService.GetAllCategories();

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "not found promotion");
    }

    @Test
    public void should_GetAllCategories_When_GetCategoriesById(){
        String expectedErrorMessage = "Database connection error";

        when(categoriesRepository.GetAllCategories()).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = categoriesService.GetAllCategories();

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_create_Successfully_Categories(){
        var categoriesCreateDTOValidator = new CategoriesCreateDTOValidator("imgCategory1", "altValue1",
                "title1");

        var resultError = new BeanPropertyBindingResult(categoriesCreateDTOValidator, "CategoriesCreateDTOValidator");

        var categoriesDTOMap = new CategoriesDTO();
        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate("publicId", "img_url", true);

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);
        when(categoriesRepository.create(any())).thenReturn(new Categories());
        when(modelMapper.map(any(Categories.class), eq(CategoriesDTO.class))).thenReturn(categoriesDTOMap);

        var result = categoriesService.CreateAsync(categoriesCreateDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, categoriesDTOMap);
    }

    @Test
    public void should_Return_Error_DTO_Is_Null_create_Categories(){
        var resultError = new BeanPropertyBindingResult(new CategoriesCreateDTOValidator(), "CategoriesCreateDTOValidator");

        // Act
        var result = categoriesService.CreateAsync(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error DTO Is Null");
    }

    @Test
    public void error_validate_DTO_When_Create_Categories(){
        var categoriesCreateDTOValidator = new CategoriesCreateDTOValidator("imgCategory1", "altValue1",
                "title1");

        var resultError = new BeanPropertyBindingResult(categoriesCreateDTOValidator, "CategoriesCreateDTOValidator");

        resultError.rejectValue("ImgCategory", "NotEmpty", "ImgCategory should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("ImgCategory", "ImgCategory should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = categoriesService.CreateAsync(categoriesCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void should_Error_When_Create_Img_Cloudinary_create_Categories(){
        var categoriesCreateDTOValidator = new CategoriesCreateDTOValidator("imgCategory1", "altValue1",
                "title1");

        var resultError = new BeanPropertyBindingResult(categoriesCreateDTOValidator, "CategoriesCreateDTOValidator");

        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate(null, null, false);

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);

        var result = categoriesService.CreateAsync(categoriesCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error when create Img category");
    }

    @Test
    public void should_ThrowException_create_Categories(){
        var categoriesCreateDTOValidator = new CategoriesCreateDTOValidator("imgCategory1", "altValue1",
                "title1");

        var resultError = new BeanPropertyBindingResult(categoriesCreateDTOValidator, "CategoriesCreateDTOValidator");

        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate("publicId", "img_url", true);

        String expectedErrorMessage = "Database connection error";

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);
        when(categoriesRepository.create(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        var result = categoriesService.CreateAsync(categoriesCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_Delete_Successfully_Categories(){
        var categoriesId = "e2eb5507-6460-4dbb-ac32-d0074e0a574e";

        var categoriesDTO = new CategoriesDTO();
        categoriesDTO.setId(UUID.fromString("f2753c7a-f4a5-4baf-b256-4f02168afd41"));
        categoriesDTO.setImgCategory("ImgCategory1");

        var categoriesDTOMap = new CategoriesDTO();
        var cloudinaryResult = new CloudinaryResult(true, false, "delete Successfully");

        when(categoriesRepository.GetCategoriesToDelete(any())).thenReturn(categoriesDTO);
        when(cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(anyString())).thenReturn(cloudinaryResult);
        when(categoriesRepository.delete(any())).thenReturn(new Categories());
        when(modelMapper.map(any(Categories.class), eq(CategoriesDTO.class))).thenReturn(categoriesDTOMap);

        var result = categoriesService.Delete(UUID.fromString(categoriesId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, categoriesDTOMap);
    }

    @Test
    public void should_Error_GetCategoriesToDelete_Return_Null_Delete_Categories(){
        var categoriesId = "e2eb5507-6460-4dbb-ac32-d0074e0a574e";

        when(categoriesRepository.GetCategoriesToDelete(any())).thenReturn(null);

        var result = categoriesService.Delete(UUID.fromString(categoriesId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "Category not found");
    }

    @Test
    public void should_Throw_Error_When_Delete_Img_Cloudinary_Delete_Categories(){
        var categoriesId = "e2eb5507-6460-4dbb-ac32-d0074e0a574e";

        var categoriesDTO = new CategoriesDTO();
        categoriesDTO.setId(UUID.fromString("f2753c7a-f4a5-4baf-b256-4f02168afd41"));
        categoriesDTO.setImgCategory("ImgCategory1");

        var cloudinaryResult = new CloudinaryResult(false, false, "error when delete img cloudinary");

        when(categoriesRepository.GetCategoriesToDelete(any())).thenReturn(categoriesDTO);
        when(cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(anyString())).thenReturn(cloudinaryResult);

        var result = categoriesService.Delete(UUID.fromString(categoriesId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error when delete img cloudinary");
    }

    @Test
    public void should_ThrowException_Delete_Categories(){
        var categoriesId = "e2eb5507-6460-4dbb-ac32-d0074e0a574e";

        var categoriesDTO = new CategoriesDTO();
        categoriesDTO.setId(UUID.fromString("f2753c7a-f4a5-4baf-b256-4f02168afd41"));
        categoriesDTO.setImgCategory("ImgCategory1");

        var categoriesDTOMap = new CategoriesDTO();
        var cloudinaryResult = new CloudinaryResult(true, false, "delete Successfully");

        String expectedErrorMessage = "Database connection error";

        when(categoriesRepository.GetCategoriesToDelete(any())).thenReturn(categoriesDTO);
        when(cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(anyString())).thenReturn(cloudinaryResult);
        when(categoriesRepository.delete(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        var result = categoriesService.Delete(UUID.fromString(categoriesId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }
}
