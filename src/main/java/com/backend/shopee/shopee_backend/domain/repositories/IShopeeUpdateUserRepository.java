package com.backend.shopee.shopee_backend.domain.repositories;

import com.backend.shopee.shopee_backend.application.dto.ShopeeUpdateUserDTO;
import com.backend.shopee.shopee_backend.domain.entities.ShopeeUpdateUser;

import java.util.List;
import java.util.UUID;

public interface IShopeeUpdateUserRepository {
    List<ShopeeUpdateUserDTO> GetByUserIdAll(UUID userId);
    List<ShopeeUpdateUserDTO> GetPromotionUserByShopeeUpdateId(UUID shopeeUpdateId);
    ShopeeUpdateUser create(ShopeeUpdateUser shopeeUpdateUser);
    ShopeeUpdateUser update(ShopeeUpdateUser shopeeUpdateUser);
    ShopeeUpdateUser delete(UUID shopeeUpdateUserId);
}
