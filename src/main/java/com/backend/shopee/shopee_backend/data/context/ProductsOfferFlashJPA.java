package com.backend.shopee.shopee_backend.data.context;

import com.backend.shopee.shopee_backend.application.dto.ProductsOfferFlashDTO;
import com.backend.shopee.shopee_backend.domain.entities.ProductsOfferFlash;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductsOfferFlashJPA extends JpaRepository<ProductsOfferFlash, UUID> {
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "ProductsOfferFlashDTO(x.Id, x.ImgProduct, null, null, null, null, null, " +
            "null, null, null) " +
            "FROM ProductsOfferFlash AS x " +
            "WHERE x.Id = :productsOfferFlashId")
    ProductsOfferFlashDTO GetProductsOfferFlashById(UUID productsOfferFlashId);

    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "ProductsOfferFlashDTO(x.Id, x.ImgProduct, x.AltValue, x.ImgPartBottom, x.PriceProduct, x.PopularityPercentage, x.DiscountPercentage, " +
            "null, null, null) " +
            "FROM ProductsOfferFlash AS x")
    List<ProductsOfferFlashDTO> GetAllProduct();

    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "ProductsOfferFlashDTO(x.Id, x.ImgProduct, x.AltValue, x.ImgPartBottom, x.PriceProduct, x.PopularityPercentage, x.DiscountPercentage, " +
            "null, x.Title, x.TagProduct) " +
            "FROM ProductsOfferFlash AS x " +
            "WHERE x.HourFlashOffer = :hourFlashOffer AND x.TagProduct = :tagProduct")
    List<ProductsOfferFlashDTO> GetAllByTagProduct(String hourFlashOffer, String tagProduct, Pageable pageable);
}
//ProductsOfferFlashDTO(UUID id, String imgProduct, String altValue, String imgPartBottom, Double priceProduct,
//                      Integer popularityPercentage, Integer discountPercentage, String hourFlashOffer, String title, String tagProduct)