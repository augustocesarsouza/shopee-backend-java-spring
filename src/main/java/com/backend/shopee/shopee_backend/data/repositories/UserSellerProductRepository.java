package com.backend.shopee.shopee_backend.data.repositories;

import com.backend.shopee.shopee_backend.application.dto.UserSellerProductDTO;
import com.backend.shopee.shopee_backend.data.context.UserSellerProductRepositoryJPA;
import com.backend.shopee.shopee_backend.domain.entities.UserSellerProduct;
import com.backend.shopee.shopee_backend.domain.repositories.IUserSellerProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserSellerProductRepository implements IUserSellerProductRepository {
    private final UserSellerProductRepositoryJPA userSellerProductRepositoryJPA;

    @Autowired
    public UserSellerProductRepository(UserSellerProductRepositoryJPA userSellerProductRepositoryJPA) {
        this.userSellerProductRepositoryJPA = userSellerProductRepositoryJPA;
    }

    @Override
    public UserSellerProductDTO GetByUserSellerProductId(UUID userSellerProductId) {
        // CONVERTER O GET AQUI "ZonedDateTime LastLogin" porque tem que transferir de "UTC" para Brazil
        return userSellerProductRepositoryJPA.GetByUserSellerProductId(userSellerProductId);
    }

    @Override
    public UserSellerProduct create(UserSellerProduct userSellerProduct) {
        if(userSellerProduct == null)
            return null;

        return userSellerProductRepositoryJPA.save(userSellerProduct);
    }

    @Override
    public UserSellerProduct update(UserSellerProduct userSellerProduct) {
        return null;
    }

    @Override
    public UserSellerProduct delete(UUID userSellerProductId) {
        if(userSellerProductId == null)
            return null;

        var entity = userSellerProductRepositoryJPA.findById(userSellerProductId).orElse(null);

        if(entity == null)
            return null;

        userSellerProductRepositoryJPA.delete(entity);

        return entity;
    }
}
