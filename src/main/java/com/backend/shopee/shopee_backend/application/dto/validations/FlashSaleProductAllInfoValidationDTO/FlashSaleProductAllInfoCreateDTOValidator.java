package com.backend.shopee.shopee_backend.application.dto.validations.FlashSaleProductAllInfoValidationDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlashSaleProductAllInfoCreateDTOValidator {
    @NotEmpty(message = "productsOfferFlashId should not be empty")
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$",
            message = "productsOfferFlashId must be a valid UUID")
    @JsonProperty("productsOfferFlashId")
    private String ProductsOfferFlashId;
    @Min(value = 1, message = "productReviewsRate must be greater than or equal to 1")
    @JsonProperty("productReviewsRate")
    private Double ProductReviewsRate;
    @Min(value = 1, message = "quantitySold must be greater than or equal to 1")
    @JsonProperty("quantitySold")
    private Integer QuantitySold;
    @Min(value = 1, message = "favoriteQuantity must be greater than or equal to 1")
    @JsonProperty("favoriteQuantity")
    private Double FavoriteQuantity;
    @Min(value = 1, message = "quantityEvaluation must be greater than or equal to 1")
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

    public FlashSaleProductAllInfoCreateDTOValidator(String productsOfferFlashId, Double productReviewsRate,
                                                     Integer quantitySold, Double favoriteQuantity, Double quantityEvaluation,
                                                     Integer coins, String creditCard, String voltage, Integer quantityPiece,
                                                     String size, Boolean productHaveInsurance) {
        ProductsOfferFlashId = productsOfferFlashId;
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

    public FlashSaleProductAllInfoCreateDTOValidator() {
    }

    public String getProductsOfferFlashId() {
        return ProductsOfferFlashId;
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

    public void setProductsOfferFlashId(String productsOfferFlashId) {
        ProductsOfferFlashId = productsOfferFlashId;
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
