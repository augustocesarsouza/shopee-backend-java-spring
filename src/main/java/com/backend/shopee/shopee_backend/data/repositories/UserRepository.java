package com.backend.shopee.shopee_backend.data.repositories;

import com.backend.shopee.shopee_backend.data.context.UserRepositoryJPA;
import com.backend.shopee.shopee_backend.domain.entities.User;
import com.backend.shopee.shopee_backend.domain.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserRepository implements IUserRepository {
    private final UserRepositoryJPA userRepositoryJPA;
    @Autowired
    public UserRepository(UserRepositoryJPA userRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
    }

    @Override
    public User GetUserById(UUID id) {
        return userRepositoryJPA.findById(id).orElse(null);
    }

    @Override
    public User GetUserByPhoneInfoUpdate(String phone) {
        return userRepositoryJPA.GetUserByPhoneInfoUpdate(phone);
    }

    @Override
    public User GetUserByIdInfoUser(UUID id) {
        return userRepositoryJPA.GetUserByIdInfoUser(id);
    }

    @Override
    public User GetUserByPhone(String phone) {
        return userRepositoryJPA.GetUserByPhone(phone);
    }

    @Override
    public User GetUserByName(String name) {
        return userRepositoryJPA.GetUserByName(name);
    }

    @Override
    public User GetIfUserExistEmail(String email) {
        return userRepositoryJPA.GetIfUserExistEmail(email);
    }

    @Override
    public User GetUserInfoToLogin(String phone) {
        return userRepositoryJPA.GetUserInfoToLogin(phone);
    }

    @Override
    public User create(User user) {
        if(user == null)
            return null;

        return userRepositoryJPA.save(user);
    }

    @Override
    public User update(User user) {
        if(user == null)
            return null;

        User userUpdate = userRepositoryJPA.findById(user.getId()).orElse(null);

        if(userUpdate == null)
            return null;

        userUpdate.setValuesUser(user.getName(), user.getEmail(), user.getGender(), user.getPhone(),
                user.getPasswordHash(), user.getCpf(), user.getBirthDate(), user.getConfirmEmail(), user.getUserImage());

        return userRepositoryJPA.save(userUpdate);
    }

    @Override
    public User delete(UUID userId) {
        if(userId == null)
            return null;

        User user = userRepositoryJPA.findById(userId).orElse(null);

        if(user == null)
            return null;

        userRepositoryJPA.delete(user);

        return user;
    }
}
