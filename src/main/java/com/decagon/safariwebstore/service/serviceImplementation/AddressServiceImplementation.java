package com.decagon.safariwebstore.service.serviceImplementation;

import com.decagon.safariwebstore.exceptions.ResourceNotFoundException;
import com.decagon.safariwebstore.model.Address;
import com.decagon.safariwebstore.model.User;
import com.decagon.safariwebstore.repository.AddressRepository;
import com.decagon.safariwebstore.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressServiceImplementation implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Address saveAddress(Address address, User user){
        address.setUser(user);
        return addressRepository.save(address);
    }

    @Override
    public Address getAUserAddress(User user, String address, String city, String state) {
        return addressRepository.findAddressByUserAndAddressAndCityAndState(user, address, city, state)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found!"));
    }

    @Override
    public List<Address> getAllUserAddresses(User user){
        return addressRepository
                .findAllByUser(user)
                .orElseThrow(
                        () -> {
                            throw new ResourceNotFoundException("Addresses not found for user!");
                        }
                );
    }

    public Address getUserDefaultAddress(User user){
        return getAllUserAddresses(user)
                .stream()
                .filter(Address::getIsDefaultShippingAddress)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Default address not found!"));
    }

    @Override
    public Boolean userDefaultAddressExists(User user) {
        return getAllUserAddresses(user)
                .stream()
                .anyMatch(Address::getIsDefaultShippingAddress);
    }

    @Override
    public Address getAddressByUserAndAddressAndCityAndState(User user, String address, String city, String state) {
        return addressRepository.findAddressByUserAndAddressAndCityAndState(user, address,
                city, state).orElseThrow(
                () -> {
                    throw new ResourceNotFoundException("Address not found");
                }
        );
    }

    @Override
    public Boolean isAddressExisting(Address address, User user) {
        return addressRepository
                .existsAddressByUserAndAddressAndCityAndState(user, address.getAddress(),
                        address.getCity(), address.getState());
    }

}
