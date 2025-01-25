package com.backend.shopee.shopee_backend.data.context;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashDescriptionDTO;
import com.backend.shopee.shopee_backend.domain.entities.ProductOfferFlashDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductOfferFlashDescriptionRepositoryJPA extends JpaRepository<ProductOfferFlashDescription, UUID> {
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "ProductOfferFlashDescriptionDTO(x.Id, x.ProductsOfferFlashId, x.Descriptions) " +
            "FROM ProductOfferFlashDescription AS x " +
            "WHERE x.ProductsOfferFlashId = :productsOfferFlashId")
    ProductOfferFlashDescriptionDTO GetByProductsOfferFlashId(UUID productsOfferFlashId);

    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "ProductOfferFlashDescriptionDTO(x.Id, null, null) " +
            "FROM ProductOfferFlashDescription AS x " +
            "WHERE x.Id = :productOfferFlashDescriptionId")
    ProductOfferFlashDescriptionDTO GetByProductsOfferFlashIdIfExist(UUID productOfferFlashDescriptionId);
}
//ProductOfferFlashDescriptionDTO(UUID id, UUID productsOfferFlashId, String descriptions)