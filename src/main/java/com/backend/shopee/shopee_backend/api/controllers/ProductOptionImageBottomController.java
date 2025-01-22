package com.backend.shopee.shopee_backend.api.controllers;

import com.backend.shopee.shopee_backend.application.dto.ProductOptionImageBottomDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductOptionImageBottomValidationDTOs.ProductOptionImageBottomCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import com.backend.shopee.shopee_backend.application.services.interfaces.IProductOptionImageBottomService;
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
public class ProductOptionImageBottomController {
    private final IProductOptionImageBottomService productOptionImageBottomService;

    @Autowired
    public ProductOptionImageBottomController(IProductOptionImageBottomService productOptionImageBottomService) {
        this.productOptionImageBottomService = productOptionImageBottomService;
    }

    @GetMapping("/product-option-image-bottom/get-by-list-flash-sale-product-image-all-id/{productsOfferFlashId}")
    public ResponseEntity<ResultService<ProductOptionImageBottomDTO>> GetByListFlashSaleProductImageAllId(@PathVariable String productsOfferFlashId) {
        var result = productOptionImageBottomService.GetByListFlashSaleProductImageAllId(UUID.fromString(productsOfferFlashId));

        if (result.IsSuccess) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/public/product-option-image-bottom/create")
    public ResponseEntity<ResultService<ProductOptionImageBottomDTO>> Create(
            @Valid @RequestBody ProductOptionImageBottomCreateDTOValidator productOptionImageBottomCreateDTOValidator, BindingResult resultValid) {
        var result = productOptionImageBottomService.CreateAsync(productOptionImageBottomCreateDTOValidator, resultValid);

        if (result.IsSuccess) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/public/product-option-image-bottom/delete/{productsOfferFlashId}")
    public ResponseEntity<ResultService<ProductOptionImageBottomDTO>> delete(@PathVariable String productsOfferFlashId) {
        var result = productOptionImageBottomService.Delete(UUID.fromString(productsOfferFlashId));

        if (result.IsSuccess) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}

