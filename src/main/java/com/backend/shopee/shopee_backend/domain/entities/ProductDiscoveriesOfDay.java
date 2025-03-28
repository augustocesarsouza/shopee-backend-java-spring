package com.backend.shopee.shopee_backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "tb_java_shopee_product_discoveries_of_days", schema = "public")
public class ProductDiscoveriesOfDay {
    @jakarta.persistence.Id
    @Column(name = "product_discoveries_of_days_id")
    @JsonProperty("id")
    private UUID Id;
    @Column(name = "title")
    @JsonProperty("title")
    private String Title;
    @Column(name = "img_product")
    @JsonProperty("imgProduct")
    private String ImgProduct;
    @Column(name = "img_part_bottom")
    @JsonProperty("imgPartBottom")
    private String ImgPartBottom;
    @Column(name = "discount_percentage")
    @JsonProperty("discountPercentage")
    private Integer DiscountPercentage;
    @Column(name = "is_ad")
    @JsonProperty("isAd")
    private Boolean IsAd;
    @Column(name = "price")
    @JsonProperty("price")
    private Double Price;
    @Column(name = "quantity_sold")
    @JsonProperty("quantitySold")
    private Double QuantitySold;

    public ProductDiscoveriesOfDay(UUID id, String title, String imgProduct, String imgPartBottom,
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

    public ProductDiscoveriesOfDay() {
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
