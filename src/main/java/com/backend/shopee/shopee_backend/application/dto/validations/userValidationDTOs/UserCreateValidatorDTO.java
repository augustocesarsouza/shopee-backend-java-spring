package com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCreateValidatorDTO {
    @JsonProperty("id")
    private UUID Id;
    @NotEmpty(message = "name should not be empty")
    @JsonProperty("phone")
    private String Phone;
    @NotEmpty(message = "email should not be empty")
    @Size(min = 8, message = "email should have at last 5 characters")
    @JsonProperty("password")
    private String Password;

    @JsonProperty("base64ImageUser")
    private String Base64ImageUser;

    public UserCreateValidatorDTO() {
    }

    public UUID getId() {
        return Id;
    }

    public String getPhone() {
        return Phone;
    }

    public String getPassword() {
        return Password;
    }

    public String getBase64ImageUser() {
        return Base64ImageUser;
    }
}
