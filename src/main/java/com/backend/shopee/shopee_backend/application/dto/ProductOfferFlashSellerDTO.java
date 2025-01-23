package com.backend.shopee.shopee_backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductOfferFlashSellerDTO {
    @JsonProperty("id")
    private UUID Id;
    @JsonProperty("userSellerProductId")
    private UUID UserSellerProductId;
    @JsonProperty("userSellerProductDTO")
    private UserSellerProductDTO UserSellerProductDTO;
    @JsonProperty("productsOfferFlashId")
    private UUID ProductsOfferFlashId;

    public ProductOfferFlashSellerDTO(UUID id, UUID userSellerProductId,
                            UserSellerProductDTO userSellerProductDTO, UUID productsOfferFlashId) {
        Id = id;
        UserSellerProductId = userSellerProductId;
        UserSellerProductDTO = userSellerProductDTO;
        ProductsOfferFlashId = productsOfferFlashId;
    }

    public ProductOfferFlashSellerDTO() {
    }

    public UUID getId() {
        return Id;
    }

    public UUID getUserSellerProductId() {
        return UserSellerProductId;
    }

    public UserSellerProductDTO getUserSellerProductDTO() {
        return UserSellerProductDTO;
    }

    public UUID getProductsOfferFlashId() {
        return ProductsOfferFlashId;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setUserSellerProductId(UUID userSellerProductId) {
        UserSellerProductId = userSellerProductId;
    }

    public void setUserSellerProductDTO(UserSellerProductDTO userSellerProductDTO) {
        UserSellerProductDTO = userSellerProductDTO;
    }

    public void setProductsOfferFlashId(UUID productsOfferFlashId) {
        ProductsOfferFlashId = productsOfferFlashId;
    }
}
