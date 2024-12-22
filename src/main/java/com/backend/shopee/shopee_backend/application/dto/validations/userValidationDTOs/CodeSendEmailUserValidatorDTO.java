package com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeSendEmailUserValidatorDTO {
    @NotEmpty(message = "Name should not be empty")
    @JsonProperty("name")
    private String Name;
    @NotEmpty(message = "Email should not be empty")
    @JsonProperty("email")
    private String Email;
    private String Code;
    private Boolean CodeSendToEmailSuccessfully;
    private Boolean UserAlreadyExist;

    public CodeSendEmailUserValidatorDTO(String name, String email, String code, Boolean codeSendToEmailSuccessfully, Boolean userAlreadyExist) {
        Name = name;
        Email = email;
        Code = code;
        CodeSendToEmailSuccessfully = codeSendToEmailSuccessfully;
        UserAlreadyExist = userAlreadyExist;
    }

    public CodeSendEmailUserValidatorDTO() {
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getCode() {
        return Code;
    }

    public Boolean getCodeSendToEmailSuccessfully() {
        return CodeSendToEmailSuccessfully;
    }

    public Boolean getUserAlreadyExist() {
        return UserAlreadyExist;
    }
}
