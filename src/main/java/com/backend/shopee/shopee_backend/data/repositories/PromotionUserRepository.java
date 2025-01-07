package com.backend.shopee.shopee_backend.data.repositories;

import com.backend.shopee.shopee_backend.application.dto.PromotionUserDTO;
import com.backend.shopee.shopee_backend.data.context.PromotionUserRepositoryJPA;
import com.backend.shopee.shopee_backend.domain.entities.PromotionUser;
import com.backend.shopee.shopee_backend.domain.repositories.IPromotionUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PromotionUserRepository implements IPromotionUserRepository {
    private final PromotionUserRepositoryJPA promotionUserRepositoryJPA;

    @Autowired
    public PromotionUserRepository(PromotionUserRepositoryJPA promotionUserRepositoryJPA) {
        this.promotionUserRepositoryJPA = promotionUserRepositoryJPA;
    }

    @Override
    public List<PromotionUserDTO> GetById(UUID userId) {
        return this.promotionUserRepositoryJPA.GetById(userId);
    }

    @Override
    public List<PromotionUserDTO> GetByUserIdAll(UUID userId) {
        return this.promotionUserRepositoryJPA.GetByUserIdAll(userId);
    }

    @Override
    public List<PromotionUserDTO> GetPromotionUserByPromotionId(UUID promotionId) {
        return this.promotionUserRepositoryJPA.GetPromotionUserByPromotionId(promotionId);
    }

    @Override
    public PromotionUser create(PromotionUser promotionUserDTO) {
        if(promotionUserDTO == null)
            return null;

        return promotionUserRepositoryJPA.save(promotionUserDTO);
    }

    @Override
    public PromotionUser update(PromotionUser promotionUser) {
        return null;
    }

    @Override
    public PromotionUser delete(UUID promotionUserId) {
        if(promotionUserId == null)
            return null;

        PromotionUser promotionUser = promotionUserRepositoryJPA.findById(promotionUserId).orElse(null);

        if(promotionUser == null)
            return null;

        promotionUserRepositoryJPA.delete(promotionUser);

        return promotionUser;
    }
}
