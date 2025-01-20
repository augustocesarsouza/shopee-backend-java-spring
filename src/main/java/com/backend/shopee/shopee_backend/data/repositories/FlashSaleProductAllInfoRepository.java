package com.backend.shopee.shopee_backend.data.repositories;

import com.backend.shopee.shopee_backend.application.dto.FlashSaleProductAllInfoDTO;
import com.backend.shopee.shopee_backend.data.context.FlashSaleProductAllInfoJPA;
import com.backend.shopee.shopee_backend.domain.entities.FlashSaleProductAllInfo;
import com.backend.shopee.shopee_backend.domain.repositories.IFlashSaleProductAllInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FlashSaleProductAllInfoRepository implements IFlashSaleProductAllInfoRepository {
    private final FlashSaleProductAllInfoJPA flashSaleProductAllInfoJPA;

    @Autowired
    public FlashSaleProductAllInfoRepository(FlashSaleProductAllInfoJPA flashSaleProductAllInfoJPA) {
        this.flashSaleProductAllInfoJPA = flashSaleProductAllInfoJPA;
    }

    @Override
    public FlashSaleProductAllInfoDTO GetFlashSaleProductByProductFlashSaleId(UUID productFlashSaleId) {
        return flashSaleProductAllInfoJPA.GetFlashSaleProductByProductFlashSaleId(productFlashSaleId);
    }//fazer "tb_product_option_images" que é para ser uma lista de imagem para aquelas imgs

    @Override
    public FlashSaleProductAllInfo create(FlashSaleProductAllInfo flashSaleProductAllInfo) {
        if(flashSaleProductAllInfo == null)
            return null;

        return flashSaleProductAllInfoJPA.save(flashSaleProductAllInfo);
    }

    @Override
    public FlashSaleProductAllInfo update(FlashSaleProductAllInfo flashSaleProductAllInfo) {
        return null;
    }

    @Override
    public FlashSaleProductAllInfo delete(UUID flashSaleProductAllInfoId) {
        if(flashSaleProductAllInfoId == null)
            return null;

        var flashSaleProductAllInfo = flashSaleProductAllInfoJPA.findById(flashSaleProductAllInfoId).orElse(null);

        if(flashSaleProductAllInfo == null)
            return null;

        flashSaleProductAllInfoJPA.delete(flashSaleProductAllInfo);

        return flashSaleProductAllInfo;
    }
}
