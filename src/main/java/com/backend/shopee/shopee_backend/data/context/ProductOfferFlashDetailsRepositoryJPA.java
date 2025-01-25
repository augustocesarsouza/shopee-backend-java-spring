package com.backend.shopee.shopee_backend.data.context;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashDetailsDTO;
import com.backend.shopee.shopee_backend.domain.entities.ProductOfferFlashDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductOfferFlashDetailsRepositoryJPA extends JpaRepository<ProductOfferFlashDetails, UUID> {
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "ProductOfferFlashDetailsDTO(x.Id, x.ProductsOfferFlashId, x.Details) " +
            "FROM ProductOfferFlashDetails AS x " +
            "WHERE x.ProductsOfferFlashId = :productsOfferFlashId")
    ProductOfferFlashDetailsDTO GetByProductsOfferFlashId(UUID productsOfferFlashId);

    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "ProductOfferFlashDetailsDTO(x.Id, null, null) " +
            "FROM ProductOfferFlashDetails AS x " +
            "WHERE x.Id = :productOfferFlashDetailsId")
    ProductOfferFlashDetailsDTO GetByProductOfferFlashDetailsId(UUID productOfferFlashDetailsId);
}
//ProductOfferFlashDetailsDTO(UUID id, UUID productsOfferFlashId, String details)