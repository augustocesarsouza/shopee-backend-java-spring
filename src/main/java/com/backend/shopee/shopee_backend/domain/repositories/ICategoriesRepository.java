package com.backend.shopee.shopee_backend.domain.repositories;

import com.backend.shopee.shopee_backend.application.dto.CategoriesDTO;
import com.backend.shopee.shopee_backend.domain.entities.Categories;
import com.backend.shopee.shopee_backend.domain.entities.Cupon;

import java.util.List;
import java.util.UUID;

public interface ICategoriesRepository {
    CategoriesDTO GetCategoriesToDelete(UUID categoriesId);
    CategoriesDTO GetCategoriesById(UUID categoriesId);
    List<CategoriesDTO> GetAllCategories();
    Categories create(Categories categories);
    Categories update(Categories categories);
    Categories delete(UUID categoriesId);
}
