package com.backend.shopee.shopee_backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PromotionDTO {
    @JsonProperty("id")
    private UUID Id;
    @JsonProperty("whatIsThePromotion")
    private Integer WhatIsThePromotion;
    @JsonProperty("title")
    private String Title;
    @JsonProperty("description")
    private String Description;
    @JsonProperty("date")
    private LocalDateTime Date;
    @JsonProperty("img")
    private String Img;
    @JsonProperty("listImgInner")
    private List<String> ListImgInner;

    public PromotionDTO(UUID id, Integer whatIsThePromotion, String title, String description, LocalDateTime date, String img, List<String> listImgInner) {
        Id = id;
        WhatIsThePromotion = whatIsThePromotion;
        Title = title;
        Description = description;
        Date = date;
        Img = img;
        ListImgInner = listImgInner;
    }

    public PromotionDTO() {
    }

    public UUID getId() {
        return Id;
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

    public LocalDateTime getDate() {
        return Date;
    }

    public String getImg() {
        return Img;
    }

    public List<String> getListImgInner() {
        return ListImgInner;
    }

    public void setId(UUID id) {
        Id = id;
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

    public void setDate(LocalDateTime date) {
        Date = date;
    }

    public void setImg(String img) {
        Img = img;
    }

    public void setListImgInner(List<String> listImgInner) {
        ListImgInner = listImgInner;
    }
}
