package com.backend.shopee.shopee_backend.data.repositories;

import com.backend.shopee.shopee_backend.application.dto.ShopeeUpdateUserDTO;
import com.backend.shopee.shopee_backend.data.context.ShopeeUpdateUserRepositoryJPA;
import com.backend.shopee.shopee_backend.domain.entities.ShopeeUpdateUser;
import com.backend.shopee.shopee_backend.domain.repositories.IShopeeUpdateUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ShopeeUpdateUserRepository implements IShopeeUpdateUserRepository {
    private final ShopeeUpdateUserRepositoryJPA shopeeUpdateUserJPA;

    @Autowired
    public ShopeeUpdateUserRepository(ShopeeUpdateUserRepositoryJPA shopeeUpdateUserJPA) {
        this.shopeeUpdateUserJPA = shopeeUpdateUserJPA;
    }

    @Override
    public List<ShopeeUpdateUserDTO> GetByUserIdAll(UUID userId) {
        return shopeeUpdateUserJPA.GetByUserIdAll(userId);
    }

    @Override
    public List<ShopeeUpdateUserDTO> GetPromotionUserByShopeeUpdateId(UUID shopeeUpdateId) {
        return shopeeUpdateUserJPA.GetPromotionUserByShopeeUpdateId(shopeeUpdateId);
    }

    @Override
    public ShopeeUpdateUser create(ShopeeUpdateUser shopeeUpdateUser) {
        if(shopeeUpdateUser == null)
            return null;

        return shopeeUpdateUserJPA.save(shopeeUpdateUser);
    }

    @Override
    public ShopeeUpdateUser update(ShopeeUpdateUser shopeeUpdateUser) {
        return null;
    }

    @Override
    public ShopeeUpdateUser delete(UUID shopeeUpdateUserId) {
        if(shopeeUpdateUserId == null)
            return null;

        var shopeeUpdateUser = shopeeUpdateUserJPA.findById(shopeeUpdateUserId).orElse(null);

        if(shopeeUpdateUser == null)
            return null;

        shopeeUpdateUserJPA.delete(shopeeUpdateUser);

        return shopeeUpdateUser;
    }
}
