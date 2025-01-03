package com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeSendPhoneDTOValidator {
    @NotEmpty(message = "Name should not be empty")
    @JsonProperty("phone")
    private String Phone;
    @NotEmpty(message = "UserId should not be empty")
    @JsonProperty("userId")
    private String UserId;
    private String Code;
    private Boolean CodeSendToPhone;
    private Boolean PhoneAlreadyRegistered;

    public CodeSendPhoneDTOValidator(String phone, String code, Boolean codeSendToPhone, Boolean phoneAlreadyRegistered) {
        Phone = phone;
        Code = code;
        CodeSendToPhone = codeSendToPhone;
        PhoneAlreadyRegistered = phoneAlreadyRegistered;
    }

    public CodeSendPhoneDTOValidator() {
    }

    public String getPhone() {
        return Phone;
    }

    public String getCode() {
        return Code;
    }

    public Boolean getCodeSendToPhone() {
        return CodeSendToPhone;
    }

    public Boolean getPhoneAlreadyRegistered() {
        return PhoneAlreadyRegistered;
    }

    public String getUserId() {
        return UserId;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setCode(String code) {
        Code = code;
    }

    public void setCodeSendToPhone(Boolean codeSendToPhone) {
        CodeSendToPhone = codeSendToPhone;
    }

    public void setPhoneAlreadyRegistered(Boolean phoneAlreadyRegistered) {
        PhoneAlreadyRegistered = phoneAlreadyRegistered;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
