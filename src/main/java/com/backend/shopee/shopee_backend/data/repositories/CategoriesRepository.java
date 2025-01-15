package com.backend.shopee.shopee_backend.data.repositories;

import com.backend.shopee.shopee_backend.application.dto.CategoriesDTO;
import com.backend.shopee.shopee_backend.data.context.CategoriesRepositoryJPA;
import com.backend.shopee.shopee_backend.domain.entities.Categories;
import com.backend.shopee.shopee_backend.domain.repositories.ICategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CategoriesRepository implements ICategoriesRepository {
    private final CategoriesRepositoryJPA categoriesRepositoryJPA;

    @Autowired
    public CategoriesRepository(CategoriesRepositoryJPA categoriesRepositoryJPA) {
        this.categoriesRepositoryJPA = categoriesRepositoryJPA;
    }

    @Override
    public CategoriesDTO GetCategoriesToDelete(UUID categoriesId) {
        return categoriesRepositoryJPA.GetCategoriesToDelete(categoriesId);
    }

    @Override
    public CategoriesDTO GetCategoriesById(UUID categoriesId) {
        return categoriesRepositoryJPA.GetCategoriesById(categoriesId);
    }

    @Override
    public List<CategoriesDTO> GetAllCategories() {
        return categoriesRepositoryJPA.GetAllCategories();
    }

    @Override
    public Categories create(Categories categories) {
        if(categories == null)
            return null;

        return categoriesRepositoryJPA.save(categories);
    }

    @Override
    public Categories update(Categories categories) {
        return null;
    }

    @Override
    public Categories delete(UUID categoriesId) {
        if(categoriesId == null)
            return null;

        var entity = categoriesRepositoryJPA.findById(categoriesId).orElse(null);

        if(entity == null)
            return null;

        categoriesRepositoryJPA.delete(entity);

        return entity;
    }
}
