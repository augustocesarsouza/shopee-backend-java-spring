package com.backend.shopee.shopee_backend.api.controllers;

import com.backend.shopee.shopee_backend.application.dto.CuponDTO;
import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashDetailsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.CuponValidationDTOs.CuponCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductOfferFlashDetailsValidationDTOs.ProductOfferFlashDetailsCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import com.backend.shopee.shopee_backend.application.services.interfaces.IProductOfferFlashDetailsService;
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
public class ProductOfferFlashDetailsController {
    private final IProductOfferFlashDetailsService productOfferFlashDetailsService;

    @Autowired
    public ProductOfferFlashDetailsController(IProductOfferFlashDetailsService productOfferFlashDetailsService) {
        this.productOfferFlashDetailsService = productOfferFlashDetailsService;
    }

    @GetMapping("/product-offer-flash-details/get-by-products-offer-flash-id/{productsOfferFlashId}")
    public ResponseEntity<ResultService<ProductOfferFlashDetailsDTO>> GetByProductsOfferFlashId(@PathVariable String productsOfferFlashId){
        var result = productOfferFlashDetailsService.GetByProductsOfferFlashId(UUID.fromString(productsOfferFlashId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/public/product-offer-flash-details/create")
    public ResponseEntity<ResultService<ProductOfferFlashDetailsDTO>> Create(
            @Valid @RequestBody ProductOfferFlashDetailsCreateDTOValidator productOfferFlashDetailsCreateDTOValidator, BindingResult resultValid){
        var result = productOfferFlashDetailsService.CreateAsync(productOfferFlashDetailsCreateDTOValidator, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/public/product-offer-flash-details/delete/{productOfferFlashDetailsId}")
    public ResponseEntity<ResultService<ProductOfferFlashDetailsDTO>> delete(@PathVariable String productOfferFlashDetailsId){
        var result = productOfferFlashDetailsService.Delete(UUID.fromString(productOfferFlashDetailsId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
