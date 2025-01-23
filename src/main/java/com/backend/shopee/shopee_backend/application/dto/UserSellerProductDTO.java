package com.backend.shopee.shopee_backend.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSellerProductDTO {
    @JsonProperty("id")
    private UUID Id;
    @JsonProperty("name")
    private String Name;
    @JsonProperty("imgProfile")
    private String ImgProfile;
    @JsonProperty("imgFloating")
    private String ImgFloating;
    @JsonProperty("lastLogin")
    private ZonedDateTime LastLogin;
    @JsonProperty("reviews")
    private Integer Reviews;
    @JsonProperty("chatResponseRate")
    private Integer ChatResponseRate;
    @JsonProperty("accountCreationDate")
    private ZonedDateTime AccountCreationDate;
    @JsonProperty("quantityOfProductSold")
    private Integer QuantityOfProductSold;
    @JsonProperty("usuallyRespondsToChatIn")
    private String UsuallyRespondsToChatIn;
    @JsonProperty("followers")
    private Integer Followers;

    public UserSellerProductDTO(UUID id, String name, String imgProfile, String imgFloating, ZonedDateTime lastLogin,
                                Integer reviews, Integer chatResponseRate, ZonedDateTime accountCreationDate,
                                Integer quantityOfProductSold, String usuallyRespondsToChatIn, Integer followers) {
        Id = id;
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

    public UserSellerProductDTO() {
    }

    public UUID getId() {
        return Id;
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

    public ZonedDateTime getLastLogin() {
        return LastLogin;
    }

    public Integer getReviews() {
        return Reviews;
    }

    public Integer getChatResponseRate() {
        return ChatResponseRate;
    }

    public ZonedDateTime getAccountCreationDate() {
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

    public void setId(UUID id) {
        Id = id;
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

    public void setLastLogin(ZonedDateTime lastLogin) {
        LastLogin = lastLogin;
    }

    public void setReviews(Integer reviews) {
        Reviews = reviews;
    }

    public void setChatResponseRate(Integer chatResponseRate) {
        ChatResponseRate = chatResponseRate;
    }

    public void setAccountCreationDate(ZonedDateTime accountCreationDate) {
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

    public ZonedDateTime convertLastLoginFromUtcToLocal() {
        ZonedDateTime lastLogin = this.getLastLogin();
//            ZoneId localZoneId = ZoneId.of("America/Sao_Paulo");
        ZoneId localZoneId = ZoneId.systemDefault();
        return lastLogin.withZoneSameInstant(localZoneId);
    }

    public ZonedDateTime convertAccountCreationDateFromUtcToLocal() {
        ZonedDateTime accountCreationDate = this.getAccountCreationDate();
//            ZoneId localZoneId = ZoneId.of("America/Sao_Paulo");
        ZoneId localZoneId = ZoneId.systemDefault();
        return accountCreationDate.withZoneSameInstant(localZoneId);
    }
}
