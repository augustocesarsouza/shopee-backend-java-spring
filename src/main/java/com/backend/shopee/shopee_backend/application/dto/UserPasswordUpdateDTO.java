package com.backend.shopee.shopee_backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPasswordUpdateDTO {
    @JsonProperty("passwordUpdateSuccessfully")
    private Boolean PasswordUpdateSuccessfully;

    public UserPasswordUpdateDTO(Boolean passwordUpdateSuccessfully) {
        PasswordUpdateSuccessfully = passwordUpdateSuccessfully;
    }

    public UserPasswordUpdateDTO() {
    }

    public Boolean getPasswordUpdateSuccessfully() {
        return PasswordUpdateSuccessfully;
    }
}
