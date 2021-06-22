package com.decagon.safariwebstore.service;

import com.decagon.safariwebstore.model.Address;
import com.decagon.safariwebstore.model.User;

import java.util.List;

public interface AddressService {
    Address saveAddress(Address address, User user);
    Address getAUserAddress(User user, String address, String city, String state);
   List<Address> getAllUserAddresses(User user);
    //Address getUserDefaultAddress(User user);
    Boolean isAddressExisting(Address address, User user);

    Address getUserDefaultAddress(User loggedInUser);
}
