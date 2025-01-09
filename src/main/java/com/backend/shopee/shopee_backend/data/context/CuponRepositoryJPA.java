package com.backend.shopee.shopee_backend.data.context;

import com.backend.shopee.shopee_backend.application.dto.CuponDTO;
import com.backend.shopee.shopee_backend.domain.entities.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CuponRepositoryJPA extends JpaRepository<Cupon, UUID> {
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "CuponDTO(x.Id, x.FirstText, x.SecondText, x.ThirdText, x.DateValidateCupon, x.QuantityCupons, " +
            "x.WhatCuponNumber, x.SecondImg) " +
            "FROM Cupon AS x " +
            "WHERE x.Id = :cuponId")
    CuponDTO GetCuponById(UUID cuponId);

    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "CuponDTO(x.Id, null, null, null, null, null, " +
            "null, null) " +
            "FROM Cupon AS x " +
            "WHERE x.Id = :cuponId")
    CuponDTO GetCuponByIdToVerifyIfExist(UUID cuponId);
}
//CuponDTO(UUID id, String firstText, String secondText, String thirdText, LocalDateTime dateValidateCupon,
//         Integer quantityCupons, Integer whatCuponNumber, String secondImg)