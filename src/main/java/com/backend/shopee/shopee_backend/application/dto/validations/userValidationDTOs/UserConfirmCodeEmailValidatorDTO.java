package com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserConfirmCodeEmailValidatorDTO {
    @NotEmpty(message = "Code should not be empty")
    @JsonProperty("code")
    private String Code;
    @NotEmpty(message = "UserId should not be empty")
    @JsonProperty("userId")
    private UUID UserId;
    @NotEmpty(message = "Email should not be empty")
    @JsonProperty("email")
    private String Email;

    public UserConfirmCodeEmailValidatorDTO(String code, UUID userId, String email) {
        Code = code;
        UserId = userId;
        Email = email;
    }

    public UserConfirmCodeEmailValidatorDTO() {
    }

    public String getCode() {
        return Code;
    }

    public UUID getUserId() {
        return UserId;
    }

    public String getEmail() {
        return Email;
    }
}
