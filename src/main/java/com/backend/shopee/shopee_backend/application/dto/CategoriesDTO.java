package com.backend.shopee.shopee_backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoriesDTO {
    @JsonProperty("id")
    private UUID Id;
    @JsonProperty("imgCategory")
    private String ImgCategory;
    @JsonProperty("altValue")
    private String AltValue;
    @JsonProperty("title")
    private String Title;

    public CategoriesDTO(UUID id, String imgCategory, String altValue, String title) {
        Id = id;
        ImgCategory = imgCategory;
        AltValue = altValue;
        Title = title;
    }

    public CategoriesDTO() {
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
