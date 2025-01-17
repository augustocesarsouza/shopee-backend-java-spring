package com.backend.shopee.shopee_backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDiscoveriesOfDayDTO {
    @JsonProperty("id")
    private UUID Id;
    @JsonProperty("title")
    private String Title;
    @JsonProperty("imgProduct")
    private String ImgProduct;
    @JsonProperty("imgPartBottom")
    private String ImgPartBottom;
    @JsonProperty("discountPercentage")
    private Integer DiscountPercentage;
    @JsonProperty("isAd")
    private Boolean IsAd;
    @JsonProperty("price")
    private Double Price;
    @JsonProperty("quantitySold")
    private Double QuantitySold;

    public ProductDiscoveriesOfDayDTO(UUID id, String title, String imgProduct, String imgPartBottom,
                                      Integer discountPercentage, Boolean isAd, Double price, Double quantitySold) {
        Id = id;
        Title = title;
        ImgProduct = imgProduct;
        ImgPartBottom = imgPartBottom;
        DiscountPercentage = discountPercentage;
        IsAd = isAd;
        Price = price;
        QuantitySold = quantitySold;
    }

    public ProductDiscoveriesOfDayDTO() {
    }

    public UUID getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public String getImgProduct() {
        return ImgProduct;
    }

    public String getImgPartBottom() {
        return ImgPartBottom;
    }

    public Integer getDiscountPercentage() {
        return DiscountPercentage;
    }

    public Boolean getAd() {
        return IsAd;
    }

    public Double getPrice() {
        return Price;
    }

    public Double getQuantitySold() {
        return QuantitySold;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setImgProduct(String imgProduct) {
        ImgProduct = imgProduct;
    }

    public void setImgPartBottom(String imgPartBottom) {
        ImgPartBottom = imgPartBottom;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        DiscountPercentage = discountPercentage;
    }

    public void setAd(Boolean ad) {
        IsAd = ad;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public void setQuantitySold(Double quantitySold) {
        QuantitySold = quantitySold;
    }
}
