package com.backend.shopee.shopee_backend.data.repositories;

import com.backend.shopee.shopee_backend.application.dto.ShopeeUpdateDTO;
import com.backend.shopee.shopee_backend.data.context.ShopeeUpdateRepositoryJPA;
import com.backend.shopee.shopee_backend.domain.entities.ShopeeUpdate;
import com.backend.shopee.shopee_backend.domain.repositories.IShopeeUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ShopeeUpdateRepository implements IShopeeUpdateRepository {
    private final ShopeeUpdateRepositoryJPA shopeeUpdateRepositoryJPA;

    @Autowired
    public ShopeeUpdateRepository(ShopeeUpdateRepositoryJPA shopeeUpdateRepositoryJPA) {
        this.shopeeUpdateRepositoryJPA = shopeeUpdateRepositoryJPA;
    }

    @Override
    public ShopeeUpdateDTO GetById(UUID shopeeUpdateId) {
        return shopeeUpdateRepositoryJPA.GetById(shopeeUpdateId);
    }

    @Override
    public ShopeeUpdateDTO GetShopeeUpdateToDelete(UUID shopeeUpdateId) {
        return shopeeUpdateRepositoryJPA.GetShopeeUpdateToDelete(shopeeUpdateId);
    }

    @Override
    public ShopeeUpdate create(ShopeeUpdate shopeeUpdate) {
        if(shopeeUpdate == null)
            return null;

        return shopeeUpdateRepositoryJPA.save(shopeeUpdate);
    }

    @Override
    public ShopeeUpdate update(ShopeeUpdate shopeeUpdate) {
        return null;
    }

    @Override
    public ShopeeUpdate delete(UUID shopeeUpdateId) {
        if(shopeeUpdateId == null)
            return null;

        ShopeeUpdate shopeeUpdate = shopeeUpdateRepositoryJPA.findById(shopeeUpdateId).orElse(null);

        if(shopeeUpdate == null)
            return null;

        shopeeUpdateRepositoryJPA.delete(shopeeUpdate);

        return shopeeUpdate;
    }
}
