package com.backend.shopee.shopee_backend.data.utilityExternal.Interface;

import com.backend.shopee.shopee_backend.domain.InfoErrors.InfoErrors;
import com.backend.shopee.shopee_backend.domain.entities.User;

public interface ISendEmailBrevo {
    InfoErrors<String> sendEmail(User user, String url);
    InfoErrors<String> sendCode(User user, int codeRandom);
}
