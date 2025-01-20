package com.backend.shopee.shopee_backend.data.context;

import com.backend.shopee.shopee_backend.application.dto.FlashSaleProductAllInfoDTO;
import com.backend.shopee.shopee_backend.domain.entities.FlashSaleProductAllInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FlashSaleProductAllInfoJPA extends JpaRepository<FlashSaleProductAllInfo, UUID> {
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "FlashSaleProductAllInfoDTO(x.Id, null, new com.backend.shopee.shopee_backend.application.dto.ProductsOfferFlashDTO" +
            "(x.ProductsOfferFlash.Id, x.ProductsOfferFlash.ImgProduct, x.ProductsOfferFlash.AltValue, x.ProductsOfferFlash.ImgPartBottom," +
            "x.ProductsOfferFlash.PriceProduct, null, x.ProductsOfferFlash.DiscountPercentage," +
            "null, x.ProductsOfferFlash.Title, null), " +
            "x.ProductReviewsRate, x.QuantitySold, x.FavoriteQuantity, x.QuantityEvaluation, x.Coins, x.CreditCard, x.Voltage, " +
            "x.QuantityPiece, x.Size, x.ProductHaveInsurance) " +
            "FROM FlashSaleProductAllInfo AS x " +
            "WHERE x.ProductsOfferFlashId = :productFlashSaleId")
    FlashSaleProductAllInfoDTO GetFlashSaleProductByProductFlashSaleId(UUID productFlashSaleId);
}
//FlashSaleProductAllInfoDTO(UUID id, UUID productsOfferFlashId, ProductsOfferFlash productsOfferFlash,
//                           Double productReviewsRate, Integer quantitySold, Double favoriteQuantity,
//                           Double quantityEvaluation, Integer coins, String creditCard, String voltage,
//                           Integer quantityPiece, String size, Boolean productHaveInsurance)

//ProductsOfferFlashDTO(UUID id, String imgProduct, String altValue, String imgPartBottom, Double priceProduct,
//                      Integer popularityPercentage, Integer discountPercentage, String hourFlashOffer, String title, String tagProduct)