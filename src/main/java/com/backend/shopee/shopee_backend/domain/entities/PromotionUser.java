package com.backend.shopee.shopee_backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_promotion_users", schema = "public")
public class PromotionUser {
    @jakarta.persistence.Id
    @Column(name = "promotion_users_id")
    @JsonProperty("id")
    private UUID Id;
    @Column(name = "promotion_id")
    @JsonProperty("promotionId")
    private UUID PromotionId;
    @ManyToOne
    @JoinColumn(name = "promotion_id", insertable = false, updatable = false)
    private Promotion Promotion;
    @Column(name = "user_id")
    @JsonProperty("userId")
    private UUID UserId;
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User User;

    public PromotionUser(UUID id, UUID promotionId, Promotion promotion, UUID userId, User user) {
        Id = id;
        PromotionId = promotionId;
        Promotion = promotion;
        UserId = userId;
        User = user;
    }

    public PromotionUser() {
    }

    public UUID getId() {
        return Id;
    }

    public UUID getPromotionId() {
        return PromotionId;
    }

    public Promotion getPromotion() {
        return Promotion;
    }

    public UUID getUserId() {
        return UserId;
    }

    public User getUser() {
        return User;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setPromotionId(UUID promotionId) {
        PromotionId = promotionId;
    }

    public void setPromotion(Promotion promotion) {
        Promotion = promotion;
    }

    public void setUserId(UUID userId) {
        UserId = userId;
    }

    public void setUser(User user) {
        User = user;
    }
}
