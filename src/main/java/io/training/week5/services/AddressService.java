package io.training.week5.services;

import io.training.week5.model.Account;
import io.training.week5.model.Address;
import io.training.week5.repo.AddressRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

  private AddressRepository addressRepository;
  private AccountService accountService;

  public AddressService(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  public List<Address> retrieveAddress(long accountId) {
    return addressRepository.getAddressByAccountId(accountId);
  }

  public Address retrieveOneAddress(long accountId, long id) {
    return addressRepository.getAddressByAccountIdAndId(accountId, id);
  }

  public Address addAddress(long accountId, Address newAddress) {
    newAddress.setAccountId(accountId);
    if(validateAddress(newAddress)) { return addressRepository.save(newAddress); }
    return new Address();
  }

  public Address updateAddress(long accountId, long addressId, Address updatedAddress) {
    Address originalAddress = retrieveOneAddress(accountId, addressId);
    if(validateAddress(originalAddress)) {
      Address newAddress = update(originalAddress, updatedAddress);
      return addressRepository.save(newAddress);
    }
    return new Address();
  }

  public boolean removeAllAddresses(long accountId) {
    Account account = accountService.retrieveAccount(accountId);
    List<Address> addressList = retrieveAddress(accountId);
    if(accountService.validateAccount(account) && validateAddressList(addressList)) {
      addressRepository.deleteAddressesByAccountId(accountId);
      return true;
    }
    return false;
  }

  public boolean removeAnAddress(long accountId, long addressId) {
    Account account = accountService.retrieveAccount(accountId);
    Address address = retrieveOneAddress(accountId, addressId);

    if(accountService.validateAccount(account) && validateAddress(address)) {
      addressRepository.deleteAddressByAccountIdAndId(accountId, addressId);
      return true;
    }
    return false;
  }

  private boolean validateAddress(Address address) {
    if(address == null) return false;
    if(address.getBuilding() == null) return false;
    if(address.getCity() == null) return false;
    if(address.getCountry() == null) return false;
    if(address.getAccountId() == 0) return false;
    if(address.getState() == null) return false;
    if(address.getStreet() == null) return false;
    if(address.getZip() == null) return false;
    return true;
  }

  private boolean validateAddressList(List<Address> addressList) {
    boolean valid = true;
    for(Address address : addressList) {
      if(!validateAddress(address)) { return false; }
    }
    return true;
  }

  private Address update(Address original, Address updated) {
    Address newAddress = new Address();
    newAddress.setId(original.getId());

    if(updated == null) return original;

    if(updated.getBuilding() == null) newAddress.setBuilding(original.getBuilding());
    else newAddress.setBuilding(updated.getBuilding());

    if(updated.getCity() == null) newAddress.setCity(original.getCity());
    else newAddress.setCity(updated.getCity());

    if(updated.getCountry() == null) newAddress.setCountry(original.getCountry());
    else newAddress.setCountry(updated.getCountry());

    if(updated.getState() == null) newAddress.setState(original.getState());
    else newAddress.setState(updated.getState());

    if(updated.getStreet() == null) newAddress.setStreet(original.getStreet());
    else newAddress.setStreet(updated.getStreet());

    if(updated.getZip() == null) newAddress.setZip(original.getZip());
    else newAddress.setZip(updated.getZip());

    if(updated.getAccountId() == 0) newAddress.setAccountId(original.getAccountId());
    else newAddress.setAccountId(updated.getAccountId());

    return newAddress;
  }

}
