package com.backend.shopee.shopee_backend.application.dto.validations.ProductOfferFlashDetailsValidationDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductOfferFlashDetailsCreateDTOValidator {
    @NotEmpty(message = "productsOfferFlashId should not be empty")
    @Size(min = 36, message = "productsOfferFlashId should have at last 36 characters")
    @JsonProperty("productsOfferFlashId")
    private String ProductsOfferFlashId;
    @JsonProperty("details")
    private Map<String, Object> Details;

    public ProductOfferFlashDetailsCreateDTOValidator(String productsOfferFlashId, Map<String, Object> details) {
        ProductsOfferFlashId = productsOfferFlashId;
        Details = details;
    }

    public ProductOfferFlashDetailsCreateDTOValidator() {
    }

    public String getProductsOfferFlashId() {
        return ProductsOfferFlashId;
    }

    public Map<String, Object> getDetails() {
        return Details;
    }

    public void setProductsOfferFlashId(String productsOfferFlashId) {
        ProductsOfferFlashId = productsOfferFlashId;
    }

    public void setDetails(Map<String, Object> details) {
        Details = details;
    }

    public String getDetailsAsJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(getDetails());  // Converte Map para JSON String
    }
}
