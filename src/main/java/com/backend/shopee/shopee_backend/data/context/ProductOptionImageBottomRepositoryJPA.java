package com.backend.shopee.shopee_backend.data.context;

import com.backend.shopee.shopee_backend.application.dto.ProductOptionImageBottomDTO;
import com.backend.shopee.shopee_backend.domain.entities.ProductOptionImageBottom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductOptionImageBottomRepositoryJPA extends JpaRepository<ProductOptionImageBottom, UUID> {
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "ProductOptionImageBottomDTO(x.Id, null, x.ListImageUrlBottom) " +
            "FROM ProductOptionImageBottom AS x " +
            "WHERE x.ProductsOfferFlashId = :productsOfferFlashId")
    ProductOptionImageBottomDTO GetByListFlashSaleProductImageAllId(UUID productsOfferFlashId);
//    ProductOptionImageBottomDTO(UUID id, UUID productsOfferFlashId, List<String> listImageUrlBottom)
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "ProductOptionImageBottomDTO(x.Id, x.ProductsOfferFlashId, x.ListImageUrlBottom) " +
            "FROM ProductOptionImageBottom AS x " +
            "WHERE x.ProductsOfferFlashId = :productsOfferFlashId")
    List<ProductOptionImageBottomDTO> GetAllByProductsOfferFlashId(UUID productsOfferFlashId);
}
