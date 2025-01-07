package com.backend.shopee.shopee_backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PromotionUserDTO {
    @JsonProperty("id")
    private UUID Id;
    @JsonProperty("promotionId")
    private UUID PromotionId;
    @JsonProperty("promotionDTO")
    private PromotionDTO PromotionDTO;
    @JsonProperty("userId")
    private UUID UserId;
    @JsonProperty("userDTO")
    private UserDTO UserDTO;

    public PromotionUserDTO(UUID id, UUID promotionId, PromotionDTO promotionDTO, UUID userId, UserDTO userDTO) {
        Id = id;
        PromotionId = promotionId;
        PromotionDTO = promotionDTO;
        UserId = userId;
        UserDTO = userDTO;
    }

    public PromotionUserDTO() {
    }

    public UUID getId() {
        return Id;
    }

    public UUID getPromotionId() {
        return PromotionId;
    }

    public PromotionDTO getPromotionDTO() {
        return PromotionDTO;
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

    public void setPromotionId(UUID promotionId) {
        PromotionId = promotionId;
    }

    public void setPromotionDTO(PromotionDTO promotionDTO) {
        PromotionDTO = promotionDTO;
    }

    public void setUserId(UUID userId) {
        UserId = userId;
    }

    public void setUserDTO(UserDTO userDTO) {
        UserDTO = userDTO;
    }
}
