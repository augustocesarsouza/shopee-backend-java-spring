package com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserUpdateFillDTOValidator {
    @NotEmpty(message = "idGuid should not be empty")
    @Size(min = 36, message = "idGuid should have at last 36 characters")
    @JsonProperty("userId")
    private String UserId;
    @NotEmpty(message = "cpf should not be empty")
    @JsonProperty("cpf")
    private String Cpf;
    @NotEmpty(message = "birthDate should not be empty")
    @JsonProperty("birthDate")
    private String BirthDate;

    public UserUpdateFillDTOValidator(String userId, String cpf, String birthDate) {
        UserId = userId;
        Cpf = cpf;
        BirthDate = birthDate;
    }

    public UserUpdateFillDTOValidator() {
    }

    public String getUserId() {
        return UserId;
    }

    public String getCpf() {
        return Cpf;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }
}
