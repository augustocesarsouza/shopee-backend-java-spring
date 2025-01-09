package com.backend.shopee.shopee_backend.api.controllers;

import com.backend.shopee.shopee_backend.application.dto.CuponDTO;
import com.backend.shopee.shopee_backend.application.dto.PromotionDTO;
import com.backend.shopee.shopee_backend.application.dto.PromotionUserDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.CuponValidationDTOs.CuponCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.PromotionValidationDTOs.PromotionCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import com.backend.shopee.shopee_backend.application.services.interfaces.ICuponService;
import com.backend.shopee.shopee_backend.domain.entities.Cupon;
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
public class CuponController {
    private final ICuponService cuponService;

    @Autowired
    public CuponController(ICuponService cuponService) {
        this.cuponService = cuponService;
    }

    @GetMapping("/public/promotion-user/get-by-user-id-all/{cuponId}")
    public ResponseEntity<ResultService<CuponDTO>> GetCuponById(@PathVariable String cuponId){
        var result = cuponService.GetCuponById(UUID.fromString(cuponId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/public/cupon/create")
    public ResponseEntity<ResultService<CuponDTO>> Create(@Valid @RequestBody CuponCreateDTOValidator cuponCreateDTOValidator,
                                                          BindingResult resultValid){
        var result = cuponService.CreateAsync(cuponCreateDTOValidator, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/public/cupon/delete/{cuponId}")
    public ResponseEntity<ResultService<CuponDTO>> delete(@PathVariable String cuponId){
        var result = cuponService.Delete(UUID.fromString(cuponId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
