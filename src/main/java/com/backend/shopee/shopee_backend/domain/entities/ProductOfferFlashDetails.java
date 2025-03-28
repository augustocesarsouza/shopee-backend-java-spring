package com.backend.shopee.shopee_backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "tb_java_shopee_product_offer_flash_details", schema = "public")
public class ProductOfferFlashDetails {
    @jakarta.persistence.Id
    @Column(name = "product_offer_flash_details_id")
    @JsonProperty("id")
    private UUID Id;
    @Column(name = "products_offer_flash_id")
    @JsonProperty("productsOfferFlashId")
    private UUID ProductsOfferFlashId;
    @Column(name = "details")
    @JsonProperty("details")
    private String Details;

    public ProductOfferFlashDetails(UUID id, UUID productsOfferFlashId, String details) {
        Id = id;
        ProductsOfferFlashId = productsOfferFlashId;
        Details = details;
    }

    public ProductOfferFlashDetails() {
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
    public void setProductsOfferFlashId(UUID productsOfferFlashId) {
        ProductsOfferFlashId = productsOfferFlashId;
    }
    public void setId(UUID id) {
        Id = id;
    }
    public void setDetails(String details) {
        Details = details;
    }
}
