package com.backend.shopee.shopee_backend.api.controllers;

import com.backend.shopee.shopee_backend.application.dto.UserChangePasswordDTO;
import com.backend.shopee.shopee_backend.application.dto.UserDTO;
import com.backend.shopee.shopee_backend.application.dto.UserLoginDTO;
import com.backend.shopee.shopee_backend.application.dto.UserPasswordUpdateDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.userValidationDTOs.*;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import com.backend.shopee.shopee_backend.application.services.interfaces.IUserAuthenticationService;
import com.backend.shopee.shopee_backend.application.services.interfaces.IUserManagementService;
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
public class UserController {
    private final IUserManagementService userManagementService;
    private final IUserAuthenticationService userAuthenticationService;

    @Autowired
    public UserController(IUserManagementService userManagementService, IUserAuthenticationService userAuthenticationService) {
        this.userManagementService = userManagementService;
        this.userAuthenticationService = userAuthenticationService;
    }

    @GetMapping("/user/find-by-id/{phone}")
    public ResponseEntity<ResultService<UserDTO>> findById(@PathVariable String phone){
        var result = userManagementService.findById(phone);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/public/user/login/{phone}/{password}")
    public ResponseEntity<ResultService<UserLoginDTO>> login(@PathVariable String phone, @PathVariable String password){
        var result = userAuthenticationService.Login(phone, password);

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

    @PostMapping("/public/user/send-code-phone")
    public ResponseEntity<ResultService<CodeSendPhoneDTOValidator>> SendCodePhone(@Valid @RequestBody CodeSendPhoneDTOValidator codeSendPhoneDTOValidator,
                                                                                  BindingResult resultValid){
        var result = userAuthenticationService.SendCodePhone(codeSendPhoneDTOValidator, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/public/user/confirm-email-send-code")
    public ResponseEntity<ResultService<CodeSendEmailUserValidatorDTO>> SendCodeEmail(@Valid @RequestBody CodeSendEmailUserValidatorDTO codeSendEmailUserValidatorDTO, BindingResult resultValid){
        var result = userAuthenticationService.SendCodeEmail(codeSendEmailUserValidatorDTO, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/public/user/verify")
    public ResponseEntity<ResultService<UserDTO>> VerifyEmailAlreadySetUp(@Valid @RequestBody UserConfirmCodeEmailValidatorDTO userConfirmCodeEmailValidatorDTO,
                                                                                                BindingResult resultValid){
        var result = userAuthenticationService.VerifyEmailAlreadySetUp(userConfirmCodeEmailValidatorDTO, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/public/user/update-user-password")
    public ResponseEntity<ResultService<UserPasswordUpdateDTO>> update(@Valid @RequestBody UserChangePasswordDTO userChangePasswordDTO, BindingResult resultValid){
        var result = userManagementService.ChangePasswordUser(userChangePasswordDTO, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/public/user/update-all-info")
    public ResponseEntity<ResultService<UserDTO>> UpdateUserAll(@Valid @RequestBody UserUpdateAllDTOValidator userUpdateAllDTOValidator, BindingResult resultValid){
        var result = userManagementService.UpdateUserAll(userUpdateAllDTOValidator, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/public/user/delete/{userId}")
    public ResponseEntity<ResultService<UserDTO>> Create(@PathVariable String userId){
        var result = userManagementService.DeleteUser(UUID.fromString(userId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
