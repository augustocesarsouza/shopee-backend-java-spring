package com.backend.shopee.shopee_backend.data.context;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashSellerDTO;
import com.backend.shopee.shopee_backend.domain.entities.ProductOfferFlashSeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductSellerRepositoryJPA extends JpaRepository<ProductOfferFlashSeller, UUID> {
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "ProductOfferFlashSellerDTO(x.Id, x.UserSellerProductId, " +
            "new com.backend.shopee.shopee_backend.application.dto.UserSellerProductDTO(" +
            "x.UserSellerProduct.Id, x.UserSellerProduct.Name, x.UserSellerProduct.ImgProfile, x.UserSellerProduct.ImgFloating, " +
            "x.UserSellerProduct.LastLogin, x.UserSellerProduct.Reviews, x.UserSellerProduct.ChatResponseRate, x.UserSellerProduct.AccountCreationDate, " +
            "x.UserSellerProduct.QuantityOfProductSold, x.UserSellerProduct.UsuallyRespondsToChatIn, x.UserSellerProduct.Followers), x.ProductsOfferFlashId) " +
            "FROM ProductOfferFlashSeller AS x " +
            "WHERE x.ProductsOfferFlashId = :productsOfferFlashId")
    ProductOfferFlashSellerDTO GetByProductsOfferFlashId(UUID productsOfferFlashId);
}
//ProductOfferFlashSellerDTO(UUID id, UUID userSellerProductId,
//                           UserSellerProductDTO userSellerProductDTO, UUID productsOfferFlashId)

//UserSellerProductDTO(UUID id, String name, String imgProfile, String imgFloating, ZonedDateTime lastLogin,
//                     Integer reviews, Integer chatResponseRate, ZonedDateTime accountCreationDate,
//                     Integer quantityOfProductSold, String usuallyRespondsToChatIn, Integer followers)