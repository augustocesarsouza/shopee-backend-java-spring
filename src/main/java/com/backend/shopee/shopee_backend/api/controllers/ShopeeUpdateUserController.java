package com.backend.shopee.shopee_backend.api.controllers;

import com.backend.shopee.shopee_backend.application.dto.ShopeeUpdateUserDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ShopeeUpdateUserValidationDTOs.PromotionUpdateUserCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import com.backend.shopee.shopee_backend.application.services.interfaces.IShopeeUpdateUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Component
@RestController
@CrossOrigin
@RequestMapping("/v1")
public class ShopeeUpdateUserController {
    private final IShopeeUpdateUserService shopeeUpdateUserService;

    @Autowired
    public ShopeeUpdateUserController(IShopeeUpdateUserService shopeeUpdateUserService) {
        this.shopeeUpdateUserService = shopeeUpdateUserService;
    }

    @GetMapping("/shopee-update-user/get-by-user-id-all/{userId}")
    public ResponseEntity<ResultService<List<ShopeeUpdateUserDTO>>> GetByUserIdAll(@PathVariable String userId){
        var result = shopeeUpdateUserService.GetByUserIdAll(UUID.fromString(userId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/public/shopee-update-user/create")
    public ResponseEntity<ResultService<ShopeeUpdateUserDTO>> Create(@Valid @RequestBody PromotionUpdateUserCreateDTOValidator promotionUpdateUserCreateDTOValidator,
                                                                  BindingResult resultValid){
        var result = shopeeUpdateUserService.CreateAsync(promotionUpdateUserCreateDTOValidator, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
