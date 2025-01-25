package com.backend.shopee.shopee_backend.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductOfferFlashDetailsDTO {
    @JsonProperty("id")
    private UUID Id;
    @JsonProperty("productsOfferFlashId")
    private UUID ProductsOfferFlashId;
    @JsonProperty("details")
    private String Details;

    public ProductOfferFlashDetailsDTO(UUID id, UUID productsOfferFlashId, String details) {
        Id = id;
        ProductsOfferFlashId = productsOfferFlashId;
        Details = details;
    }

    public ProductOfferFlashDetailsDTO() {
    }

    public UUID getId() {
        return Id;
    }

    public UUID getProductsOfferFlashId() {
        return ProductsOfferFlashId;
    }

    public String getDetails() {
        return Details;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setProductsOfferFlashId(UUID productsOfferFlashId) {
        ProductsOfferFlashId = productsOfferFlashId;
    }

    public void setDetails(String details) {
        Details = details;
    }
}
