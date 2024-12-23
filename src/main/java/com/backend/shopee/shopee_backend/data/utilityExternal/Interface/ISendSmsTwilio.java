package com.backend.shopee.shopee_backend.data.utilityExternal.Interface;

import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryCreate;

public interface ISendSmsTwilio {
    Boolean SendSms(String toPhoneNumber, String messageContent);
}
