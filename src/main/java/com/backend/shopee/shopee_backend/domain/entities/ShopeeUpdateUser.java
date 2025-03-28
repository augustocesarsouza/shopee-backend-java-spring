package com.backend.shopee.shopee_backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_java_shopee_shopee_update_users", schema = "public")
public class ShopeeUpdateUser {
    @jakarta.persistence.Id
    @Column(name = "shopee_update_users_id")
    @JsonProperty("id")
    private UUID Id;
    @Column(name = "shopee_updates_id")
    @JsonProperty("shopeeUpdateId")
    private UUID ShopeeUpdateId;
    @ManyToOne
    @JoinColumn(name = "shopee_updates_id", insertable = false, updatable = false)
    private ShopeeUpdate ShopeeUpdate;
    @Column(name = "user_id")
    @JsonProperty("userId")
    private UUID UserId;
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User User;

    public ShopeeUpdateUser(UUID id, UUID shopeeUpdateId, ShopeeUpdate shopeeUpdate, UUID userId, User user) {
        Id = id;
        ShopeeUpdateId = shopeeUpdateId;
        ShopeeUpdate = shopeeUpdate;
        UserId = userId;
        User = user;
    }

    public ShopeeUpdateUser() {
    }

    public UUID getId() {
        return Id;
    }

    public UUID getShopeeUpdateId() {
        return ShopeeUpdateId;
    }

    public ShopeeUpdate getShopeeUpdate() {
        return ShopeeUpdate;
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

    public void setShopeeUpdateId(UUID shopeeUpdateId) {
        ShopeeUpdateId = shopeeUpdateId;
    }

    public void setShopeeUpdate(ShopeeUpdate shopeeUpdate) {
        ShopeeUpdate = shopeeUpdate;
    }

    public void setUserId(UUID userId) {
        UserId = userId;
    }

    public void setUser(User user) {
        User = user;
    }
}
