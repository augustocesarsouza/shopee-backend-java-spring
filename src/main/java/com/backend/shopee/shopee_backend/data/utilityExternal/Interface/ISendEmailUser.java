package com.backend.shopee.shopee_backend.data.utilityExternal.Interface;


import com.backend.shopee.shopee_backend.domain.InfoErrors.InfoErrors;
import com.backend.shopee.shopee_backend.domain.entities.User;

public interface ISendEmailUser {
    InfoErrors<String> sendCodeRandom(User user, int code);
//    InfoErrors<String> sendEmailConfirmRegisterUser(User user);
}
