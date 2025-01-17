package com.backend.shopee.shopee_backend.domain.repositories;

import com.backend.shopee.shopee_backend.application.dto.ProductDiscoveriesOfDayDTO;
import com.backend.shopee.shopee_backend.domain.entities.ProductDiscoveriesOfDay;

import java.util.List;
import java.util.UUID;

public interface IProductDiscoveriesOfDayRepository {
    ProductDiscoveriesOfDayDTO GetProductDiscoveriesOfDayToDelete(UUID productDiscoveriesOfDayId);
    ProductDiscoveriesOfDayDTO GetProductDiscoveriesOfDayById(UUID productDiscoveriesOfDayId);
    List<ProductDiscoveriesOfDayDTO> GetAllProductDiscoveriesOfDay();
    ProductDiscoveriesOfDay create(ProductDiscoveriesOfDay productDiscoveriesOfDay);
    ProductDiscoveriesOfDay update(ProductDiscoveriesOfDay productDiscoveriesOfDay);
    ProductDiscoveriesOfDay delete(UUID productDiscoveriesOfDayId);
}
