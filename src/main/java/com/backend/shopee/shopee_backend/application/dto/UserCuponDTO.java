package com.backend.shopee.shopee_backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCuponDTO {
    @JsonProperty("id")
    private UUID Id;
    @JsonProperty("cuponId")
    private UUID CuponId;
    @JsonProperty("cuponDTO")
    private CuponDTO CuponDTO;
    @JsonProperty("userId")
    private UUID UserId;
    @JsonProperty("userDTO")
    private UserDTO UserDTO;

    public UserCuponDTO(UUID id, UUID cuponId, CuponDTO cuponDTO, UUID userId, UserDTO userDTO) {
        Id = id;
        CuponId = cuponId;
        CuponDTO = cuponDTO;
        UserId = userId;
        UserDTO = userDTO;
    }

    public UserCuponDTO() {
    }

    public UUID getId() {
        return Id;
    }

    public UUID getCuponId() {
        return CuponId;
    }

    public CuponDTO getCuponDTO() {
        return CuponDTO;
    }

    public UUID getUserId() {
        return UserId;
    }

    public UserDTO getUserDTO() {
        return UserDTO;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setCuponId(UUID cuponId) {
        CuponId = cuponId;
    }

    public void setCuponDTO(CuponDTO cuponDTO) {
        CuponDTO = cuponDTO;
    }

    public void setUserId(UUID userId) {
        UserId = userId;
    }

    public void setUserDTO(UserDTO userDTO) {
        UserDTO = userDTO;
    }
}
