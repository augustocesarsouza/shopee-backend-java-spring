package com.backend.shopee.shopee_backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLoginDTO {
    @JsonProperty("passwordIsCorrect")
    private Boolean PasswordIsCorrect;
    @JsonProperty("userDTO")
    private UserDTO  UserDTO;

    public UserLoginDTO(Boolean passwordIsCorrect, com.backend.shopee.shopee_backend.application.dto.UserDTO userDTO) {
        PasswordIsCorrect = passwordIsCorrect;
        UserDTO = userDTO;
    }

    public UserLoginDTO() {
    }

    public Boolean getPasswordIsCorrect() {
        return PasswordIsCorrect;
    }

    public UserDTO getUserDTO() {
        return UserDTO;
    }

    public void setPasswordIsCorrect(Boolean passwordIsCorrect) {
        PasswordIsCorrect = passwordIsCorrect;
    }

    public void setUserDTO(com.backend.shopee.shopee_backend.application.dto.UserDTO userDTO) {
        UserDTO = userDTO;
    }
}
