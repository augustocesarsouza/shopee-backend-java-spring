package com.backend.shopee.shopee_backend.data.context;

import com.backend.shopee.shopee_backend.application.dto.UserCuponDTO;
import com.backend.shopee.shopee_backend.domain.entities.UserCupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserCuponRepositoryJPA extends JpaRepository<UserCupon, UUID> {
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "UserCuponDTO(null, null, new com.backend.shopee.shopee_backend.application.dto.CuponDTO" +
            "(x.Cupon.Id, x.Cupon.FirstText, x.Cupon.SecondText, x.Cupon.ThirdText, x.Cupon.DateValidateCupon, " +
            "x.Cupon.QuantityCupons, x.Cupon.WhatCuponNumber, x.Cupon.SecondImg), null, null) " +
            "FROM UserCupon AS x " +
            "WHERE x.UserId = :userId")
    List<UserCuponDTO> GetAllCuponByUserId(UUID userId);
}
//UserCuponDTO(UUID id, UUID cuponId, CuponDTO cuponDTO, UUID userId, UserDTO userDTO)
//CuponDTO(UUID id, String firstText, String secondText, String thirdText, LocalDateTime dateValidateCupon,
//         Integer quantityCupons, Integer whatCuponNumber, String secondImg)