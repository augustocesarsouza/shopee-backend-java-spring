package com.backend.shopee.shopee_backend.data.repositories;

import com.backend.shopee.shopee_backend.application.dto.CategoriesDTO;
import com.backend.shopee.shopee_backend.application.dto.ProductHighlightDTO;
import com.backend.shopee.shopee_backend.data.context.ProductHighlightRepositoryJPA;
import com.backend.shopee.shopee_backend.domain.entities.ProductHighlight;
import com.backend.shopee.shopee_backend.domain.repositories.IProductHighlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ProductHighlightRepository implements IProductHighlightRepository {
    private final ProductHighlightRepositoryJPA productHighlightRepositoryJPA;

    @Autowired
    public ProductHighlightRepository(ProductHighlightRepositoryJPA productHighlightRepositoryJPA) {
        this.productHighlightRepositoryJPA = productHighlightRepositoryJPA;
    }

    @Override
    public ProductHighlightDTO GetProductHighlightToDelete(UUID categoriesId) {
        return productHighlightRepositoryJPA.GetCategoriesToDelete(categoriesId);
    }

    @Override
    public ProductHighlightDTO GetProductHighlightById(UUID productHighlightId) {
        return productHighlightRepositoryJPA.GetProductHighlightById(productHighlightId);
    }

    @Override
    public List<ProductHighlightDTO> GetAllProductHighlight() {
        return productHighlightRepositoryJPA.GetAllProductHighlight();
    }

    @Override
    public ProductHighlight create(ProductHighlight productHighlight) {
        if(productHighlight == null)
            return null;

        return productHighlightRepositoryJPA.save(productHighlight);
    }

    @Override
    public ProductHighlight update(ProductHighlight productHighlight) {
        return null;
    }

    @Override
    public ProductHighlight delete(UUID productHighlightId) {
        if(productHighlightId == null)
            return null;

        var entity = productHighlightRepositoryJPA.findById(productHighlightId).orElse(null);

        if(entity == null)
            return null;

        productHighlightRepositoryJPA.delete(entity);

        return entity;
    }
}
