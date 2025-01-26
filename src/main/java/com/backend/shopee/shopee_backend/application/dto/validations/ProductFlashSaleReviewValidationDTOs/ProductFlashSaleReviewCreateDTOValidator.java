package com.backend.shopee.shopee_backend.application.dto.validations.ProductFlashSaleReviewValidationDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductFlashSaleReviewCreateDTOValidator {
    @NotEmpty(message = "message should not be empty")
    @JsonProperty("message")
    private String Message;
    @NotEmpty(message = "costBenefit should not be empty")
    @JsonProperty("costBenefit")
    private String CostBenefit;
    @NotEmpty(message = "similarToAd should not be empty")
    @JsonProperty("similarToAd")
    private String SimilarToAd;
    @Min(value = 1, message = "starQuantity must be greater than 0")
    @JsonProperty("starQuantity")
    private Integer StarQuantity;
    @NotEmpty(message = "productsOfferFlashId should not be empty")
    @Size(min = 36, message = "productsOfferFlashId should have at last 36 characters")
    @JsonProperty("productsOfferFlashId")
    private String ProductsOfferFlashId;
    @NotEmpty(message = "userId should not be empty")
    @Size(min = 36, message = "userId should have at last 36 characters")
    @JsonProperty("userId")
    private String UserId;
    @JsonProperty("imgAndVideoReviewsProduct")
    private List<String> ImgAndVideoReviewsProduct;
    @NotEmpty(message = "variation should not be empty")
    @JsonProperty("variation")
    private String Variation;

    public ProductFlashSaleReviewCreateDTOValidator(String message, String costBenefit, String similarToAd,
                                                    Integer starQuantity, String productsOfferFlashId, String userId,
                                                    List<String> imgAndVideoReviewsProduct, String variation) {
        Message = message;
        CostBenefit = costBenefit;
        SimilarToAd = similarToAd;
        StarQuantity = starQuantity;
        ProductsOfferFlashId = productsOfferFlashId;
        UserId = userId;
        ImgAndVideoReviewsProduct = imgAndVideoReviewsProduct;
        Variation = variation;
    }

    public ProductFlashSaleReviewCreateDTOValidator() {
    }

    public String getMessage() {
        return Message;
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

    public String getProductsOfferFlashId() {
        return ProductsOfferFlashId;
    }

    public String getUserId() {
        return UserId;
    }

    public List<String> getImgAndVideoReviewsProduct() {
        return ImgAndVideoReviewsProduct;
    }

    public String getVariation() {
        return Variation;
    }

    public void setMessage(String message) {
        Message = message;
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

    public void setProductsOfferFlashId(String productsOfferFlashId) {
        ProductsOfferFlashId = productsOfferFlashId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public void setImgAndVideoReviewsProduct(List<String> imgAndVideoReviewsProduct) {
        ImgAndVideoReviewsProduct = imgAndVideoReviewsProduct;
    }

    public void setVariation(String variation) {
        Variation = variation;
    }
}
