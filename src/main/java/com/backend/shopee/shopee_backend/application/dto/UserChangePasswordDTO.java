package com.backend.shopee.shopee_backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserChangePasswordDTO {
    @NotEmpty(message = "phone should not be empty")
    @JsonProperty("phone")
    private String Phone;
    @NotEmpty(message = "confirmPassword should not be empty")
    @JsonProperty("confirmPassword")
    private String ConfirmPassword;

    public UserChangePasswordDTO(String phone, String confirmPassword) {
        Phone = phone;
        ConfirmPassword = confirmPassword;
    }

    public UserChangePasswordDTO() {
    }

    public String getPhone() {
        return Phone;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }
}
