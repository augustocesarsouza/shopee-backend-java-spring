package com.backend.shopee.shopee_backend.data.context;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashTypeDTO;
import com.backend.shopee.shopee_backend.domain.entities.ProductOfferFlashType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductOfferFlashTypeRepositoryJPA extends JpaRepository<ProductOfferFlashType, UUID> {
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "ProductOfferFlashTypeDTO(x.Id, x.ImgProduct, x.OptionType, null, x.TitleImg) " +
            "FROM ProductOfferFlashType AS x " +
            "WHERE x.ProductsOfferFlashId = :productsOfferFlashId")
    List<ProductOfferFlashTypeDTO> GetAllProductOfferFlashTypeByProductsOfferFlashId(UUID productsOfferFlashId);
}
//ProductOfferFlashTypeDTO(UUID id, String imgProduct, String optionType, UUID productsOfferFlashId, String titleImg)