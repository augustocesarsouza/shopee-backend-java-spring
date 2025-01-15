package com.backend.shopee.shopee_backend.data.context;

import com.backend.shopee.shopee_backend.application.dto.CategoriesDTO;
import com.backend.shopee.shopee_backend.domain.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoriesRepositoryJPA extends JpaRepository<Categories, UUID> {
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "CategoriesDTO(x.Id, x.ImgCategory, null, null) " +
            "FROM Categories AS x " +
            "WHERE x.Id = :categoryId")
    CategoriesDTO GetCategoriesToDelete(UUID categoryId);

    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "CategoriesDTO(x.Id, x.ImgCategory, x.AltValue, x.Title) " +
            "FROM Categories AS x " +
            "WHERE x.Id = :categoryId")
    CategoriesDTO GetCategoriesById(UUID categoryId);

    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "CategoriesDTO(x.Id, x.ImgCategory, x.AltValue, x.Title) " +
            "FROM Categories AS x")
    List<CategoriesDTO> GetAllCategories();
}
//CategoriesDTO(UUID id, String imgCategory, String altValue, String title)