package com.backend.shopee.shopee_backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserChangePasswordDTO {
    @JsonProperty("phone")
    private String Phone;
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
