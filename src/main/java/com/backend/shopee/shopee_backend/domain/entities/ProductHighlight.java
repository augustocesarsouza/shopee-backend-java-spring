package com.backend.shopee.shopee_backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "tb_java_shopee_product_highlights", schema = "public")
public class ProductHighlight {
    @jakarta.persistence.Id
    @Column(name = "product_highlights_id")
    @JsonProperty("id")
    private UUID Id;
    @Column(name = "title")
    @JsonProperty("title")
    private String Title;
    @Column(name = "img_product")
    @JsonProperty("imgProduct")
    private String ImgProduct;
    @Column(name = "img_top")
    @JsonProperty("imgTop")
    private String ImgTop;
    @Column(name = "quantity_sold")
    @JsonProperty("quantitySold")
    private Double QuantitySold;

    public ProductHighlight(UUID id, String title, String imgProduct, String imgTop, Double quantitySold) {
        Id = id;
        Title = title;
        ImgProduct = imgProduct;
        ImgTop = imgTop;
        QuantitySold = quantitySold;
    }

    public ProductHighlight() {
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
