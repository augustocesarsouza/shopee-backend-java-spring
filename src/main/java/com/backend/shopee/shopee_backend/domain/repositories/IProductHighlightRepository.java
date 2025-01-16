package com.backend.shopee.shopee_backend.domain.repositories;

import com.backend.shopee.shopee_backend.application.dto.ProductHighlightDTO;
import com.backend.shopee.shopee_backend.domain.entities.ProductHighlight;

import java.util.List;
import java.util.UUID;

public interface IProductHighlightRepository {
    ProductHighlightDTO GetProductHighlightById(UUID productHighlightId);
    List<ProductHighlightDTO> GetAllProductHighlight();
    ProductHighlight create(ProductHighlight productHighlight);
    ProductHighlight update(ProductHighlight productHighlight);
    ProductHighlight delete(UUID productHighlightId);
}
