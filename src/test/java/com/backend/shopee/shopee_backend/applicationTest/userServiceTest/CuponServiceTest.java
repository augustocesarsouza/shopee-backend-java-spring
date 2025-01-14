package com.backend.shopee.shopee_backend.applicationTest.userServiceTest;

import com.backend.shopee.shopee_backend.application.dto.CuponDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.CuponValidationDTOs.CuponCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.CuponService;
import com.backend.shopee.shopee_backend.application.services.ErrorValidation;
import com.backend.shopee.shopee_backend.domain.entities.Cupon;
import com.backend.shopee.shopee_backend.domain.repositories.ICuponRepository;
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

public class CuponServiceTest {
    @Mock
    private ICuponRepository cuponRepository;
    @Mock
    private IValidateErrorsDTO validateErrorsDTO;
    @Mock
    private ModelMapper modelMapper;

    private CuponService cuponService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        cuponService = new CuponService(cuponRepository, validateErrorsDTO, modelMapper);
    }

    @Test
    public void should_GetCuponById_WithoutErrors(){
        String cuponId = "95ea1ca6-29ec-488b-90f7-50227e28dd37";
        var cuponDTO = new CuponDTO();

        when(cuponRepository.GetCuponById(any())).thenReturn(cuponDTO);

        // Act
        var result = cuponService.GetCuponById(UUID.fromString(cuponId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(cuponDTO, result.Data);
    }

    @Test
    public void should_Error_When_GetCuponById_Return_Null(){
        String cuponId = "95ea1ca6-29ec-488b-90f7-50227e28dd37";

        when(cuponRepository.GetCuponById(any())).thenReturn(null);

        // Act
        var result = cuponService.GetCuponById(UUID.fromString(cuponId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "not found promotion");
    }

    @Test
    public void should_ThrowException_When_GetCuponById(){
        String cuponId = "95ea1ca6-29ec-488b-90f7-50227e28dd37";
        String expectedErrorMessage = "Database connection error";

        when(cuponRepository.GetCuponById(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = cuponService.GetCuponById(UUID.fromString(cuponId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_create_Successfully_Cupon(){
        var cuponCreateDTOValidator = new CuponCreateDTOValidator("firstText1", "secondText1", "thirdText1", "05/10/1999",
                5, 2, "secondImg1");

        var resultError = new BeanPropertyBindingResult(cuponCreateDTOValidator, "cuponCreateDTOValidator");

        var cuponDTOCreateMap = new CuponDTO();

        when(cuponRepository.create(any())).thenReturn(new Cupon());
        when(modelMapper.map(any(Cupon.class), eq(CuponDTO.class))).thenReturn(cuponDTOCreateMap);

        // Act
        var result = cuponService.CreateAsync(cuponCreateDTOValidator, resultError);

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(result.Data, cuponDTOCreateMap);
    }

    @Test
    public void should_Return_Error_DTO_Is_Null_create_Cupon(){
        var resultError = new BeanPropertyBindingResult(new CuponCreateDTOValidator(), "CuponCreateDTOValidator");

        // Act
        var result = cuponService.CreateAsync(null, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error DTO Is Null");
    }

    @Test
    public void error_validate_DTO_When_Create_Cupon(){
        var cuponCreateDTOValidator = new CuponCreateDTOValidator(null, "secondText1", "thirdText1", "05/10/1999",
                5, 2, "secondImg1");

        var resultError = new BeanPropertyBindingResult(cuponCreateDTOValidator, "CuponCreateDTOValidator");

        resultError.rejectValue("FirstText", "NotEmpty", "FirstText should not be empty");

        List<ErrorValidation> errors = new ArrayList<>();
        var errorValidation = new ErrorValidation("FirstText", "FirstText should not be empty");
        errors.add(errorValidation);

        when(validateErrorsDTO.ValidateDTO(anyList())).thenReturn(errors);

        // Act
        var result = cuponService.CreateAsync(cuponCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "error validate DTO");
        assertEquals(result.Errors, errors);
    }

    @Test
    public void should_ThrowException_When_create_Cupon(){
        var cuponCreateDTOValidator = new CuponCreateDTOValidator("firstText1", "secondText1", "thirdText1", "05/10/1999",
                5, 2, "secondImg1");

        var resultError = new BeanPropertyBindingResult(cuponCreateDTOValidator, "cuponCreateDTOValidator");

        var cuponDTOCreateMap = new CuponDTO();
        String expectedErrorMessage = "Database connection error";

        when(cuponRepository.create(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = cuponService.CreateAsync(cuponCreateDTOValidator, resultError);

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, expectedErrorMessage);
    }

    @Test
    public void should_Delete_WithoutErrors(){
        String cuponId = "95ea1ca6-29ec-488b-90f7-50227e28dd37";
        var cuponDTO = new CuponDTO();
        cuponDTO.setId(UUID.fromString(cuponId));

        var cuponDTODelete = new CuponDTO();

        when(cuponRepository.GetCuponByIdToVerifyIfExist(any())).thenReturn(cuponDTO);
        when(cuponRepository.delete(any())).thenReturn(new Cupon());
        when(modelMapper.map(any(Cupon.class), eq(CuponDTO.class))).thenReturn(cuponDTODelete);

        // Act
        var result = cuponService.Delete(UUID.fromString(cuponId));

        // Assert
        assertTrue(result.IsSuccess);
        assertEquals(cuponDTODelete, result.Data);
    }

    @Test
    public void should_Return_Null_When_GetCuponByIdToVerifyIfExist_Delete(){
        String cuponId = "95ea1ca6-29ec-488b-90f7-50227e28dd37";

        when(cuponRepository.GetCuponByIdToVerifyIfExist(any())).thenReturn(null);

        // Act
        var result = cuponService.Delete(UUID.fromString(cuponId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(result.Message, "Cupon not found");
    }
    @Test
    public void should_ThrowException_When_Delete(){
        String cuponId = "95ea1ca6-29ec-488b-90f7-50227e28dd37";
        var cuponDTO = new CuponDTO();
        cuponDTO.setId(UUID.fromString(cuponId));

        String expectedErrorMessage = "Database connection error";

        when(cuponRepository.GetCuponByIdToVerifyIfExist(any())).thenReturn(cuponDTO);
        when(cuponRepository.delete(any())).thenThrow(new RuntimeException(expectedErrorMessage));

        // Act
        var result = cuponService.Delete(UUID.fromString(cuponId));

        // Assert
        assertFalse(result.IsSuccess);
        assertEquals(expectedErrorMessage, result.Message);
    }

}
