package com.backend.shopee.shopee_backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductHighlightDTO {
    @JsonProperty("id")
    private UUID Id;
    @JsonProperty("title")
    private String Title;
    @JsonProperty("imgProduct")
    private String ImgProduct;
    @JsonProperty("imgTop")
    private String ImgTop;
    @JsonProperty("quantitySold")
    private Double QuantitySold;

    public ProductHighlightDTO(UUID id, String title, String imgProduct, String imgTop, Double quantitySold) {
        Id = id;
        Title = title;
        ImgProduct = imgProduct;
        ImgTop = imgTop;
        QuantitySold = quantitySold;
    }

    public ProductHighlightDTO() {
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

    public String getImgTop() {
        return ImgTop;
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

    public void setImgTop(String imgTop) {
        ImgTop = imgTop;
    }

    public void setQuantitySold(Double quantitySold) {
        QuantitySold = quantitySold;
    }
}
