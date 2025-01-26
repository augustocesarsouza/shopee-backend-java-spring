package com.backend.shopee.shopee_backend.api.controllers;

import com.backend.shopee.shopee_backend.application.dto.ProductFlashSaleReviewDTO;
import com.backend.shopee.shopee_backend.application.dto.UserSellerProductDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductFlashSaleReviewValidationDTOs.ProductFlashSaleReviewCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.UserSellerProductValidationDTOs.UserSellerProductCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ProductFlashSaleReviewService;
import com.backend.shopee.shopee_backend.application.services.ResultService;
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
public class ProductFlashSaleReviewController {
    private final ProductFlashSaleReviewService productFlashSaleReviewService;

    @Autowired
    public ProductFlashSaleReviewController(ProductFlashSaleReviewService productFlashSaleReviewService) {
        this.productFlashSaleReviewService = productFlashSaleReviewService;
    }

    @GetMapping("/product-flash-sale-reviews/get-all-product-flash-sale-reviews-by-product-flash-sale-id/{productsOfferFlashId}")
    public ResponseEntity<ResultService<List<ProductFlashSaleReviewDTO>>> GetByUserSellerProductId(@PathVariable String productsOfferFlashId){
        var result = productFlashSaleReviewService.GetAllProductFlashSaleReviewsByProductsOfferFlashId(UUID.fromString(productsOfferFlashId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/public/product-flash-sale-reviews/create")
    public ResponseEntity<ResultService<ProductFlashSaleReviewDTO>> Create(@Valid @RequestBody ProductFlashSaleReviewCreateDTOValidator productFlashSaleReviewCreateDTOValidator,
                                                                      BindingResult resultValid){
        var result = productFlashSaleReviewService.CreateAsync(productFlashSaleReviewCreateDTOValidator, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/public/product-flash-sale-reviews/delete/{productFlashSaleReviewId}")
    public ResponseEntity<ResultService<ProductFlashSaleReviewDTO>> Create(@PathVariable String productFlashSaleReviewId){
        var result = productFlashSaleReviewService.Delete(UUID.fromString(productFlashSaleReviewId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
