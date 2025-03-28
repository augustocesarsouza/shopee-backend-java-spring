package com.backend.shopee.shopee_backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_java_shopee_product_offer_flash_sellers", schema = "public")
public class ProductOfferFlashSeller {
    @jakarta.persistence.Id
    @Column(name = "product_offer_flash_sellers_id")
    @JsonProperty("id")
    private UUID Id;
    @Column(name = "user_seller_product_id")
    @JsonProperty("userSellerProductId")
    private UUID UserSellerProductId;
    @ManyToOne
    @JoinColumn(name = "user_seller_product_id", insertable = false, updatable = false)
    private UserSellerProduct UserSellerProduct;
    @Column(name = "products_offer_flash_id")
    @JsonProperty("productsOfferFlashId")
    private UUID ProductsOfferFlashId;

    public ProductOfferFlashSeller(UUID id, UUID userSellerProductId,
                         UserSellerProduct userSellerProduct, UUID productsOfferFlashId) {
        Id = id;
        UserSellerProductId = userSellerProductId;
        UserSellerProduct = userSellerProduct;
        ProductsOfferFlashId = productsOfferFlashId;
    }

    public ProductOfferFlashSeller() {
    }

    public UUID getId() {
        return Id;
    }

    public UUID getUserSellerProductId() {
        return UserSellerProductId;
    }

    public UserSellerProduct getUserSellerProduct() {
        return UserSellerProduct;
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

    public void setUserSellerProduct(UserSellerProduct userSellerProduct) {
        UserSellerProduct = userSellerProduct;
    }

    public void setProductsOfferFlashId(UUID productsOfferFlashId) {
        ProductsOfferFlashId = productsOfferFlashId;
    }
}
