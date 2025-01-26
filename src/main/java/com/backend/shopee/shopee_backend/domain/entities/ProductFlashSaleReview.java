package com.backend.shopee.shopee_backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_product_flash_sale_reviews", schema = "public")
public class ProductFlashSaleReview {
    @jakarta.persistence.Id
    @Column(name = "product_flash_sale_reviews_id")
    @JsonProperty("id")
    private UUID Id;
    @Column(name = "message", length = 300)
    @JsonProperty("message")
    private String Message;
    @Column(name = "creation_date")
    @JsonProperty("creationDate")
    private ZonedDateTime CreationDate;
    @Column(name = "cost_benefit")
    @JsonProperty("costBenefit")
    private String CostBenefit;
    @Column(name = "similar_to_ad")
    @JsonProperty("similarToAd")
    private String SimilarToAd;
    @Column(name = "star_quantity")
    @JsonProperty("starQuantity")
    private Integer StarQuantity;
    @Column(name = "products_offer_flash_id")
    @JsonProperty("productsOfferFlashId")
    private UUID ProductsOfferFlashId;
    @Column(name = "user_id")
    @JsonProperty("userId")
    private UUID UserId;
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User User;
    @Column(name = "img_and_video_reviews_product")
    @JsonProperty("imgAndVideoReviewsProduct")
    private List<String> ImgAndVideoReviewsProduct;
    @Column(name = "variation")
    @JsonProperty("variation")
    private String Variation;

    public ProductFlashSaleReview(UUID id, String message, ZonedDateTime creationDate, String costBenefit,
                                  String similarToAd, Integer starQuantity, UUID productsOfferFlashId, UUID userId,
                                  User user, List<String> imgAndVideoReviewsProduct, String variation) {
        Id = id;
        Message = message;
        CreationDate = creationDate;
        CostBenefit = costBenefit;
        SimilarToAd = similarToAd;
        StarQuantity = starQuantity;
        ProductsOfferFlashId = productsOfferFlashId;
        UserId = userId;
        User = user;
        ImgAndVideoReviewsProduct = imgAndVideoReviewsProduct;
        Variation = variation;
    }

    public ProductFlashSaleReview() {
    }

    public UUID getId() {
        return Id;
    }

    public String getMessage() {
        return Message;
    }

    public ZonedDateTime getCreationDate() {
        return CreationDate;
    }

    public String getCostBenefit() {
        return CostBenefit;
    }

    public String getSimilarToAd() {
        return SimilarToAd;
    }

    public Integer getStarQuantity() {
        return StarQuantity;
    }

    public UUID getProductsOfferFlashId() {
        return ProductsOfferFlashId;
    }

    public UUID getUserId() {
        return UserId;
    }

    public User getUser() {
        return User;
    }

    public List<String> getImgAndVideoReviewsProduct() {
        return ImgAndVideoReviewsProduct;
    }

    public String getVariation() {
        return Variation;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        CreationDate = creationDate;
    }

    public void setCostBenefit(String costBenefit) {
        CostBenefit = costBenefit;
    }

    public void setSimilarToAd(String similarToAd) {
        SimilarToAd = similarToAd;
    }

    public void setStarQuantity(Integer starQuantity) {
        StarQuantity = starQuantity;
    }

    public void setProductsOfferFlashId(UUID productsOfferFlashId) {
        ProductsOfferFlashId = productsOfferFlashId;
    }

    public void setUserId(UUID userId) {
        UserId = userId;
    }

    public void setUser(User user) {
        User = user;
    }

    public void setImgAndVideoReviewsProduct(List<String> imgAndVideoReviewsProduct) {
        ImgAndVideoReviewsProduct = imgAndVideoReviewsProduct;
    }

    public void setVariation(String variation) {
        Variation = variation;
    }
}
