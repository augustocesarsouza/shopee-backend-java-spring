package com.backend.shopee.shopee_backend.api.controllers;

import com.backend.shopee.shopee_backend.application.dto.ProductHighlightDTO;
import com.backend.shopee.shopee_backend.application.dto.ProductOfferFlashTypeDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductHighlightValidatorDTOs.ProductHighlightDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductOfferFlashTypeValidationDTOs.ProductOfferFlashTypeCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import com.backend.shopee.shopee_backend.application.services.interfaces.IProductOfferFlashTypeService;
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
public class ProductOfferFlashTypeController {
    private final IProductOfferFlashTypeService productOfferFlashTypeService;

    @Autowired
    public ProductOfferFlashTypeController(IProductOfferFlashTypeService productOfferFlashTypeService) {
        this.productOfferFlashTypeService = productOfferFlashTypeService;
    }

    @GetMapping("/product-offer-flash-type/get-all-product-offer-flash-type-by-products-offer-flash-id/{productsOfferFlashId}")
    public ResponseEntity<ResultService<List<ProductOfferFlashTypeDTO>>> GetAllProductOfferFlashTypeByProductsOfferFlashId(@PathVariable String productsOfferFlashId){
        var result = productOfferFlashTypeService.GetAllProductOfferFlashTypeByProductsOfferFlashId(UUID.fromString(productsOfferFlashId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/public/product-offer-flash-type/create")
    public ResponseEntity<ResultService<ProductOfferFlashTypeDTO>> Create(@Valid @RequestBody ProductOfferFlashTypeCreateDTOValidator productOfferFlashTypeCreateDTOValidator,
                                                                     BindingResult resultValid){
        var result = productOfferFlashTypeService.CreateAsync(productOfferFlashTypeCreateDTOValidator, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/public/product-offer-flash-type/delete/{productOfferFlashTypeId}")
    public ResponseEntity<ResultService<ProductOfferFlashTypeDTO>> delete(@PathVariable String productOfferFlashTypeId){
        var result = productOfferFlashTypeService.Delete(UUID.fromString(productOfferFlashTypeId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
