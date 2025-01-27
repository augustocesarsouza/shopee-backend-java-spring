package com.backend.shopee.shopee_backend.applicationTest.AllServiceTest;
import com.backend.shopee.shopee_backend.application.dto.PromotionDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.PromotionValidationDTOs.PromotionCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ErrorValidation;
import com.backend.shopee.shopee_backend.application.services.PromotionService;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryCreate;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryResult;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.shopee.shopee_backend.domain.entities.Promotion;
import com.backend.shopee.shopee_backend.domain.repositories.IPromotionRepository;
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
import static org.mockito.Mockito.when;

public class PromotionServiceTest {
    @Mock
    private IPromotionRepository promotionRepository;
    @Mock
    private IValidateErrorsDTO validateErrorsDTO;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private ICloudinaryUti cloudinaryUti;
    private PromotionService promotionService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        promotionService = new PromotionService(promotionRepository, validateErrorsDTO, modelMapper, cloudinaryUti);
    }

    @Test
    public void should_GetById_WithoutErrors(){
        String promotionId = "cc27392b-944a-4e22-b399-231387bdd984";
        var promotionDTO = new PromotionDTO();

        when(promotionRepository.GetById(any())).thenReturn(promotionDTO);

        // Act
        var result = promotionService.GetById(UUID.fromString(promotionId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, promotionDTO);
    }

    @Test
    public void should_GetById_Return_Null(){
        String promotionId = "cc27392b-944a-4e22-b399-231387bdd984";

        when(promotionRepository.GetById(any())).thenReturn(null);

        // Act
        var result = promotionService.GetById(UUID.fromString(promotionId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "not found promotion");
    }

    @Test
    public void should_ThrowException_When_GetById(){
        String promotionId = "cc27392b-944a-4e22-b399-231387bdd984";
        var promotionDTO = new PromotionDTO();

        String expectedErrorMessage = "Database connection error";

        when(promotionRepository.GetById(any()))
                .thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = promotionService.GetById(UUID.fromString(promotionId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_create_Successfully_Promotion(){
        var promotionCreateDTOValidator = new PromotionCreateDTOValidator(1, "Os favoritos do momento estão aqui!",
                "description1", "img1", null);
        promotionCreateDTOValidator.setDateCreate("05/10/1999 10:00");
        promotionCreateDTOValidator.setListImgInner(new ArrayList<>());

        var resultError = new BeanPropertyBindingResult(promotionCreateDTOValidator, "PromotionCreateDTOValidator");

        var promotionDTOMap = new PromotionDTO();
        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate("publicId", "img_url", true);

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);
        when(promotionRepository.create(any())).thenReturn(new Promotion());
        when(modelMapper.map(any(Promotion.class), eq(PromotionDTO.class))).thenReturn(promotionDTOMap);

        // Act
        var result = promotionService.CreateAsync(promotionCreateDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, promotionDTOMap);
    }

    @Test
    public void should_Return_Error_DTO_Is_Null_create_Promotion(){
        var resultError = new BeanPropertyBindingResult(new PromotionCreateDTOValidator(), "PromotionCreateDTOValidator");

        // Act
        var result = promotionService.CreateAsync(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error DTO Is Null");
    }

    @Test
    public void error_validate_DTO_When_Create_ProductsOfferFlash(){
        var promotionCreateDTOValidator = new PromotionCreateDTOValidator(1, null,
                "description1", "img1", null);

        var resultError = new BeanPropertyBindingResult(promotionCreateDTOValidator, "PromotionCreateDTOValidator");

        resultError.rejectValue("Title", "NotEmpty", "Title should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("Title", "Title should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = promotionService.CreateAsync(promotionCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void should_Return_Error_When_Create_Cloudinary_create_Promotion(){
        var promotionCreateDTOValidator = new PromotionCreateDTOValidator(1, "Os favoritos do momento estão aqui!",
                "description1", "img1", null);
        promotionCreateDTOValidator.setDateCreate("05/10/1999 10:00");
        promotionCreateDTOValidator.setListImgInner(new ArrayList<>());

        var resultError = new BeanPropertyBindingResult(promotionCreateDTOValidator, "PromotionCreateDTOValidator");

        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate(null, null, false);

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);

        // Act
        var result = promotionService.CreateAsync(promotionCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error when create Img Promotion");
    }

    @Test
    public void should_ThrowException_create_Promotion(){
        var promotionCreateDTOValidator = new PromotionCreateDTOValidator(1, "Os favoritos do momento estão aqui!",
                "description1", "img1", null);
        promotionCreateDTOValidator.setDateCreate("05/10/1999 10:00");
        promotionCreateDTOValidator.setListImgInner(new ArrayList<>());

        var resultError = new BeanPropertyBindingResult(promotionCreateDTOValidator, "PromotionCreateDTOValidator");

        CloudinaryCreate cloudinaryCreate = new CloudinaryCreate("publicId", "img_url", true);

        String expectedErrorMessage = "Database connection error";

        when(cloudinaryUti.CreateMedia(anyString(), anyString(), anyInt(), anyInt())).thenReturn(cloudinaryCreate);
        when(promotionRepository.create(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = promotionService.CreateAsync(promotionCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_Delete_Successfully_Promotion(){
        var promotionId = "4fc06468-52c9-46b9-898a-6cb24c9d3598";

        PromotionDTO promotionDelete = new PromotionDTO();
        promotionDelete.setImg("img1");
        promotionDelete.setWhatIsThePromotion(1);
        promotionDelete.setListImgInner(new ArrayList<>());

        PromotionDTO promotionDelDTO = new PromotionDTO();

        var cloudinaryResult = new CloudinaryResult(true, false, "delete Successfully");

        when(promotionRepository.GetById(any())).thenReturn(promotionDelete);
        when(cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(anyString())).thenReturn(cloudinaryResult);
        when(promotionRepository.delete(any())).thenReturn(new Promotion());
        when(modelMapper.map(any(Promotion.class), eq(PromotionDTO.class))).thenReturn(promotionDelDTO);

        var result = promotionService.Delete(UUID.fromString(promotionId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, promotionDelDTO);
    }

    @Test
    public void should_Return_Null_When_GetById_Delete_Promotion(){
        var promotionId = "4fc06468-52c9-46b9-898a-6cb24c9d3598";

        PromotionDTO promotionDelDTO = new PromotionDTO();

        when(promotionRepository.GetById(any())).thenReturn(null);

        var result = promotionService.Delete(UUID.fromString(promotionId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "User not found");
    }

    @Test
    public void should_Error_When_Delete_Img_Cloudinary_Delete_Promotion(){
        var promotionId = "4fc06468-52c9-46b9-898a-6cb24c9d3598";

        PromotionDTO promotionDelete = new PromotionDTO();
        promotionDelete.setImg("img1");
        promotionDelete.setWhatIsThePromotion(1);
        promotionDelete.setListImgInner(new ArrayList<>());

        PromotionDTO promotionDelDTO = new PromotionDTO();

        var cloudinaryResult = new CloudinaryResult(false, false, "error when delete img");

        when(promotionRepository.GetById(any())).thenReturn(promotionDelete);
        when(cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(anyString())).thenReturn(cloudinaryResult);

        var result = promotionService.Delete(UUID.fromString(promotionId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error when delete img");
    }

    @Test
    public void should_ThrowException_When_Delete_Promotion(){
        var promotionId = "4fc06468-52c9-46b9-898a-6cb24c9d3598";

        PromotionDTO promotionDelete = new PromotionDTO();
        promotionDelete.setImg("img1");
        promotionDelete.setWhatIsThePromotion(1);
        promotionDelete.setListImgInner(new ArrayList<>());

        var cloudinaryResult = new CloudinaryResult(true, false, "delete Successfully");

        String expectedErrorMessage = "Database connection error";

        when(promotionRepository.GetById(any())).thenReturn(promotionDelete);
        when(cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(anyString())).thenReturn(cloudinaryResult);
        when(promotionRepository.delete(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        var result = promotionService.Delete(UUID.fromString(promotionId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }
}