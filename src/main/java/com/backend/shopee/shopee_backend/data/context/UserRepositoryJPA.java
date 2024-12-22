package com.backend.shopee.shopee_backend.data.context;

import com.backend.shopee.shopee_backend.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepositoryJPA extends JpaRepository<User, UUID> {

    @Query("SELECT new com.backend.shopee.shopee_backend.domain.entities." +
            "User(x.Id, x.Name, x.Email, x.Gender, x.Phone, x.PasswordHash, x.Cpf, x.BirthDate, x.ConfirmEmail, x.UserImage) " +
            "FROM User AS x " +
            "WHERE x.Id = :id")
    User GetUserById(UUID id);
    @Query("SELECT new com.backend.shopee.shopee_backend.domain.entities." +
            "User(x.Id, x.Name, x.Email, x.Gender, x.Phone, null, x.Cpf, x.BirthDate, x.ConfirmEmail, x.UserImage) " +
            "FROM User AS x " +
            "WHERE x.Phone = :phone")
    User GetUserByPhoneInfoUpdate(String phone);
    @Query("SELECT new com.backend.shopee.shopee_backend.domain.entities." +
            "User(x.Id, x.Name, x.Email, x.Gender, x.Phone, null, x.Cpf, x.BirthDate, x.ConfirmEmail, x.UserImage) " +
            "FROM User AS x " +
            "WHERE x.Id = :id")
    User GetUserByIdInfoUser(UUID id);
    @Query("SELECT new com.backend.shopee.shopee_backend.domain.entities." +
            "User(x.Id, x.Name, null, null, null, null, null, null, null, x.UserImage) " +
            "FROM User AS x " +
            "WHERE x.Phone = :phone")
    User GetUserByPhone(String phone);
    @Query("SELECT new com.backend.shopee.shopee_backend.domain.entities." +
            "User(x.Id, x.Name, null, null, null, null, null, null, null, x.UserImage) " +
            "FROM User AS x " +
            "WHERE x.Name = :name")
    User GetUserByName(String name);
    @Query("SELECT new com.backend.shopee.shopee_backend.domain.entities." +
            "User(x.Id, x.Name, null, null, null, null, null, null, null, x.UserImage) " +
            "FROM User AS x " +
            "WHERE x.Email = :email")
    User GetIfUserExistEmail(String email);
    @Query("SELECT new com.backend.shopee.shopee_backend.domain.entities." +
            "User(x.Id, x.Name, x.Email, null, x.Phone, x.PasswordHash, null, null, null, x.UserImage) " +
            "FROM User AS x " +
            "WHERE x.Phone = :phone")
    User GetUserInfoToLogin(String phone);
    @Query("SELECT new com.backend.shopee.shopee_backend.domain.entities." +
            "User(x.Id, null, null, null, null, null, null, null, null, x.UserImage) " +
            "FROM User AS x " +
            "WHERE x.Id = :userId")
    User GetUserByIdForDeleteImg(UUID userId);
    @Query("SELECT new com.backend.shopee.shopee_backend.domain.entities." +
            "User(null, null, x.Email, null, null, x.PasswordHash, null, null, null, null) " +
            "FROM User AS x " +
            "WHERE x.Id = :userId")
    User GetUserByIdInfoEmailPasswordHash(UUID userId);
    @Query("SELECT new com.backend.shopee.shopee_backend.domain.entities." +
            "User(x.Id, null, x.Email, null, null, x.PasswordHash, null, null, null, null) " +
            "FROM User AS x " +
            "WHERE x.Phone = :phone")
    User GetUserByPhoneInfoEmailPasswordHash(String phone);
}

//User(UUID id, String name, String email, String gender, String phone, String passwordHash,
//     String cpf, LocalDateTime birthDate, Short confirmEmail, String userImage)