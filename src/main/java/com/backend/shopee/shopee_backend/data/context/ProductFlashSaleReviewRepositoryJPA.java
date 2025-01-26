package com.backend.shopee.shopee_backend.data.context;

import com.backend.shopee.shopee_backend.application.dto.ProductFlashSaleReviewDTO;
import com.backend.shopee.shopee_backend.domain.entities.ProductFlashSaleReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductFlashSaleReviewRepositoryJPA extends JpaRepository<ProductFlashSaleReview, UUID> {
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "ProductFlashSaleReviewDTO(x.Id, null, null, null, null, null, " +
            "null, null, null, x.ImgAndVideoReviewsProduct, null) " +
            "FROM ProductFlashSaleReview AS x " +
            "WHERE x.Id = :productFlashSaleReviewId")
    ProductFlashSaleReviewDTO GetByProductFlashSaleReviewIdToDelete(UUID productFlashSaleReviewId);

    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "ProductFlashSaleReviewDTO(x.Id, x.Message, x.CreationDate, x.CostBenefit, x.SimilarToAd, x.StarQuantity, " +
            "x.ProductsOfferFlashId, x.UserId, new com.backend.shopee.shopee_backend.application.dto." +
            "UserDTO(null, x.User.Name, null, null, null, null, null, x.User.UserImage, null, null), " +
            "x.ImgAndVideoReviewsProduct, x.Variation) " +
            "FROM ProductFlashSaleReview AS x " +
            "WHERE x.ProductsOfferFlashId = :productsOfferFlashId")
    List<ProductFlashSaleReviewDTO> GetAllProductFlashSaleReviewsByProductsOfferFlashId(UUID productsOfferFlashId);
}
//ProductFlashSaleReviewDTO(UUID id, String message, ZonedDateTime creationDate, String costBenefit,
//                          String similarToAd, Integer starQuantity, UUID productsOfferFlashId, UUID userId,
//                          User user, List<String> imgAndVideoReviewsProduct, String variation)

//UserDTO(UUID id, String name, String email, String passwordHash, String cpf,
//        LocalDateTime birthDate, Short confirmEmail, String userImage, String base64ImageUser, String token)