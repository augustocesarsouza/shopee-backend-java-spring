package com.backend.shopee.shopee_backend.data.context;

import com.backend.shopee.shopee_backend.application.dto.AddressDTO;
import com.backend.shopee.shopee_backend.application.dto.PromotionDTO;
import com.backend.shopee.shopee_backend.domain.entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PromotionRepositoryJPA extends JpaRepository<Promotion, UUID> {
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "PromotionDTO(x.Id, x.WhatIsThePromotion, x.Title, x.Description, x.Date, x.Img, x.ListImgInner) " +
            "FROM Promotion AS x " +
            "WHERE x.Id = :promotionId")
    PromotionDTO GetById(UUID promotionId);
}
//PromotionDTO(UUID id, Integer whatIsThePromotion, String title, String description, LocalDateTime date, String img, List<String> listImgInner)