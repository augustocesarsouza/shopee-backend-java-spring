package com.backend.shopee.shopee_backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "tb_categories", schema = "public")
public class Categories {
    @jakarta.persistence.Id
    @Column(name = "categories_id")
    @JsonProperty("id")
    private UUID Id;
    @Column(name = "img_category")
    @JsonProperty("imgCategory")
    private String ImgCategory;
    @Column(name = "alt_value")
    @JsonProperty("altValue")
    private String AltValue;
    @Column(name = "title")
    @JsonProperty("title")
    private String Title;

    public Categories(UUID id, String imgCategory, String altValue, String title) {
        Id = id;
        ImgCategory = imgCategory;
        AltValue = altValue;
        Title = title;
    }

    public Categories() {
    }

    public UUID getId() {
        return Id;
    }

    public String getImgCategory() {
        return ImgCategory;
    }

    public String getAltValue() {
        return AltValue;
    }

    public String getTitle() {
        return Title;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setImgCategory(String imgCategory) {
        ImgCategory = imgCategory;
    }

    public void setAltValue(String altValue) {
        AltValue = altValue;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
