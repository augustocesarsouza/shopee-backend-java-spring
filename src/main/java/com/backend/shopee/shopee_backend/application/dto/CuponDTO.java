package com.backend.shopee.shopee_backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CuponDTO {
    @JsonProperty("id")
    private UUID Id;
    @JsonProperty("firstText")
    private String FirstText;
    @JsonProperty("secondText")
    private String SecondText;
    @JsonProperty("thirdText")
    private String ThirdText;
    @JsonProperty("dateValidateCupon")
    private LocalDateTime DateValidateCupon;
    @JsonProperty("quantityCupons")
    private Integer QuantityCupons;
    @JsonProperty("whatCuponNumber")
    private Integer WhatCuponNumber;
    @JsonProperty("secondImg")
    private String SecondImg;

    public CuponDTO(UUID id, String firstText, String secondText, String thirdText, LocalDateTime dateValidateCupon,
                    Integer quantityCupons, Integer whatCuponNumber, String secondImg) {
        Id = id;
        FirstText = firstText;
        SecondText = secondText;
        ThirdText = thirdText;
        DateValidateCupon = dateValidateCupon;
        QuantityCupons = quantityCupons;
        WhatCuponNumber = whatCuponNumber;
        SecondImg = secondImg;
    }

    public CuponDTO() {
    }

    public UUID getId() {
        return Id;
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

    public LocalDateTime getDateValidateCupon() {
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

    public void setId(UUID id) {
        Id = id;
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

    public void setDateValidateCupon(LocalDateTime dateValidateCupon) {
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
