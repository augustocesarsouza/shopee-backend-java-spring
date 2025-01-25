package com.backend.shopee.shopee_backend.api.controllers;

import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashDescriptionDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductOfferFlashDescriptionValidationDTOs.ProductOfferFlashDescriptionCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import com.backend.shopee.shopee_backend.application.services.interfaces.IProductOfferFlashDescriptionService;
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
public class ProductOfferFlashDescriptionController {
    private final IProductOfferFlashDescriptionService productOfferFlashDescriptionService;

    @Autowired
    public ProductOfferFlashDescriptionController(IProductOfferFlashDescriptionService productOfferFlashDescriptionService) {
        this.productOfferFlashDescriptionService = productOfferFlashDescriptionService;
    }

    @GetMapping("/product-offer-flash-description/get-by-products-offer-flash-id/{productsOfferFlashId}")
    public ResponseEntity<ResultService<ProductOfferFlashDescriptionDTO>> GetByProductsOfferFlashId(@PathVariable String productsOfferFlashId){
        var result = productOfferFlashDescriptionService.GetByProductsOfferFlashId(UUID.fromString(productsOfferFlashId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/public/product-offer-flash-description/create")
    public ResponseEntity<ResultService<ProductOfferFlashDescriptionDTO>> Create(@Valid @RequestBody ProductOfferFlashDescriptionCreateDTOValidator productOfferFlashDescriptionCreateDTOValidator,
                                                                     BindingResult resultValid){
        var result = productOfferFlashDescriptionService.CreateAsync(productOfferFlashDescriptionCreateDTOValidator, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/public/product-offer-flash-description/delete/{productOfferFlashDescriptionId}")
    public ResponseEntity<ResultService<ProductOfferFlashDescriptionDTO>> delete(@PathVariable String productOfferFlashDescriptionId){
        var result = productOfferFlashDescriptionService.Delete(UUID.fromString(productOfferFlashDescriptionId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
