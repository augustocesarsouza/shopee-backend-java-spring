package com.backend.shopee.shopee_backend.application.dto.validations.ShopeeUpdateValidationDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShopeeUpdateCreateDTOValidator {
    @NotEmpty(message = "title should not be empty")
    @JsonProperty("title")
    private String Title;
    @NotEmpty(message = "description should not be empty")
    @JsonProperty("description")
    private String Description;
    @NotEmpty(message = "dateCreate should not be empty")
    @Pattern(
            regexp = "^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}$",
            message = "dateCreate must follow the format dd/MM/yyyy HH:mm"
    )
    @JsonProperty("dateCreate")
    private String DateCreate;
    @NotEmpty(message = "img should not be empty")
    @JsonProperty("img")
    private String Img;

    public ShopeeUpdateCreateDTOValidator(String title, String description, String dateCreate, String img) {
        Title = title;
        Description = description;
        DateCreate = dateCreate;
        Img = img;
    }

    public ShopeeUpdateCreateDTOValidator() {
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getDateCreate() {
        return DateCreate;
    }

    public String getImg() {
        return Img;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setDateCreate(String dateCreate) {
        DateCreate = dateCreate;
    }

    public void setImg(String img) {
        Img = img;
    }
}
