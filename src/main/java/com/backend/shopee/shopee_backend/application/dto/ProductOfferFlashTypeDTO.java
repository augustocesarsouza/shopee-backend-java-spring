package com.backend.shopee.shopee_backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductOfferFlashTypeDTO {
    @JsonProperty("id")
    private UUID Id;
    @JsonProperty("imgProduct")
    private String ImgProduct;
    @JsonProperty("optionType")
    private String OptionType;
    @JsonProperty("productsOfferFlashId")
    private UUID ProductsOfferFlashId;
    @JsonProperty("titleImg")
    private String TitleImg;

    public ProductOfferFlashTypeDTO(UUID id, String imgProduct, String optionType, UUID productsOfferFlashId, String titleImg) {
        Id = id;
        ImgProduct = imgProduct;
        OptionType = optionType;
        ProductsOfferFlashId = productsOfferFlashId;
        TitleImg = titleImg;
    }

    public ProductOfferFlashTypeDTO() {
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
