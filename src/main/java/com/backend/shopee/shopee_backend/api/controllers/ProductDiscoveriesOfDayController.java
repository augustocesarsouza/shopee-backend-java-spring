package com.backend.shopee.shopee_backend.api.controllers;

import com.backend.shopee.shopee_backend.application.dto.CategoriesDTO;
import com.backend.shopee.shopee_backend.application.dto.ProductDiscoveriesOfDayDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.CategoriesValidationDTOs.CategoriesCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductDiscoveriesOfDayValidationDTOs.ProductDiscoveriesOfDayCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import com.backend.shopee.shopee_backend.application.services.interfaces.IProductDiscoveriesOfDayService;
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
public class ProductDiscoveriesOfDayController {
    private final IProductDiscoveriesOfDayService productDiscoveriesOfDayService;

    @Autowired
    public ProductDiscoveriesOfDayController(IProductDiscoveriesOfDayService productDiscoveriesOfDayService) {
        this.productDiscoveriesOfDayService = productDiscoveriesOfDayService;
    }

    @GetMapping("/product-discoveries-of-day/get-product-discoveries-of-day-by-id/{productDiscoveriesOfDayId}")
    public ResponseEntity<ResultService<ProductDiscoveriesOfDayDTO>> GetProductDiscoveriesOfDayById(@PathVariable String productDiscoveriesOfDayId){
        var result = productDiscoveriesOfDayService.GetProductDiscoveriesOfDayById(UUID.fromString(productDiscoveriesOfDayId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/product-discoveries-of-day/get-all-product-discoveries-of-day")
    public ResponseEntity<ResultService<List<ProductDiscoveriesOfDayDTO>>> GetAllProductDiscoveriesOfDays(){
        var result = productDiscoveriesOfDayService.GetAllProductDiscoveriesOfDays();

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/public/product-discoveries-of-day/create")
    public ResponseEntity<ResultService<ProductDiscoveriesOfDayDTO>> Create(@Valid @RequestBody ProductDiscoveriesOfDayCreateDTOValidator productDiscoveriesOfDayCreateDTOValidator,
                                                               BindingResult resultValid){
        var result = productDiscoveriesOfDayService.CreateAsync(productDiscoveriesOfDayCreateDTOValidator, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/public/product-discoveries-of-day/delete/{productDiscoveriesOfDayId}")
    public ResponseEntity<ResultService<ProductDiscoveriesOfDayDTO>> delete(@PathVariable String productDiscoveriesOfDayId){
        var result = productDiscoveriesOfDayService.Delete(UUID.fromString(productDiscoveriesOfDayId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
