package io.training.week5.services;

import io.training.week5.model.Account;
import io.training.week5.model.Address;
import io.training.week5.repo.AddressRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  private AddressRepository addressRepository;
  private AccountService accountService;

  public AddressService(AddressRepository addressRepository,
      AccountService accountService) {
    this.addressRepository = addressRepository;
    this.accountService = accountService;
  }

  public List<Address> retrieveAddress(long accountId) {
    List<Address> addressList = addressRepository.getAddressByAccountId(accountId);
    if(validateAddressList(addressList)) {
      logger.info("Valid Address List Found at retrieveAddress");
      return addressList;
    }
    logger.debug("Invalid Account Id at retrieveAccount");
    logger.debug("Account Id: {}", accountId);
    return new ArrayList<Address>();
  }

  public Address retrieveOneAddress(long accountId, long id) {
    Address address = addressRepository.getAddressByAccountIdAndId(accountId, id);
    if(validateAddress(address)) {
      logger.info("Valid Address Found at retrieveOneAddress");
      return address;
    }
    logger.debug("Invalid Address Id at retrieveOneAddress");
    logger.debug("Account Id: {}, Address Id {}", accountId, id);
    return new Address();
  }

  public Address addAddress(long accountId, Address newAddress) {
    newAddress.setAccountId(accountId);
    if(validateAddress(newAddress)) {
      logger.info("Valid Address Added");
      return addressRepository.save(newAddress);
    }
    logger.debug("Invalid Address Attempt to Add at Account Id: {}", accountId);
    logger.debug("Address: {}", newAddress.toString());
    return new Address();
  }

  public Address updateAddress(long accountId, long addressId, Address updatedAddress) {
    Address originalAddress = retrieveOneAddress(accountId, addressId);
    if(validateAddress(originalAddress)) {
      logger.info("Valid Address Updated");
      Address newAddress = update(originalAddress, updatedAddress);
      return addressRepository.save(newAddress);
    }
    logger.debug("Invalid Address Attempt to Update at Account Id: {}, addressId {}", accountId, addressId);
    logger.debug("Address: {}", updatedAddress.toString());
    return new Address();
  }

  public boolean removeAllAddresses(long accountId) {
    Account account = accountService.retrieveAccount(accountId);
    List<Address> addressList = retrieveAddress(accountId);
    if(accountService.validateAccount(account) && validateAddressList(addressList)) {
      logger.info("Address List Removed Successfully");
      addressRepository.deleteAddressesByAccountId(accountId);
      return true;
    }
    logger.debug("Invalid Address List Attempted to  Remove at Account Id: {}", accountId);
    return false;
  }

  public boolean removeAnAddress(long accountId, long addressId) {
    Account account = accountService.retrieveAccount(accountId);
    Address address = retrieveOneAddress(accountId, addressId);
    if(accountService.validateAccount(account) && validateAddress(address)) {
      logger.info("Address Removed Successfully");
      addressRepository.deleteAddressByAccountIdAndId(accountId, addressId);
      return true;
    }
    logger.debug("Invalid Address Attempted to Remove at Account Id: {}, Address Id {}", accountId, addressId);
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
