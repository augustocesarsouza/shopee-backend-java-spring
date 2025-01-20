package com.backend.shopee.shopee_backend.api.controllers;

import com.backend.shopee.shopee_backend.application.dto.FlashSaleProductAllInfoDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.FlashSaleProductAllInfoValidationDTO.FlashSaleProductAllInfoCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import com.backend.shopee.shopee_backend.application.services.interfaces.IFlashSaleProductAllInfoService;
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
public class FlashSaleProductAllInfoController {
    private final IFlashSaleProductAllInfoService flashSaleProductAllInfoService;

    @Autowired
    public FlashSaleProductAllInfoController(IFlashSaleProductAllInfoService flashSaleProductAllInfoService) {
        this.flashSaleProductAllInfoService = flashSaleProductAllInfoService;
    }

    @GetMapping("/flash-sale-product-all-info/get-flash-sale-product-by-product-flash-sale-id/{productFlashSaleId}")
    public ResponseEntity<ResultService<FlashSaleProductAllInfoDTO>> GetFlashSaleProductByProductFlashSaleId(@PathVariable String productFlashSaleId){
        var result = flashSaleProductAllInfoService.GetFlashSaleProductByProductFlashSaleId(UUID.fromString(productFlashSaleId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/public/flash-sale-product-all-info/create")
    public ResponseEntity<ResultService<FlashSaleProductAllInfoDTO>> Create(@Valid @RequestBody FlashSaleProductAllInfoCreateDTOValidator flashSaleProductAllInfoCreateDTOValidator,
                                                          BindingResult resultValid){
        var result = flashSaleProductAllInfoService.CreateAsync(flashSaleProductAllInfoCreateDTOValidator, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
