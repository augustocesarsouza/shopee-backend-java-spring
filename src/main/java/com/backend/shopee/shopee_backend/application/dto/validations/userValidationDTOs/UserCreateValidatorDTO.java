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
    @NotEmpty(message = "phone should not be empty")
    @JsonProperty("phone")
    private String Phone;
    @NotEmpty(message = "password should not be empty")
    @Size(min = 8, message = "password should have at last 8 characters")
    @JsonProperty("password")
    private String Password;

    @JsonProperty("base64ImageUser")
    private String Base64ImageUser;

    public UserCreateValidatorDTO() {
    }

    public UserCreateValidatorDTO(UUID id, String phone, String password, String base64ImageUser) {
        Id = id;
        Phone = phone;
        Password = password;
        Base64ImageUser = base64ImageUser;
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
