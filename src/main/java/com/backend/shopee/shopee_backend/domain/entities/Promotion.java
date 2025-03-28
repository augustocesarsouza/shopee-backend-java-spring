package com.backend.shopee.shopee_backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_java_shopee_promotions", schema = "public")
public class Promotion {
    @jakarta.persistence.Id
    @Column(name = "promotions_id")
    @JsonProperty("id")
    private UUID Id;
    @Column(name = "what_is_the_promotion")
    @JsonProperty("whatIsThePromotion")
    private Integer WhatIsThePromotion;
    @Column(name = "title")
    @JsonProperty("title")
    private String Title;
    @Column(name = "description")
    @JsonProperty("description")
    private String Description;
    @Column(name = "date")
    @JsonProperty("date")
    private LocalDateTime Date;
    @Column(name = "img")
    @JsonProperty("img")
    private String Img;
    @Column(name = "list_img_inner")
    @JsonProperty("listImgInner")
    private List<String> ListImgInner;

    public Promotion(UUID id, Integer whatIsThePromotion, String title, String description, LocalDateTime date, String img, List<String> listImgInner) {
        Id = id;
        WhatIsThePromotion = whatIsThePromotion;
        Title = title;
        Description = description;
        Date = date;
        Img = img;
        ListImgInner = listImgInner;
    }

    public Promotion() {
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
