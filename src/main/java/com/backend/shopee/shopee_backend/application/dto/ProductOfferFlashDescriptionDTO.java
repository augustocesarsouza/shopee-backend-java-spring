package com.backend.shopee.shopee_backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductOfferFlashDescriptionDTO {
    @JsonProperty("id")
    private UUID Id;
    @JsonProperty("productsOfferFlashId")
    private UUID ProductsOfferFlashId;
    @JsonProperty("descriptions")
    private String Descriptions; // quando criar da descrição talvez tenha que mandar com isso "\n" que é quebrar uma linha

    public ProductOfferFlashDescriptionDTO(UUID id, UUID productsOfferFlashId, String descriptions) {
        Id = id;
        ProductsOfferFlashId = productsOfferFlashId;
        Descriptions = descriptions;
    }

    public ProductOfferFlashDescriptionDTO() {
    }

    public UUID getId() {
        return Id;
    }

    public UUID getProductsOfferFlashId() {
        return ProductsOfferFlashId;
    }

    public String getDescriptions() {
        return Descriptions;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setProductsOfferFlashId(UUID productsOfferFlashId) {
        ProductsOfferFlashId = productsOfferFlashId;
    }

    public void setDescriptions(String descriptions) {
        Descriptions = descriptions;
    }
}
