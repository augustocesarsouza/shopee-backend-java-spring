package com.backend.shopee.shopee_backend.data.context;

import com.backend.shopee.shopee_backend.application.dto.PromotionDTO;
import com.backend.shopee.shopee_backend.application.dto.ShopeeUpdateDTO;
import com.backend.shopee.shopee_backend.domain.entities.Promotion;
import com.backend.shopee.shopee_backend.domain.entities.ShopeeUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface ShopeeUpdateRepositoryJPA extends JpaRepository<ShopeeUpdate, UUID> {
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "ShopeeUpdateDTO(x.Id, x.Title, x.Description, x.Date, x.Img) " +
            "FROM ShopeeUpdate AS x " +
            "WHERE x.Id = :shopeeUpdateId")
    ShopeeUpdateDTO GetById(UUID shopeeUpdateId);
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "ShopeeUpdateDTO(x.Id, null, null, null, x.Img) " +
            "FROM ShopeeUpdate AS x " +
            "WHERE x.Id = :shopeeUpdateId")
    ShopeeUpdateDTO GetShopeeUpdateToDelete(UUID shopeeUpdateId);
}
//ShopeeUpdateDTO(UUID id, String title, String description, LocalDateTime date, String img)