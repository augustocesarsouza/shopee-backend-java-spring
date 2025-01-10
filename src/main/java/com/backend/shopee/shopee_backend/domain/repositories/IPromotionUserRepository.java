package com.backend.shopee.shopee_backend.domain.repositories;

import com.backend.shopee.shopee_backend.application.dto.PromotionUserDTO;
import com.backend.shopee.shopee_backend.domain.entities.PromotionUser;

import java.util.List;
import java.util.UUID;

public interface IPromotionUserRepository {
    List<PromotionUserDTO> GetById(UUID userId);
    List<PromotionUserDTO> GetByUserIdAll(UUID userId);
    List<PromotionUserDTO> GetPromotionUserByPromotionId(UUID promotionId);
    PromotionUser create(PromotionUser promotionUser);
    PromotionUser update(PromotionUser promotionUser);
    PromotionUser delete(UUID promotionUserId);
}
