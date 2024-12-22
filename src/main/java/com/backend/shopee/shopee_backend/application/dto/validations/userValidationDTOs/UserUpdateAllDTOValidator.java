package com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserUpdateAllDTOValidator {
    @NotEmpty(message = "idGuid should not be empty")
    @Size(min = 36, message = "idGuid should have at last 36 characters")
    @JsonProperty("userId")
    private String UserId;
    @NotEmpty(message = "name should not be empty")
    @JsonProperty("name")
    private String Name;
    @NotEmpty(message = "email should not be empty")
    @JsonProperty("email")
    private String Email;
    @NotEmpty(message = "gender should not be empty")
    @JsonProperty("gender")
    private String Gender;
    @NotEmpty(message = "phone should not be empty")
    @JsonProperty("phone")
    private String Phone;
    @JsonProperty("cpf")
    private String Cpf;
    @JsonProperty("birthDate")
    private LocalDateTime BirthDate;
    @JsonProperty("base64StringImage")
    private String Base64StringImage;

    public UserUpdateAllDTOValidator(String userId, String name, String email, String gender, String phone, String cpf, LocalDateTime birthDate, String base64StringImage) {
        UserId = userId;
        Name = name;
        Email = email;
        Gender = gender;
        Phone = phone;
        Cpf = cpf;
        BirthDate = birthDate;
        Base64StringImage = base64StringImage;
    }

    public UserUpdateAllDTOValidator() {
    }

    public String getUserId() {
        return UserId;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getGender() {
        return Gender;
    }

    public String getPhone() {
        return Phone;
    }

    public String getCpf() {
        return Cpf;
    }

    public LocalDateTime getBirthDate() {
        return BirthDate;
    }

    public String getBase64StringImage() {
        return Base64StringImage;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        BirthDate = birthDate;
    }

    public void setBase64StringImage(String base64StringImage) {
        Base64StringImage = base64StringImage;
    }
}
