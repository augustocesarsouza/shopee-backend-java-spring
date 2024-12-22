package com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserConfirmCodeEmailValidatorDTO {
    @NotEmpty(message = "Code should not be empty")
    @JsonProperty("code")
    private String Code;
    @NotEmpty(message = "idGuid should not be empty")
    @Size(min = 36, message = "idGuid should have at last 36 characters")
    @JsonProperty("userId")
    private String UserId;
    @NotEmpty(message = "Email should not be empty")
    @JsonProperty("email")
    private String Email;

    public UserConfirmCodeEmailValidatorDTO(String code, String userId, String email) {
        Code = code;
        UserId = userId;
        Email = email;
    }

    public UserConfirmCodeEmailValidatorDTO() {
    }

    public String getCode() {
        return Code;
    }

    public String getUserId() {
        return UserId;
    }

    public String getEmail() {
        return Email;
    }
}
