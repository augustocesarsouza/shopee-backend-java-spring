package com.backend.shopee.shopee_backend.application.dto;

import com.backend.shopee.shopee_backend.domain.entities.ProductsOfferFlash;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlashSaleProductAllInfoDTO {
    @JsonProperty("id")
    private UUID Id;
    @JsonProperty("productsOfferFlashId")
    private UUID ProductsOfferFlashId;
    @JsonProperty("productsOfferFlashDTO")
    private ProductsOfferFlashDTO ProductsOfferFlashDTO;
    @JsonProperty("productReviewsRate")
    private Double ProductReviewsRate;
    @JsonProperty("quantitySold")
    private Integer QuantitySold;
    @JsonProperty("favoriteQuantity")
    private Double FavoriteQuantity;
    @JsonProperty("quantityEvaluation")
    private Double QuantityEvaluation;
    @JsonProperty("coins")
    private Integer Coins;
    @JsonProperty("creditCard")
    private String CreditCard;
    @JsonProperty("voltage")
    private String Voltage;
    @JsonProperty("quantityPiece")
    private Integer QuantityPiece;
    @JsonProperty("size")
    private String Size;
    @JsonProperty("productHaveInsurance")
    private Boolean ProductHaveInsurance;

    public FlashSaleProductAllInfoDTO(UUID id, UUID productsOfferFlashId, ProductsOfferFlashDTO productsOfferFlashDTO,
                                      Double productReviewsRate, Integer quantitySold, Double favoriteQuantity,
                                      Double quantityEvaluation, Integer coins, String creditCard, String voltage,
                                      Integer quantityPiece, String size, Boolean productHaveInsurance) {
        Id = id;
        ProductsOfferFlashId = productsOfferFlashId;
        ProductsOfferFlashDTO = productsOfferFlashDTO;
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

    public FlashSaleProductAllInfoDTO() {
    }

    public UUID getId() {
        return Id;
    }

    public UUID getProductsOfferFlashId() {
        return ProductsOfferFlashId;
    }

    public ProductsOfferFlashDTO getProductsOfferFlashDTO() {
        return ProductsOfferFlashDTO;
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

    public Double getQuantityEvaluation() {
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

        public void setProductsOfferFlashDTO(ProductsOfferFlashDTO productsOfferFlashDTO) {
        ProductsOfferFlashDTO = productsOfferFlashDTO;
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

    public void setQuantityEvaluation(Double quantityEvaluation) {
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
