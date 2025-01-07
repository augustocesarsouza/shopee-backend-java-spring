package com.backend.shopee.shopee_backend.application.dto.validations.PromotionUserValidationDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PromotionUserCreateDTOValidator {
    @NotEmpty(message = "promotionId should not be empty")
    @Size(min = 36, message = "promotionId should have at last 36 characters")
    @JsonProperty("promotionId")
    private String PromotionId;
    @NotEmpty(message = "userId should not be empty")
    @Size(min = 36, message = "userId should have at last 36 characters")
    @JsonProperty("userId")
    private String UserId;

    public PromotionUserCreateDTOValidator(String promotionId, String userId) {
        PromotionId = promotionId;
        UserId = userId;
    }

    public PromotionUserCreateDTOValidator() {
    }

    public String getPromotionId() {
        return PromotionId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setPromotionId(String promotionId) {
        PromotionId = promotionId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
