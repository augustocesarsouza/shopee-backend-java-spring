package com.backend.shopee.shopee_backend.data.context;

import com.backend.shopee.shopee_backend.application.dto.AddressDTO;
import com.backend.shopee.shopee_backend.application.dto.UserDTO;
import com.backend.shopee.shopee_backend.domain.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressRepositoryJPA extends JpaRepository<Address, UUID> {
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "AddressDTO(x.Id, x.FullName, x.PhoneNumber, x.Cep, x.StateCity, x.Neighborhood, x.Street, x.NumberHome, x.Complement, x.DefaultAddress," +
            "x.UserId, null, null) " +
            "FROM Address AS x " +
            "WHERE x.Id = :addressId")
    AddressDTO GetAddressById(UUID addressId);
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "AddressDTO(x.Id, x.FullName, x.PhoneNumber, x.Cep, x.StateCity, x.Neighborhood, x.Street, x.NumberHome, x.Complement, x.DefaultAddress," +
            "x.UserId, new com.backend.shopee.shopee_backend.application.dto." +
            "UserDTO(x.User.Id, x.User.Name, null, null, null, null, null, null, null, null), null) " +
            "FROM Address AS x " +
            "WHERE x.Id = :addressId")
    AddressDTO GetAddressByIdWithUserProperty(UUID addressId);
    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "AddressDTO(x.Id, x.FullName, null, null, null, null, null, null, null, null," +
            "null, null, null) " +
            "FROM Address AS x " +
            "WHERE x.Id = :addressId")
    AddressDTO GetAddressByIdToDelete(UUID addressId);

    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "AddressDTO(x.Id, x.FullName, x.PhoneNumber, x.Cep, x.StateCity, x.Neighborhood, x.Street, x.NumberHome, x.Complement, x.DefaultAddress," +
            "x.UserId, null, x.SaveAs) " +
            "FROM Address AS x " +
            "WHERE x.UserId = :userId")
    List<AddressDTO> GetAddressByUserId(UUID userId);

    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
        "AddressDTO(x.Id, x.FullName, null, null, null, null, null, null, null, x.DefaultAddress," +
        "null, null, null) " +
        "FROM Address AS x " +
        "WHERE x.UserId = :userId AND x.DefaultAddress = 1")
    AddressDTO VerifyIfUserAlreadyHaveAddress(UUID userId);

    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
        "AddressDTO(x.Id, x.FullName, null, null, null, null, null, null, null, x.DefaultAddress," +
        "null, null, null) " +
        "FROM Address AS x " +
        "WHERE x.DefaultAddress = 1")
    AddressDTO GetAddressDefault();

    @Query("SELECT new com.backend.shopee.shopee_backend.application.dto." +
            "AddressDTO(x.Id, x.FullName, null, null, null, null, null, null, null, x.DefaultAddress," +
            "null, null, null) " +
            "FROM Address AS x " +
            "WHERE x.DefaultAddress = 1")
    AddressDTO GetAddressDefaultAllInfo();
}

//AddressDTO(UUID id, String fullName, String phoneNumber, String cep, String stateCity, String neighborhood, String street,
//           String numberHome, String complement, Byte defaultAddress, UUID userId, UserDTO userDTO, Byte saveAs)

//@Query("SELECT new com.backend.ingresso.application.dto." +
//        "UserPermissionDTO(up.Id, new com.backend.ingresso.application.dto.UserDTO(up.User.Id), new com.backend.ingresso.application.dto." +
//        "PermissionDTO(null, up.Permission.VisualName, up.Permission.PermissionName)) " +
//        "FROM UserPermission AS up " +
//        "WHERE up.User.Id = :idUser")
//List<UserPermissionDTO> getAllPermissionUser(UUID idUser);

