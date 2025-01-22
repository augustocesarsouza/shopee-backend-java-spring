package com.backend.shopee.shopee_backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_product_option_image_bottoms", schema = "public")
public class ProductOptionImageBottom {
    @jakarta.persistence.Id
    @Column(name = "product_option_image_bottoms_id")
    @JsonProperty("id")
    private UUID Id;
    @Column(name = "products_offer_flash_id")
    @JsonProperty("productsOfferFlashId")
    private UUID ProductsOfferFlashId;
    @Column(name = "list_image_url_bottom")
    @JsonProperty("listImageUrlBottom")
    private List<String> ListImageUrlBottom;

    public ProductOptionImageBottom(UUID id, UUID productsOfferFlashId, List<String> listImageUrlBottom) {
        Id = id;
        ProductsOfferFlashId = productsOfferFlashId;
        ListImageUrlBottom = listImageUrlBottom;
    }

    public ProductOptionImageBottom() {
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
