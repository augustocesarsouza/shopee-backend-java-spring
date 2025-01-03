package com.backend.shopee.shopee_backend.domain.repositories;

import com.backend.shopee.shopee_backend.application.dto.AddressDTO;
import com.backend.shopee.shopee_backend.domain.entities.Address;

import java.util.List;
import java.util.UUID;

public interface IAddressRepository {
    AddressDTO GetAddressById(UUID addressId);
    AddressDTO GetAddressByIdWithUserProperty(UUID addressId);
    AddressDTO GetAddressByIdToDelete(UUID addressId);
    List<AddressDTO> GetAddressByUserId(UUID userId);
    AddressDTO VerifyIfUserAlreadyHaveAddress(UUID userId);
    AddressDTO GetAddressDefault();
    AddressDTO GetAddressDefaultAllInfo();
    Address create(Address address);
    Address update(Address address);
    Address updateOnlyDefaultAddress(Address address);
    Address delete(UUID addressId);
}
