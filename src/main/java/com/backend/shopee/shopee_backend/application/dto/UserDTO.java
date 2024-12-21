package com.backend.shopee.shopee_backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    @JsonProperty("id")
    private UUID Id;
    @JsonProperty("name")
    private String Name;
    @JsonProperty("email")
    private String Email;
    @JsonProperty("phone")
    private String Phone;
    @JsonProperty("passwordHash")
    private String PasswordHash;
    @JsonProperty("cpf")
    private String Cpf;
    @JsonProperty("birthDate")
    private LocalDateTime BirthDate;
    @JsonProperty("confirmEmail")
    private Short ConfirmEmail; // se 0 false, 1 true
    @JsonProperty("userImage")
    private String UserImage;
    @JsonProperty("base64ImageUser")
    private String Base64ImageUser;
    @JsonProperty("token")
    private String Token;

    public UserDTO() {
    }

    public UserDTO(UUID id, String name, String email, String passwordHash, String cpf,
                   LocalDateTime birthDate, Short confirmEmail, String userImage, String base64ImageUser, String token) {
        Id = id;
        Name = name;
        Email = email;
        PasswordHash = passwordHash;
        Cpf = cpf;
        BirthDate = birthDate;
        ConfirmEmail = confirmEmail;
        UserImage = userImage;
        Base64ImageUser = base64ImageUser;
        Token = token;
    }

    public UUID getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPasswordHash() {
        return PasswordHash;
    }

    public String getCpf() {
        return Cpf;
    }

    public LocalDateTime getBirthDate() {
        return BirthDate;
    }

    public Short getConfirmEmail() {
        return ConfirmEmail;
    }

    public String getUserImage() {
        return UserImage;
    }

    public String getBase64ImageUser() {
        return Base64ImageUser;
    }

    public String getToken() {
        return Token;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPasswordHash(String passwordHash) {
        PasswordHash = passwordHash;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        BirthDate = birthDate;
    }

    public void setConfirmEmail(Short confirmEmail) {
        ConfirmEmail = confirmEmail;
    }

    public void setUserImage(String userImage) {
        UserImage = userImage;
    }

    public void setBase64ImageUser(String base64ImageUser) {
        Base64ImageUser = base64ImageUser;
    }

    public void setToken(String token) {
        Token = token;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPhone() {
        return Phone;
    }
}
