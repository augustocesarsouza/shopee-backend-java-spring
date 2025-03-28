package com.backend.shopee.shopee_backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "tb_java_shopee_products_offer_flash", schema = "public")
public class ProductsOfferFlash {
    @jakarta.persistence.Id
    @Column(name = "products_offer_flash_id")
    @JsonProperty("id")
    private UUID Id;
    @Column(name = "img_product")
    @JsonProperty("imgProduct")
    private String ImgProduct;
    @Column(name = "alt_value")
    @JsonProperty("altValue")
    private String AltValue;
    @Column(name = "img_part_bottom")
    @JsonProperty("imgPartBottom")
    private String ImgPartBottom;
    @Column(name = "price_product")
    @JsonProperty("priceProduct")
    private Double PriceProduct;
    @Column(name = "popularity_percentage")
    @JsonProperty("popularityPercentage")
    private Integer PopularityPercentage;
    @Column(name = "discount_percentage")
    @JsonProperty("discountPercentage")
    private Integer DiscountPercentage;
    @Column(name = "hour_flash_offer")
    @JsonProperty("hourFlashOffer")
    private String HourFlashOffer;
    @Column(name = "title")
    @JsonProperty("title")
    private String Title;
    @Column(name = "tag_product")
    @JsonProperty("tagProduct")
    private String TagProduct;

    public ProductsOfferFlash(UUID id, String imgProduct, String altValue, String imgPartBottom, Double priceProduct,
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

    public ProductsOfferFlash() {
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
