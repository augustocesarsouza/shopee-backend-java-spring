package com.backend.shopee.shopee_backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_user_cupons", schema = "public")
public class UserCupon {
    @jakarta.persistence.Id
    @Column(name = "user_cupons_id")
    @JsonProperty("id")
    private UUID Id;
    @Column(name = "cupon_id")
    @JsonProperty("cuponId")
    private UUID CuponId;
    @ManyToOne
    @JoinColumn(name = "cupon_id", insertable = false, updatable = false)
    private Cupon Cupon;
    @Column(name = "user_id")
    @JsonProperty("userId")
    private UUID UserId;
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User User;

    public UserCupon(UUID id, UUID cuponId, Cupon cupon, UUID userId, User user) {
        Id = id;
        CuponId = cuponId;
        Cupon = cupon;
        UserId = userId;
        User = user;
    }

    public UserCupon() {
    }

    public UUID getId() {
        return Id;
    }

    public UUID getCuponId() {
        return CuponId;
    }

    public Cupon getCupon() {
        return Cupon;
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

    public void setCuponId(UUID cuponId) {
        CuponId = cuponId;
    }

    public void setCupon(Cupon cupon) {
        Cupon = cupon;
    }

    public void setUserId(UUID userId) {
        UserId = userId;
    }

    public void setUser(User user) {
        User = user;
    }
}
