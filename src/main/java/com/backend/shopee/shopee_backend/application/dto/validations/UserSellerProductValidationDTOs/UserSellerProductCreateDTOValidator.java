package com.backend.shopee.shopee_backend.application.dto.validations.UserSellerProductValidationDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSellerProductCreateDTOValidator {
    @NotEmpty(message = "name should not be empty")
    @JsonProperty("name")
    private String Name;
    @NotEmpty(message = "imgProfile should not be empty")
    @JsonProperty("imgProfile")
    private String ImgProfile;
    @NotEmpty(message = "imgFloating should not be empty")
    @JsonProperty("imgFloating")
    private String ImgFloating;
    @JsonProperty("lastLogin")
    private LocalDateTime LastLogin;
    @Min(value = 1, message = "reviews must be greater than 0")
    @JsonProperty("reviews")
    private Integer Reviews;
    @Min(value = 1, message = "chatResponseRate must be greater than 0")
    @JsonProperty("chatResponseRate")
    private Integer ChatResponseRate;
    @JsonProperty("accountCreationDate")
    private LocalDateTime AccountCreationDate;
    @Min(value = 1, message = "quantityOfProductSold must be greater than 0")
    @JsonProperty("quantityOfProductSold")
    private Integer QuantityOfProductSold;
    @JsonProperty("usuallyRespondsToChatIn")
    private String UsuallyRespondsToChatIn;
    @Min(value = 1, message = "followers must be greater than 0")
    @JsonProperty("followers")
    private Integer Followers;

    public UserSellerProductCreateDTOValidator(String name, String imgProfile, String imgFloating, LocalDateTime lastLogin,
                                               Integer reviews, Integer chatResponseRate, LocalDateTime accountCreationDate,
                                               Integer quantityOfProductSold, String usuallyRespondsToChatIn, Integer followers) {
        Name = name;
        ImgProfile = imgProfile;
        ImgFloating = imgFloating;
        LastLogin = lastLogin;
        Reviews = reviews;
        ChatResponseRate = chatResponseRate;
        AccountCreationDate = accountCreationDate;
        QuantityOfProductSold = quantityOfProductSold;
        UsuallyRespondsToChatIn = usuallyRespondsToChatIn;
        Followers = followers;
    }

    public UserSellerProductCreateDTOValidator() {
    }

    public String getName() {
        return Name;
    }

    public String getImgProfile() {
        return ImgProfile;
    }

    public String getImgFloating() {
        return ImgFloating;
    }

    public LocalDateTime getLastLogin() {
        return LastLogin;
    }

    public Integer getReviews() {
        return Reviews;
    }

    public Integer getChatResponseRate() {
        return ChatResponseRate;
    }

    public LocalDateTime getAccountCreationDate() {
        return AccountCreationDate;
    }

    public Integer getQuantityOfProductSold() {
        return QuantityOfProductSold;
    }

    public String getUsuallyRespondsToChatIn() {
        return UsuallyRespondsToChatIn;
    }

    public Integer getFollowers() {
        return Followers;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setImgProfile(String imgProfile) {
        ImgProfile = imgProfile;
    }

    public void setImgFloating(String imgFloating) {
        ImgFloating = imgFloating;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        LastLogin = lastLogin;
    }

    public void setReviews(Integer reviews) {
        Reviews = reviews;
    }

    public void setChatResponseRate(Integer chatResponseRate) {
        ChatResponseRate = chatResponseRate;
    }

    public void setAccountCreationDate(LocalDateTime accountCreationDate) {
        AccountCreationDate = accountCreationDate;
    }

    public void setQuantityOfProductSold(Integer quantityOfProductSold) {
        QuantityOfProductSold = quantityOfProductSold;
    }

    public void setUsuallyRespondsToChatIn(String usuallyRespondsToChatIn) {
        UsuallyRespondsToChatIn = usuallyRespondsToChatIn;
    }

    public void setFollowers(Integer followers) {
        Followers = followers;
    }
}
