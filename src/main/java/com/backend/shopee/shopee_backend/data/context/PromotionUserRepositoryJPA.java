package com.backend.shopee.shopee_backend.data.context;

import com.backend.shopee.shopee_backend.application.dto.PromotionDTO;
import com.backend.shopee.shopee_backend.application.dto.PromotionUserDTO;
import com.backend.shopee.shopee_backend.application.dto.UserDTO;
import com.backend.shopee.shopee_backend.domain.entities.PromotionUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PromotionUserRepositoryJPA extends JpaRepository<PromotionUser, UUID> {
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "PromotionUserDTO(null, null, new com.backend.shopee.shopee_backend.application.dto.PromotionDTO" +
            "(null, x.Promotion.WhatIsThePromotion, x.Promotion.Title, x.Promotion.Description, x.Promotion.Date, x.Promotion.Img, null), null, null) " +
            "FROM PromotionUser AS x " +
            "WHERE x.UserId = :userId AND x.Promotion.WhatIsThePromotion = 1")
    List<PromotionUserDTO> GetById(UUID userId);

    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "PromotionUserDTO(null, null, new com.backend.shopee.shopee_backend.application.dto.PromotionDTO" +
            "(null, x.Promotion.WhatIsThePromotion, x.Promotion.Title, x.Promotion.Description, x.Promotion.Date, " +
            "x.Promotion.Img, x.Promotion.ListImgInner), null, null) " +
            "FROM PromotionUser AS x " +
            "WHERE x.UserId = :userId")
    List<PromotionUserDTO> GetByUserIdAll(UUID userId);

    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "PromotionUserDTO(x.Id, x.PromotionId, null, null, null) " +
            "FROM PromotionUser AS x " +
            "WHERE x.PromotionId = :promotionId")
    List<PromotionUserDTO> GetPromotionUserByPromotionId(UUID promotionId);
}
//PromotionUserDTO(UUID id, UUID promotionId, PromotionDTO promotionDTO, UUID userId, UserDTO userDTO)
//PromotionDTO(UUID id, Integer whatIsThePromotion, String title, String description, LocalDateTime date, String img, List<String> listImgInner)