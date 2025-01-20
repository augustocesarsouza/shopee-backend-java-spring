package com.backend.shopee.shopee_backend.domain.repositories;

import com.backend.shopee.shopee_backend.application.dto.FlashSaleProductAllInfoDTO;
import com.backend.shopee.shopee_backend.domain.entities.FlashSaleProductAllInfo;

import java.util.UUID;

public interface IFlashSaleProductAllInfoRepository {
    FlashSaleProductAllInfoDTO GetFlashSaleProductByProductFlashSaleId(UUID productFlashSaleId);
    FlashSaleProductAllInfo create(FlashSaleProductAllInfo flashSaleProductAllInfo);
    FlashSaleProductAllInfo update(FlashSaleProductAllInfo flashSaleProductAllInfo);
    FlashSaleProductAllInfo delete(UUID flashSaleProductAllInfoId);
}
