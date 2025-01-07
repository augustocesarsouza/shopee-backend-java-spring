package com.backend.shopee.shopee_backend.domain.repositories;

import com.backend.shopee.shopee_backend.application.dto.PromotionDTO;
import com.backend.shopee.shopee_backend.domain.entities.Address;
import com.backend.shopee.shopee_backend.domain.entities.Promotion;

import java.util.UUID;

public interface IPromotionRepository {
    PromotionDTO GetById(UUID promotionId);
    Promotion create(Promotion promotion);
    Promotion update(Promotion promotion);
    Promotion delete(UUID promotionId);
}
