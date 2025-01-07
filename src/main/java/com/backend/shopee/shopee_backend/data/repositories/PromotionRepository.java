package com.backend.shopee.shopee_backend.data.repositories;

import com.backend.shopee.shopee_backend.application.dto.PromotionDTO;
import com.backend.shopee.shopee_backend.data.context.PromotionRepositoryJPA;
import com.backend.shopee.shopee_backend.domain.entities.Promotion;
import com.backend.shopee.shopee_backend.domain.repositories.IPromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PromotionRepository implements IPromotionRepository {
    private final PromotionRepositoryJPA promotionRepositoryJPA;

    @Autowired
    public PromotionRepository(PromotionRepositoryJPA promotionRepositoryJPA) {
        this.promotionRepositoryJPA = promotionRepositoryJPA;
    }

    @Override
    public PromotionDTO GetById(UUID promotionId) {
        return promotionRepositoryJPA.GetById(promotionId);
    }

    @Override
    public Promotion create(Promotion promotion) {
        if(promotion == null)
            return null;

        return promotionRepositoryJPA.save(promotion);
    }

    @Override
    public Promotion update(Promotion promotion) {
        return null;
    }

    @Override
    public Promotion delete(UUID promotionId) {
        if(promotionId == null)
            return null;

        Promotion promotion = promotionRepositoryJPA.findById(promotionId).orElse(null);

        if(promotion == null)
            return null;

        promotionRepositoryJPA.delete(promotion);
//        address.setUser(null);

        return promotion;
    }
}
