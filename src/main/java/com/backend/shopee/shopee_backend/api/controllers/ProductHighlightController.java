package com.backend.shopee.shopee_backend.api.controllers;

import com.backend.shopee.shopee_backend.application.dto.ProductHighlightDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductHighlightValidatorDTOs.ProductHighlightDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import com.backend.shopee.shopee_backend.application.services.interfaces.IProductHighlightService;
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
public class ProductHighlightController {
    private final IProductHighlightService productHighlightService;

    @Autowired
    public ProductHighlightController(IProductHighlightService productHighlightService) {
        this.productHighlightService = productHighlightService;
    }

    @GetMapping("/public/product-highlight/get-product-highlight-by-id/{productHighlightId}")
    public ResponseEntity<ResultService<ProductHighlightDTO>> GetProductHighlightById(@PathVariable String productHighlightId){
        var result = productHighlightService.GetProductHighlightById(UUID.fromString(productHighlightId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/product-highlight/get-all-product-highlights")
    public ResponseEntity<ResultService<List<ProductHighlightDTO>>> GetAllProductHighlights(){
        var result = productHighlightService.GetAllProductHighlights();

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/public/product-highlight/create")
    public ResponseEntity<ResultService<ProductHighlightDTO>> Create(@Valid @RequestBody ProductHighlightDTOValidator productHighlightDTOValidator,
                                                               BindingResult resultValid){
        var result = productHighlightService.CreateAsync(productHighlightDTOValidator, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/public/product-highlight/delete/{productHighlightId}")
    public ResponseEntity<ResultService<ProductHighlightDTO>> delete(@PathVariable String productHighlightId){
        var result = productHighlightService.Delete(UUID.fromString(productHighlightId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
