package com.backend.shopee.shopee_backend.domain.repositories;

import com.backend.shopee.shopee_backend.domain.entities.User;

import java.util.UUID;

public interface IUserRepository {
    User GetUserById(UUID id);
    User GetUserByPhoneInfoUpdate(String phone);
    User GetUserByIdInfoUser(UUID id);
    User GetUserByPhone(String phone);
    User GetUserByName(String name);
    User GetIfUserExistEmail(String email);
    User GetUserInfoToLogin(String phone);
    User GetUserByIdForDeleteImg(UUID userId);
    User GetUserByIdInfoEmailPasswordHash(UUID guidId);
    User GetUserByPhoneInfoEmailPasswordHash(String phone);
    User create(User user);
    User update(User user);
    User delete(UUID userId);
}
