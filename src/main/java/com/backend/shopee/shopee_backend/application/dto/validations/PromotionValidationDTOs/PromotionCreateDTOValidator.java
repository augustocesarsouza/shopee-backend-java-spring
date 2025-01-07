package com.backend.shopee.shopee_backend.application.dto.validations.PromotionValidationDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PromotionCreateDTOValidator {
    @Min(value = 1, message = "whatIsThePromotion must be greater than 0")
    @JsonProperty("whatIsThePromotion")
    private Integer WhatIsThePromotion;
    @NotEmpty(message = "title should not be empty")
    @JsonProperty("title")
    private String Title;
    @NotEmpty(message = "description should not be empty")
    @JsonProperty("description")
    private String Description;
    @NotEmpty(message = "img should not be empty")
    @JsonProperty("img")
    private String Img;
    @JsonProperty("listImgInner")
    private List<String> ListImgInner;
    @NotEmpty(message = "dateCreate should not be empty")
    @Pattern(
            regexp = "^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}$",
            message = "dateCreate must follow the format dd/MM/yyyy HH:mm"
    )
    @JsonProperty("dateCreate")
    private String DateCreate;

    public PromotionCreateDTOValidator(Integer whatIsThePromotion, String title, String description, String img, List<String> listImgInner) {
        WhatIsThePromotion = whatIsThePromotion;
        Title = title;
        Description = description;
        Img = img;
        ListImgInner = listImgInner;
    }

    public PromotionCreateDTOValidator() {
    }

    public Integer getWhatIsThePromotion() {
        return WhatIsThePromotion;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getImg() {
        return Img;
    }

    public List<String> getListImgInner() {
        return ListImgInner;
    }

    public String getDateCreate() {
        return DateCreate;
    }

    public void setWhatIsThePromotion(Integer whatIsThePromotion) {
        WhatIsThePromotion = whatIsThePromotion;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setImg(String img) {
        Img = img;
    }

    public void setListImgInner(List<String> listImgInner) {
        ListImgInner = listImgInner;
    }

    public void setDateCreate(String dateCreate) {
        DateCreate = dateCreate;
    }
}
