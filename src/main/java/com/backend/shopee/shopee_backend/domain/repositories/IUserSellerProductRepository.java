package com.backend.shopee.shopee_backend.domain.repositories;

import com.backend.shopee.shopee_backend.application.dto.UserSellerProductDTO;
import com.backend.shopee.shopee_backend.domain.entities.UserSellerProduct;

import java.util.UUID;

public interface IUserSellerProductRepository {
    UserSellerProductDTO GetByUserSellerProductId(UUID userSellerProductId);
    UserSellerProduct create(UserSellerProduct userSellerProduct);
    UserSellerProduct update(UserSellerProduct userSellerProduct);
    UserSellerProduct delete(UUID userSellerProductId);
}
