package com.backend.shopee.shopee_backend.application.dto.validations.ProductOptionImageBottomValidationDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductOptionImageBottomCreateDTOValidator {
    @NotEmpty(message = "productsOfferFlashId should not be empty")
    @Size(min = 36, message = "productsOfferFlashId should have at last 36 characters")
    @JsonProperty("productsOfferFlashId")
    private String ProductsOfferFlashId;
    @Size(min = 1, message = "listImageUrlBottom must contain at least one URL")
    @JsonProperty("listImageUrlBottom")
    @Valid
    private List<String> ListImageUrlBottom;

    public ProductOptionImageBottomCreateDTOValidator(String productsOfferFlashId, List<String> listImageUrlBottom) {
        ProductsOfferFlashId = productsOfferFlashId;
        ListImageUrlBottom = listImageUrlBottom;
    }

    public ProductOptionImageBottomCreateDTOValidator() {
    }

    public String getProductsOfferFlashId() {
        return ProductsOfferFlashId;
    }

    public List<String> getListImageUrlBottom() {
        return ListImageUrlBottom;
    }

    public void setProductsOfferFlashId(String productsOfferFlashId) {
        ProductsOfferFlashId = productsOfferFlashId;
    }

    public void setListImageUrlBottom(List<String> listImageUrlBottom) {
        ListImageUrlBottom = listImageUrlBottom;
    }
}
