package com.backend.shopee.shopee_backend.data.context;

import com.backend.shopee.shopee_backend.application.dto.CategoriesDTO;
import com.backend.shopee.shopee_backend.application.dto.ProductHighlightDTO;
import com.backend.shopee.shopee_backend.domain.entities.ProductHighlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductHighlightRepositoryJPA extends JpaRepository<ProductHighlight, UUID> {
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "ProductHighlightDTO(x.Id, null, x.ImgProduct, null, null) " +
            "FROM ProductHighlight AS x " +
            "WHERE x.Id = :productHighlightId")
    ProductHighlightDTO GetCategoriesToDelete(UUID productHighlightId);
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "ProductHighlightDTO(x.Id, x.Title, x.ImgProduct, x.ImgTop, x.QuantitySold) " +
            "FROM ProductHighlight AS x " +
            "WHERE x.Id = :productHighlightId")
    ProductHighlightDTO GetProductHighlightById(UUID productHighlightId);

    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "ProductHighlightDTO(x.Id, x.Title, x.ImgProduct, x.ImgTop, x.QuantitySold) " +
            "FROM ProductHighlight AS x")
    List<ProductHighlightDTO> GetAllProductHighlight();
}
