package com.backend.shopee.shopee_backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShopeeUpdateDTO {
    @JsonProperty("id")
    private UUID Id;
    @JsonProperty("title")
    private String Title;
    @JsonProperty("description")
    private String Description;
    @JsonProperty("date")
    private LocalDateTime Date;
    @JsonProperty("img")
    private String Img;

    public ShopeeUpdateDTO(UUID id, String title, String description, LocalDateTime date, String img) {
        Id = id;
        Title = title;
        Description = description;
        Date = date;
        Img = img;
    }

    public ShopeeUpdateDTO() {
    }

    public UUID getId() {
        return Id;
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

    public void setId(UUID id) {
        Id = id;
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
}
