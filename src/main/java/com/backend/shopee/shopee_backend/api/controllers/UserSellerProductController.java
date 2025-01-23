package com.backend.shopee.shopee_backend.api.controllers;

import com.backend.shopee.shopee_backend.application.dto.CuponDTO;
import com.backend.shopee.shopee_backend.application.dto.PromotionDTO;
import com.backend.shopee.shopee_backend.application.dto.UserSellerProductDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.PromotionValidationDTOs.PromotionCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.UserSellerProductValidationDTOs.UserSellerProductCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import com.backend.shopee.shopee_backend.application.services.UserSellerProductService;
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
public class UserSellerProductController {
    private final UserSellerProductService userSellerProductService;

    @Autowired
    public UserSellerProductController(UserSellerProductService userSellerProductService) {
        this.userSellerProductService = userSellerProductService;
    }

    @GetMapping("/public/user-seller-product/get-user-seller-product-by-id/{userSellerProductId}")
    public ResponseEntity<ResultService<UserSellerProductDTO>> GetByUserSellerProductId(@PathVariable String userSellerProductId){
        var result = userSellerProductService.GetByUserSellerProductId(UUID.fromString(userSellerProductId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/public/user-seller-product/create")
    public ResponseEntity<ResultService<UserSellerProductDTO>> Create(@Valid @RequestBody UserSellerProductCreateDTOValidator userSellerProductCreateDTOValidator,
                                                                      BindingResult resultValid){
        var result = userSellerProductService.CreateAsync(userSellerProductCreateDTOValidator, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
