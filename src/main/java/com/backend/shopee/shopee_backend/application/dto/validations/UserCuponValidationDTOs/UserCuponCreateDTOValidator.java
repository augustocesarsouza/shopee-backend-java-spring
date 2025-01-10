package com.backend.shopee.shopee_backend.application.dto.validations.UserCuponValidationDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCuponCreateDTOValidator {
    @NotEmpty(message = "promotionId should not be empty")
    @Size(min = 36, message = "promotionId should have at last 36 characters")
    @JsonProperty("cuponId")
    private String CuponId;
    @NotEmpty(message = "userId should not be empty")
    @Size(min = 36, message = "userId should have at last 36 characters")
    @JsonProperty("userId")
    private String UserId;

    public UserCuponCreateDTOValidator(String cuponId, String userId) {
        CuponId = cuponId;
        UserId = userId;
    }

    public UserCuponCreateDTOValidator() {
    }

    public String getCuponId() {
        return CuponId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setCuponId(String cuponId) {
        CuponId = cuponId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
