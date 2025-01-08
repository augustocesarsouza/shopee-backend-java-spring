package com.backend.shopee.shopee_backend.api.controllers;

import com.backend.shopee.shopee_backend.application.dto.PromotionDTO;
import com.backend.shopee.shopee_backend.application.dto.ShopeeUpdateDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.PromotionValidationDTOs.PromotionCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.ShopeeUpdateValidationDTOs.ShopeeUpdateCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import com.backend.shopee.shopee_backend.application.services.ShopeeUpdateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Component
@RestController
@CrossOrigin
@RequestMapping("/v1")
public class ShopeeUpdateController {
    private final ShopeeUpdateService shopeeUpdateService;

    @Autowired
    public ShopeeUpdateController(ShopeeUpdateService shopeeUpdateService) {
        this.shopeeUpdateService = shopeeUpdateService;
    }

    @PostMapping("/public/shopee-update/create")
    public ResponseEntity<ResultService<ShopeeUpdateDTO>> Create(@Valid @RequestBody ShopeeUpdateCreateDTOValidator shopeeUpdateCreateDTOValidator,
                                                                 BindingResult resultValid){
        var result = shopeeUpdateService.CreateAsync(shopeeUpdateCreateDTOValidator, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/public/shopee-update/delete/{shopeeUpdateId}")
    public ResponseEntity<ResultService<ShopeeUpdateDTO>> delete(@PathVariable String shopeeUpdateId){
        var result = shopeeUpdateService.Delete(UUID.fromString(shopeeUpdateId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
