package com.backend.shopee.shopee_backend.application.dto;

import com.backend.shopee.shopee_backend.domain.entities.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductFlashSaleReviewDTO {
    @JsonProperty("id")
    private UUID Id;
    @JsonProperty("message")
    private String Message;
    @JsonProperty("creationDate")
    private ZonedDateTime CreationDate;
    @JsonProperty("costBenefit")
    private String CostBenefit;
    @JsonProperty("similarToAd")
    private String SimilarToAd;
    @JsonProperty("starQuantity")
    private Integer StarQuantity;
    @JsonProperty("productsOfferFlashId")
    private UUID ProductsOfferFlashId;
    @JsonProperty("userId")
    private UUID UserId;
    @JsonProperty("userDTO")
    private UserDTO UserDTO;
    @JsonProperty("imgAndVideoReviewsProduct")
    private List<String> ImgAndVideoReviewsProduct;
    @JsonProperty("variation")
    private String Variation;

    public ProductFlashSaleReviewDTO(UUID id, String message, ZonedDateTime creationDate, String costBenefit,
                                     String similarToAd, Integer starQuantity, UUID productsOfferFlashId, UUID userId,
                                     UserDTO userDTO, List<String> imgAndVideoReviewsProduct, String variation) {
        Id = id;
        Message = message;
        CreationDate = creationDate;
        CostBenefit = costBenefit;
        SimilarToAd = similarToAd;
        StarQuantity = starQuantity;
        ProductsOfferFlashId = productsOfferFlashId;
        UserId = userId;
        UserDTO = userDTO;
        ImgAndVideoReviewsProduct = imgAndVideoReviewsProduct;
        Variation = variation;
    }

    public ProductFlashSaleReviewDTO() {
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

    public UserDTO getUserDTO() {
        return UserDTO;
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

    public void setUserDTO(UserDTO userDTO) {
        UserDTO = userDTO;
    }

    public void setImgAndVideoReviewsProduct(List<String> imgAndVideoReviewsProduct) {
        ImgAndVideoReviewsProduct = imgAndVideoReviewsProduct;
    }

    public void setVariation(String variation) {
        Variation = variation;
    }

    public ZonedDateTime convertCreationDateDateFromUtcToLocal() {
        ZonedDateTime creationDate = this.getCreationDate();
//            ZoneId localZoneId = ZoneId.of("America/Sao_Paulo");
        ZoneId localZoneId = ZoneId.systemDefault();
        return creationDate.withZoneSameInstant(localZoneId);
    }
}
