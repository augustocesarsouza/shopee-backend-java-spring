package com.backend.shopee.shopee_backend.api.controllers;

import com.backend.shopee.shopee_backend.application.dto.CategoriesDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.CategoriesValidationDTOs.CategoriesCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import com.backend.shopee.shopee_backend.application.services.interfaces.ICategoriesService;
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
public class CategoriesController {
    private final ICategoriesService categoriesService;

    @Autowired
    public CategoriesController(ICategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping("/public/categories/get-categories-by-id/{categoryId}")
    public ResponseEntity<ResultService<CategoriesDTO>> GetCuponById(@PathVariable String categoryId){
        var result = categoriesService.GetCategoriesById(UUID.fromString(categoryId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/categories/get-all-categories")
    public ResponseEntity<ResultService<List<CategoriesDTO>>> GetAllCategories(){
        var result = categoriesService.GetAllCategories();

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/public/categories/create")
    public ResponseEntity<ResultService<CategoriesDTO>> Create(@Valid @RequestBody CategoriesCreateDTOValidator categoriesCreateDTOValidator,
                                                          BindingResult resultValid){
        var result = categoriesService.CreateAsync(categoriesCreateDTOValidator, resultValid);

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/public/categories/delete/{categoryId}")
    public ResponseEntity<ResultService<CategoriesDTO>> delete(@PathVariable String categoryId){
        var result = categoriesService.Delete(UUID.fromString(categoryId));

        if(result.IsSuccess){
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
}
