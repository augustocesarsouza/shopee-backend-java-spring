package com.backend.shopee.shopee_backend.application.dto.validations.ProductsOfferFlashValidator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductsOfferFlashDTOValidator {
    @NotEmpty(message = "imgProduct should not be empty")
    @JsonProperty("imgProduct")
    private String ImgProduct;
    @NotEmpty(message = "altValue should not be empty")
    @JsonProperty("altValue")
    private String AltValue;
    @JsonProperty("imgPartBottom")
    private String ImgPartBottom;
    @Min(value = 1, message = "priceProduct must be greater than 0")
    @JsonProperty("priceProduct")
    private Double PriceProduct;
    @Min(value = 1, message = "priceProduct must be greater than 0")
    @Max(value = 100, message = "priceProduct must be less than 100")
    @JsonProperty("popularityPercentage")
    private Integer PopularityPercentage;
    @Min(value = 1, message = "discountPercentage must be greater than 0")
    @JsonProperty("discountPercentage")
    private Integer DiscountPercentage;
    @NotEmpty(message = "hourFlashOffer should not be empty")
    @JsonProperty("hourFlashOffer")
    private String HourFlashOffer;
    @NotEmpty(message = "hourFlashOffer should not be empty")
    @JsonProperty("title")
    private String Title;
    @NotEmpty(message = "tagProduct should not be empty")
    @JsonProperty("tagProduct")
    private String TagProduct;

    public ProductsOfferFlashDTOValidator(String imgProduct, String altValue, Double priceProduct, Integer popularityPercentage,
                                          Integer discountPercentage, String hourFlashOffer, String title, String tagProduct) {
        ImgProduct = imgProduct;
        AltValue = altValue;
        PriceProduct = priceProduct;
        PopularityPercentage = popularityPercentage;
        DiscountPercentage = discountPercentage;
        HourFlashOffer = hourFlashOffer;
        Title = title;
        TagProduct = tagProduct;
    }

    public ProductsOfferFlashDTOValidator() {
    }

    public String getImgProduct() {
        return ImgProduct;
    }

    public String getAltValue() {
        return AltValue;
    }

    public String getImgPartBottom() {
        return ImgPartBottom;
    }

    public Double getPriceProduct() {
        return PriceProduct;
    }

    public Integer getPopularityPercentage() {
        return PopularityPercentage;
    }

    public Integer getDiscountPercentage() {
        return DiscountPercentage;
    }

    public String getHourFlashOffer() {
        return HourFlashOffer;
    }

    public String getTitle() {
        return Title;
    }

    public String getTagProduct() {
        return TagProduct;
    }

    public void setImgProduct(String imgProduct) {
        ImgProduct = imgProduct;
    }

    public void setAltValue(String altValue) {
        AltValue = altValue;
    }

    public void setImgPartBottom(String imgPartBottom) {
        ImgPartBottom = imgPartBottom;
    }

    public void setPriceProduct(Double priceProduct) {
        PriceProduct = priceProduct;
    }

    public void setPopularityPercentage(Integer popularityPercentage) {
        PopularityPercentage = popularityPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        DiscountPercentage = discountPercentage;
    }

    public void setHourFlashOffer(String hourFlashOffer) {
        HourFlashOffer = hourFlashOffer;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setTagProduct(String tagProduct) {
        TagProduct = tagProduct;
    }
}
