package com.backend.shopee.shopee_backend.data.repositories;

import com.backend.shopee.shopee_backend.application.dto.AddressDTO;
import com.backend.shopee.shopee_backend.data.context.AddressRepositoryJPA;
import com.backend.shopee.shopee_backend.domain.entities.Address;
import com.backend.shopee.shopee_backend.domain.repositories.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AddressRepository implements IAddressRepository {
    private final AddressRepositoryJPA addressRepositoryJPA;

    @Autowired
    public AddressRepository(AddressRepositoryJPA addressRepositoryJPA) {
        this.addressRepositoryJPA = addressRepositoryJPA;
    }

    @Override
    public AddressDTO GetAddressById(UUID addressId) {
        return addressRepositoryJPA.GetAddressById(addressId);
    }

    @Override
    public AddressDTO GetAddressByIdWithUserProperty(UUID addressId) {
        return addressRepositoryJPA.GetAddressByIdWithUserProperty(addressId);
    }

    @Override
    public AddressDTO GetAddressByIdToDelete(UUID addressId) {
        return addressRepositoryJPA.GetAddressByIdToDelete(addressId);
    }

    @Override
    public List<AddressDTO> GetAddressByUserId(UUID userId) {
        List<AddressDTO> addresses = addressRepositoryJPA.GetAddressByUserId(userId);

        List<AddressDTO> sortedAddresses = addresses.stream()
                .sorted((a1, a2) -> Integer.compare(a2.getDefaultAddress(), a1.getDefaultAddress()))
                .toList();
        // tem que testar isso por causa do get " .OrderByDescending(x => x.DefaultAddress == 1)" é para ser similar ao c#

        return sortedAddresses;
    }

    @Override
    public AddressDTO VerifyIfUserAlreadyHaveAddress(UUID userId) {
        return addressRepositoryJPA.VerifyIfUserAlreadyHaveAddress(userId);
    }

    @Override
    public AddressDTO GetAddressDefault() {
        return null;
    }

    @Override
    public AddressDTO GetAddressDefaultAllInfo() {
        return addressRepositoryJPA.GetAddressDefaultAllInfo();
    }

    @Override
    public Address create(Address address) {
        if(address == null)
            return null;

        return addressRepositoryJPA.save(address);
    }

    @Override
    public Address update(Address address) {
        if(address == null)
            return null;

        Address addressUpdate = addressRepositoryJPA.findById(address.getId()).orElse(null);

        if(addressUpdate == null)
            return null;

        addressUpdate.SetValueAddress(address.getFullName(), address.getPhoneNumber(), address.getCep(), address.getStateCity(),
                address.getNeighborhood(), address.getStreet(), address.getNumberHome(), address.getComplement(), address.getDefaultAddress(),
                address.getUserId(), address.getSaveAs());

        return addressRepositoryJPA.save(addressUpdate);
    }

    @Override
    public Address updateOnlyDefaultAddress(Address address) {
        if(address == null)
            return null;

        Address addressUpdate = addressRepositoryJPA.findById(address.getId()).orElse(null);

        if(addressUpdate == null)
            return null;

        addressUpdate.setDefaultAddress(address.getDefaultAddress());

        return addressRepositoryJPA.save(addressUpdate);
    }

    @Override
    public Address delete(UUID addressId) {
        if(addressId == null)
            return null;

        Address address = addressRepositoryJPA.findById(addressId).orElse(null);

        if(address == null)
            return null;

        addressRepositoryJPA.delete(address);
//        address.setUser(null);

        return address;
    }
}
