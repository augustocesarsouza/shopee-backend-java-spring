package com.backend.shopee.shopee_backend.domain.authentication;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.backend.shopee.shopee_backend.domain.InfoErrors.InfoErrors;
import com.backend.shopee.shopee_backend.domain.entities.User;

import java.util.List;

public interface ITokenGenerator{
    InfoErrors<TokenOutValue> generatorTokenUser(User user);
    Claim getClaimUserId(String token) throws TokenExpiredException;
}
