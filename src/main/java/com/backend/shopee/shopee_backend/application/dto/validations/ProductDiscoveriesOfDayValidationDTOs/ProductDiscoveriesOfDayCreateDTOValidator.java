package com.backend.shopee.shopee_backend.application.dto.validations.ProductDiscoveriesOfDayValidationDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDiscoveriesOfDayCreateDTOValidator {
    @NotEmpty(message = "title should not be empty")
    @JsonProperty("title")
    private String Title;
    @NotEmpty(message = "imgProduct should not be empty")
    @JsonProperty("ImgProduct")
    private String ImgProduct;
    @NotNull(message = "isAd should not be null")
    @JsonProperty("isAd")
    private Boolean IsAd;
    @Min(value = 1, message = "price must be greater than 0")
    @JsonProperty("price")
    private Double Price;
    @JsonProperty("imgPartBottom")
    private String ImgPartBottom;
    @JsonProperty("discountPercentage")
    private Integer DiscountPercentage;
    @Min(value = 1, message = "price must be greater than 0")
    @JsonProperty("quantitySold")
    private Double QuantitySold;

    public ProductDiscoveriesOfDayCreateDTOValidator(String title, String imgProduct, Boolean isAd, Double price,
                                                     String imgPartBottom, Integer discountPercentage, Double quantitySold) {
        Title = title;
        ImgProduct = imgProduct;
        IsAd = isAd;
        Price = price;
        ImgPartBottom = imgPartBottom;
        DiscountPercentage = discountPercentage;
        QuantitySold = quantitySold;
    }

    public ProductDiscoveriesOfDayCreateDTOValidator() {
    }

    public String getTitle() {
        return Title;
    }

    public String getImgProduct() {
        return ImgProduct;
    }

    public Boolean getAd() {
        return IsAd;
    }

    public Double getPrice() {
        return Price;
    }

    public String getImgPartBottom() {
        return ImgPartBottom;
    }

    public Integer getDiscountPercentage() {
        return DiscountPercentage;
    }

    public Double getQuantitySold() {
        return QuantitySold;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setImgProduct(String imgProduct) {
        ImgProduct = imgProduct;
    }

    public void setAd(Boolean ad) {
        IsAd = ad;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public void setImgPartBottom(String imgPartBottom) {
        ImgPartBottom = imgPartBottom;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        DiscountPercentage = discountPercentage;
    }

    public void setQuantitySold(Double quantitySold) {
        QuantitySold = quantitySold;
    }
}
