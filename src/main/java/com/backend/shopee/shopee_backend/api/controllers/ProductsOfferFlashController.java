package com.backend.shopee.shopee_backend.api.controllers;

import com.backend.shopee.shopee_backend.application.dto.CuponDTO;
import com.backend.shopee.shopee_backend.application.dto.ProductsOfferFlashDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.CuponValidationDTOs.CuponCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductsOfferFlashValidator.ProductsOfferFlashDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import com.backend.shopee.shopee_backend.application.services.interfaces.IProductsOfferFlashService;
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
public class ProductsOfferFlashController {
    private final IProductsOfferFlashService productsOfferFlashService;

    @Autowired
    public ProductsOfferFlashController(IProductsOfferFlashService productsOfferFlashService) {
        this.productsOfferFlashService = productsOfferFlashService;
    }

    @GetMapping("/public/product-offer-flash/get-product-offer-flash-all")
    public ResponseEntity<ResultService<List<ProductsOfferFlashDTO>>> GetAllProduct(){
        var result = productsOfferFlashService.GetAllProduct();

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/product-offer-flash/get-all-by-tag-product/{hourFlashOffer}/{tagProduct}/{pageNumber}/{pageSize}")
    public ResponseEntity<ResultService<List<ProductsOfferFlashDTO>>> GetAllByTagProduct(@PathVariable String hourFlashOffer, @PathVariable String tagProduct,
                                                                @PathVariable String pageNumber, @PathVariable String pageSize){
        var result = productsOfferFlashService.GetAllByTagProduct(hourFlashOffer, tagProduct, Integer.parseInt(pageNumber), Integer.parseInt(pageSize));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/public/product-offer-flash/create")
    public ResponseEntity<ResultService<ProductsOfferFlashDTO>> Create(@Valid @RequestBody ProductsOfferFlashDTOValidator productsOfferFlashDTOValidator,
                                                          BindingResult resultValid){
        var result = productsOfferFlashService.CreateAsync(productsOfferFlashDTOValidator, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/public/product-offer-flash/delete/{productsOfferFlashId}")
    public ResponseEntity<ResultService<ProductsOfferFlashDTO>> delete(@PathVariable String productsOfferFlashId){
        var result = productsOfferFlashService.Delete(productsOfferFlashId);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
