package com.backend.shopee.shopee_backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShopeeUpdateUserDTO {
    @JsonProperty("id")
    private UUID Id;
    @JsonProperty("shopeeUpdateId")
    private UUID ShopeeUpdateId;
    @JsonProperty("shopeeUpdateDTO")
    private ShopeeUpdateDTO ShopeeUpdateDTO;
    @JsonProperty("userId")
    private UUID UserId;
    @JsonProperty("userDTO")
    private UserDTO UserDTO;

    public ShopeeUpdateUserDTO(UUID id, UUID shopeeUpdateId, ShopeeUpdateDTO shopeeUpdateDTO, UUID userId, UserDTO userDTO) {
        Id = id;
        ShopeeUpdateId = shopeeUpdateId;
        ShopeeUpdateDTO = shopeeUpdateDTO;
        UserId = userId;
        UserDTO = userDTO;
    }

    public ShopeeUpdateUserDTO() {
    }

    public UUID getId() {
        return Id;
    }

    public UUID getShopeeUpdateId() {
        return ShopeeUpdateId;
    }

    public ShopeeUpdateDTO getShopeeUpdateDTO() {
        return ShopeeUpdateDTO;
    }

    public UUID getUserId() {
        return UserId;
    }

    public UserDTO getUserDTO() {
        return UserDTO;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setShopeeUpdateId(UUID shopeeUpdateId) {
        ShopeeUpdateId = shopeeUpdateId;
    }

    public void setShopeeUpdateDTO(ShopeeUpdateDTO shopeeUpdateDTO) {
        ShopeeUpdateDTO = shopeeUpdateDTO;
    }

    public void setUserId(UUID userId) {
        UserId = userId;
    }

    public void setUserDTO(UserDTO userDTO) {
        UserDTO = userDTO;
    }
}
