package com.backend.shopee.shopee_backend.api.controllers;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashSellerDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductOfferFlashSellerValidationDTOs.ProductOfferFlashSellerCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import com.backend.shopee.shopee_backend.application.services.interfaces.IProductOfferFlashSellerService;
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
public class ProductOfferFlashSellerController {
    private final IProductOfferFlashSellerService productOfferFlashSellerService;

    @Autowired
    public ProductOfferFlashSellerController(IProductOfferFlashSellerService productOfferFlashSellerService) {
        this.productOfferFlashSellerService = productOfferFlashSellerService;
    }

    @GetMapping("/product-offer-flash-seller/get-by-products-offer-flash-id/{productsOfferFlashId}")
    public ResponseEntity<ResultService<ProductOfferFlashSellerDTO>> GetByProductsOfferFlashId(@PathVariable String productsOfferFlashId){
        var result = productOfferFlashSellerService.GetByProductsOfferFlashId(UUID.fromString(productsOfferFlashId));
//        user-seller-product-offer-flash
        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/public/product-offer-flash-seller/create")
    public ResponseEntity<ResultService<ProductOfferFlashSellerDTO>> Create(@Valid @RequestBody ProductOfferFlashSellerCreateDTOValidator productOfferFlashSellerCreateDTOValidator,
                                                          BindingResult resultValid){
        var result = productOfferFlashSellerService.CreateAsync(productOfferFlashSellerCreateDTOValidator, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
