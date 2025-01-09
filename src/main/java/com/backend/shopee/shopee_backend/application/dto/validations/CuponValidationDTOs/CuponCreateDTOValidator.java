package com.backend.shopee.shopee_backend.application.dto.validations.CuponValidationDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CuponCreateDTOValidator {
    @NotEmpty(message = "firstText should not be empty")
    @JsonProperty("firstText")
    private String FirstText;
    @NotEmpty(message = "secondText should not be empty")
    @JsonProperty("secondText")
    private String SecondText;
    @NotEmpty(message = "thirdText should not be empty")
    @JsonProperty("thirdText")
    private String ThirdText;
    @NotEmpty(message = "dateValidateCupon should not be empty")
    @Pattern(
            regexp = "^\\d{2}/\\d{2}/\\d{4}$",
            message = "dateValidateCupon must follow the format dd/MM/yyyy"
    )
    @JsonProperty("dateValidateCupon")
    private String DateValidateCupon;
    @Min(value = 1, message = "quantityCupons must be greater than 0")
    @JsonProperty("quantityCupons")
    private Integer QuantityCupons;
    @Min(value = 1, message = "whatCuponNumber must be greater than or equal to 1")
    @Max(value = 6, message = "whatCuponNumber must be less than or equal to 6")
    @JsonProperty("whatCuponNumber")
    private Integer WhatCuponNumber;
    @JsonProperty("secondImg")
    private String SecondImg;

    public CuponCreateDTOValidator(String firstText, String secondText, String thirdText, String dateValidateCupon,
                                   Integer quantityCupons, Integer whatCuponNumber, String secondImg) {
        FirstText = firstText;
        SecondText = secondText;
        ThirdText = thirdText;
        DateValidateCupon = dateValidateCupon;
        QuantityCupons = quantityCupons;
        WhatCuponNumber = whatCuponNumber;
        SecondImg = secondImg;
    }

    public CuponCreateDTOValidator() {
    }

    public String getFirstText() {
        return FirstText;
    }

    public String getSecondText() {
        return SecondText;
    }

    public String getThirdText() {
        return ThirdText;
    }

    public String getDateValidateCupon() {
        return DateValidateCupon;
    }

    public Integer getQuantityCupons() {
        return QuantityCupons;
    }

    public Integer getWhatCuponNumber() {
        return WhatCuponNumber;
    }

    public String getSecondImg() {
        return SecondImg;
    }

    public void setFirstText(String firstText) {
        FirstText = firstText;
    }

    public void setSecondText(String secondText) {
        SecondText = secondText;
    }

    public void setThirdText(String thirdText) {
        ThirdText = thirdText;
    }

    public void setDateValidateCupon(String dateValidateCupon) {
        DateValidateCupon = dateValidateCupon;
    }

    public void setQuantityCupons(Integer quantityCupons) {
        QuantityCupons = quantityCupons;
    }

    public void setWhatCuponNumber(Integer whatCuponNumber) {
        WhatCuponNumber = whatCuponNumber;
    }

    public void setSecondImg(String secondImg) {
        SecondImg = secondImg;
    }
}
