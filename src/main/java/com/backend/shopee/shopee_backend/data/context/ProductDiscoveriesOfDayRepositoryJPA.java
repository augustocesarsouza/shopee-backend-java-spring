package com.backend.shopee.shopee_backend.data.context;

import com.backend.shopee.shopee_backend.application.dto.CategoriesDTO;
import com.backend.shopee.shopee_backend.application.dto.ProductDiscoveriesOfDayDTO;
import com.backend.shopee.shopee_backend.domain.entities.ProductDiscoveriesOfDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductDiscoveriesOfDayRepositoryJPA extends JpaRepository<ProductDiscoveriesOfDay, UUID> {
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "ProductDiscoveriesOfDayDTO(x.Id, null, x.ImgProduct, null, null, null, " +
            "null, null) " +
            "FROM ProductDiscoveriesOfDay AS x " +
            "WHERE x.Id = :productDiscoveriesOfDayId")
    ProductDiscoveriesOfDayDTO GetProductDiscoveriesOfDayToDelete(UUID productDiscoveriesOfDayId);
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "ProductDiscoveriesOfDayDTO(x.Id, x.Title, x.ImgProduct, x.ImgPartBottom, x.DiscountPercentage, x.IsAd, " +
            "x.Price, x.QuantitySold) " +
            "FROM ProductDiscoveriesOfDay AS x " +
            "WHERE x.Id = :productDiscoveriesOfDayId")
    ProductDiscoveriesOfDayDTO GetProductDiscoveriesOfDayById(UUID productDiscoveriesOfDayId);

    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "ProductDiscoveriesOfDayDTO(x.Id, x.Title, x.ImgProduct, x.ImgPartBottom, x.DiscountPercentage, x.IsAd, " +
            "x.Price, x.QuantitySold) " +
            "FROM ProductDiscoveriesOfDay AS x")
    List<ProductDiscoveriesOfDayDTO> GetAllProductDiscoveriesOfDay();
}
