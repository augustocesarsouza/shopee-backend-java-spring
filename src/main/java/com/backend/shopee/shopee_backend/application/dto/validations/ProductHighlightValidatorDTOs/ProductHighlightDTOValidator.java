package com.backend.shopee.shopee_backend.application.dto.validations.ProductHighlightValidatorDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductHighlightDTOValidator {
    @NotEmpty(message = "title should not be empty")
    @JsonProperty("title")
    private String Title;
    @NotEmpty(message = "imgProduct should not be empty")
    @JsonProperty("imgProduct")
    private String ImgProduct;
    @NotEmpty(message = "imgTop should not be empty")
    @JsonProperty("imgTop")
    private String ImgTop;
    @Min(value = 1, message = "quantitySold must be greater than 0")
    @JsonProperty("quantitySold")
    private Double QuantitySold;

    public ProductHighlightDTOValidator(String title, String imgProduct, String imgTop, Double quantitySold) {
        Title = title;
        ImgProduct = imgProduct;
        ImgTop = imgTop;
        QuantitySold = quantitySold;
    }

    public ProductHighlightDTOValidator() {
    }

    public String getTitle() {
        return Title;
    }

    public String getImgProduct() {
        return ImgProduct;
    }

    public String getImgTop() {
        return ImgTop;
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

    public void setImgTop(String imgTop) {
        ImgTop = imgTop;
    }

    public void setQuantitySold(Double quantitySold) {
        QuantitySold = quantitySold;
    }
}
