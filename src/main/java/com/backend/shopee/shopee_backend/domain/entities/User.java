package com.backend.shopee.shopee_backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_java_shopee_users", schema = "public")
public class User implements UserDetails {
    @jakarta.persistence.Id
    @Column(name = "user_id")
    @JsonProperty("id")
    private UUID Id;
    @Column(name = "name")
    @JsonProperty("name")
    private String Name;
    @Column(name = "email")
    @JsonProperty("email")
    private String Email;
    @Column(name = "gender")
    @JsonProperty("gender")
    private String Gender;
    @Column(name = "phone")
    @JsonProperty("phone")
    private String Phone;
    @Column(name = "password_hash")
    @JsonProperty("passwordHash")
    private String PasswordHash;
    @Column(name = "cpf")
    @JsonProperty("cpf")
    private String Cpf;
    @Column(name = "birth_date")
    @JsonProperty("birthDate")
    private LocalDateTime BirthDate;
    @Column(name = "confirm_email")
    @JsonProperty("confirmEmail")
    private Short ConfirmEmail; // se 0 false, 1 true
    @Column(name = "user_image")
    @JsonProperty("userImage")
    private String UserImage;

    public User() {
    }

    public User(UUID id, String name, String email, String gender, String phone, String passwordHash,
                String cpf, LocalDateTime birthDate, Short confirmEmail, String userImage) {
        Id = id;
        Name = name;
        Email = email;
        Gender = gender;
        Phone = phone;
        PasswordHash = passwordHash;
        Cpf = cpf;
        BirthDate = birthDate;
        ConfirmEmail = confirmEmail;
        UserImage = userImage;
    }

    public UUID getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getGender() {
        return Gender;
    }

    public String getPhone() {
        return Phone;
    }

    public String getPasswordHash() {
        return PasswordHash;
    }

    public String getCpf() {
        return Cpf;
    }

    public LocalDateTime getBirthDate() {
        return BirthDate;
    }

    public Short getConfirmEmail() {
        return ConfirmEmail;
    }

    public String getUserImage() {
        return UserImage;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setPasswordHash(String passwordHash) {
        PasswordHash = passwordHash;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        BirthDate = birthDate;
    }

    public void setConfirmEmail(Short confirmEmail) {
        ConfirmEmail = confirmEmail;
    }

    public void setUserImage(String userImage) {
        UserImage = userImage;
    }

    public void setValuesUser(String name, String email, String gender, String phone, String passwordHash,
                              String cpf, LocalDateTime birthDate, Short confirmEmail, String userImage) {
        Name = name;
        Email = email;
        Gender = gender;
        Phone = phone;
        PasswordHash = passwordHash;
        Cpf = cpf;
        BirthDate = birthDate;
        ConfirmEmail = confirmEmail;
        UserImage = userImage;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return PasswordHash;
    }

    @Override
    public String getUsername() {
        return Phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
