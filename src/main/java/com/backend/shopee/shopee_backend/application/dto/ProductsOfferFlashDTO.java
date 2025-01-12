package com.backend.shopee.shopee_backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductsOfferFlashDTO {
    @JsonProperty("id")
    private UUID Id;
    @JsonProperty("imgProduct")
    private String ImgProduct;
    @JsonProperty("altValue")
    private String AltValue;
    @JsonProperty("imgPartBottom")
    private String ImgPartBottom;
    @JsonProperty("priceProduct")
    private Double PriceProduct;
    @JsonProperty("popularityPercentage")
    private Integer PopularityPercentage;
    @JsonProperty("discountPercentage")
    private Integer DiscountPercentage;
    @JsonProperty("hourFlashOffer")
    private String HourFlashOffer;
    @JsonProperty("title")
    private String Title;
    @JsonProperty("tagProduct")
    private String TagProduct;

    public ProductsOfferFlashDTO(UUID id, String imgProduct, String altValue, String imgPartBottom, Double priceProduct,
                                 Integer popularityPercentage, Integer discountPercentage, String hourFlashOffer, String title, String tagProduct) {
        Id = id;
        ImgProduct = imgProduct;
        AltValue = altValue;
        ImgPartBottom = imgPartBottom;
        PriceProduct = priceProduct;
        PopularityPercentage = popularityPercentage;
        DiscountPercentage = discountPercentage;
        HourFlashOffer = hourFlashOffer;
        Title = title;
        TagProduct = tagProduct;
    }

    public ProductsOfferFlashDTO() {
    }

    public UUID getId() {
        return Id;
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

    public void setId(UUID id) {
        Id = id;
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
