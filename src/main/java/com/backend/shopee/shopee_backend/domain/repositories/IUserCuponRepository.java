package com.backend.shopee.shopee_backend.domain.repositories;

import com.backend.shopee.shopee_backend.application.dto.UserCuponDTO;
import com.backend.shopee.shopee_backend.domain.entities.UserCupon;

import java.util.List;
import java.util.UUID;

public interface IUserCuponRepository {
    List<UserCuponDTO> GetAllCuponByUserId(UUID userCuponId);
    UserCupon create(UserCupon userCupon);
    UserCupon update(UserCupon userCupon);
    UserCupon delete(UUID userCuponId);
}
