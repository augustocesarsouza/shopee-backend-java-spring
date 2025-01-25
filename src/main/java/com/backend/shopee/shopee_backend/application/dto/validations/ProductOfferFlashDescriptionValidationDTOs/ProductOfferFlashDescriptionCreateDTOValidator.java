package com.backend.shopee.shopee_backend.application.dto.validations.ProductOfferFlashDescriptionValidationDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductOfferFlashDescriptionCreateDTOValidator {
    @NotEmpty(message = "productsOfferFlashId should not be empty")
    @Size(min = 36, message = "productsOfferFlashId should have at last 36 characters")
    @JsonProperty("productsOfferFlashId")
    private String ProductsOfferFlashId;
    @NotEmpty(message = "descriptions should not be empty")
    @JsonProperty("descriptions")
    private String Descriptions;

    public ProductOfferFlashDescriptionCreateDTOValidator(String productsOfferFlashId, String descriptions) {
        ProductsOfferFlashId = productsOfferFlashId;
        Descriptions = descriptions;
    }

    public ProductOfferFlashDescriptionCreateDTOValidator() {
    }

    public String getProductsOfferFlashId() {
        return ProductsOfferFlashId;
    }

    public String getDescriptions() {
        return Descriptions;
    }

    public void setProductsOfferFlashId(String productsOfferFlashId) {
        ProductsOfferFlashId = productsOfferFlashId;
    }

    public void setDescriptions(String descriptions) {
        Descriptions = descriptions;
    }
}
