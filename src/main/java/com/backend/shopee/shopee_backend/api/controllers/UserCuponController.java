package com.backend.shopee.shopee_backend.api.controllers;

import com.backend.shopee.shopee_backend.application.dto.PromotionUserDTO;
import com.backend.shopee.shopee_backend.application.dto.UserCuponDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.PromotionUserValidationDTOs.PromotionUserCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.dto.validations.UserCuponValidationDTOs.UserCuponCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import com.backend.shopee.shopee_backend.application.services.interfaces.IUserCuponService;
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
public class UserCuponController {
    private final IUserCuponService userCuponService;

    @Autowired
    public UserCuponController(IUserCuponService userCuponService) {
        this.userCuponService = userCuponService;
    }

    @GetMapping("/public/user-cupon/get-all-cupon-by-user-id/{userId}")
    public ResponseEntity<ResultService<List<UserCuponDTO>>> GetByUserIdAll(@PathVariable String userId){
        var result = userCuponService.GetAllCuponByUserId(UUID.fromString(userId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/public/user-cupon/create") // Continuar a criação dos "UserCupon" e testar "GetByUserIdAll" pegar os cupons
    public ResponseEntity<ResultService<UserCuponDTO>> Create(@Valid @RequestBody UserCuponCreateDTOValidator userCuponCreateDTOValidator,
                                                                  BindingResult resultValid){
        var result = userCuponService.CreateAsync(userCuponCreateDTOValidator, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
