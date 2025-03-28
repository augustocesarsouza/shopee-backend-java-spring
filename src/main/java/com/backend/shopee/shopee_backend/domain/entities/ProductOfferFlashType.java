package com.backend.shopee.shopee_backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "tb_product_offer_flash_types", schema = "public")
public class ProductOfferFlashType {
    @jakarta.persistence.Id
    @Column(name = "product_offer_flash_types_id")
    @JsonProperty("id")
    private UUID Id;
    @Column(name = "img_product")
    @JsonProperty("imgProduct")
    private String ImgProduct;
    @Column(name = "option_type")
    @JsonProperty("optionType")
    private String OptionType;
    @Column(name = "products_offer_flash_id")
    @JsonProperty("productsOfferFlashId")
    private UUID ProductsOfferFlashId;
    @Column(name = "title_img")
    @JsonProperty("titleImg")
    private String TitleImg;

    public ProductOfferFlashType(UUID id, String imgProduct, String optionType, UUID productsOfferFlashId, String titleImg) {
        Id = id;
        ImgProduct = imgProduct;
        OptionType = optionType;
        ProductsOfferFlashId = productsOfferFlashId;
        TitleImg = titleImg;
    }

    public ProductOfferFlashType() {
    }

    public UUID getId() {
        return Id;
    }

    public String getImgProduct() {
        return ImgProduct;
    }

    public String getOptionType() {
        return OptionType;
    }

    public UUID getProductsOfferFlashId() {
        return ProductsOfferFlashId;
    }

    public String getTitleImg() {
        return TitleImg;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setImgProduct(String imgProduct) {
        ImgProduct = imgProduct;
    }

    public void setOptionType(String optionType) {
        OptionType = optionType;
    }

    public void setProductsOfferFlashId(UUID productsOfferFlashId) {
        ProductsOfferFlashId = productsOfferFlashId;
    }

    public void setTitleImg(String titleImg) {
        TitleImg = titleImg;
    }
}
