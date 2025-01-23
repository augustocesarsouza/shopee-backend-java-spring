package com.backend.shopee.shopee_backend.data.context;

import com.backend.shopee.shopee_backend.application.dto.UserSellerProductDTO;
import com.backend.shopee.shopee_backend.domain.entities.UserSellerProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.UUID;

@Repository
public interface UserSellerProductRepositoryJPA extends JpaRepository<UserSellerProduct, UUID> {
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "UserSellerProductDTO(x.Id, x.Name, x.ImgProfile, x.ImgFloating, x.LastLogin, x.Reviews, " +
            "x.ChatResponseRate, x.AccountCreationDate, x.QuantityOfProductSold, x.UsuallyRespondsToChatIn, x.Followers) " +
            "FROM UserSellerProduct AS x " +
            "WHERE x.Id = :userSellerProductId")
    UserSellerProductDTO GetByUserSellerProductId(UUID userSellerProductId);
}
//UserSellerProduct(UUID id, String name, String imgProfile, String imgFloating, ZonedDateTime lastLogin,
//                  Integer reviews, Integer chatResponseRate, ZonedDateTime accountCreationDate,
//                  Integer quantityOfProductSold, String usuallyRespondsToChatIn, Integer followers)