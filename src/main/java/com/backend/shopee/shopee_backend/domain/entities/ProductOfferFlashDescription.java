package com.backend.shopee.shopee_backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "tb_java_shopee_product_offer_flash_descriptions", schema = "public")
public class ProductOfferFlashDescription {
    @jakarta.persistence.Id
    @Column(name = "product_offer_flash_descriptions_id")
    @JsonProperty("id")
    private UUID Id;
    @Column(name = "products_offer_flash_id")
    @JsonProperty("productsOfferFlashId")
    private UUID ProductsOfferFlashId;
    @Column(name = "descriptions", length = 5000)
    @JsonProperty("descriptions")
    private String Descriptions;

    public ProductOfferFlashDescription(UUID id, UUID productsOfferFlashId, String descriptions) {
        Id = id;
        ProductsOfferFlashId = productsOfferFlashId;
        Descriptions = descriptions;
    }

    public ProductOfferFlashDescription() {
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
