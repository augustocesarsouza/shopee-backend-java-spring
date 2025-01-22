package com.backend.shopee.shopee_backend.application.dto.validations.ProductOfferFlashTypeValidationDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductOfferFlashTypeCreateDTOValidator {
    @NotEmpty(message = "imgProduct should not be empty")
    @JsonProperty("imgProduct")
    private String ImgProduct;
    @NotEmpty(message = "optionType should not be empty")
    @JsonProperty("optionType")
    private String OptionType;
    @NotEmpty(message = "productsOfferFlashId should not be empty")
    @Size(min = 36, message = "productsOfferFlashId should have at last 36 characters")
    @JsonProperty("productsOfferFlashId")
    private String ProductsOfferFlashId;
    @NotEmpty(message = "titleImg should not be empty")
    @JsonProperty("titleImg")
    private String TitleImg;

    public ProductOfferFlashTypeCreateDTOValidator(String imgProduct, String optionType, String productsOfferFlashId, String titleImg) {
        ImgProduct = imgProduct;
        OptionType = optionType;
        ProductsOfferFlashId = productsOfferFlashId;
        TitleImg = titleImg;
    }

    public ProductOfferFlashTypeCreateDTOValidator() {
    }

    public String getImgProduct() {
        return ImgProduct;
    }

    public String getOptionType() {
        return OptionType;
    }

    public String getProductsOfferFlashId() {
        return ProductsOfferFlashId;
    }

    public String getTitleImg() {
        return TitleImg;
    }

    public void setImgProduct(String imgProduct) {
        ImgProduct = imgProduct;
    }

    public void setOptionType(String optionType) {
        OptionType = optionType;
    }

    public void setProductsOfferFlashId(String productsOfferFlashId) {
        ProductsOfferFlashId = productsOfferFlashId;
    }

    public void setTitleImg(String titleImg) {
        TitleImg = titleImg;
    }
}
