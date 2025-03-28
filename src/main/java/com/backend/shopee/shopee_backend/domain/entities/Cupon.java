package com.backend.shopee.shopee_backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_java_shopee_cupons", schema = "public")
public class Cupon {
    @jakarta.persistence.Id
    @Column(name = "cupons_id")
    @JsonProperty("id")
    private UUID Id;
    @Column(name = "first_text")
    @JsonProperty("firstText")
    private String FirstText;
    @Column(name = "second_text")
    @JsonProperty("secondText")
    private String SecondText;
    @Column(name = "third_text")
    @JsonProperty("thirdText")
    private String ThirdText;
    @Column(name = "date_validate_cupon")
    @JsonProperty("dateValidateCupon")
    private LocalDateTime DateValidateCupon;
    @Column(name = "quantity_cupons")
    @JsonProperty("quantityCupons")
    private Integer QuantityCupons;
    @Column(name = "what_cupon_number")
    @JsonProperty("whatCuponNumber")
    private Integer WhatCuponNumber;
    @Column(name = "second_img")
    @JsonProperty("secondImg")
    private String SecondImg;

    public Cupon(UUID id, String firstText, String secondText, String thirdText, LocalDateTime dateValidateCupon,
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

    public Cupon() {
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
