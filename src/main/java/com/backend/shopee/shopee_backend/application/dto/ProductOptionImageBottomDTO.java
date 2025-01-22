package com.backend.shopee.shopee_backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductOptionImageBottomDTO {
    @JsonProperty("id")
    private UUID Id;
    @JsonProperty("productsOfferFlashId")
    private UUID ProductsOfferFlashId;
    @JsonProperty("listImageUrlBottom")
    private List<String> ListImageUrlBottom;

    public ProductOptionImageBottomDTO(UUID id, UUID productsOfferFlashId, List<String> listImageUrlBottom) {
        Id = id;
        ProductsOfferFlashId = productsOfferFlashId;
        ListImageUrlBottom = listImageUrlBottom;
    }

    public ProductOptionImageBottomDTO() {
    }

    public UUID getId() {
        return Id;
    }

    public UUID getProductsOfferFlashId() {
        return ProductsOfferFlashId;
    }

    public List<String> getListImageUrlBottom() {
        return ListImageUrlBottom;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setProductsOfferFlashId(UUID productsOfferFlashId) {
        ProductsOfferFlashId = productsOfferFlashId;
    }

    public void setListImageUrlBottom(List<String> listImageUrlBottom) {
        ListImageUrlBottom = listImageUrlBottom;
    }
}
