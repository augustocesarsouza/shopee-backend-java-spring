package com.backend.shopee.shopee_backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_java_shopee_flash_sale_product_all_infos", schema = "public")
public class FlashSaleProductAllInfo {
    @jakarta.persistence.Id
    @Column(name = "flash_sale_product_all_infos_id")
    @JsonProperty("id")
    private UUID Id;
    @Column(name = "products_offer_flash_id")
    @JsonProperty("productsOfferFlashId")
    private UUID ProductsOfferFlashId;
    @ManyToOne
    @JoinColumn(name = "products_offer_flash_id", insertable = false, updatable = false)
    private ProductsOfferFlash ProductsOfferFlash;
    @Column(name = "product_reviews_rate")
    @JsonProperty("productReviewsRate")
    private Double ProductReviewsRate;
    @Column(name = "quantity_sold")
    @JsonProperty("quantitySold")
    private Integer QuantitySold;
    @Column(name = "favorite_quantity")
    @JsonProperty("favoriteQuantity")
    private Double FavoriteQuantity;
    @Column(name = "quantity_evaluation")
    @JsonProperty("quantityEvaluation")
    private Integer QuantityEvaluation;
    @Column(name = "coins")
    @JsonProperty("coins")
    private Integer Coins;
    @Column(name = "credit_card")
    @JsonProperty("creditCard")
    private String CreditCard;
    @Column(name = "voltage")
    @JsonProperty("voltage")
    private String Voltage;
    @Column(name = "quantity_piece")
    @JsonProperty("quantityPiece")
    private Integer QuantityPiece;
    @Column(name = "size")
    @JsonProperty("size")
    private String Size;
    @Column(name = "product_have_insurance")
    @JsonProperty("productHaveInsurance")
    private Boolean ProductHaveInsurance;

    public FlashSaleProductAllInfo(UUID id, UUID productsOfferFlashId, ProductsOfferFlash productsOfferFlash,
                                   Double productReviewsRate, Integer quantitySold, Double favoriteQuantity,
                                   Integer quantityEvaluation, Integer coins, String creditCard, String voltage,
                                   Integer quantityPiece, String size, Boolean productHaveInsurance) {
        Id = id;
        ProductsOfferFlashId = productsOfferFlashId;
        ProductsOfferFlash = productsOfferFlash;
        ProductReviewsRate = productReviewsRate;
        QuantitySold = quantitySold;
        FavoriteQuantity = favoriteQuantity;
        QuantityEvaluation = quantityEvaluation;
        Coins = coins;
        CreditCard = creditCard;
        Voltage = voltage;
        QuantityPiece = quantityPiece;
        Size = size;
        ProductHaveInsurance = productHaveInsurance;
    }

    public FlashSaleProductAllInfo() {
    }

    public UUID getId() {
        return Id;
    }

    public UUID getProductsOfferFlashId() {
        return ProductsOfferFlashId;
    }

    public ProductsOfferFlash getProductsOfferFlash() {
        return ProductsOfferFlash;
    }

    public Double getProductReviewsRate() {
        return ProductReviewsRate;
    }

    public Integer getQuantitySold() {
        return QuantitySold;
    }

    public Double getFavoriteQuantity() {
        return FavoriteQuantity;
    }

    public Integer getQuantityEvaluation() {
        return QuantityEvaluation;
    }

    public Integer getCoins() {
        return Coins;
    }

    public String getCreditCard() {
        return CreditCard;
    }

    public String getVoltage() {
        return Voltage;
    }

    public Integer getQuantityPiece() {
        return QuantityPiece;
    }

    public String getSize() {
        return Size;
    }

    public Boolean getProductHaveInsurance() {
        return ProductHaveInsurance;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setProductsOfferFlashId(UUID productsOfferFlashId) {
        ProductsOfferFlashId = productsOfferFlashId;
    }

    public void setProductsOfferFlash(ProductsOfferFlash productsOfferFlash) {
        ProductsOfferFlash = productsOfferFlash;
    }

    public void setProductReviewsRate(Double productReviewsRate) {
        ProductReviewsRate = productReviewsRate;
    }

    public void setQuantitySold(Integer quantitySold) {
        QuantitySold = quantitySold;
    }

    public void setFavoriteQuantity(Double favoriteQuantity) {
        FavoriteQuantity = favoriteQuantity;
    }

    public void setQuantityEvaluation(Integer quantityEvaluation) {
        QuantityEvaluation = quantityEvaluation;
    }

    public void setCoins(Integer coins) {
        Coins = coins;
    }

    public void setCreditCard(String creditCard) {
        CreditCard = creditCard;
    }

    public void setVoltage(String voltage) {
        Voltage = voltage;
    }

    public void setQuantityPiece(Integer quantityPiece) {
        QuantityPiece = quantityPiece;
    }

    public void setSize(String size) {
        Size = size;
    }

    public void setProductHaveInsurance(Boolean productHaveInsurance) {
        ProductHaveInsurance = productHaveInsurance;
    }
}
