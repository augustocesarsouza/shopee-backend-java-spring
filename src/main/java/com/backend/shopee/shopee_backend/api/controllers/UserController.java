package com.backend.shopee.shopee_backend.api.controllers;

import com.backend.shopee.shopee_backend.application.dto.UserDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs.UserCreateValidatorDTO;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import com.backend.shopee.shopee_backend.application.services.interfaces.IUserManagementService;
import com.backend.shopee.shopee_backend.domain.entities.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Component
@RestController
@CrossOrigin
@RequestMapping("/v1")
public class UserController {
    private final IUserManagementService userManagementService;

    public UserController(IUserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @GetMapping("/public/user/find-by-id/{phone}")
    public ResponseEntity<ResultService<UserDTO>> findById(@PathVariable String phone){
        var result = userManagementService.findById(phone);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/public/user/create")
    public ResponseEntity<ResultService<UserDTO>> Create(@Valid @RequestBody UserCreateValidatorDTO userCreateValidatorDTO,  BindingResult resultValid){
        var result = userManagementService.create(userCreateValidatorDTO, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
