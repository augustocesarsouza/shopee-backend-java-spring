package com.backend.shopee.shopee_backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_java_shopee_user_seller_products", schema = "public")
public class UserSellerProduct {
    @jakarta.persistence.Id
    @Column(name = "user_seller_products_id")
    @JsonProperty("id")
    private UUID Id;
    @Column(name = "name")
    @JsonProperty("name")
    private String Name;
    @Column(name = "img_profile")
    @JsonProperty("imgProfile")
    private String ImgProfile;
    @Column(name = "img_floating")
    @JsonProperty("imgFloating")
    private String ImgFloating;
    @Column(name = "last_login")
    @JsonProperty("lastLogin")
    private ZonedDateTime LastLogin;
    @Column(name = "reviews")
    @JsonProperty("reviews")
    private Integer Reviews;
    @Column(name = "chat_response_rate")
    @JsonProperty("chatResponseRate")
    private Integer ChatResponseRate;
    @Column(name = "account_creation_date")
    @JsonProperty("accountCreationDate")
    private ZonedDateTime AccountCreationDate;
    @Column(name = "quantity_of_product_sold")
    @JsonProperty("quantityOfProductSold")
    private Integer QuantityOfProductSold;
    @Column(name = "usually_responds_to_chat_in")
    @JsonProperty("usuallyRespondsToChatIn")
    private String UsuallyRespondsToChatIn;
    @Column(name = "followers")
    @JsonProperty("followers")
    private Integer Followers;

    public UserSellerProduct(UUID id, String name, String imgProfile, String imgFloating, ZonedDateTime lastLogin,
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

    public UserSellerProduct() {
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
        ZonedDateTime getLastLogin = this.getLastLogin();
//            ZoneId localZoneId = ZoneId.of("America/Sao_Paulo");
        ZoneId localZoneId = ZoneId.systemDefault();
        return getLastLogin.withZoneSameInstant(localZoneId);
    }

    public ZonedDateTime convertAccountCreationDateFromUtcToLocal() {
        ZonedDateTime accountCreationDate = this.getAccountCreationDate();
//            ZoneId localZoneId = ZoneId.of("America/Sao_Paulo");
        ZoneId localZoneId = ZoneId.systemDefault();
        return accountCreationDate.withZoneSameInstant(localZoneId);
    }
}
