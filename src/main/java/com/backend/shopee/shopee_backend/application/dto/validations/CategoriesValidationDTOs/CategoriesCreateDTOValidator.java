package com.backend.shopee.shopee_backend.application.dto.validations.CategoriesValidationDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoriesCreateDTOValidator {
    @NotEmpty(message = "imgCategory should not be empty")
    @JsonProperty("imgCategory")
    private String ImgCategory;
    @NotEmpty(message = "altValue should not be empty")
    @JsonProperty("altValue")
    private String AltValue;
    @NotEmpty(message = "title should not be empty")
    @JsonProperty("title")
    private String Title;

    public CategoriesCreateDTOValidator(String imgCategory, String altValue, String title) {
        ImgCategory = imgCategory;
        AltValue = altValue;
        Title = title;
    }

    public CategoriesCreateDTOValidator() {
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
