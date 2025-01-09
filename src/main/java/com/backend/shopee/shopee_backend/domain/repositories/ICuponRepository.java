package com.backend.shopee.shopee_backend.domain.repositories;

import com.backend.shopee.shopee_backend.application.dto.CuponDTO;
import com.backend.shopee.shopee_backend.domain.entities.Cupon;

import java.util.UUID;

public interface ICuponRepository {
    CuponDTO GetCuponById(UUID cuponId);
    CuponDTO GetCuponByIdToVerifyIfExist(UUID cuponId);
    Cupon create(Cupon cupon);
    Cupon update(Cupon cupon);
    Cupon delete(UUID cuponId);
}
