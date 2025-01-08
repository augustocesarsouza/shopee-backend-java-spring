package com.backend.shopee.shopee_backend.data.context;

import com.backend.shopee.shopee_backend.application.dto.ShopeeUpdateUserDTO;
import com.backend.shopee.shopee_backend.domain.entities.ShopeeUpdateUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ShopeeUpdateUserRepositoryJPA extends JpaRepository<ShopeeUpdateUser, UUID> {
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "ShopeeUpdateUserDTO(null, null, new com.backend.shopee.shopee_backend.application.dto.ShopeeUpdateDTO" +
            "(null, x.ShopeeUpdate.Title, x.ShopeeUpdate.Description, x.ShopeeUpdate.Date, x.ShopeeUpdate.Img), null, null) " +
            "FROM ShopeeUpdateUser AS x " +
            "WHERE x.UserId = :userId")
    List<ShopeeUpdateUserDTO> GetByUserIdAll(UUID userId);

    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "ShopeeUpdateUserDTO(null, null, null, null, null) " +
            "FROM ShopeeUpdateUser AS x " +
            "WHERE x.ShopeeUpdateId = :shopeeUpdateId")
    List<ShopeeUpdateUserDTO> GetPromotionUserByShopeeUpdateId(UUID shopeeUpdateId);
}
//ShopeeUpdateUserDTO(UUID id, UUID shopeeUpdateId, ShopeeUpdateDTO shopeeUpdateDTO, UUID userId, UserDTO userDTO)
//ShopeeUpdateDTO(UUID id, String title, String description, LocalDateTime date, String img)