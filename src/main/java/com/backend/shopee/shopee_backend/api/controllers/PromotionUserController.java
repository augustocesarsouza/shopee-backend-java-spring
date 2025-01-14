package com.backend.shopee.shopee_backend.api.controllers;

import com.backend.shopee.shopee_backend.application.dto.AddressDTO;
import com.backend.shopee.shopee_backend.application.dto.PromotionDTO;
import com.backend.shopee.shopee_backend.application.dto.PromotionUserDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.PromotionUserValidationDTOs.PromotionUserCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.PromotionValidationDTOs.PromotionCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import com.backend.shopee.shopee_backend.application.services.interfaces.IPromotionService;
import com.backend.shopee.shopee_backend.application.services.interfaces.IPromotionUserService;
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
public class PromotionUserController {
    private final IPromotionUserService promotionUserService;

    @Autowired
    public PromotionUserController(IPromotionUserService promotionUserService) {
        this.promotionUserService = promotionUserService;
    }

    @GetMapping("/public/promotion-user/get-by-user-id-all-to-promotion-user/{userId}")
    public ResponseEntity<ResultService<List<PromotionUserDTO>>> GetByUserIdAll(@PathVariable String userId){
        var result = promotionUserService.GetByUserIdAll(UUID.fromString(userId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/public/promotion-user/create")
    public ResponseEntity<ResultService<PromotionUserDTO>> Create(@Valid @RequestBody PromotionUserCreateDTOValidator promotionUserCreateDTOValidator,
                                                                  BindingResult resultValid){
        var result = promotionUserService.CreateAsync(promotionUserCreateDTOValidator, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
