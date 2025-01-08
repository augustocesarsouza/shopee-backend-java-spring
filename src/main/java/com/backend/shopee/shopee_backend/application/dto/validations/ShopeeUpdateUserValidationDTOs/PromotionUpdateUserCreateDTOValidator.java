package com.backend.shopee.shopee_backend.application.dto.validations.ShopeeUpdateUserValidationDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PromotionUpdateUserCreateDTOValidator {
    @NotEmpty(message = "shopeeUpdateId should not be empty")
    @Size(min = 36, message = "shopeeUpdateId should have at last 36 characters")
    @JsonProperty("shopeeUpdateId")
    private String ShopeeUpdateId;
    @NotEmpty(message = "userId should not be empty")
    @Size(min = 36, message = "userId should have at last 36 characters")
    @JsonProperty("userId")
    private String UserId;

    public PromotionUpdateUserCreateDTOValidator(String shopeeUpdateId, String userId) {
        ShopeeUpdateId = shopeeUpdateId;
        UserId = userId;
    }

    public PromotionUpdateUserCreateDTOValidator() {
    }

    public String getShopeeUpdateId() {
        return ShopeeUpdateId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setShopeeUpdateId(String shopeeUpdateId) {
        ShopeeUpdateId = shopeeUpdateId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
