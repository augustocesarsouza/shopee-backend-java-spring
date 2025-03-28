package com.backend.shopee.shopee_backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_shopee_updates", schema = "public")
public class ShopeeUpdate {
    @jakarta.persistence.Id
    @Column(name = "shopee_updates_id")
    @JsonProperty("id")
    private UUID Id;
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

    public ShopeeUpdate(UUID id, String title, String description, LocalDateTime date, String img) {
        Id = id;
        Title = title;
        Description = description;
        Date = date;
        Img = img;
    }

    public ShopeeUpdate() {
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
