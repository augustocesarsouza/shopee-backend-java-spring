package com.backend.shopee.shopee_backend.domain.repositories;

import com.backend.shopee.shopee_backend.application.dto.ShopeeUpdateDTO;
import com.backend.shopee.shopee_backend.domain.entities.ShopeeUpdate;

import java.util.UUID;

public interface IShopeeUpdateRepository {
    ShopeeUpdateDTO GetById(UUID shopeeUpdateId);
    ShopeeUpdateDTO GetShopeeUpdateToDelete(UUID shopeeUpdateId);
    ShopeeUpdate create(ShopeeUpdate shopeeUpdate);
    ShopeeUpdate update(ShopeeUpdate shopeeUpdate);
    ShopeeUpdate delete(UUID shopeeUpdateId);
}
