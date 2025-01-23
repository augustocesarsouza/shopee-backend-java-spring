package com.backend.shopee.shopee_backend.application.dto.validations.ProductOfferFlashSellerValidationDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductOfferFlashSellerCreateDTOValidator {
    @NotEmpty(message = "userSellerProductId should not be empty")
    @Size(min = 36, message = "userSellerProductId should have at last 36 characters")
    @JsonProperty("userSellerProductId")
    private String UserSellerProductId;
    @NotEmpty(message = "imgProduct should not be empty")
    @Size(min = 36, message = "imgProduct should have at last 36 characters")
    @JsonProperty("productsOfferFlashId")
    private String ProductsOfferFlashId;

    public ProductOfferFlashSellerCreateDTOValidator(String userSellerProductId, String productsOfferFlashId) {
        UserSellerProductId = userSellerProductId;
        ProductsOfferFlashId = productsOfferFlashId;
    }

    public ProductOfferFlashSellerCreateDTOValidator() {
    }

    public String getUserSellerProductId() {
        return UserSellerProductId;
    }

    public String getProductsOfferFlashId() {
        return ProductsOfferFlashId;
    }

    public void setUserSellerProductId(String userSellerProductId) {
        UserSellerProductId = userSellerProductId;
    }

    public void setProductsOfferFlashId(String productsOfferFlashId) {
        ProductsOfferFlashId = productsOfferFlashId;
    }
}
