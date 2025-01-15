package com.backend.shopee.shopee_backend.application.services.interfaces;

import com.backend.shopee.shopee_backend.application.dto.CategoriesDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.CategoriesValidationDTOs.CategoriesCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

public interface ICategoriesService {
    ResultService<CategoriesDTO> GetCategoriesById(UUID categoriesId);
    ResultService<List<CategoriesDTO>> GetAllCategories();
    ResultService<CategoriesDTO> CreateAsync(CategoriesCreateDTOValidator categoriesCreateDTOValidator, BindingResult result);
    ResultService<CategoriesDTO> Delete(UUID categoriesId);
}
